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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "candidates")
@NamedQueries({
    @NamedQuery(name = "Candidates.findAll", query = "SELECT c FROM Candidates c"),
    @NamedQuery(name = "Candidates.findById", query = "SELECT c FROM Candidates c WHERE c.id = :id"),
    @NamedQuery(name = "Candidates.findByFirstname", query = "SELECT c FROM Candidates c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Candidates.findByLastname", query = "SELECT c FROM Candidates c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Candidates.findByEmail", query = "SELECT c FROM Candidates c WHERE c.email = :email"),
    @NamedQuery(name = "Candidates.findByPhoneNo", query = "SELECT c FROM Candidates c WHERE c.phoneNo = :phoneNo"),
    @NamedQuery(name = "Candidates.findByResumeFile", query = "SELECT c FROM Candidates c WHERE c.resumeFile = :resumeFile"),
    @NamedQuery(name = "Candidates.findByStatus", query = "SELECT c FROM Candidates c WHERE c.status = :status")})
public class Candidates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 10,message= "Phone No must be of 10 digits")
    @Column(name = "phone_no")
    private String phoneNo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "resume_file")
    private String resumeFile;
    
    
    @Basic(optional = false)
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    
    
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JobsPostings jobId;
    
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateId")
    private Collection<JobStatus> jobStatusCollection;

    public Candidates() {
    }

    public Candidates(Integer id) {
        this.id = id;
    }

    public Candidates(Integer id, String firstname, String lastname, String email, String phoneNo, String resumeFile, String status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.resumeFile = resumeFile;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JobsPostings getJobId() {
        return jobId;
    }

    public void setJobId(JobsPostings jobId) {
        this.jobId = jobId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }
    @JsonbTransient
    public Collection<JobStatus> getJobStatusCollection() {
        return jobStatusCollection;
    }

    public void setJobStatusCollection(Collection<JobStatus> jobStatusCollection) {
        this.jobStatusCollection = jobStatusCollection;
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
        if (!(object instanceof Candidates)) {
            return false;
        }
        Candidates other = (Candidates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Candidates[ id=" + id + " ]";
    }
    
}
