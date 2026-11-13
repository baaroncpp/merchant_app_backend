package com.pearlbank.base.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    @Value("${app.jwt.issuer}")
    private String jwtIssuer;

    @Value("${app.jwt.public-key-location}")
    private String jwtPublicKeyLocation;

    @Value("${app.jwt.private-key-location}")
    private String jwtPrivateKeyLocation;

    @Value("${app.token-duration}")
    private int tokenDuration;

    @Value("${app.refresh-token-duration}")
    private int refreshTokenDuration;
}
