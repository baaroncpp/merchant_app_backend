package com.pbu.commons.models.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 19:35
 **/
@Data
@Builder
public class OvaDetailsDto {
    private String payBillNumber;
    private String username;
    private String password;
    private String passKey;
    private String mnoUrl;
    private String encryptionKey;
}
