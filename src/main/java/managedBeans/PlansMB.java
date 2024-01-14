/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import client.RestClient;
import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import entities.PlanTransactions;
import entities.Plans;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Response;

/**
 *
 * @author vanshita
 */
@Named(value = "plansMB")
@ViewScoped
public class PlansMB implements Serializable {

    RestClient cl;
    Response res;

    @EJB
    AdminBeanLocal adminBean;
    @EJB
    UserBeanLocal userBean;

    PlanTransactions pt;

    Integer planid;
    Integer planAmt;
    
    String selMode;
    Collection<PlanTransactions> ptrans;
    
    

    String message;

    public String getSelMode() {
        return selMode;
    }

    public void setSelMode(String selMode) {
        this.selMode = selMode;
    }

    public Collection<PlanTransactions> getPtrans() {
        return ptrans;
    }

    public void setPtrans(Collection<PlanTransactions> ptrans) {
        this.ptrans = ptrans;
    }

    
    
    
    
    public PlanTransactions getPt() {
        return pt;
    }

    public void setPt(PlanTransactions pt) {
        this.pt = pt;
    }

    public Integer getPlanid() {
        return planid;
    }

    public void setPlanid(Integer planid) {
        this.planid = planid;
    }

    public Integer getPlanAmt() {
        return planAmt;
    }

    public void setPlanAmt(Integer planAmt) {
        this.planAmt = planAmt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String navigateToPaymentPage(Integer pid, Integer amt) {
        System.out.print("pid : " + pid);

        return "payment_page.jsf?faces-redirect=true&pid=" + pid + "&amt=" + amt;
    }

    public String addPlanTransactions() {
        System.out.println("CDI Add Plan Transactions");
        System.out.println("payment_method : " + pt.getPaymentMethod());
        System.out.println("em : " + pt.getEmail());
        System.out.println("cn : " + pt.getContactNo());
        System.out.println("cardno : " + pt.getCardNumber());
        System.out.println("cardname : " + pt.getCardHolderName());
        System.out.println("exdate : " + pt.getExpiryDate());
        System.out.println("cvv : " + pt.getCvv());
        System.out.println("bt : " + pt.getBankTransactionId());
        System.out.println("ut : " + pt.getUpiTransactionId());
        System.out.println("amt : " + planAmt);
        System.out.println("err : " + pt.getErrorReason());
        System.out.println("pid : " + planid);

        if (pt.getPaymentMethod().equals("card")) {
            adminBean.addPlanTransactions(pt.getPaymentMethod(), pt.getEmail(), pt.getContactNo(), pt.getCardNumber(), pt.getCardHolderName(), pt.getExpiryDate(), pt.getCvv(), null, null, null, planAmt.toString(), "no", planid);
        } else if (pt.getPaymentMethod().equals("upi")) {
            cl.addPlanTransactions(pt.getPaymentMethod(), pt.getEmail(), pt.getContactNo(), null, null, null, null, null, null, pt.getUpiTransactionId(), planAmt.toString(), "no", planid.toString());
        } else if (pt.getPaymentMethod().equals("bank")) {
            cl.addPlanTransactions(pt.getPaymentMethod(), pt.getEmail(), pt.getContactNo(), null, null, null, null, pt.getBankName(), pt.getBankTransactionId(), null, planAmt.toString(), "no", planid.toString());
        }

        Integer userId = userBean.getLastInsertedUserId();
        Integer ptID = adminBean.getLastInsertedPTId();
        Plans plan = adminBean.findPlanById(planid);

        try {
            LocalDate currentDate = LocalDate.now();

            //Getting days corresponds to plan
            Long days = Long.valueOf(plan.getDuration());

            LocalDate endDate = currentDate.plusDays(days);

            // Format dates
            Date start_date = java.sql.Date.valueOf(currentDate);
            Date end_date = java.sql.Date.valueOf(endDate);

            if (userId != null) {
                adminBean.addUserPlans(planAmt, start_date, end_date, ptID, userId, planid);
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "Plan Transaction failed due to some reason.Try again.";
            // Add an error message to the FacesContext (optional)
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));

        }
        pt = new PlanTransactions();
        planAmt = null;
        planid = null;

        System.out.print("Added");
        return "login.jsf?faces-redirect=true";
    }
    
//    
//    public Collection<PlanTransactions> getAllPlanTransactions(){
//        ptrans = adminBean.getAllPlanTransactionsOfBank(selMode);
//        return ptrans;
//    }
//    
    
    public Collection<PlanTransactions> getAllPlanTransactions() {
        return adminBean.getAllPlanTransactions();
    }
    /**
     * Creates a new instance of PlansMB
     */
    public PlansMB() {
        cl = new RestClient();
        pt = new PlanTransactions();
    }

}
