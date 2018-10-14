package com.srb.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "location", schema = "srb")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLOCATION", nullable = false)
    private int idlocation;

    @Basic
    @Column(name = "IDVEHICLE", nullable = false)
    private Integer idvehicle;

    @Basic
    @Column(name = "LOCATIONLATITUDE", nullable = true, length = 100)
    private String locationlatitude;

    @Basic
    @Column(name = "LOCATIONLENGTH", nullable = true, length = 100)
    private String locationlength;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOCATIONDATE", nullable = true)
    private Date locationdate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IDVEHICLE", referencedColumnName = "IDVEHICLE",insertable = false, updatable = false, nullable = false)
    private VehicleEntity vehicleByIdvehicle;

    @Basic
    @Column(name = "STATEDELETE")
    private Byte statedelete;

    public LocationEntity (){}

    public LocationEntity(String locationlatitude, String locationlength, int idvehicle, Byte statedelete) {
        this.locationlatitude = locationlatitude;
        this.locationlength = locationlength;
        this.idvehicle = idvehicle;
        this.statedelete = statedelete;
    }
    @PrePersist
    public void prePersist() {
        locationdate =  new Date();
    }


    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public int getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(int idlocation) {
        this.idlocation = idlocation;
    }

    public String getLocationlatitude() {
        return locationlatitude;
    }

    public void setLocationlatitude(String locationlatitude) {
        this.locationlatitude = locationlatitude;
    }


    public String getLocationlength() {
        return locationlength;
    }

    public void setLocationlength(String locationlength) {
        this.locationlength = locationlength;
    }


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


    public VehicleEntity getVehicleByIdvehicle() {
        return vehicleByIdvehicle;
    }

    public void setVehicleByIdvehicle(VehicleEntity vehicleByIdvehicle) {
        this.vehicleByIdvehicle = vehicleByIdvehicle;
    }


    public Byte getStatedelete() {
        return statedelete;
    }

    public void setStatedelete(Byte statedelete) {
        this.statedelete = statedelete;
    }
}
