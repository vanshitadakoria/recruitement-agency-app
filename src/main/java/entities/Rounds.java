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
@Table(name = "rounds")
@NamedQueries({
    @NamedQuery(name = "Rounds.findAll", query = "SELECT r FROM Rounds r"),
    @NamedQuery(name = "Rounds.findById", query = "SELECT r FROM Rounds r WHERE r.id = :id"),
    @NamedQuery(name = "Rounds.findByName", query = "SELECT r FROM Rounds r WHERE r.name = :name"),
    @NamedQuery(name = "Rounds.findByAmount", query = "SELECT r FROM Rounds r WHERE r.amount = :amount"),
    @NamedQuery(name = "Rounds.findByStatus", query = "SELECT r FROM Rounds r WHERE r.status = :status")})
public class Rounds implements Serializable {

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
    
    @Column(name = "amount")
    private Integer amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roundId")
    private Collection<JobStatus> jobStatusCollection;

    public Rounds() {
    }

    public Rounds(Integer id) {
        this.id = id;
    }

    public Rounds(Integer id, String name, Integer amount, String status) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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
        if (!(object instanceof Rounds)) {
            return false;
        }
        Rounds other = (Rounds) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rounds[ id=" + id + " ]";
    }
    
}
