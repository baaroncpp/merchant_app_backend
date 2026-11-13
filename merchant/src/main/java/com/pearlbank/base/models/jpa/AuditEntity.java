package com.pearlbank.base.models.jpa;

import com.pearlbank.user_mgt.model.jpa.TUser;
import jakarta.persistence.*;
import lombok.*;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 12:07
 **/
@MappedSuperclass
@Setter
@ToString
public class AuditEntity extends BaseEntity {
    private TUser modifiedBy;
    private TUser createdBy;
    private boolean isDeleted;

    @JoinColumn(name = "modified_by_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getModifiedBy() {
        return modifiedBy;
    }

    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getCreatedBy() {
        return createdBy;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }
}
