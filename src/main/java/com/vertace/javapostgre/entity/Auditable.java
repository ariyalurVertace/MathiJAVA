package com.vertace.javapostgre.entity;

import com.vertace.javapostgre.model.ApplicationContext;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@MappedSuperclass
public class Auditable implements Serializable {

    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    private boolean active;

    @PrePersist
    public void onPrePersist() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = dtf.format(now);
        this.active = Boolean.TRUE;
        if (ApplicationContext.getCurrentTenantContext() != null) {
            this.createdBy =
                    String.valueOf(ApplicationContext.getCurrentTenantContext().getUsername());
        }
    }

    @PreUpdate
    public void onPreUpdate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = dtf.format(now);
        if (ApplicationContext.getCurrentTenantContext() != null) {
            this.updatedBy =
                    String.valueOf(ApplicationContext.getCurrentTenantContext().getUsername());
        }
    }

    @PreRemove
    public void onPreRemove() {
        onPreUpdate();
    }
}