package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "device", schema = "srb")
@IdClass(DeviceEntityPK.class)
public class DeviceEntity {
    private int idDevice;
    private String mark;
    private String model;
    private String imei;
    private String phonenumber;
    private int usersIdusers;
    private int usersRolesIdroles;
    private int vehicleIdVehicle;

    @Id
    @Column(name = "idDEVICE", nullable = false)
    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
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

    @Id
    @Column(name = "users_idusers", nullable = false)
    public int getUsersIdusers() {
        return usersIdusers;
    }

    public void setUsersIdusers(int usersIdusers) {
        this.usersIdusers = usersIdusers;
    }

    @Id
    @Column(name = "users_roles_idroles", nullable = false)
    public int getUsersRolesIdroles() {
        return usersRolesIdroles;
    }

    public void setUsersRolesIdroles(int usersRolesIdroles) {
        this.usersRolesIdroles = usersRolesIdroles;
    }

    @Id
    @Column(name = "vehicle_idVEHICLE", nullable = false)
    public int getVehicleIdVehicle() {
        return vehicleIdVehicle;
    }

    public void setVehicleIdVehicle(int vehicleIdVehicle) {
        this.vehicleIdVehicle = vehicleIdVehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntity that = (DeviceEntity) o;

        if (idDevice != that.idDevice) return false;
        if (usersIdusers != that.usersIdusers) return false;
        if (usersRolesIdroles != that.usersRolesIdroles) return false;
        if (vehicleIdVehicle != that.vehicleIdVehicle) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (phonenumber != null ? !phonenumber.equals(that.phonenumber) : that.phonenumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDevice;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (phonenumber != null ? phonenumber.hashCode() : 0);
        result = 31 * result + usersIdusers;
        result = 31 * result + usersRolesIdroles;
        result = 31 * result + vehicleIdVehicle;
        return result;
    }
}
