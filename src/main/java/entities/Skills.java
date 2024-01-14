/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "skills")
@NamedQueries({
    @NamedQuery(name = "Skills.findAll", query = "SELECT s FROM Skills s"),
    @NamedQuery(name = "Skills.findById", query = "SELECT s FROM Skills s WHERE s.id = :id"),
    @NamedQuery(name = "Skills.findByName", query = "SELECT s FROM Skills s WHERE s.name = :name"),
    @NamedQuery(name = "Skills.findByStatus", query = "SELECT s FROM Skills s WHERE s.status = :status")})
public class Skills implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @ManyToMany(mappedBy = "skillsCollection")
    private Collection<JobsPostings> jobsPostingsCollection;

    public Skills() {
    }

    public Skills(Integer id) {
        this.id = id;
    }

    public Skills(Integer id, String name, String description, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonbTransient
    public Collection<JobsPostings> getJobsPostingsCollection() {
        return jobsPostingsCollection;
    }

    public void setJobsPostingsCollection(Collection<JobsPostings> jobsPostingsCollection) {
        this.jobsPostingsCollection = jobsPostingsCollection;
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
        if (!(object instanceof Skills)) {
            return false;
        }
        Skills other = (Skills) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Skills[ id=" + id + " ]";
    }
    
}
