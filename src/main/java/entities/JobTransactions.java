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
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "job_transactions")
@NamedQueries({
    @NamedQuery(name = "JobTransactions.findAll", query = "SELECT j FROM JobTransactions j"),
    @NamedQuery(name = "JobTransactions.findById", query = "SELECT j FROM JobTransactions j WHERE j.id = :id"),
    @NamedQuery(name = "JobTransactions.findByAmount", query = "SELECT j FROM JobTransactions j WHERE j.amount = :amount"),
    @NamedQuery(name = "JobTransactions.findByStatus", query = "SELECT j FROM JobTransactions j WHERE j.status = :status")})
public class JobTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    
    @Column(name = "amount")
    private Integer amount;
    @Basic(optional = false)
    
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "job_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JobStatus jobStatusId;
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JobsPostings jobId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public JobTransactions() {
    }

    public JobTransactions(Integer id) {
        this.id = id;
    }

    public JobTransactions(Integer id, Integer amount, String status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JobStatus getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(JobStatus jobStatusId) {
        this.jobStatusId = jobStatusId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobTransactions)) {
            return false;
        }
        JobTransactions other = (JobTransactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.JobTransactions[ id=" + id + " ]";
    }
    
}
