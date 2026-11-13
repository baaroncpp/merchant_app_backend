package com.pbu.commons.models.dto;

import com.pbu.commons.models.enums.NotificationStatusEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 16:10
 **/
@Data
@Builder
public class QueueNotificationDto {
    private String sender;
    private String recipient;
    private String message;
    private NotificationStatusEnum status;
    private String merchantCode;
    private NotificationTypeEnum notificationType;
    private String internalReference;
    private String externalReference;
    private OvaDetailsDto ovaDetails;
}
