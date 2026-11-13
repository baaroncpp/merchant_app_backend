package com.pearlbank.security.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HexFormat;

import static com.pearlbank.security.utils.SecurityMsgConstant.JWT_KEY_NOT_PKCS8;
import static com.pearlbank.security.utils.SecurityMsgConstant.JWT_KEY_UNREADABLE;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
public class SecurityUtils {

    private SecurityUtils() {}

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int TOKEN_BYTES = 32;

    public static String generateOpaqueToken() {
        var bytes = new byte[TOKEN_BYTES];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String sha256(String value) {
        try {
            var digest = MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    public static RSAPublicKey readPublicKey(String location) {
        try {
            var keySpec = new X509EncodedKeySpec(decodePem(readFile(location)));
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (GeneralSecurityException | IllegalArgumentException e) {
            throw new IllegalStateException(String.format(JWT_KEY_UNREADABLE, location), e);
        }
    }

    public static RSAPrivateKey readPrivateKey(String location) {
        var pem = readFile(location);
        if (pem.contains("BEGIN RSA PRIVATE KEY")) {
            throw new IllegalStateException(String.format(JWT_KEY_NOT_PKCS8, location));
        }
        try {
            var keySpec = new PKCS8EncodedKeySpec(decodePem(pem));
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        } catch (GeneralSecurityException | IllegalArgumentException e) {
            throw new IllegalStateException(String.format(JWT_KEY_UNREADABLE, location), e);
        }
    }

    private static String readFile(String location) {
        try {
            return Files.readString(Path.of(location));
        } catch (IOException e) {
            throw new IllegalStateException(String.format(JWT_KEY_UNREADABLE, location), e);
        }
    }

    private static byte[] decodePem(String pem) {
        var base64 = pem
                .replaceAll("-----(BEGIN|END) [A-Z ]+-----", "")
                .replaceAll("\\s", "");
        return Base64.getDecoder().decode(base64);
    }
}
