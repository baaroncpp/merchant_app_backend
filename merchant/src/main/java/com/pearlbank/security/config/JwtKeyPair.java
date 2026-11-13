package com.pearlbank.security.config;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:05
 **/
public record JwtKeyPair(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
) {
    public static JwtKeyPair generate() {
        try {
            var generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            var keyPair = generator.generateKeyPair();
            return new JwtKeyPair((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
