package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "routes", schema = "srb")
public class RoutesEntity {
    private int idRoutes;
    private String nameroutes;
    private String description;

    @Id
    @Column(name = "idROUTES", nullable = false)
    public int getIdRoutes() {
        return idRoutes;
    }

    public void setIdRoutes(int idRoutes) {
        this.idRoutes = idRoutes;
    }

    @Basic
    @Column(name = "NAMEROUTES", nullable = true, length = 100)
    public String getNameroutes() {
        return nameroutes;
    }

    public void setNameroutes(String nameroutes) {
        this.nameroutes = nameroutes;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 400)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesEntity that = (RoutesEntity) o;

        if (idRoutes != that.idRoutes) return false;
        if (nameroutes != null ? !nameroutes.equals(that.nameroutes) : that.nameroutes != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoutes;
        result = 31 * result + (nameroutes != null ? nameroutes.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
