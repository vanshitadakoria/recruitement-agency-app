/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "settings")
@NamedQueries({
    @NamedQuery(name = "Settings.findAll", query = "SELECT s FROM Settings s"),
    @NamedQuery(name = "Settings.findById", query = "SELECT s FROM Settings s WHERE s.id = :id"),
    @NamedQuery(name = "Settings.findByNoOfResumeSubmit", query = "SELECT s FROM Settings s WHERE s.noOfResumeSubmit = :noOfResumeSubmit"),
    @NamedQuery(name = "Settings.findByNoOfJobAssign", query = "SELECT s FROM Settings s WHERE s.noOfJobAssign = :noOfJobAssign"),
    @NamedQuery(name = "Settings.findByAmtToPay", query = "SELECT s FROM Settings s WHERE s.amtToPay = :amtToPay")})
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "no_of_resume_submit")
    private int noOfResumeSubmit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "no_of_job_assign")
    private int noOfJobAssign;
    @Basic(optional = false)
    
    @Column(name = "amt_to_pay")
    private int amtToPay;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RolesPermissions roleId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public Settings() {
    }

    public Settings(Integer id) {
        this.id = id;
    }

    public Settings(Integer id, int noOfResumeSubmit, int noOfJobAssign, int amtToPay) {
        this.id = id;
        this.noOfResumeSubmit = noOfResumeSubmit;
        this.noOfJobAssign = noOfJobAssign;
        this.amtToPay = amtToPay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNoOfResumeSubmit() {
        return noOfResumeSubmit;
    }

    public void setNoOfResumeSubmit(int noOfResumeSubmit) {
        this.noOfResumeSubmit = noOfResumeSubmit;
    }

    public int getNoOfJobAssign() {
        return noOfJobAssign;
    }

    public void setNoOfJobAssign(int noOfJobAssign) {
        this.noOfJobAssign = noOfJobAssign;
    }

    public int getAmtToPay() {
        return amtToPay;
    }

    public void setAmtToPay(int amtToPay) {
        this.amtToPay = amtToPay;
    }

    public RolesPermissions getRoleId() {
        return roleId;
    }

    public void setRoleId(RolesPermissions roleId) {
        this.roleId = roleId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Settings)) {
            return false;
        }
        Settings other = (Settings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Settings[ id=" + id + " ]";
    }
    
}
