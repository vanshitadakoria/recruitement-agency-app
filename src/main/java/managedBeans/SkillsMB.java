/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import ejb.AdminBeanLocal;
import entities.Skills;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author vanshita
 */
@Named(value = "skillsMB")
@ViewScoped
public class SkillsMB implements Serializable {

    @EJB
    private AdminBeanLocal adminBean;

    private Skills skill = new Skills();
    private Skills selectedSkill;
    
    Collection<Skills> allSkillsOfJob;

    public Collection<Skills> getAllSkillsOfJob(Integer jobid) {
        allSkillsOfJob = adminBean.getAllSkillsOfJob(jobid);
        return allSkillsOfJob;
    }

    public void setAllSkillsOfJob(Collection<Skills> allSkillsOfJob) {
        this.allSkillsOfJob = allSkillsOfJob;
    }
    
    

    public Skills getSkill() {
        return skill;
    }

    public void setSkill(Skills skill) {
        this.skill = skill;
    }

    public Skills getSelectedSkill() {
        return selectedSkill;
    }

    public void setSelectedSkill(Skills selectedSkill) {
        this.selectedSkill = selectedSkill;
    }

    /**
     * Creates a new instance of SkillsMB
     */
    public SkillsMB() {
    }

    public String addSkill(String name) {
//        String page = "skills.jsf";
        if (!adminBean.findSkillByName(name)) {
            //For adding message from cdi to jsf page
            FacesMessage skillExistError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Skill Already Exists", null);
            FacesContext.getCurrentInstance().addMessage(null, skillExistError);
        } else {
//            skill.setStatus("active");
            adminBean.addSkill(skill.getName(), skill.getDescription(), "active");
            
            this.skill = new Skills();
            
        }
        return "skills.jsf?faces-redirect=true";
    }

    public Collection<Skills> getAllSkills() {
        Collection<Skills> skills = adminBean.getAllSkills();
        return skills;
    }

//    public String updateSkill(Skills s) {
//        selectedSkill = s;
//        skill.setName(s.getName());
//        skill.setDescription(s.getDescription());
//        this.skill = this.adminBean.findSkillById(s.getId());
//        System.out.print(this.skill);
//        return "update_skill";
//    }
    public void updateSkill(Skills s) {
        selectedSkill = s;
        skill.setName(s.getName());
        skill.setDescription(s.getDescription());
    }

    public String update() {
        if (selectedSkill != null) {
            // Update the selected skill with the modified values
            selectedSkill.setName(skill.getName());
            selectedSkill.setDescription(skill.getDescription());
            selectedSkill.setStatus("active");

            adminBean.updateSkill(selectedSkill.getId(), selectedSkill.getName(), selectedSkill.getDescription(), selectedSkill.getStatus());
            
            // Reset the input fields
            skill = new Skills();
            selectedSkill = null;
        }
        return "skills.jsf?faces-redirect=true";
    }
//    public void update() {
//        System.out.print(this.skill);
////        String page = "update_skill";
////        if(skill != null){
////            adminBean.updateSkill(skill.getId(), skill);
////        this.skill = skill;
////            page =  "skills?faces-redirect=true";
////        }
////        return page;
//    }
    
    public String deleteSkill(Skills s){
        
        adminBean.removeSkill(s.getId());
        return "skills.jsf?faces-redirect=true";
    }
}
