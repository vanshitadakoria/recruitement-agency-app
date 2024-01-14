/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "roles_permissions")
@NamedQueries({
    @NamedQuery(name = "RolesPermissions.findAll", query = "SELECT r FROM RolesPermissions r"),
    @NamedQuery(name = "RolesPermissions.findById", query = "SELECT r FROM RolesPermissions r WHERE r.id = :id"),
    @NamedQuery(name = "RolesPermissions.findByRoleName", query = "SELECT r FROM RolesPermissions r WHERE r.roleName = :roleName"),
    @NamedQuery(name = "RolesPermissions.findByPermission", query = "SELECT r FROM RolesPermissions r WHERE r.permission = :permission"),
    @NamedQuery(name = "RolesPermissions.findByStatus", query = "SELECT r FROM RolesPermissions r WHERE r.status = :status")})
public class RolesPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "permission")
    private Integer permission;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<Settings> settingsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<Users> usersCollection;

    public RolesPermissions() {
    }

    public RolesPermissions(Integer id) {
        this.id = id;
    }

    public RolesPermissions(Integer id, String roleName, String status) {
        this.id = id;
        this.roleName = roleName;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonbTransient
    public Collection<Settings> getSettingsCollection() {
        return settingsCollection;
    }

    public void setSettingsCollection(Collection<Settings> settingsCollection) {
        this.settingsCollection = settingsCollection;
    }

    @JsonbTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPermissions)) {
            return false;
        }
        RolesPermissions other = (RolesPermissions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RolesPermissions[ id=" + id + " ]";
    }
    
}
