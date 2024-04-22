package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    private Boolean accountBalanceUpdate;

    private Boolean transcationUpdate;

    private Boolean securityAlert;
    @OneToOne(mappedBy = "notification")
    @JoinColumn(name = "userId")
    private User user;

    public Notification(Boolean accountBalanceUpdate, Boolean transcationUpdate, Boolean securityAlert,User user) {
        this.accountBalanceUpdate = accountBalanceUpdate;
        this.transcationUpdate = transcationUpdate;
        this.securityAlert = securityAlert;
        this.user = user;
    }


    public Notification() {

    }

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
