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
@Table(name = "plans")
@NamedQueries({
    @NamedQuery(name = "Plans.findAll", query = "SELECT p FROM Plans p"),
    @NamedQuery(name = "Plans.findById", query = "SELECT p FROM Plans p WHERE p.id = :id"),
    @NamedQuery(name = "Plans.findByPlanName", query = "SELECT p FROM Plans p WHERE p.planName = :planName"),
    @NamedQuery(name = "Plans.findByAmount", query = "SELECT p FROM Plans p WHERE p.amount = :amount"),
    @NamedQuery(name = "Plans.findByDuration", query = "SELECT p FROM Plans p WHERE p.duration = :duration"),
    @NamedQuery(name = "Plans.findByOfferAmt", query = "SELECT p FROM Plans p WHERE p.offerAmt = :offerAmt"),
    @NamedQuery(name = "Plans.findByIsPopular", query = "SELECT p FROM Plans p WHERE p.isPopular = :isPopular"),
    @NamedQuery(name = "Plans.findByIsBestseller", query = "SELECT p FROM Plans p WHERE p.isBestseller = :isBestseller"),
    @NamedQuery(name = "Plans.findByStatus", query = "SELECT p FROM Plans p WHERE p.status = :status")})
public class Plans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "plan_name")
    private String planName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private String duration;
    @Basic(optional = false)
    
    @Column(name = "offer_amt")
    private int offerAmt;
    @Basic(optional = false)
    
    @Column(name = "is_popular")
    private boolean isPopular;
    @Basic(optional = false)
    
    @Column(name = "is_bestseller")
    private boolean isBestseller;
    @Basic(optional = false)
    
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planId")
    private Collection<PlanTransactions> planTransactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planId")
    private Collection<UserPlans> userPlansCollection;

    public Plans() {
    }

    public Plans(Integer id) {
        this.id = id;
    }

    public Plans(Integer id, String planName, int amount, String duration, int offerAmt, boolean isPopular, boolean isBestseller, String status) {
        this.id = id;
        this.planName = planName;
        this.amount = amount;
        this.duration = duration;
        this.offerAmt = offerAmt;
        this.isPopular = isPopular;
        this.isBestseller = isBestseller;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getOfferAmt() {
        return offerAmt;
    }

    public void setOfferAmt(int offerAmt) {
        this.offerAmt = offerAmt;
    }

    public boolean getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(boolean isPopular) {
        this.isPopular = isPopular;
    }

    public boolean getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(boolean isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonbTransient
    public Collection<PlanTransactions> getPlanTransactionsCollection() {
        return planTransactionsCollection;
    }

    public void setPlanTransactionsCollection(Collection<PlanTransactions> planTransactionsCollection) {
        this.planTransactionsCollection = planTransactionsCollection;
    }
    @JsonbTransient
    public Collection<UserPlans> getUserPlansCollection() {
        return userPlansCollection;
    }

    public void setUserPlansCollection(Collection<UserPlans> userPlansCollection) {
        this.userPlansCollection = userPlansCollection;
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
        if (!(object instanceof Plans)) {
            return false;
        }
        Plans other = (Plans) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Plans[ id=" + id + " ]";
    }
    
}
