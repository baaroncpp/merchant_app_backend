package com.pearlbank.security.service;

import com.pbu.commons.exceptions.BadCredentialsException;
import com.pbu.commons.exceptions.model.ExceptionType;
import com.pbu.commons.text.StringUtil;
import com.pbu.commons.utils.Validate;
import com.pearlbank.base.models.enums.RefreshTokenStatusEnum;
import com.pearlbank.base.service.AppProperties;
import com.pearlbank.base.service.AuditService;
import com.pearlbank.security.model.dto.RefreshTokenDto;
import com.pearlbank.security.model.jpa.TRefreshToken;
import com.pearlbank.security.repository.TRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.pbu.commons.utils.DateTimeUtil.getCurrentUTCTime;
import static com.pearlbank.security.utils.SecurityMsgConstant.INVALID_REFRESH_TOKEN;
import static com.pearlbank.security.utils.SecurityUtils.generateOpaqueToken;
import static com.pearlbank.security.utils.SecurityUtils.sha256;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final TRefreshTokenRepository refreshTokenRepository;
    private final AppProperties appProperties;
    private final AuditService auditService;

    @Transactional
    public RefreshTokenDto createRefreshToken(Long userId) {
        return saveRefreshToken(userId, StringUtil.randomString());
    }

    @Transactional(noRollbackFor = BadCredentialsException.class)
    public RefreshTokenDto rotateRefreshToken(String token) {

        var refreshToken = refreshTokenRepository.findByTokenHash(sha256(token))
                .orElseThrow(() -> new BadCredentialsException(INVALID_REFRESH_TOKEN));
        var now = getCurrentUTCTime();

        Validate.isTrue(refreshToken.getStatus().equals(RefreshTokenStatusEnum.ACTIVE) , ExceptionType.BAD_CREDENTIALS, INVALID_REFRESH_TOKEN);

        if (refreshToken.getStatus().equals(RefreshTokenStatusEnum.USED)) {
            revokeFamily(refreshToken);
            throw new BadCredentialsException(INVALID_REFRESH_TOKEN);
        }

        Validate.isTrue((refreshToken.getExpiryDate().isAfter(now)),  ExceptionType.BAD_CREDENTIALS, INVALID_REFRESH_TOKEN);

        refreshToken.setUsedOn(now);
        auditService.stampLongEntity(refreshToken);
        refreshTokenRepository.save(refreshToken);

        return saveRefreshToken(refreshToken.getUserId(), refreshToken.getFamilyId());
    }

    @Transactional
    public void revokeRefreshToken(String token) {
        refreshTokenRepository.findByTokenHash(sha256(token)).ifPresent(this::revokeFamily);
    }

    @Transactional
    public void revokeAllUserRefreshTokens(Long userId) {
        var now = getCurrentUTCTime();
        var refreshTokens = refreshTokenRepository.findAllByUserIdAndRevokedOnIsNull(userId);
        refreshTokens.forEach(refreshToken -> {
            refreshToken.setRevokedOn(now);
            refreshToken.setModifiedOn(now);
        });
        refreshTokenRepository.saveAll(refreshTokens);
    }

    private void revokeFamily(TRefreshToken refreshToken) {
        var now = getCurrentUTCTime();
        var familyTokens = refreshTokenRepository.findAllByFamilyIdAndRevokedOnIsNull(refreshToken.getFamilyId());
        familyTokens.forEach(familyToken -> {
            familyToken.setRevokedOn(now);
            //familyToken.setModifiedOn(now);
            familyToken.setStatus(RefreshTokenStatusEnum.REVOKED);
        });
        refreshTokenRepository.saveAll(familyTokens);
    }

    private RefreshTokenDto saveRefreshToken(Long userId, String familyId) {
        var token = generateOpaqueToken();
        var now = getCurrentUTCTime();

        var refreshToken = TRefreshToken.builder()
                .tokenHash(sha256(token))
                .familyId(familyId)
                .userId(userId)
                .expiryDate(now.plusDays(appProperties.getRefreshTokenDuration()))
                .status(RefreshTokenStatusEnum.ACTIVE)
                .build();

        //refreshToken.setCreatedOn(now);
        //refreshToken.setModifiedOn(now);

        refreshTokenRepository.save(refreshToken);

        return new RefreshTokenDto(userId, token, refreshToken.getExpiryDate());
    }
}
