package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "srb")
@IdClass(UsersEntityPK.class)
public class UsersEntity {
    private int idusers;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private Integer age;
    private String phoneNumber;
    private String email;
    private int rolesIdroles;
    private int assignedvehicleIdAssignedvehicle;

    @Id
    @Column(name = "idusers", nullable = false)
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstName", nullable = true, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "IdentityDocument", nullable = true, length = 45)
    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "phoneNumber", nullable = true, length = 45)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @Column(name = "roles_idroles", nullable = false)
    public int getRolesIdroles() {
        return rolesIdroles;
    }

    public void setRolesIdroles(int rolesIdroles) {
        this.rolesIdroles = rolesIdroles;
    }

    @Id
    @Column(name = "assignedvehicle_idASSIGNEDVEHICLE", nullable = false)
    public int getAssignedvehicleIdAssignedvehicle() {
        return assignedvehicleIdAssignedvehicle;
    }

    public void setAssignedvehicleIdAssignedvehicle(int assignedvehicleIdAssignedvehicle) {
        this.assignedvehicleIdAssignedvehicle = assignedvehicleIdAssignedvehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idusers != that.idusers) return false;
        if (rolesIdroles != that.rolesIdroles) return false;
        if (assignedvehicleIdAssignedvehicle != that.assignedvehicleIdAssignedvehicle) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (identityDocument != null ? !identityDocument.equals(that.identityDocument) : that.identityDocument != null)
            return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idusers;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (identityDocument != null ? identityDocument.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + rolesIdroles;
        result = 31 * result + assignedvehicleIdAssignedvehicle;
        return result;
    }
}
