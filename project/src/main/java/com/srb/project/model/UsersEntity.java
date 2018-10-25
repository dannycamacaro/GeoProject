package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "srb")
public class UsersEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSERS", nullable = false)
    private int idusers;

    @Basic
    @Column(name = "USERNAME", nullable = true, length = 100)
    private String username;

    @Basic
    @Column(name = "FIRSTNAME", nullable = true, length = 100)
    private String firstname;

    @Basic
    @Column(name = "LASTNAME", nullable = true, length = 45)
    private String lastname;

    @Basic
    @Column(name = "IDENTITYDOCUMENT", nullable = true, length = 45)
    private String identitydocument;

    @Basic
    @Column(name = "AGE", nullable = true)
    private Integer age;

    @Basic
    @Column(name = "PHONENUMBER", nullable = true, length = 45)
    private String phonenumber;

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 200)
    private String email;

    @Basic
    @Column(name = "IDROL", nullable = false)
    private int idrol;
    @OneToMany(mappedBy = "usersByIdusers")
    private Collection<AssignedvehicleEntity> assignedvehiclesByIdusers;
    @OneToMany(mappedBy = "usersByIdusers")
    private Collection<AuditsEntity> auditsByIdusers;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL",insertable = false, updatable = false, nullable = false)
    private RolesEntity rolesByIdrol;

    @Basic
    @Column(name = "STATEDELETE")
    private Byte statedelete;

    @Basic
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "usersByIdusers")
    private Collection<AuditsEntity> auditssByIdusers;

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getIdentitydocument() {
        return identitydocument;
    }

    public void setIdentitydocument(String identitydocument) {
        this.identitydocument = identitydocument;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idusers != that.idusers) return false;
        if (idrol != that.idrol) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
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
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (identitydocument != null ? identitydocument.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (phonenumber != null ? phonenumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + idrol;

        return result;
    }


    public Collection<AssignedvehicleEntity> getAssignedvehiclesByIdusers() {
        return assignedvehiclesByIdusers;
    }

    public void setAssignedvehiclesByIdusers(Collection<AssignedvehicleEntity> assignedvehiclesByIdusers) {
        this.assignedvehiclesByIdusers = assignedvehiclesByIdusers;
    }


    public Collection<AuditsEntity> getAuditsByIdusers() {
        return auditsByIdusers;
    }

    public void setAuditsByIdusers(Collection<AuditsEntity> auditsByIdusers) {
        this.auditsByIdusers = auditsByIdusers;
    }


    public RolesEntity getRolesByIdrol() {
        return rolesByIdrol;
    }

    public void setRolesByIdrol(RolesEntity rolesByIdrol) {
        this.rolesByIdrol = rolesByIdrol;
    }


    public Byte getStatedelete() {
        return statedelete;
    }

    public void setStatedelete(Byte statedelete) {
        this.statedelete = statedelete;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Collection<AuditsEntity> getAuditssByIdusers() {
        return auditssByIdusers;
    }

    public void setAuditssByIdusers(Collection<AuditsEntity> auditssByIdusers) {
        this.auditssByIdusers = auditssByIdusers;
    }
}
