package com.pearlbank.base.models.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 12:06
 **/
@ToString
@MappedSuperclass
@Setter
public class BaseEntity implements Serializable {
    private Long id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "created_on")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @Column(name = "modified_on")
    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }
}
