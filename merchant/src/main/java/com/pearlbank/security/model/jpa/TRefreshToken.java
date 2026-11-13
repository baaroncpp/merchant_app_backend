package com.pearlbank.security.model.jpa;

import com.pearlbank.base.models.enums.RefreshTokenStatusEnum;
import com.pearlbank.base.models.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Table(name = "t_refresh_token")
public class TRefreshToken extends BaseEntity {
    private String tokenHash;
    private String familyId;
    private Long userId;
    private LocalDateTime expiryDate;
    private LocalDateTime usedOn;
    private LocalDateTime revokedOn;
    private RefreshTokenStatusEnum status;

    @Column(name = "token_hash")
    public String getTokenHash() {
        return tokenHash;
    }

    @Column(name = "family_id")
    public String getFamilyId() {
        return familyId;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    @Column(name = "expiry_date")
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    @Column(name = "used_on")
    public LocalDateTime getUsedOn() {
        return usedOn;
    }

    @Column(name = "revoked_on")
    public LocalDateTime getRevokedOn() {
        return revokedOn;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public RefreshTokenStatusEnum getStatus() {
        return this.status;
    }
}
