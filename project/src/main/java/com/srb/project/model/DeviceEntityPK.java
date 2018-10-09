package com.srb.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DeviceEntityPK implements Serializable {
    private int idDevice;
    private int usersIdusers;
    private int usersRolesIdroles;
    private int vehicleIdVehicle;

    @Column(name = "idDEVICE", nullable = false)
    @Id
    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    @Column(name = "users_idusers", nullable = false)
    @Id
    public int getUsersIdusers() {
        return usersIdusers;
    }

    public void setUsersIdusers(int usersIdusers) {
        this.usersIdusers = usersIdusers;
    }

    @Column(name = "users_roles_idroles", nullable = false)
    @Id
    public int getUsersRolesIdroles() {
        return usersRolesIdroles;
    }

    public void setUsersRolesIdroles(int usersRolesIdroles) {
        this.usersRolesIdroles = usersRolesIdroles;
    }

    @Column(name = "vehicle_idVEHICLE", nullable = false)
    @Id
    public int getVehicleIdVehicle() {
        return vehicleIdVehicle;
    }

    public void setVehicleIdVehicle(int vehicleIdVehicle) {
        this.vehicleIdVehicle = vehicleIdVehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntityPK that = (DeviceEntityPK) o;

        if (idDevice != that.idDevice) return false;
        if (usersIdusers != that.usersIdusers) return false;
        if (usersRolesIdroles != that.usersRolesIdroles) return false;
        if (vehicleIdVehicle != that.vehicleIdVehicle) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDevice;
        result = 31 * result + usersIdusers;
        result = 31 * result + usersRolesIdroles;
        result = 31 * result + vehicleIdVehicle;
        return result;
    }
}
