package com.srb.project.model;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "srb", catalog = "")
public class RolesEntity {
    private int idroles;
    private String role;
    private String descriptionRole;

    @Id
    @Column(name = "idroles", nullable = false)
    public int getIdroles() {
        return idroles;
    }

    public void setIdroles(int idroles) {
        this.idroles = idroles;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "DescriptionRole", nullable = true, length = 400)
    public String getDescriptionRole() {
        return descriptionRole;
    }

    public void setDescriptionRole(String descriptionRole) {
        this.descriptionRole = descriptionRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (idroles != that.idroles) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (descriptionRole != null ? !descriptionRole.equals(that.descriptionRole) : that.descriptionRole != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idroles;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (descriptionRole != null ? descriptionRole.hashCode() : 0);
        return result;
    }
}
