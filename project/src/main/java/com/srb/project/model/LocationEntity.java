package com.srb.project.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "location", schema = "srb", catalog = "")
public class LocationEntity {
    private int idLocation;
    private Integer idvehicle;
    private String locationlatitude;
    private String locationlength;
    private Date date;

    @Id
    @Column(name = "idLOCATION", nullable = false)
    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
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
    @Column(name = "DATE", nullable = true)
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

        LocationEntity that = (LocationEntity) o;

        if (idLocation != that.idLocation) return false;
        if (idvehicle != null ? !idvehicle.equals(that.idvehicle) : that.idvehicle != null) return false;
        if (locationlatitude != null ? !locationlatitude.equals(that.locationlatitude) : that.locationlatitude != null)
            return false;
        if (locationlength != null ? !locationlength.equals(that.locationlength) : that.locationlength != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLocation;
        result = 31 * result + (idvehicle != null ? idvehicle.hashCode() : 0);
        result = 31 * result + (locationlatitude != null ? locationlatitude.hashCode() : 0);
        result = 31 * result + (locationlength != null ? locationlength.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
