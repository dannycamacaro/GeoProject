package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "routes", schema = "srb", catalog = "")
@IdClass(RoutesEntityPK.class)
public class RoutesEntity {
    private int idRoutes;
    private String nameroutes;
    private String description;
    private int deviceIdDevice;
    private int deviceUsersIdusers;
    private int deviceUsersRolesIdroles;

    @Id
    @Column(name = "idROUTES", nullable = false)
    public int getIdRoutes() {
        return idRoutes;
    }

    public void setIdRoutes(int idRoutes) {
        this.idRoutes = idRoutes;
    }

    @Basic
    @Column(name = "NAMEROUTES", nullable = true, length = 100)
    public String getNameroutes() {
        return nameroutes;
    }

    public void setNameroutes(String nameroutes) {
        this.nameroutes = nameroutes;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 400)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column(name = "device_idDEVICE", nullable = false)
    public int getDeviceIdDevice() {
        return deviceIdDevice;
    }

    public void setDeviceIdDevice(int deviceIdDevice) {
        this.deviceIdDevice = deviceIdDevice;
    }

    @Id
    @Column(name = "device_users_idusers", nullable = false)
    public int getDeviceUsersIdusers() {
        return deviceUsersIdusers;
    }

    public void setDeviceUsersIdusers(int deviceUsersIdusers) {
        this.deviceUsersIdusers = deviceUsersIdusers;
    }

    @Id
    @Column(name = "device_users_roles_idroles", nullable = false)
    public int getDeviceUsersRolesIdroles() {
        return deviceUsersRolesIdroles;
    }

    public void setDeviceUsersRolesIdroles(int deviceUsersRolesIdroles) {
        this.deviceUsersRolesIdroles = deviceUsersRolesIdroles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesEntity that = (RoutesEntity) o;

        if (idRoutes != that.idRoutes) return false;
        if (deviceIdDevice != that.deviceIdDevice) return false;
        if (deviceUsersIdusers != that.deviceUsersIdusers) return false;
        if (deviceUsersRolesIdroles != that.deviceUsersRolesIdroles) return false;
        if (nameroutes != null ? !nameroutes.equals(that.nameroutes) : that.nameroutes != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoutes;
        result = 31 * result + (nameroutes != null ? nameroutes.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + deviceIdDevice;
        result = 31 * result + deviceUsersIdusers;
        result = 31 * result + deviceUsersRolesIdroles;
        return result;
    }
}
