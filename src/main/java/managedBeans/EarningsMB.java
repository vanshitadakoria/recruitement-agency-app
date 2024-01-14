/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import entities.JobTransactions;
import entities.Users;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vanshita
 */
@Named(value = "earningsMB")
@ViewScoped
public class EarningsMB implements Serializable {
    
    @EJB
    AdminBeanLocal adminBean;
    
    @EJB
    UserBeanLocal userBean;
    
    Collection<JobTransactions> jobTransactions;

    public Collection<JobTransactions> getJobTransactions() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String user_email = request.getSession().getAttribute("user-email").toString();
        Users userByEmail = null;
        if (user_email != null) {

            userByEmail = userBean.findUserByEmail(user_email);
        } else {
            System.out.print("Session email found null");
        }
        jobTransactions = adminBean.getAllJobTransactionsByUserId(userByEmail.getId());
        return jobTransactions;
    }

    public void setJobTransactions(Collection<JobTransactions> jobTransactions) {
        this.jobTransactions = jobTransactions;
    }
    

    /**
     * Creates a new instance of EarningsMB
     */
    public EarningsMB() {
    }
    
}
