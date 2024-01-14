/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entities.Cities;
import entities.JobStatus;
import entities.JobTransactions;
import entities.JobsPostings;
import entities.PlanTransactions;
import entities.Plans;
import entities.Rounds;
import entities.Settings;
import entities.Skills;
import entities.States;
import entities.UserPlans;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author vanshita
 */
@Local
public interface AdminBeanLocal {

    
    
    //==========Skills=========
    //sid
    void addSkill(String name, String description, String status);

    void updateSkill(Integer sid, String name, String description, String status);

    void removeSkill(Integer sid);

    boolean findSkillByName(String name);

    Skills findSkillById(Integer id);

    Collection<Skills> getAllSkills();

    //============JobsPostings(Jobs)==========
    //jid
    Integer getJobsCounts();
    Integer getLastInsertedJobId();
    //void addJobsPosting(String title, String job_type, String description, String experience, long min_salary, long max_salary, Date deadline, Integer stateid, Integer cityid, Integer uid, String status);
    void addJobsPosting(String title, String job_type, String description, String experience, long min_salary, long max_salary,Integer stateid, Integer cityid);

    //void updateJobsPosting(Integer jid, String title, String job_type, String description, String experience, long min_salary, long max_salary, Date deadline, Integer stateid, Integer cityid, Integer uid, String status);
    public void updateJobsPosting(Integer jid, String title, String job_type, String description, String experience, long min_salary, long max_salary, Integer stateid, Integer cityid);

    //void removeJobsPosting(Integer jid, Integer stateid, Integer cityid, Integer uid);
    void removeJobsPosting(Integer jid, Integer stateid, Integer cityid);

    Collection<JobsPostings> getAllJobsPostings();

    Collection<JobsPostings> getAllJobsPostingsByState(Integer stateid);

    Collection<JobsPostings> getAllJobsPostingsByCity(Integer cityid);

    JobsPostings getJobsPostingById(Integer jid);     //For single Job

    Collection<Skills> getAllSkillsOfJob(Integer jid);

    //===========JobSkills(JoinTable)==========
    void addSkillsToJob(int jid, Collection<Integer> sids);

    void removeSkillsToJob(int jid, Collection<Integer> sids);
    
    
    
    
    //-============Plans=============
    //pid
    void addPlan(String plan_name, Integer amount, String duration, String status);

    void updatePlan(Integer pid, String plan_name, Integer amount, String duration, String status);

    void removePlan(Integer pid);

    Collection<Plans> getAllPlans();

    Plans findPlanById(Integer pid);

    Collection<Plans> findPlansByPlanName(String plan_name);


    
    
    //==========States============
    //stateid
    void addState(String name);
    void updateState(Integer stateid,String name);
    void removeState(Integer stateid);
    States getStateById(Integer stateid);
    Collection<States> getAllStates();
    
    
    //============Cities==========
    //cityid
    void addCity(String name,Integer stateid);
    void updateCity(Integer cityid,String name,Integer stateid);
    void removeCity(Integer cityid,Integer stateid);
    Cities getCityById(Integer cityid);
    Collection<Cities> getAllCities();
    Collection<Cities> getAllCitiesByStateName(Integer stateid);
    
    
    //============Rounds===========
    //roundid
    void addRound(String name,Integer amount,String status);
    void updateRound(Integer roundid,String name,Integer amount,String status);
    void removeRound(Integer roundid);
    Rounds getRoundById(Integer roundid);
    Collection<Rounds> getAllRounds();
    
    
    //==========JobStatus===========
    //jsid
    Integer getLastInsertedJobStatusId();
    void addJobStatus(String status,String reject_reason,Integer uid,Integer cid,Integer roundid);
    void updateJobStatus(Integer jsid,String status,String reject_reason,Integer uid,Integer cid,Integer roundid);
    void removeJobStatus(Integer jsid,Integer uid,Integer cid,Integer roundid);
    JobStatus findJobStatusById(Integer jsid);
    Collection<JobStatus> findJobStatusByCandidateId(Integer cid);
    Collection<JobStatus> getAllJobStatus();
    
    
    //==========JobTransactions========
    //jtid
    void addJobTransaction(Integer amount,Integer jid,Integer jsid,Integer uid,String status);
    void updateJobTransaction(Integer jtid,Integer amount,Integer jid,Integer jsid,Integer uid,String status);
    void removeJobTransaction(Integer jtid,Integer jid,Integer jsid,Integer uid);
    JobTransactions findJobTransactionById(Integer jtid);
    Collection<JobTransactions> getAllJobTransactions();
    Collection<JobTransactions> getAllJobTransactionsByUserId(Integer uid);
    
    
    //=========Settings========
    //setid
    void addSetting(Integer noOfResumeSubmit,Integer NoOfJobAssign,Integer roleid,Integer uid);
    void updateSetting(Integer setid,Integer noOfResumeSubmit,Integer NoOfJobAssign,Integer roleid,Integer uid);
    void removeSetting(Integer setid,Integer roleid,Integer uid);
    Settings findSettingById(Integer setid);
    Collection<Settings> getAllSettings();
    
    
    
    //=============UserPlans===========
    //userPlansId
    void addUserPlans(Integer price,Date start_date,Date end_date,Integer payid,Integer uid,Integer pid);
    void updateUserPlans(Integer userPlansId, Integer price,Date start_date,Date end_date,Integer payid,Integer uid,Integer pid);
    void removeUserPlans(Integer userPlansId,Integer payid,Integer uid,Integer pid);
    Collection<UserPlans> getAllUserPlans();
    UserPlans findUserPlansByUserId(Integer uid);
    
    //============PlanTransactions=======
    //planTransId
    Integer getLastInsertedPTId();
    void addPlanTransactions(String payment_method, String email, String contact_no, String card_number, String card_holder_name,String expiry_date,String cvv, String bank_name, String bank_transaction_id, String upi_transaction_id, String amount, String error_reason,Integer pid);
    void updatePlanTransactions(Integer planTransId,String payment_method, String email, String contact_no, String card_number, String card_holder_name,String expiry_date,String cvv, String bank_name, String bank_transaction_id, String upi_transaction_id, String amount, String error_reason,Integer pid);
    void removePlanTransactions(Integer planTransId,Integer pid);
    Collection<PlanTransactions> getAllPlanTransactions();
    PlanTransactions findPlanTransactionsById(Integer planTransId);

   
}
