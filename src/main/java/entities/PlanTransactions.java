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
@Table(name = "plan_transactions")
@NamedQueries({
    @NamedQuery(name = "PlanTransactions.findAll", query = "SELECT p FROM PlanTransactions p"),
    @NamedQuery(name = "PlanTransactions.findById", query = "SELECT p FROM PlanTransactions p WHERE p.id = :id"),
    @NamedQuery(name = "PlanTransactions.findByPaymentMethod", query = "SELECT p FROM PlanTransactions p WHERE p.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "PlanTransactions.findByEmail", query = "SELECT p FROM PlanTransactions p WHERE p.email = :email"),
    @NamedQuery(name = "PlanTransactions.findByContactNo", query = "SELECT p FROM PlanTransactions p WHERE p.contactNo = :contactNo"),
    @NamedQuery(name = "PlanTransactions.findByCardHolderName", query = "SELECT p FROM PlanTransactions p WHERE p.cardHolderName = :cardHolderName"),
    @NamedQuery(name = "PlanTransactions.findByBankTransactionId", query = "SELECT p FROM PlanTransactions p WHERE p.bankTransactionId = :bankTransactionId"),
    @NamedQuery(name = "PlanTransactions.findByUpiTransactionId", query = "SELECT p FROM PlanTransactions p WHERE p.upiTransactionId = :upiTransactionId"),
    @NamedQuery(name = "PlanTransactions.findByAmount", query = "SELECT p FROM PlanTransactions p WHERE p.amount = :amount"),
    @NamedQuery(name = "PlanTransactions.findByErrorReason", query = "SELECT p FROM PlanTransactions p WHERE p.errorReason = :errorReason"),
    @NamedQuery(name = "PlanTransactions.findByStatus", query = "SELECT p FROM PlanTransactions p WHERE p.status = :status")})
public class PlanTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "payment_method")
    private String paymentMethod;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contact_no")
    private String contactNo;
    @Size(max = 255)
    @Column(name = "card_number")
    private String cardNumber;
    @Size(max = 255)
    @Column(name = "card_holder_name")
    private String cardHolderName;
    @Size(max = 255)
    @Column(name = "expiry_date")
    private String expiryDate;
    @Size(max = 255)
    @Column(name = "cvv")
    private String cvv;
    @Size(max = 255)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 255)
    @Column(name = "bank_transaction_id")
    private String bankTransactionId;
    @Size(max = 255)
    @Column(name = "upi_transaction_id")
    private String upiTransactionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "amount")
    private String amount;
    @Size(max = 255)
    @Column(name = "error_reason")
    private String errorReason;
    @Basic(optional = false)
    
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plans planId;
    
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentId")
    private Collection<UserPlans> userPlansCollection;
    public PlanTransactions() {
    }

    public PlanTransactions(Integer id) {
        this.id = id;
    }

    public PlanTransactions(Integer id, String paymentMethod, String email, String contactNo, String amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.email = email;
        this.contactNo = contactNo;
        this.amount = amount;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankTransactionId() {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId) {
        this.bankTransactionId = bankTransactionId;
    }

    public String getUpiTransactionId() {
        return upiTransactionId;
    }

    public void setUpiTransactionId(String upiTransactionId) {
        this.upiTransactionId = upiTransactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
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
        if (!(object instanceof PlanTransactions)) {
            return false;
        }
        PlanTransactions other = (PlanTransactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PlanTransactions[ id=" + id + " ]";
    }
    
}
