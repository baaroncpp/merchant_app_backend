package com.pearlbank.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.pearlbank.base.service.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.util.StringUtils;

import static com.pearlbank.security.utils.SecurityMsgConstant.JWT_KEYS_REQUIRED;
import static com.pearlbank.security.utils.SecurityUtils.readPrivateKey;
import static com.pearlbank.security.utils.SecurityUtils.readPublicKey;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    private final AppProperties appProperties;
    private final Environment environment;

    @Bean
    public JwtKeyPair jwtKeyPair() {
        if (StringUtils.hasText(appProperties.getJwtPublicKeyLocation()) && StringUtils.hasText(appProperties.getJwtPrivateKeyLocation())) {
            return new JwtKeyPair(
                    readPublicKey(appProperties.getJwtPublicKeyLocation()),
                    readPrivateKey(appProperties.getJwtPrivateKeyLocation())
            );
        }

        if (environment.matchesProfiles("dev")) {
            log.warn("No JWT keys configured; using a temporary key pair. Tokens stop working when the app restarts.");
            return JwtKeyPair.generate();
        }

        throw new IllegalStateException(JWT_KEYS_REQUIRED);
    }

    @Bean
    public JwtEncoder jwtEncoder(JwtKeyPair jwtKeyPair) {
        var rsaKey = new RSAKey.Builder(jwtKeyPair.publicKey())
                .privateKey(jwtKeyPair.privateKey())
                .build();
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(rsaKey)));
    }

    @Bean
    public JwtDecoder jwtDecoder(JwtKeyPair jwtKeyPair) {
        var jwtDecoder = NimbusJwtDecoder.withPublicKey(jwtKeyPair.publicKey()).build();
        jwtDecoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(appProperties.getJwtIssuer()));
        return jwtDecoder;
    }
}
