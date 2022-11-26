package io.gynacare.gynacare.appointments;

import java.math.BigDecimal;
import java.sql.Date;

public class AppointmentBean {
    private BigDecimal appId;
    private Date appDate;
    private String appStatus;
    private String appLocation;
    private String appTime;
    private BigDecimal appPatientId;
    private BigDecimal appGynaId;
    private Date appAppointmentDate;
    public BigDecimal getAppId() {
        return appId;
    }
    public void setAppId(BigDecimal appId) {
        this.appId = appId;
    }
    public Date getAppDate() {
        return appDate;
    }
    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
    public String getAppStatus() {
        return appStatus;
    }
    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }
  
    public String getAppTime() {
        return appTime;
    }
    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }
    public BigDecimal getAppPatientId() {
        return appPatientId;
    }
    public void setAppPatientId(BigDecimal appPatientId) {
        this.appPatientId = appPatientId;
    }
    public BigDecimal getAppGynaId() {
        return appGynaId;
    }
    public void setAppGynaId(BigDecimal appGynaId) {
        this.appGynaId = appGynaId;
    }
    public Date getAppAppointmentDate() {
        return appAppointmentDate;
    }
    public void setAppAppointmentDate(Date appAppointmentDate) {
        this.appAppointmentDate = appAppointmentDate;
    }
    public String getAppLocation() {
        return appLocation;
    }
    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }


}
