package com.srb.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RoutesEntityPK implements Serializable {
    private int idRoutes;
    private int deviceIdDevice;
    private int deviceUsersIdusers;
    private int deviceUsersRolesIdroles;

    @Column(name = "idROUTES", nullable = false)
    @Id
    public int getIdRoutes() {
        return idRoutes;
    }

    public void setIdRoutes(int idRoutes) {
        this.idRoutes = idRoutes;
    }

    @Column(name = "device_idDEVICE", nullable = false)
    @Id
    public int getDeviceIdDevice() {
        return deviceIdDevice;
    }

    public void setDeviceIdDevice(int deviceIdDevice) {
        this.deviceIdDevice = deviceIdDevice;
    }

    @Column(name = "device_users_idusers", nullable = false)
    @Id
    public int getDeviceUsersIdusers() {
        return deviceUsersIdusers;
    }

    public void setDeviceUsersIdusers(int deviceUsersIdusers) {
        this.deviceUsersIdusers = deviceUsersIdusers;
    }

    @Column(name = "device_users_roles_idroles", nullable = false)
    @Id
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

        RoutesEntityPK that = (RoutesEntityPK) o;

        if (idRoutes != that.idRoutes) return false;
        if (deviceIdDevice != that.deviceIdDevice) return false;
        if (deviceUsersIdusers != that.deviceUsersIdusers) return false;
        if (deviceUsersRolesIdroles != that.deviceUsersRolesIdroles) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoutes;
        result = 31 * result + deviceIdDevice;
        result = 31 * result + deviceUsersIdusers;
        result = 31 * result + deviceUsersRolesIdroles;
        return result;
    }
}
