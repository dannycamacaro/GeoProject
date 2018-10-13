package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vehicle", schema = "srb")
public class VehicleEntity {
    @Id
    @Column(name = "IDVEHICLE", nullable = false)
    private int idvehicle;
    @Basic
    @Column(name = "MARK", nullable = true, length = 45)
    private String mark;

    @Basic
    @Column(name = "LICENSEPLATE", nullable = true, length = 45)
    private String licenseplate;
    @Basic
    @Column(name = "VEHICLEYEAR", nullable = true)
    private Integer vehicleyear;
    @Basic
    @Column(name = "TON", nullable = true)
    private Integer ton;
    @OneToMany(mappedBy = "vehicleByIdvehicle")
    private Collection<AssignedvehicleEntity> assignedvehiclesByIdvehicle;
    @OneToMany(mappedBy = "vehicleByIdvehicle")
    private Collection<DeviceEntity> devicesByIdvehicle;
    @OneToMany(mappedBy = "vehicleByIdvehicle")
    private Collection<LocationEntity> locationsByIdvehicle;

    @Basic
    @Column(name = "STATEDELETE")
    private Byte statedelete;

    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }


    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }


    public Integer getVehicleyear() {
        return vehicleyear;
    }

    public void setVehicleyear(Integer vehicleyear) {
        this.vehicleyear = vehicleyear;
    }


    public Integer getTon() {
        return ton;
    }

    public void setTon(Integer ton) {
        this.ton = ton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleEntity that = (VehicleEntity) o;

        if (idvehicle != that.idvehicle) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (licenseplate != null ? !licenseplate.equals(that.licenseplate) : that.licenseplate != null) return false;
        if (vehicleyear != null ? !vehicleyear.equals(that.vehicleyear) : that.vehicleyear != null) return false;
        if (ton != null ? !ton.equals(that.ton) : that.ton != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idvehicle;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (licenseplate != null ? licenseplate.hashCode() : 0);
        result = 31 * result + (vehicleyear != null ? vehicleyear.hashCode() : 0);
        result = 31 * result + (ton != null ? ton.hashCode() : 0);
        return result;
    }


    public Collection<AssignedvehicleEntity> getAssignedvehiclesByIdvehicle() {
        return assignedvehiclesByIdvehicle;
    }

    public void setAssignedvehiclesByIdvehicle(Collection<AssignedvehicleEntity> assignedvehiclesByIdvehicle) {
        this.assignedvehiclesByIdvehicle = assignedvehiclesByIdvehicle;
    }


    public Collection<DeviceEntity> getDevicesByIdvehicle() {
        return devicesByIdvehicle;
    }

    public void setDevicesByIdvehicle(Collection<DeviceEntity> devicesByIdvehicle) {
        this.devicesByIdvehicle = devicesByIdvehicle;
    }


    public Collection<LocationEntity> getLocationsByIdvehicle() {
        return locationsByIdvehicle;
    }

    public void setLocationsByIdvehicle(Collection<LocationEntity> locationsByIdvehicle) {
        this.locationsByIdvehicle = locationsByIdvehicle;
    }


    public Byte getStatedelete() {
        return statedelete;
    }

    public void setStatedelete(Byte statedelete) {
        this.statedelete = statedelete;
    }
}
