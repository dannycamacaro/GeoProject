package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "routes_has_device", schema = "srb")
@IdClass(RoutesHasDeviceEntityPK.class)
public class RoutesHasDeviceEntity {
    private int routesIdRoutes;
    private int deviceIdDevice;
    private int deviceUsersIdusers;
    private int deviceUsersRolesIdroles;
    private int deviceVehicleIdVehicle;

    @Id
    @Column(name = "routes_idROUTES", nullable = false)
    public int getRoutesIdRoutes() {
        return routesIdRoutes;
    }

    public void setRoutesIdRoutes(int routesIdRoutes) {
        this.routesIdRoutes = routesIdRoutes;
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

    @Id
    @Column(name = "device_vehicle_idVEHICLE", nullable = false)
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

        RoutesHasDeviceEntity that = (RoutesHasDeviceEntity) o;

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
