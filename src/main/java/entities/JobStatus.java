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
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "job_status")
@NamedQueries({
    @NamedQuery(name = "JobStatus.findAll", query = "SELECT j FROM JobStatus j"),
    @NamedQuery(name = "JobStatus.findById", query = "SELECT j FROM JobStatus j WHERE j.id = :id"),
    @NamedQuery(name = "JobStatus.findByStatus", query = "SELECT j FROM JobStatus j WHERE j.status = :status"),
    @NamedQuery(name = "JobStatus.findByRejectReason", query = "SELECT j FROM JobStatus j WHERE j.rejectReason = :rejectReason")})
public class JobStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)    
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
        @Size(min = 1, max = 255)
    @Column(name = "reject_reason")
    private String rejectReason;
    
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidates candidateId;
    
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rounds roundId;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobStatusId")
    private Collection<JobTransactions> jobTransactionsCollection;

    public JobStatus() {
    }

    public JobStatus(Integer id) {
        this.id = id;
    }

    public JobStatus(Integer id, String status, String rejectReason) {
        this.id = id;
        this.status = status;
        this.rejectReason = rejectReason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Candidates getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidates candidateId) {
        this.candidateId = candidateId;
    }

    public Rounds getRoundId() {
        return roundId;
    }

    public void setRoundId(Rounds roundId) {
        this.roundId = roundId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof JobStatus)) {
            return false;
        }
        JobStatus other = (JobStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.JobStatus[ id=" + id + " ]";
    }
    
}
