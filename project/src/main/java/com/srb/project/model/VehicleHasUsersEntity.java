package com.srb.project.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vehicle_has_users", schema = "srb")
@IdClass(VehicleHasUsersEntityPK.class)
public class VehicleHasUsersEntity {
    private int vehicleIdVehicle;
    private int usersIdusers;
    private int usersRolesIdroles;
    private int usersAssignedvehicleIdAssignedvehicle;
    private Date date;

    @Id
    @Column(name = "vehicle_idVEHICLE", nullable = false)
    public int getVehicleIdVehicle() {
        return vehicleIdVehicle;
    }

    public void setVehicleIdVehicle(int vehicleIdVehicle) {
        this.vehicleIdVehicle = vehicleIdVehicle;
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

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleHasUsersEntity that = (VehicleHasUsersEntity) o;

        if (vehicleIdVehicle != that.vehicleIdVehicle) return false;
        if (usersIdusers != that.usersIdusers) return false;
        if (usersRolesIdroles != that.usersRolesIdroles) return false;
        if (usersAssignedvehicleIdAssignedvehicle != that.usersAssignedvehicleIdAssignedvehicle) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vehicleIdVehicle;
        result = 31 * result + usersIdusers;
        result = 31 * result + usersRolesIdroles;
        result = 31 * result + usersAssignedvehicleIdAssignedvehicle;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
