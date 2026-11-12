package com.pbu.commons.models.dto;

import com.pbu.commons.models.enums.NotificationStatusEnum;
import com.pbu.commons.models.enums.NotificationTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 4:10PM
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
