package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    private Boolean accountBalanceUpdate;

    private Boolean transcationUpdate;

    private Boolean securityAlert;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Boolean getAccountBalanceUpdate() {
        return accountBalanceUpdate;
    }

    public void setAccountBalanceUpdate(Boolean accountBalanceUpdate) {
        this.accountBalanceUpdate = accountBalanceUpdate;
    }

    public Boolean getTranscationUpdate() {
        return transcationUpdate;
    }

    public void setTranscationUpdate(Boolean transcationUpdate) {
        this.transcationUpdate = transcationUpdate;
    }

    public Boolean getSecurityAlert() {
        return securityAlert;
    }

    public void setSecurityAlert(Boolean securityAlert) {
        this.securityAlert = securityAlert;
    }
}
