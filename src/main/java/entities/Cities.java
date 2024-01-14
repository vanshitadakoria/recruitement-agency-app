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
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "cities")
@NamedQueries({
    @NamedQuery(name = "Cities.findAll", query = "SELECT c FROM Cities c"),
    @NamedQuery(name = "Cities.findById", query = "SELECT c FROM Cities c WHERE c.id = :id"),
    @NamedQuery(name = "Cities.findByName", query = "SELECT c FROM Cities c WHERE c.name = :name"),
    @NamedQuery(name = "Cities.findByStateId", query = "SELECT c FROM Cities c WHERE c.stateId.id = :stateId")})
public class Cities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private States stateId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityId")
    private Collection<JobsPostings> jobsPostingsCollection;

    public Cities() {
    }

    public Cities(Integer id) {
        this.id = id;
    }

    public Cities(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public States getStateId() {
        return stateId;
    }

    public void setStateId(States stateId) {
        this.stateId = stateId;
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
        if (!(object instanceof Cities)) {
            return false;
        }
        Cities other = (Cities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cities[ id=" + id + " ]";
    }
    
}
