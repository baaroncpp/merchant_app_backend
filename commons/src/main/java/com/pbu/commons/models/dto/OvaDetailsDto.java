package com.pbu.commons.models.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 7:35PM
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
