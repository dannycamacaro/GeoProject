package com.srb.project.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "assignedvehicle", schema = "srb")
public class AssignedvehicleEntity {
    private int idassignedvehicle;
    private int idusers;
    private int idvehicle;
    private Date initialdate;
    private Date finishdate;
    private UsersEntity usersByIdusers;
    private VehicleEntity vehicleByIdvehicle;

    public void setIdassignedvehicle(Integer idassignedvehicle) {
        this.idassignedvehicle = idassignedvehicle;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }

    @Id
    @Column(name = "IDASSIGNEDVEHICLE", nullable = false)
    public int getIdassignedvehicle() {
        return idassignedvehicle;
    }

    public void setIdassignedvehicle(int idassignedvehicle) {
        this.idassignedvehicle = idassignedvehicle;
    }

    @Basic
    @Column(name = "IDUSERS", nullable = false)
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Basic
    @Column(name = "IDVEHICLE", nullable = false)
    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }

    @Basic
    @Column(name = "INITIALDATE", nullable = true)
    public Date getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(Date initialdate) {
        this.initialdate = initialdate;
    }

    @Basic
    @Column(name = "FINISHDATE", nullable = true)
    public Date getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignedvehicleEntity that = (AssignedvehicleEntity) o;

        if (idassignedvehicle != that.idassignedvehicle) return false;
        if (idusers != that.idusers) return false;
        if (idvehicle != that.idvehicle) return false;
        if (initialdate != null ? !initialdate.equals(that.initialdate) : that.initialdate != null) return false;
        if (finishdate != null ? !finishdate.equals(that.finishdate) : that.finishdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idassignedvehicle;
        result = 31 * result + idusers;
        result = 31 * result + idvehicle;
        result = 31 * result + (initialdate != null ? initialdate.hashCode() : 0);
        result = 31 * result + (finishdate != null ? finishdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDUSERS", referencedColumnName = "IDUSERS",insertable = false, updatable = false, nullable = false)
    public UsersEntity getUsersByIdusers() {
        return usersByIdusers;
    }

    public void setUsersByIdusers(UsersEntity usersByIdusers) {
        this.usersByIdusers = usersByIdusers;
    }

    @ManyToOne
    @JoinColumn(name = "IDVEHICLE", referencedColumnName = "IDVEHICLE",insertable = false, updatable = false, nullable = false)
    public VehicleEntity getVehicleByIdvehicle() {
        return vehicleByIdvehicle;
    }

    public void setVehicleByIdvehicle(VehicleEntity vehicleByIdvehicle) {
        this.vehicleByIdvehicle = vehicleByIdvehicle;
    }
}
