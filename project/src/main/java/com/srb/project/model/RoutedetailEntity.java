package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "routedetail", schema = "srb")
public class RoutedetailEntity {
    private int idroutedetail;
    private Integer idroutes;
    private String routelatitude;
    private String routelength;
    private String description;

    @Id
    @Column(name = "IDROUTEDETAIL", nullable = false)
    public int getIdroutedetail() {
        return idroutedetail;
    }

    public void setIdroutedetail(int idroutedetail) {
        this.idroutedetail = idroutedetail;
    }

    @Basic
    @Column(name = "IDROUTES", nullable = true)
    public Integer getIdroutes() {
        return idroutes;
    }

    public void setIdroutes(Integer idroutes) {
        this.idroutes = idroutes;
    }

    @Basic
    @Column(name = "ROUTELATITUDE", nullable = true, length = 200)
    public String getRoutelatitude() {
        return routelatitude;
    }

    public void setRoutelatitude(String routelatitude) {
        this.routelatitude = routelatitude;
    }

    @Basic
    @Column(name = "ROUTELENGTH", nullable = true, length = 200)
    public String getRoutelength() {
        return routelength;
    }

    public void setRoutelength(String routelength) {
        this.routelength = routelength;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 150)
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

        RoutedetailEntity that = (RoutedetailEntity) o;

        if (idroutedetail != that.idroutedetail) return false;
        if (idroutes != null ? !idroutes.equals(that.idroutes) : that.idroutes != null) return false;
        if (routelatitude != null ? !routelatitude.equals(that.routelatitude) : that.routelatitude != null)
            return false;
        if (routelength != null ? !routelength.equals(that.routelength) : that.routelength != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idroutedetail;
        result = 31 * result + (idroutes != null ? idroutes.hashCode() : 0);
        result = 31 * result + (routelatitude != null ? routelatitude.hashCode() : 0);
        result = 31 * result + (routelength != null ? routelength.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
