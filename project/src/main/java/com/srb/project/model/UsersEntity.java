package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "srb")
public class UsersEntity {
    private int idusers;
    private String username;
    private String password2;
    private String firstname;
    private String lastname;
    private String identitydocument;
    private Integer age;
    private String phonenumber;
    private String email;
    private int idrol;
    private int idassignedvehicle;
    private Collection<AssignedvehicleEntity> assignedvehiclesByIdusers;
    private Collection<AuditsEntity> auditsByIdusers;
    private RolesEntity rolesByIdrol;

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public void setIdassignedvehicle(Integer idassignedvehicle) {
        this.idassignedvehicle = idassignedvehicle;
    }

    @Id
    @Column(name = "IDUSERS", nullable = false)
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Basic
    @Column(name = "USERNAME", nullable = true, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD2", nullable = true, length = 45)
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = true, length = 100)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, length = 45)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "IDENTITYDOCUMENT", nullable = true, length = 45)
    public String getIdentitydocument() {
        return identitydocument;
    }

    public void setIdentitydocument(String identitydocument) {
        this.identitydocument = identitydocument;
    }

    @Basic
    @Column(name = "AGE", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "PHONENUMBER", nullable = true, length = 45)
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "IDROL", nullable = false)
    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    @Basic
    @Column(name = "IDASSIGNEDVEHICLE", nullable = false)
    public int getIdassignedvehicle() {
        return idassignedvehicle;
    }

    public void setIdassignedvehicle(int idassignedvehicle) {
        this.idassignedvehicle = idassignedvehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idusers != that.idusers) return false;
        if (idrol != that.idrol) return false;
        if (idassignedvehicle != that.idassignedvehicle) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password2 != null ? !password2.equals(that.password2) : that.password2 != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (identitydocument != null ? !identitydocument.equals(that.identitydocument) : that.identitydocument != null)
            return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (phonenumber != null ? !phonenumber.equals(that.phonenumber) : that.phonenumber != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idusers;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password2 != null ? password2.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (identitydocument != null ? identitydocument.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (phonenumber != null ? phonenumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + idrol;
        result = 31 * result + idassignedvehicle;
        return result;
    }

    @OneToMany(mappedBy = "usersByIdusers")
    public Collection<AssignedvehicleEntity> getAssignedvehiclesByIdusers() {
        return assignedvehiclesByIdusers;
    }

    public void setAssignedvehiclesByIdusers(Collection<AssignedvehicleEntity> assignedvehiclesByIdusers) {
        this.assignedvehiclesByIdusers = assignedvehiclesByIdusers;
    }

    @OneToMany(mappedBy = "usersByIdusers")
    public Collection<AuditsEntity> getAuditsByIdusers() {
        return auditsByIdusers;
    }

    public void setAuditsByIdusers(Collection<AuditsEntity> auditsByIdusers) {
        this.auditsByIdusers = auditsByIdusers;
    }

    @ManyToOne
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL",insertable = false, updatable = false, nullable = false)
    public RolesEntity getRolesByIdrol() {
        return rolesByIdrol;
    }

    public void setRolesByIdrol(RolesEntity rolesByIdrol) {
        this.rolesByIdrol = rolesByIdrol;
    }
}
