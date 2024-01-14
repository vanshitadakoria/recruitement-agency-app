/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhoneNo", query = "SELECT u FROM Users u WHERE u.phoneNo = :phoneNo"),
    @NamedQuery(name = "Users.findByDob", query = "SELECT u FROM Users u WHERE u.dob = :dob"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByVpaAddress", query = "SELECT u FROM Users u WHERE u.vpaAddress = :vpaAddress"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 10,message="Phone No must be of 10 digits")
    @Column(name = "phone_no")
    private String phoneNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "vpa_address")
    private String vpaAddress;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Settings> settingsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<JobsPostings> jobsPostingsCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    
    @ManyToOne(optional = false)
    private RolesPermissions roleId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Candidates> candidatesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<JobStatus> jobStatusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserPlans> userPlansCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<JobTransactions> jobTransactionsCollection;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String name, String email, String phoneNo, Date dob, String password, String vpaAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.password = password;
        this.vpaAddress = vpaAddress;
    }

    public Users(String name, String email, String phoneNo, Date dob, String password, String vpaAddress, String status) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.password = password;
        this.vpaAddress = vpaAddress;
        this.status = status;
    }
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVpaAddress() {
        return vpaAddress;
    }

    public void setVpaAddress(String vpaAddress) {
        this.vpaAddress = vpaAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @JsonbTransient
    public Collection<Settings> getSettingsCollection() {
        return settingsCollection;
    }

    public void setSettingsCollection(Collection<Settings> settingsCollection) {
        this.settingsCollection = settingsCollection;
    }

    @JsonbTransient
    public Collection<JobsPostings> getJobsPostingsCollection() {
        return jobsPostingsCollection;
    }

    public void setJobsPostingsCollection(Collection<JobsPostings> jobsPostingsCollection) {
        this.jobsPostingsCollection = jobsPostingsCollection;
    }

    public RolesPermissions getRoleId() {
        return roleId;
    }

    public void setRoleId(RolesPermissions roleId) {
        this.roleId = roleId;
    }

    @JsonbTransient
    public Collection<Candidates> getCandidatesCollection() {
        return candidatesCollection;
    }

    public void setCandidatesCollection(Collection<Candidates> candidatesCollection) {
        this.candidatesCollection = candidatesCollection;
    }

    @JsonbTransient
    public Collection<JobStatus> getJobStatusCollection() {
        return jobStatusCollection;
    }

    public void setJobStatusCollection(Collection<JobStatus> jobStatusCollection) {
        this.jobStatusCollection = jobStatusCollection;
    }

    @JsonbTransient
    public Collection<UserPlans> getUserPlansCollection() {
        return userPlansCollection;
    }

    public void setUserPlansCollection(Collection<UserPlans> userPlansCollection) {
        this.userPlansCollection = userPlansCollection;
    }

    @JsonbTransient
    public Collection<JobTransactions> getJobTransactionsCollection() {
        return jobTransactionsCollection;
    }

    public void setJobTransactionsCollection(Collection<JobTransactions> jobTransactionsCollection) {
        this.jobTransactionsCollection = jobTransactionsCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ id=" + id + " ]";
    }
    
}
