package com.pearlbank.security.service;

import com.pearlbank.base.service.AppProperties;
import com.pearlbank.security.model.dto.AccessTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.pearlbank.security.utils.SecurityMsgConstant.CLAIM_AUTHORITIES;
import static com.pearlbank.security.utils.SecurityMsgConstant.CLAIM_USERNAME;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final AppProperties appProperties;

    public AccessTokenDto generateToken(Long userId, String username, List<String> authorities) {
        var issuedAt = Instant.now();
        var expiresAt = issuedAt.plus(Duration.ofMinutes(appProperties.getTokenDuration()));

        var claims = JwtClaimsSet.builder()
                .id(UUID.randomUUID().toString())
                .issuer(appProperties.getJwtIssuer())
                .subject(String.valueOf(userId))
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .claim(CLAIM_USERNAME, username)
                .claim(CLAIM_AUTHORITIES, authorities)
                .build();

        var header = JwsHeader.with(SignatureAlgorithm.RS256).build();
        var token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();

        return new AccessTokenDto(token, expiresAt.toEpochMilli(), appProperties.getTokenDuration());
    }
}
