package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "vehicle", schema = "srb")
public class VehicleEntity {
    private int idVehicle;
    private String mark;
    private String licenseplate;
    private Integer year;
    private Integer ton;

    @Id
    @Column(name = "idVEHICLE", nullable = false)
    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
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
    @Column(name = "YEAR", nullable = true)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

        if (idVehicle != that.idVehicle) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (licenseplate != null ? !licenseplate.equals(that.licenseplate) : that.licenseplate != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (ton != null ? !ton.equals(that.ton) : that.ton != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVehicle;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (licenseplate != null ? licenseplate.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (ton != null ? ton.hashCode() : 0);
        return result;
    }
}
