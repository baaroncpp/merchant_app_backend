package com.pearlbank.user_mgt.model.jpa;

import com.pearlbank.base.models.enums.GenderEnum;
import com.pearlbank.base.models.enums.IdentificationTypeEnum;
import com.pearlbank.base.models.enums.NotificationTypeEnum;
import com.pearlbank.base.models.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 12:08
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user", schema = "core")
@Setter
public class TUser extends BaseEntity {
    private boolean isDeleted;
    private String username;
    private String firstName;
    private String secondName;
    private String emailAddress;
    private IdentificationTypeEnum identificationType;
    private String idNumber;
    private String msisdn;
    private String password;
    private Integer passwordAttempts;
    private LocalDateTime passwordChangedOn;
    private boolean active;
    private boolean accountLocked;
    private boolean accountExpired;
    private boolean credentialExpired;
    private LocalDateTime activatedOn;
    private Boolean noneVerifiedEmail;
    private Boolean noneVerifiedMsisdn;
    private int failedLoginAttempts;
    private LocalDateTime lockTime;
    private String otpCode;
    private boolean mfaEnabled;
    private LocalDateTime otpExpiry;
    private NotificationTypeEnum otpChannel;
    private String currentLoginIpAddress;
    private GenderEnum gender;
}
