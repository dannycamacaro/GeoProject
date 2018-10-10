package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "assignedroutes", schema = "srb")
public class AssignedroutesEntity {
    private int idassignedroutes;
    private int iddevice;
    private int idroutes;

    @Id
    @Column(name = "IDASSIGNEDROUTES", nullable = false)
    public int getIdassignedroutes() {
        return idassignedroutes;
    }

    public void setIdassignedroutes(int idassignedroutes) {
        this.idassignedroutes = idassignedroutes;
    }

    @Basic
    @Column(name = "IDDEVICE", nullable = false)
    public int getIddevice() {
        return iddevice;
    }

    public void setIddevice(int iddevice) {
        this.iddevice = iddevice;
    }

    @Basic
    @Column(name = "IDROUTES", nullable = false)
    public int getIdroutes() {
        return idroutes;
    }

    public void setIdroutes(int idroutes) {
        this.idroutes = idroutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignedroutesEntity that = (AssignedroutesEntity) o;

        if (idassignedroutes != that.idassignedroutes) return false;
        if (iddevice != that.iddevice) return false;
        if (idroutes != that.idroutes) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idassignedroutes;
        result = 31 * result + iddevice;
        result = 31 * result + idroutes;
        return result;
    }
}
