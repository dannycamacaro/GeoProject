package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles", schema = "srb")
public class RolesEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "IDROL", nullable = false)
    private int idrol;

    @Basic
    @Column(name = "NAMEROLE", nullable = true, length = 45)
    private String namerole;
    @Basic
    @Column(name = "DESCRIPTIONROLE", nullable = true, length = 400)
    private String descriptionrole;
    @OneToMany(mappedBy = "rolesByIdrol")
    private Collection<UsersEntity> usersByIdrol;

    @Basic
    @Column(name = "STATEDELETE")
    private Byte statedelete;
    @OneToMany(mappedBy = "rolesByIdrol")
    private Collection<UsersEntity> userssByIdrol;

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }


    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }


    public String getNamerole() {
        return namerole;
    }

    public void setNamerole(String namerole) {
        this.namerole = namerole;
    }


    public String getDescriptionrole() {
        return descriptionrole;
    }

    public void setDescriptionrole(String descriptionrole) {
        this.descriptionrole = descriptionrole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (idrol != that.idrol) return false;
        if (namerole != null ? !namerole.equals(that.namerole) : that.namerole != null) return false;
        if (descriptionrole != null ? !descriptionrole.equals(that.descriptionrole) : that.descriptionrole != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrol;
        result = 31 * result + (namerole != null ? namerole.hashCode() : 0);
        result = 31 * result + (descriptionrole != null ? descriptionrole.hashCode() : 0);
        return result;
    }


    public Collection<UsersEntity> getUsersByIdrol() {
        return usersByIdrol;
    }

    public void setUsersByIdrol(Collection<UsersEntity> usersByIdrol) {
        this.usersByIdrol = usersByIdrol;
    }


    public Byte getStatedelete() {
        return statedelete;
    }

    public void setStatedelete(Byte statedelete) {
        this.statedelete = statedelete;
    }


    public Collection<UsersEntity> getUserssByIdrol() {
        return userssByIdrol;
    }

    public void setUserssByIdrol(Collection<UsersEntity> userssByIdrol) {
        this.userssByIdrol = userssByIdrol;
    }
}
