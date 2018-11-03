package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "device", schema = "srb")
public class DeviceEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "IDDEVICE", nullable = false)
    private int iddevice;

    @Basic
    @Column(name = "IDVEHICLE", nullable = false)
    private int idvehicle;

    @Basic
    @Column(name = "MARK", nullable = true, length = 150)
    private String mark;

    @Basic
    @Column(name = "MODEL", nullable = true, length = 150)
    private String model;

    @Basic
    @Column(name = "IMEI", nullable = true, length = 100)
    private String imei;

    @Basic
    @Column(name = "PHONENUMBER", nullable = true, length = 45)
    private String phonenumber;
    @OneToMany(mappedBy = "deviceByIddevice")
    private Collection<AssignedroutesEntity> assignedroutesByIddevice;
    @ManyToOne
    @JoinColumn(name = "IDVEHICLE", referencedColumnName = "IDVEHICLE",insertable = false, updatable = false, nullable = false)
    private VehicleEntity vehicleByIdvehicle;

    @Basic
    @Column(name = "STATEDELETE")
    private Byte statedelete;

    @Transient
    private String  nameVehicle;

    public void setIddevice(Integer iddevice) {
        this.iddevice = iddevice;
    }

    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }


    public int getIddevice() {
        return iddevice;
    }

    public void setIddevice(int iddevice) {
        this.iddevice = iddevice;
    }


    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNameVehicle() {
        return nameVehicle;
    }

    public void setNameVehicle(String nameVehicle) {
        this.nameVehicle = nameVehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntity that = (DeviceEntity) o;

        if (iddevice != that.iddevice) return false;
        if (idvehicle != that.idvehicle) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (phonenumber != null ? !phonenumber.equals(that.phonenumber) : that.phonenumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddevice;
        result = 31 * result + idvehicle;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (phonenumber != null ? phonenumber.hashCode() : 0);
        return result;
    }


    public Collection<AssignedroutesEntity> getAssignedroutesByIddevice() {
        return assignedroutesByIddevice;
    }

    public void setAssignedroutesByIddevice(Collection<AssignedroutesEntity> assignedroutesByIddevice) {
        this.assignedroutesByIddevice = assignedroutesByIddevice;
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
