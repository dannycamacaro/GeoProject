package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "routedetail", schema = "srb")
public class RoutedetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDROUTEDETAIL", nullable = false)
    private int idroutedetail;

    @Basic
    @Column(name = "IDROUTES", nullable = true)
    private Integer idroutes;

    @Basic
    @Column(name = "ROUTELATITUDE", nullable = true, length = 200)
    private String routelatitude;

    @Basic
    @Column(name = "ROUTELENGTH", nullable = true, length = 200)
    private String routelength;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 150)
    private String description;
    @ManyToOne
    @JoinColumn(name = "IDROUTES", referencedColumnName = "IDROUTES",insertable = false, updatable = false, nullable = false)
    private RoutesEntity routesByIdroutes;

    public void setIdroutedetail(Integer idroutedetail) {
        this.idroutedetail = idroutedetail;
    }


    public int getIdroutedetail() {
        return idroutedetail;
    }

    public void setIdroutedetail(int idroutedetail) {
        this.idroutedetail = idroutedetail;
    }


    public Integer getIdroutes() {
        return idroutes;
    }

    public void setIdroutes(Integer idroutes) {
        this.idroutes = idroutes;
    }


    public String getRoutelatitude() {
        return routelatitude;
    }

    public void setRoutelatitude(String routelatitude) {
        this.routelatitude = routelatitude;
    }


    public String getRoutelength() {
        return routelength;
    }

    public void setRoutelength(String routelength) {
        this.routelength = routelength;
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

    @Override
    public String toString() {
        return "RoutedetailEntity{" +
                "idroutedetail=" + idroutedetail +
                ", routelatitude='" + routelatitude + '\'' +
                ", routelength='" + routelength + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public RoutesEntity getRoutesByIdroutes() {
        return routesByIdroutes;
    }

    public void setRoutesByIdroutes(RoutesEntity routesByIdroutes) {
        this.routesByIdroutes = routesByIdroutes;
    }
}
