package com.srb.project.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "location", schema = "srb")
public class LocationEntity {
    private int idlocation;
    private Integer idvehicle;
    private String locationlatitude;
    private String locationlength;
    private Date locationdate;
    private VehicleEntity vehicleByIdvehicle;

    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }

    @Id
    @Column(name = "IDLOCATION", nullable = false)
    public int getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(int idlocation) {
        this.idlocation = idlocation;
    }

    @Basic
    @Column(name = "IDVEHICLE", nullable = true)
    public Integer getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }

    @Basic
    @Column(name = "LOCATIONLATITUDE", nullable = true, length = 100)
    public String getLocationlatitude() {
        return locationlatitude;
    }

    public void setLocationlatitude(String locationlatitude) {
        this.locationlatitude = locationlatitude;
    }

    @Basic
    @Column(name = "LOCATIONLENGTH", nullable = true, length = 100)
    public String getLocationlength() {
        return locationlength;
    }

    public void setLocationlength(String locationlength) {
        this.locationlength = locationlength;
    }

    @Basic
    @Column(name = "LOCATIONDATE", nullable = true)
    public Date getLocationdate() {
        return locationdate;
    }

    public void setLocationdate(Date locationdate) {
        this.locationdate = locationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

        if (idlocation != that.idlocation) return false;
        if (idvehicle != null ? !idvehicle.equals(that.idvehicle) : that.idvehicle != null) return false;
        if (locationlatitude != null ? !locationlatitude.equals(that.locationlatitude) : that.locationlatitude != null)
            return false;
        if (locationlength != null ? !locationlength.equals(that.locationlength) : that.locationlength != null)
            return false;
        if (locationdate != null ? !locationdate.equals(that.locationdate) : that.locationdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idlocation;
        result = 31 * result + (idvehicle != null ? idvehicle.hashCode() : 0);
        result = 31 * result + (locationlatitude != null ? locationlatitude.hashCode() : 0);
        result = 31 * result + (locationlength != null ? locationlength.hashCode() : 0);
        result = 31 * result + (locationdate != null ? locationdate.hashCode() : 0);
        return result;
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
