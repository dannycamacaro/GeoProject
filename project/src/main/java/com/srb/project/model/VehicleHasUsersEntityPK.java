package com.srb.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class VehicleHasUsersEntityPK implements Serializable {
    private int vehicleIdVehicle;
    private int usersIdusers;
    private int usersRolesIdroles;
    private int usersAssignedvehicleIdAssignedvehicle;

    @Column(name = "vehicle_idVEHICLE", nullable = false)
    @Id
    public int getVehicleIdVehicle() {
        return vehicleIdVehicle;
    }

    public void setVehicleIdVehicle(int vehicleIdVehicle) {
        this.vehicleIdVehicle = vehicleIdVehicle;
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

    @Column(name = "users_assignedvehicle_idASSIGNEDVEHICLE", nullable = false)
    @Id
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

        VehicleHasUsersEntityPK that = (VehicleHasUsersEntityPK) o;

        if (vehicleIdVehicle != that.vehicleIdVehicle) return false;
        if (usersIdusers != that.usersIdusers) return false;
        if (usersRolesIdroles != that.usersRolesIdroles) return false;
        if (usersAssignedvehicleIdAssignedvehicle != that.usersAssignedvehicleIdAssignedvehicle) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vehicleIdVehicle;
        result = 31 * result + usersIdusers;
        result = 31 * result + usersRolesIdroles;
        result = 31 * result + usersAssignedvehicleIdAssignedvehicle;
        return result;
    }
}
