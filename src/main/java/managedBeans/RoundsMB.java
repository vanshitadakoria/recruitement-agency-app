/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import entities.Candidates;
import entities.JobStatus;
import entities.Rounds;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author vanshita
 */
@Named(value = "roundsMB")
@ViewScoped
public class RoundsMB implements Serializable {
    
    @EJB AdminBeanLocal adminBean;
    
    
    @EJB
    UserBeanLocal userBean;
    
    Collection<Rounds> rounds;
    Collection<JobStatus> jobStatusOfCandidate;
    
//    private Integer selectedRoundId;
//
//    public Integer getSelectedRoundId() {
//        return selectedRoundId;
//    }
//
//    public void setSelectedRoundId(Integer selectedRoundId) {
//        this.selectedRoundId = selectedRoundId;
//    }

    private Map<Integer, Integer> selectedRoundIds = new HashMap<>();

    public Map<Integer, Integer> getSelectedRoundIds() {
        return selectedRoundIds;
    }

    public void setSelectedRoundIds(Map<Integer, Integer> selectedRoundIds) {
        this.selectedRoundIds = selectedRoundIds;
    }
    
    
    
    

    public Collection<Rounds> getRounds() {
        rounds = adminBean.getAllRounds();
        return rounds;
    }

    public void setRounds(Collection<Rounds> rounds) {
        this.rounds = rounds;
    }

    public Collection<JobStatus> getJobStatusOfCandidate(Integer cid) {
        jobStatusOfCandidate = adminBean.findJobStatusByCandidateId(cid);
        return jobStatusOfCandidate;
    }

//    public void setJobStatusOfCandidate(Collection<JobStatus> jobStatusOfCandidate) {
//        this.jobStatusOfCandidate = jobStatusOfCandidate;
//    }

    
    
    public Integer getHighestRoundNumber(Integer cid){
        Collection<JobStatus> highest = this.getJobStatusOfCandidate(cid);
        Integer rn=0;
        for(JobStatus js : highest){
            rn = js.getRoundId().getId();
        }
        return rn;
    }
    
    public boolean isRoundDisabled(Rounds round,Integer cid){
        return round.getId() <= getHighestRoundNumber(cid);
    }
    
//    public void handleRoundChange(Integer cid) {
//        
//        Long selectedRoundId = selectedRoundIds.get(cid);
//
//        System.out.println("Change method called");
//        System.out.println("cid round chnge : " + cid);
//        System.out.print("roundids : " + selectedRoundIds);  
//        System.out.print("roundid : " + selectedRoundIds.get(cid));  
////        if(selectedRoundId != null){
////            adminBean.addJobStatus("active", null, 2, cid, selectedRoundId.intValue());
////        } else {
////            FacesMessage msg = new FacesMessage("Round Id or Candidate Id found null");
////            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
////            FacesContext.getCurrentInstance().addMessage("roundChange", msg);
////        }
//    }
    
    public String handleRoundChange(Integer cid) {
        
        System.out.println("Change method called");
        System.out.println("Candidate ID: " + cid);
        System.out.println("Selected Round ID: " + selectedRoundIds.get(cid));

        String roundIdString = String.valueOf(selectedRoundIds.get(cid));
        Candidates c = userBean.findCandidateById(cid);
        adminBean.addJobStatus("active", null, c.getUserId().getId(), cid,Integer.parseInt(roundIdString));
        
        Rounds round = adminBean.getRoundById(Integer.parseInt(roundIdString));
        Integer lastInsertedJobStatus = adminBean.getLastInsertedJobStatusId();
        adminBean.addJobTransaction(round.getAmount(), c.getJobId().getId(), lastInsertedJobStatus, c.getUserId().getId(), "active");
        return "candidates.jsf";

    }
    
    

    /**
     * Creates a new instance of RoundsMB
     */
    public RoundsMB() {
    }
    
}
