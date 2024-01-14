/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entities.JobTransactions;
import entities.Users;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vanshita
 */
@Stateless
public class CountBean {

    @PersistenceContext(unitName = "recruitment_pu")
    private EntityManager em;
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Integer totalCandidates(){
        Integer tc = em.createNamedQuery("Candidates.findAll").getResultList().size();
        return tc;
    }
    
    public Integer totalUsers(){
        Integer tu = em.createNamedQuery("Users.findAll").getResultList().size();
        return tu;
    }
    
    public Integer totalJobs() {
        Integer tj = em.createNamedQuery("JobsPostings.findAll").getResultList().size();
        return tj;
    }
    
    public Integer totalSkills() {
        Integer ts = em.createNamedQuery("Skills.findAll").getResultList().size();
        return ts;
    }
    
    public Integer totalEarnings(Integer user_id) {
        Users u = em.find(Users.class, user_id);
        Collection<JobTransactions> trans = u.getJobTransactionsCollection();
        Integer te = 0;
        for(JobTransactions jt : trans ){
            te += jt.getAmount();
        }
        return te;
    }
    
    

}
