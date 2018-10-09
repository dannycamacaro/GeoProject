package com.srb.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UsersEntityPK implements Serializable {
    private int idusers;
    private int rolesIdroles;
    private int assignedvehicleIdAssignedvehicle;

    @Column(name = "idusers", nullable = false)
    @Id
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Column(name = "roles_idroles", nullable = false)
    @Id
    public int getRolesIdroles() {
        return rolesIdroles;
    }

    public void setRolesIdroles(int rolesIdroles) {
        this.rolesIdroles = rolesIdroles;
    }

    @Column(name = "assignedvehicle_idASSIGNEDVEHICLE", nullable = false)
    @Id
    public int getAssignedvehicleIdAssignedvehicle() {
        return assignedvehicleIdAssignedvehicle;
    }

    public void setAssignedvehicleIdAssignedvehicle(int assignedvehicleIdAssignedvehicle) {
        this.assignedvehicleIdAssignedvehicle = assignedvehicleIdAssignedvehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntityPK that = (UsersEntityPK) o;

        if (idusers != that.idusers) return false;
        if (rolesIdroles != that.rolesIdroles) return false;
        if (assignedvehicleIdAssignedvehicle != that.assignedvehicleIdAssignedvehicle) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idusers;
        result = 31 * result + rolesIdroles;
        result = 31 * result + assignedvehicleIdAssignedvehicle;
        return result;
    }
}
