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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "jobs_postings")
@NamedQueries({
    @NamedQuery(name = "JobsPostings.findAll", query = "SELECT j FROM JobsPostings j"),
    @NamedQuery(name = "JobsPostings.findById", query = "SELECT j FROM JobsPostings j WHERE j.id = :id"),
    @NamedQuery(name = "JobsPostings.findByTitle", query = "SELECT j FROM JobsPostings j WHERE j.title = :title"),
    @NamedQuery(name = "JobsPostings.findByJobType", query = "SELECT j FROM JobsPostings j WHERE j.jobType = :jobType"),
    @NamedQuery(name = "JobsPostings.findByExperience", query = "SELECT j FROM JobsPostings j WHERE j.experience = :experience"),
    @NamedQuery(name = "JobsPostings.findByMinSalary", query = "SELECT j FROM JobsPostings j WHERE j.minSalary = :minSalary"),
    @NamedQuery(name = "JobsPostings.findByMaxSalary", query = "SELECT j FROM JobsPostings j WHERE j.maxSalary = :maxSalary"),
    @NamedQuery(name = "JobsPostings.findByDeadline", query = "SELECT j FROM JobsPostings j WHERE j.deadline = :deadline"),
    @NamedQuery(name = "JobsPostings.findByStatus", query = "SELECT j FROM JobsPostings j WHERE j.status = :status")})
public class JobsPostings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "job_type")
    private String jobType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "experience")
    private String experience;
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_salary")
    private long minSalary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_salary")
    private long maxSalary;
    @Basic(optional = false)
    
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Basic(optional = false)
    
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @JoinTable(name = "jobs_skills", joinColumns = {
        @JoinColumn(name = "job_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "skill_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Skills> skillsCollection;

    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cities cityId;
    
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private States stateId;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
    
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId")
    private Collection<Candidates> candidatesCollection;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId")
    private Collection<JobTransactions> jobTransactionsCollection;

    public JobsPostings() {
    }

    public JobsPostings(Integer id) {
        this.id = id;
    }

    public JobsPostings(Integer id, String title, String description, String jobType, String experience, long minSalary, long maxSalary, Date deadline, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.jobType = jobType;
        this.experience = experience;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.deadline = deadline;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(long minSalary) {
        this.minSalary = minSalary;
    }

    public long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonbTransient
    public Collection<Skills> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(Collection<Skills> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }

    public Cities getCityId() {
        return cityId;
    }

    public void setCityId(Cities cityId) {
        this.cityId = cityId;
    }

    public States getStateId() {
        return stateId;
    }

    public void setStateId(States stateId) {
        this.stateId = stateId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }
    @JsonbTransient
    public Collection<Candidates> getCandidatesCollection() {
        return candidatesCollection;
    }

    public void setCandidatesCollection(Collection<Candidates> candidatesCollection) {
        this.candidatesCollection = candidatesCollection;
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
        if (!(object instanceof JobsPostings)) {
            return false;
        }
        JobsPostings other = (JobsPostings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.JobsPostings[ id=" + id + " ]";
    }
    
}
