package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "routes", schema = "srb")
public class RoutesEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "IDROUTES", nullable = false)
    private int idroutes;

    @Basic
    @Column(name = "NAMEROUTES", nullable = true, length = 100)
    private String nameroutes;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 400)
    private String description;
    @OneToMany(mappedBy = "routesByIdroutes")
    private Collection<AssignedroutesEntity> assignedroutesByIdroutes;
    @OneToMany(mappedBy = "routesByIdroutes")
    private Collection<RoutedetailEntity> routedetailsByIdroutes;

    @Basic
    @Column(name = "STATEDELETE")
    private Byte statedelete;

    public void setIdroutes(Integer idroutes) {
        this.idroutes = idroutes;
    }


    public int getIdroutes() {
        return idroutes;
    }

    public void setIdroutes(int idroutes) {
        this.idroutes = idroutes;
    }


    public String getNameroutes() {
        return nameroutes;
    }

    public void setNameroutes(String nameroutes) {
        this.nameroutes = nameroutes;
    }


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

        if (idroutes != that.idroutes) return false;
        if (nameroutes != null ? !nameroutes.equals(that.nameroutes) : that.nameroutes != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idroutes;
        result = 31 * result + (nameroutes != null ? nameroutes.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


    public Collection<AssignedroutesEntity> getAssignedroutesByIdroutes() {
        return assignedroutesByIdroutes;
    }

    public void setAssignedroutesByIdroutes(Collection<AssignedroutesEntity> assignedroutesByIdroutes) {
        this.assignedroutesByIdroutes = assignedroutesByIdroutes;
    }


    public Collection<RoutedetailEntity> getRoutedetailsByIdroutes() {
        return routedetailsByIdroutes;
    }

    public void setRoutedetailsByIdroutes(Collection<RoutedetailEntity> routedetailsByIdroutes) {
        this.routedetailsByIdroutes = routedetailsByIdroutes;
    }


    public Byte getStatedelete() {
        return statedelete;
    }

    public void setStatedelete(Byte statedelete) {
        this.statedelete = statedelete;
    }
}
