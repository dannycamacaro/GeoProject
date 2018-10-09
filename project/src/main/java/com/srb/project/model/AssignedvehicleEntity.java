package com.srb.project.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "assignedvehicle", schema = "srb", catalog = "")
public class AssignedvehicleEntity {
    private int idAssignedvehicle;
    private Integer iddriver;
    private Integer idvehicle;
    private Date date;

    @Id
    @Column(name = "idASSIGNEDVEHICLE", nullable = false)
    public int getIdAssignedvehicle() {
        return idAssignedvehicle;
    }

    public void setIdAssignedvehicle(int idAssignedvehicle) {
        this.idAssignedvehicle = idAssignedvehicle;
    }

    @Basic
    @Column(name = "IDDRIVER", nullable = true)
    public Integer getIddriver() {
        return iddriver;
    }

    public void setIddriver(Integer iddriver) {
        this.iddriver = iddriver;
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

        AssignedvehicleEntity that = (AssignedvehicleEntity) o;

        if (idAssignedvehicle != that.idAssignedvehicle) return false;
        if (iddriver != null ? !iddriver.equals(that.iddriver) : that.iddriver != null) return false;
        if (idvehicle != null ? !idvehicle.equals(that.idvehicle) : that.idvehicle != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAssignedvehicle;
        result = 31 * result + (iddriver != null ? iddriver.hashCode() : 0);
        result = 31 * result + (idvehicle != null ? idvehicle.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
