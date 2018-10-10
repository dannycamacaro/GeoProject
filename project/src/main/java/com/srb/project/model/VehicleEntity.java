package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vehicle", schema = "srb")
public class VehicleEntity {
    private int idvehicle;
    private String mark;
    private String licenseplate;
    private Integer vehicleyear;
    private Integer ton;
    private Collection<AssignedvehicleEntity> assignedvehiclesByIdvehicle;
    private Collection<DeviceEntity> devicesByIdvehicle;
    private Collection<LocationEntity> locationsByIdvehicle;

    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }

    @Id
    @Column(name = "IDVEHICLE", nullable = false)
    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }

    @Basic
    @Column(name = "MARK", nullable = true, length = 45)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "LICENSEPLATE", nullable = true, length = 45)
    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    @Basic
    @Column(name = "VEHICLEYEAR", nullable = true)
    public Integer getVehicleyear() {
        return vehicleyear;
    }

    public void setVehicleyear(Integer vehicleyear) {
        this.vehicleyear = vehicleyear;
    }

    @Basic
    @Column(name = "TON", nullable = true)
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

    @OneToMany(mappedBy = "vehicleByIdvehicle")
    public Collection<AssignedvehicleEntity> getAssignedvehiclesByIdvehicle() {
        return assignedvehiclesByIdvehicle;
    }

    public void setAssignedvehiclesByIdvehicle(Collection<AssignedvehicleEntity> assignedvehiclesByIdvehicle) {
        this.assignedvehiclesByIdvehicle = assignedvehiclesByIdvehicle;
    }

    @OneToMany(mappedBy = "vehicleByIdvehicle")
    public Collection<DeviceEntity> getDevicesByIdvehicle() {
        return devicesByIdvehicle;
    }

    public void setDevicesByIdvehicle(Collection<DeviceEntity> devicesByIdvehicle) {
        this.devicesByIdvehicle = devicesByIdvehicle;
    }

    @OneToMany(mappedBy = "vehicleByIdvehicle")
    public Collection<LocationEntity> getLocationsByIdvehicle() {
        return locationsByIdvehicle;
    }

    public void setLocationsByIdvehicle(Collection<LocationEntity> locationsByIdvehicle) {
        this.locationsByIdvehicle = locationsByIdvehicle;
    }
}
