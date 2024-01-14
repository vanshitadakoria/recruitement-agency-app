/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vanshita
 */
@Entity
@Table(name = "user_plans")
@NamedQueries({
    @NamedQuery(name = "UserPlans.findAll", query = "SELECT u FROM UserPlans u"),
    @NamedQuery(name = "UserPlans.findById", query = "SELECT u FROM UserPlans u WHERE u.id = :id"),
    @NamedQuery(name = "UserPlans.findByPrice", query = "SELECT u FROM UserPlans u WHERE u.price = :price"),
    @NamedQuery(name = "UserPlans.findByStartDate", query = "SELECT u FROM UserPlans u WHERE u.startDate = :startDate"),
    @NamedQuery(name = "UserPlans.findByEndDate", query = "SELECT u FROM UserPlans u WHERE u.endDate = :endDate"),
    @NamedQuery(name = "UserPlans.findByPaymentId", query = "SELECT u FROM UserPlans u WHERE u.paymentId = :paymentId"),
    @NamedQuery(name = "UserPlans.findByStatus", query = "SELECT u FROM UserPlans u WHERE u.status = :status")})
public class UserPlans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Basic(optional = false)
    
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plans planId;
        
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
    
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PlanTransactions paymentId;

    public UserPlans() {
    }

    public UserPlans(Integer id) {
        this.id = id;
    }

    public UserPlans(Integer id, int price, Date startDate, Date endDate, String status) {
        this.id = id;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Plans getPlanId() {
        return planId;
    }

    public void setPlanId(Plans planId) {
        this.planId = planId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public PlanTransactions getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(PlanTransactions paymentId) {
        this.paymentId = paymentId;
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
        if (!(object instanceof UserPlans)) {
            return false;
        }
        UserPlans other = (UserPlans) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserPlans[ id=" + id + " ]";
    }
    
}
