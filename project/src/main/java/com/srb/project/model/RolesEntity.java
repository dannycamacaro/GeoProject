package com.srb.project.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles", schema = "srb")
public class RolesEntity {
    private int idrol;
    private String namerole;
    private String descriptionrole;
    private Collection<UsersEntity> usersByIdrol;

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    @Id
    @Column(name = "IDROL", nullable = false)
    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    @Basic
    @Column(name = "NAMEROLE", nullable = true, length = 45)
    public String getNamerole() {
        return namerole;
    }

    public void setNamerole(String namerole) {
        this.namerole = namerole;
    }

    @Basic
    @Column(name = "DESCRIPTIONROLE", nullable = true, length = 400)
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

    @OneToMany(mappedBy = "rolesByIdrol")
    public Collection<UsersEntity> getUsersByIdrol() {
        return usersByIdrol;
    }

    public void setUsersByIdrol(Collection<UsersEntity> usersByIdrol) {
        this.usersByIdrol = usersByIdrol;
    }
}
