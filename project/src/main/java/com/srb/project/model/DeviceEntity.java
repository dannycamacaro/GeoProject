package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "device", schema = "srb")
public class DeviceEntity {
    private int iddevice;
    private int idvehicle;
    private String mark;
    private String model;
    private String imei;
    private String phonenumber;

    @Id
    @Column(name = "IDDEVICE", nullable = false)
    public int getIddevice() {
        return iddevice;
    }

    public void setIddevice(int iddevice) {
        this.iddevice = iddevice;
    }

    @Basic
    @Column(name = "IDVEHICLE", nullable = false)
    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }

    @Basic
    @Column(name = "MARK", nullable = true, length = 150)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "MODEL", nullable = true, length = 150)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "IMEI", nullable = true, length = 100)
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Basic
    @Column(name = "PHONENUMBER", nullable = true, length = 45)
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
}
