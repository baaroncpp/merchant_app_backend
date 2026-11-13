package com.pearlbank.base.service;

import com.pbu.commons.exceptions.model.ExceptionType;
import com.pbu.commons.utils.DateTimeUtil;
import com.pbu.commons.utils.Validate;
import com.pearlbank.base.models.jpa.*;
import com.pearlbank.security.model.SecurityUserDetails;
import com.pearlbank.user_mgt.model.jpa.TUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 12:04
 **/
@Service
public class AuditService {

    private static final String ONLY_LOGGED_IN_USER = "Only a logged in user can make this change";

    public void stampLongEntity(BaseEntity entity) {
        var date = DateTimeUtil.getCurrentUTCTime();
        if(entity.getId() == null){
            entity.setCreatedOn(date);
            entity.setModifiedOn(date);
        }
        entity.setModifiedOn(date);
    }

    public void stampAuditedEntity(AuditEntity auditEntity) {
        SecurityUserDetails user = getLoggedInUser();
        Validate.notNull(user, ExceptionType.BAD_CREDENTIALS, ONLY_LOGGED_IN_USER);
        var date = DateTimeUtil.getCurrentUTCTime();
        var tUser = new TUser();
        tUser.setId(user.getId());

        if(auditEntity.getId() == null){
            auditEntity.setCreatedOn(date);
            auditEntity.setCreatedBy(tUser);
        }

        auditEntity.setModifiedBy(tUser);
        auditEntity.setModifiedOn(date);
    }

    public SecurityUserDetails getLoggedInUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        if(authentication.isAuthenticated()){
            return (SecurityUserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
