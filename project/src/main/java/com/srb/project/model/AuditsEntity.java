package com.srb.project.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "audits", schema = "srb", catalog = "")
@IdClass(AuditsEntityPK.class)
public class AuditsEntity {
    private int idAudits;
    private String operation;
    private String ip;
    private Date date;
    private Time time;
    private Integer statusOperation;
    private String conten;
    private int usersIdusers;
    private int usersRolesIdroles;
    private int usersAssignedvehicleIdAssignedvehicle;

    @Id
    @Column(name = "idAudits", nullable = false)
    public int getIdAudits() {
        return idAudits;
    }

    public void setIdAudits(int idAudits) {
        this.idAudits = idAudits;
    }

    @Basic
    @Column(name = "Operation", nullable = true, length = 45)
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 45)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "statusOperation", nullable = true)
    public Integer getStatusOperation() {
        return statusOperation;
    }

    public void setStatusOperation(Integer statusOperation) {
        this.statusOperation = statusOperation;
    }

    @Basic
    @Column(name = "conten", nullable = true, length = 3000)
    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    @Id
    @Column(name = "users_idusers", nullable = false)
    public int getUsersIdusers() {
        return usersIdusers;
    }

    public void setUsersIdusers(int usersIdusers) {
        this.usersIdusers = usersIdusers;
    }

    @Id
    @Column(name = "users_roles_idroles", nullable = false)
    public int getUsersRolesIdroles() {
        return usersRolesIdroles;
    }

    public void setUsersRolesIdroles(int usersRolesIdroles) {
        this.usersRolesIdroles = usersRolesIdroles;
    }

    @Id
    @Column(name = "users_assignedvehicle_idASSIGNEDVEHICLE", nullable = false)
    public int getUsersAssignedvehicleIdAssignedvehicle() {
        return usersAssignedvehicleIdAssignedvehicle;
    }

    public void setUsersAssignedvehicleIdAssignedvehicle(int usersAssignedvehicleIdAssignedvehicle) {
        this.usersAssignedvehicleIdAssignedvehicle = usersAssignedvehicleIdAssignedvehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditsEntity that = (AuditsEntity) o;

        if (idAudits != that.idAudits) return false;
        if (usersIdusers != that.usersIdusers) return false;
        if (usersRolesIdroles != that.usersRolesIdroles) return false;
        if (usersAssignedvehicleIdAssignedvehicle != that.usersAssignedvehicleIdAssignedvehicle) return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (statusOperation != null ? !statusOperation.equals(that.statusOperation) : that.statusOperation != null)
            return false;
        if (conten != null ? !conten.equals(that.conten) : that.conten != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAudits;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (statusOperation != null ? statusOperation.hashCode() : 0);
        result = 31 * result + (conten != null ? conten.hashCode() : 0);
        result = 31 * result + usersIdusers;
        result = 31 * result + usersRolesIdroles;
        result = 31 * result + usersAssignedvehicleIdAssignedvehicle;
        return result;
    }
}
