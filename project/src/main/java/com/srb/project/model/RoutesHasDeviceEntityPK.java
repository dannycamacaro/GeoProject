package com.srb.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RoutesHasDeviceEntityPK implements Serializable {
    private int routesIdRoutes;
    private int deviceIdDevice;
    private int deviceUsersIdusers;
    private int deviceUsersRolesIdroles;
    private int deviceVehicleIdVehicle;

    @Column(name = "routes_idROUTES", nullable = false)
    @Id
    public int getRoutesIdRoutes() {
        return routesIdRoutes;
    }

    public void setRoutesIdRoutes(int routesIdRoutes) {
        this.routesIdRoutes = routesIdRoutes;
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

    @Column(name = "device_vehicle_idVEHICLE", nullable = false)
    @Id
    public int getDeviceVehicleIdVehicle() {
        return deviceVehicleIdVehicle;
    }

    public void setDeviceVehicleIdVehicle(int deviceVehicleIdVehicle) {
        this.deviceVehicleIdVehicle = deviceVehicleIdVehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesHasDeviceEntityPK that = (RoutesHasDeviceEntityPK) o;

        if (routesIdRoutes != that.routesIdRoutes) return false;
        if (deviceIdDevice != that.deviceIdDevice) return false;
        if (deviceUsersIdusers != that.deviceUsersIdusers) return false;
        if (deviceUsersRolesIdroles != that.deviceUsersRolesIdroles) return false;
        if (deviceVehicleIdVehicle != that.deviceVehicleIdVehicle) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routesIdRoutes;
        result = 31 * result + deviceIdDevice;
        result = 31 * result + deviceUsersIdusers;
        result = 31 * result + deviceUsersRolesIdroles;
        result = 31 * result + deviceVehicleIdVehicle;
        return result;
    }
}
