package com.srb.project.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "assignedvehicle", schema = "srb")
public class AssignedvehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDASSIGNEDVEHICLE", nullable = false)
    private int idassignedvehicle;

    @Basic
    @Column(name = "IDUSERS", nullable = false)
    private int idusers;
    @Basic
    @Column(name = "IDVEHICLE", nullable = false)
    private int idvehicle;

    @Basic
    @Column(name = "INITIALDATE", nullable = true)
    private LocalDate initialdate;

    @Basic
    @Column(name = "FINISHDATE", nullable = true)
    private LocalDate finishdate;
    @ManyToOne
    @JoinColumn(name = "IDUSERS", referencedColumnName = "IDUSERS", insertable = false, updatable = false, nullable = false)
    private UsersEntity usersByIdusers;
    @ManyToOne
    @JoinColumn(name = "IDVEHICLE", referencedColumnName = "IDVEHICLE", insertable = false, updatable = false, nullable = false)
    private VehicleEntity vehicleByIdvehicle;

    @Transient
    private String nameUser;

    @Transient
    private String licensePlate;

    public void setIdassignedvehicle(Integer idassignedvehicle) {
        this.idassignedvehicle = idassignedvehicle;
    }

    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }

    public int getIdassignedvehicle() {
        return idassignedvehicle;
    }

    public void setIdassignedvehicle(int idassignedvehicle) {
        this.idassignedvehicle = idassignedvehicle;
    }

    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }


    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }


    public LocalDate  getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(LocalDate initialdate) {
        this.initialdate = initialdate;
    }


    public LocalDate getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(LocalDate finishdate) {
        this.finishdate = finishdate;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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


    public UsersEntity getUsersByIdusers() {
        return usersByIdusers;
    }

    public void setUsersByIdusers(UsersEntity usersByIdusers) {
        this.usersByIdusers = usersByIdusers;
    }


    public VehicleEntity getVehicleByIdvehicle() {
        return vehicleByIdvehicle;
    }

    public void setVehicleByIdvehicle(VehicleEntity vehicleByIdvehicle) {
        this.vehicleByIdvehicle = vehicleByIdvehicle;
    }
}
