/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import ejb.AdminBeanLocal;
import entities.Cities;
import entities.JobsPostings;
import entities.Skills;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author vanshita
 */
@Named(value = "jobsMB")
@ViewScoped
public class JobsMB implements Serializable {

    @EJB AdminBeanLocal adminBean;
    
    JobsPostings j;
    Collection<JobsPostings> jobs;

    Collection<Integer> selectedSkills;
    
    Collection<Cities> cities;
    
    Integer selectedState;
    Integer selectedCity;
    String message;
    
    JobsPostings existJob;
    
    Integer jobsCounts;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    public JobsPostings getExistJob() {
        return existJob;
    }

    public void setExistJob(JobsPostings existJob) {
        this.existJob = existJob;
    }
    
    
    
    public JobsPostings getJ() {
        return j;
    }

    public void setJ(JobsPostings j) {
        this.j = j;
    }

    public Collection<JobsPostings> getJobs() {
        jobs = adminBean.getAllJobsPostings();
        return jobs;
    }

    public void setJobs(Collection<JobsPostings> jobs) {
        this.jobs = jobs;
    }
    
    public Collection<Integer> getSelectedSkills() {
        return selectedSkills;
    }

    public void setSelectedSkills(Collection<Integer> selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

    public Integer getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(Integer selectedState) {
        this.selectedState = selectedState;
    }

    public Integer getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(Integer selectedCity) {
        this.selectedCity = selectedCity;
    }
    
    

    public Collection<Cities> getCities() {
        return cities;
    }

    public void setCities(Collection<Cities> cities) {
        
        this.cities = cities;
    }

    public Integer getJobsCounts() {
        jobsCounts = adminBean.getJobsCounts();
        return jobsCounts;
    }

    public void setJobsCounts(Integer jobsCounts) {
        this.jobsCounts = jobsCounts;
    }
    
    
    
    public void updateCities() {
        
        if(selectedState != null && selectedState != 0){
            cities = adminBean.getAllCitiesByStateName(selectedState);
            if (cities == null || cities.isEmpty()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No cities found for the selected state.", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }else {
            // Clear collection if no state is selected
            cities = null;
        }
                
    }
    
    
    
    public String insertJob(){
        
        try{
            System.out.print("Insert Job");

            if (j != null) {
                adminBean.addJobsPosting(j.getTitle(), j.getJobType(), j.getDescription(), j.getExperience(), j.getMinSalary(), j.getMaxSalary(), selectedState, selectedCity);
            }

            Integer jobId = adminBean.getLastInsertedJobId();
            if (jobId != null && !selectedSkills.isEmpty()) {
                adminBean.addSkillsToJob(jobId, selectedSkills);
            }

            this.j = new JobsPostings();
            this.selectedSkills = null;
            this.selectedState = null;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "jobs.jsf?faces-redirect=true";

    }
    
    public String deleteJob(Integer jobId,Integer stateId,Integer cityId){
        if(jobId != null && stateId != null && cityId != null){
            adminBean.removeJobsPosting(jobId, stateId, cityId);
        } else {
            System.out.print("Delete Job : Error in some value");
        }
        return "jobs.jsf";
    }
   
        
    public void updateJob(JobsPostings job){
        System.out.print("Update job method : " + job.getId() + " " + job.getTitle());

        
//        try{
            Collection<Skills> skills = job.getSkillsCollection();
            Collection<Integer> selSkill = new ArrayList<>();

            for (Skills s : skills) {
                selSkill.add(s.getId());
            }
            selectedSkills = selSkill;
            existJob = job;
            selectedState = job.getStateId().getId();
            updateCities();
            selectedCity = job.getCityId().getId();
            j.setId(job.getId());
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setExperience(job.getExperience());
            j.setJobType(job.getJobType());
            j.setMinSalary(job.getMinSalary());
            j.setMaxSalary(job.getMaxSalary());
            j.setCityId(job.getCityId());

            System.out.print("Exist job : " + existJob.getId() + " " + existJob.getTitle());
            System.out.print("Exist job state : " + selectedState + " " + selectedCity);

            

//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
    
    public String update(){
        System.out.print("job : " + j.getId());
        if(existJob != null){
            System.out.print("ejob : " + existJob.getId());

            existJob.setTitle(j.getTitle());
            existJob.setDescription(j.getDescription());
            existJob.setExperience(j.getExperience());
            existJob.setJobType(j.getJobType());
            existJob.setMinSalary(j.getMinSalary());
            existJob.setMaxSalary(j.getMaxSalary());

            adminBean.updateJobsPosting(existJob.getId(), existJob.getTitle(), existJob.getJobType(), existJob.getDescription(), existJob.getExperience(), existJob.getMinSalary(), existJob.getMaxSalary(), selectedState, selectedCity);
            adminBean.addSkillsToJob(existJob.getId(), selectedSkills);
            
            j = new JobsPostings();
            existJob = null;
            this.selectedSkills = null;
            this.selectedState = null;
 
            
            message = "Job Updated Successfully";
            // Add an error message to the FacesContext (optional)
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "info", message));
            
            // Add a success message
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "*Job updated successfully.", null);
//            FacesContext.getCurrentInstance().addMessage(null, message);
            
        } else {
            System.out.print("exist job null");
        }

        return "jobs.jsf?faces-redirect=true";
    }
    
    
    
    
    
    
    
    
    
    
//    public void updateJob(JobsPostings job){
//        System.out.print("Update job : " + job.getId() + " " + job.getTitle());
//
//        
//        try{
//            Collection<Skills> skills = job.getSkillsCollection();
//            Collection<Integer> selSkill = new ArrayList<>();
//
//            for (Skills s : skills) {
//                selSkill.add(s.getId());
//            }
//            selectedSkills = selSkill;
//            j = job;
//            selectedState = job.getStateId().getId();
//            selectedCity = job.getCityId().getId();
//
//            System.out.print("Update j : " + j.getId() + " " + j.getTitle());
//
//            // Add a success message
//            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Job updated successfully.", null);
//            //FacesContext.getCurrentInstance().addMessage(null, message);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    
//    public void update(){
//        System.out.print("Update j : " + j.getId() + " " + j.getTitle());
//
//        adminBean.updateJobsPosting(j.getId(), j.getTitle(), j.getJobType(), j.getDescription(), j.getExperience(), j.getMinSalary(), j.getMaxSalary(), selectedState, selectedCity);
//        adminBean.addSkillsToJob(j.getId(), selectedSkills);
//
//        this.j = new JobsPostings();
//        this.selectedSkills = null;
//        this.selectedState = null;
//
//        //return "jobs.jsf";
//    }
//    
    /**
     * Creates a new instance of JobsMB
     */
    public JobsMB() {
        
        j = new JobsPostings();
//        jobs = new ArrayList<>();
        
        
    }
    
}
