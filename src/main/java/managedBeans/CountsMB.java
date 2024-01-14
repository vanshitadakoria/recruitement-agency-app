/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import ejb.CountBean;
import ejb.UserBeanLocal;
import entities.Users;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author vanshita
 */
@Named(value = "countsMB")
@ViewScoped
public class CountsMB implements Serializable {
    
    @EJB 
    CountBean countBean;
    
    @EJB
    UserBeanLocal userBean;

    Integer jobsCount;
    Integer skillsCount;
    Integer usersCount;
    Integer earings;

    public Integer getJobsCount() {
        jobsCount = countBean.totalJobs();
        return jobsCount;
    }

    public void setJobsCount(Integer jobsCount) {
        this.jobsCount = jobsCount;
    }

    public Integer getSkillsCount() {
        skillsCount = countBean.totalSkills();
        return skillsCount;
    }

    public void setSkillsCount(Integer skillsCount) {
        this.skillsCount = skillsCount;
    }

    public Integer getUsersCount() {
        usersCount = countBean.totalUsers();
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Integer getEarings() {
        return earings;
    }

    public void setEarings(Integer earings) {
        this.earings = earings;
    }
    
    public Integer getTotalEarnings(String email){
        Users u = userBean.findUserByEmail(email);
        earings = countBean.totalEarnings(u.getId());
        return earings;
    }
    
    
    
    
    
    /**
     * Creates a new instance of CountsMB
     */
    public CountsMB() {
    }
    
}
