/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entities.Candidates;
import entities.Cities;
import entities.JobStatus;
import entities.JobTransactions;
import entities.JobsPostings;
import entities.PlanTransactions;
import entities.Plans;
import entities.RolesPermissions;
import entities.Rounds;
import entities.Settings;
import entities.Skills;
import entities.States;
import entities.UserPlans;
import entities.Users;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vanshita
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "recruitment_pu")
    private EntityManager em;

    @Override
    public void addSkill(String name, String description, String status) {

        Skills s = new Skills();
        s.setName(name);
        s.setDescription(description);
        s.setStatus(status);
        em.persist(s);
    }

    @Override
    public void updateSkill(Integer sid, String name, String description, String status) {

        Skills s = em.find(Skills.class, sid);
        s.setName(name);
        s.setDescription(description);
        s.setStatus(status);

        em.merge(s);
    }

    @Override
    public void removeSkill(Integer sid) {

        Skills s = em.find(Skills.class, sid);
        em.remove(s);

    }

    @Override
    public Skills findSkillById(Integer id) {

        Skills skill = em.find(Skills.class, id);
        return skill;
    }

    //For finding if skill name already exists or not
    @Override
    public boolean findSkillByName(String name) {

        TypedQuery<Skills> qry = em.createNamedQuery("Skills.findByName", Skills.class).setParameter("name", name);
        List<Skills> skillWithSameName = qry.getResultList();
        // will return true if list is empty
        return skillWithSameName.isEmpty();

    }

    @Override
    public Collection<Skills> getAllSkills() {

        Collection<Skills> skills = em.createNamedQuery("Skills.findAll").getResultList();
        return skills;
    }

    @Override
    public Collection<Skills> getAllSkillsOfJob(Integer jid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings j = em.find(JobsPostings.class, jid);
        return j.getSkillsCollection();

    }

    //==================JobsPostings==============
    
    
    @Override
    public Integer getJobsCounts() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("JobsPostings.findAll").getResultList().size();
    }
    @Override
    public Integer getLastInsertedJobId() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createQuery("SELECT j.id FROM JobsPostings j ORDER BY j.id DESC", Integer.class).setMaxResults(1).getSingleResult();
    }

    @Override
    //public void addJobsPosting(String title, String job_type, String description, String experience, long min_salary, long max_salary, Date deadline, Integer stateid, Integer cityid, Integer uid, String status) {
    public void addJobsPosting(String title, String job_type, String description, String experience, long min_salary, long max_salary, Integer stateid, Integer cityid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        //Users u = em.find(Users.class, uid);
        States state = em.find(States.class, stateid);
        Cities city = em.find(Cities.class, cityid);

        //Collection<JobsPostings> jobsOfUser = u.getJobsPostingsCollection();
        Collection<JobsPostings> jobsOfState = state.getJobsPostingsCollection();
        Collection<JobsPostings> jobsOfCity = city.getJobsPostingsCollection();

        JobsPostings j = new JobsPostings();
        j.setTitle(title);
        j.setJobType(job_type);
        j.setDescription(description);
        j.setExperience(experience);
        j.setMinSalary(min_salary);
        j.setMaxSalary(max_salary);
        j.setDeadline(null);
        j.setStatus("active");
        j.setStateId(state);
        j.setCityId(city);
        j.setUserId(null);

        //jobsOfUser.add(j);
        jobsOfState.add(j);
        jobsOfCity.add(j);

        //u.setJobsPostingsCollection(jobsOfUser);
        state.setJobsPostingsCollection(jobsOfState);
        city.setJobsPostingsCollection(jobsOfCity);

        System.out.print(j.toString());
        em.persist(j);

        //em.merge(u);
        em.merge(state);
        em.merge(city);

    }

    @Override
    //public void updateJobsPosting(Integer jid, String title, String job_type, String description, String experience, long min_salary, long max_salary, Date deadline, Integer stateid, Integer cityid, Integer uid, String status) {
    public void updateJobsPosting(Integer jid, String title, String job_type, String description, String experience, long min_salary, long max_salary, Integer stateid, Integer cityid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings j = em.find(JobsPostings.class, jid);
        //Users u = em.find(Users.class, uid);
        States state = em.find(States.class, stateid);
        Cities city = em.find(Cities.class, cityid);

        //Collection<JobsPostings> jobsOfUser = u.getJobsPostingsCollection();
        Collection<JobsPostings> jobsOfState = state.getJobsPostingsCollection();
        Collection<JobsPostings> jobsOfCity = city.getJobsPostingsCollection();

//        if (jobsOfUser.contains(j) && jobsOfState.contains(j) && jobsOfCity.contains(j)) {
//
//            jobsOfUser.remove(j);
//            jobsOfState.remove(j);
//            jobsOfCity.remove(j);
        j.setTitle(title);
        j.setJobType(job_type);
        j.setDescription(description);
        j.setExperience(experience);
        j.setMinSalary(min_salary);
        j.setMaxSalary(max_salary);
        j.setDeadline(null);
        j.setStatus("active");
        j.setStateId(state);
        j.setCityId(city);
        j.setUserId(null);

        //jobsOfUser.add(j);
        jobsOfState.add(j);
        jobsOfCity.add(j);

        //u.setJobsPostingsCollection(jobsOfUser);
        state.setJobsPostingsCollection(jobsOfState);
        city.setJobsPostingsCollection(jobsOfCity);

        em.merge(j);

        //em.merge(u);
        em.merge(state);
        em.merge(city);

//        }
    }

    @Override
    public void removeJobsPosting(Integer jid, Integer stateid, Integer cityid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings job = em.find(JobsPostings.class, jid);
        //Users u = em.find(Users.class, uid);
        States state = em.find(States.class, stateid);
        Cities city = em.find(Cities.class, cityid);

        //Collection<JobsPostings> jobsOfUser = u.getJobsPostingsCollection();
        Collection<JobsPostings> jobsOfState = state.getJobsPostingsCollection();
        Collection<JobsPostings> jobsOfCity = city.getJobsPostingsCollection();

        //if (jobsOfUser.contains(job) && jobsOfState.contains(job) && jobsOfCity.contains(job)) {
        if (jobsOfState.contains(job) && jobsOfCity.contains(job)) {

            //jobsOfUser.remove(job);
            //u.setJobsPostingsCollection(jobsOfUser);
            jobsOfState.remove(job);
            state.setJobsPostingsCollection(jobsOfState);
            jobsOfCity.remove(job);
            city.setJobsPostingsCollection(jobsOfCity);

            em.remove(job);
        } else {
            System.out.print("Error");
        }

    }

    @Override
    public Collection<JobsPostings> getAllJobsPostings() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("JobsPostings.findAll").getResultList();

    }

    @Override
    public Collection<JobsPostings> getAllJobsPostingsByState(Integer stateid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        States state = em.find(States.class, stateid);
        return state.getJobsPostingsCollection();
    }

    @Override
    public Collection<JobsPostings> getAllJobsPostingsByCity(Integer cityid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Cities city = em.find(Cities.class, cityid);
        return city.getJobsPostingsCollection();

    }

    @Override
    public JobsPostings getJobsPostingById(Integer jid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings job = em.find(JobsPostings.class, jid);
        return job;

    }

    //--------JobSkills(JoinTable)------------
    @Override
    public void addSkillsToJob(int jid, Collection<Integer> sids) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings job = em.find(JobsPostings.class, jid);

        Collection<Skills> skills = job.getSkillsCollection();

        for (Integer sid : sids) {
            Skills s = em.find(Skills.class, sid);
            if (!skills.contains(s)) {
                Collection<JobsPostings> jobs = s.getJobsPostingsCollection();

                jobs.add(job);
                skills.add(s);

                job.setSkillsCollection(skills);
                s.setJobsPostingsCollection(jobs);

                em.merge(s);
            }
        }

    }

    @Override
    public void removeSkillsToJob(int jid, Collection<Integer> sids) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings job = em.find(JobsPostings.class, jid);

        Collection<Skills> skills = job.getSkillsCollection();

        for (Integer sid : sids) {
            Skills s = em.find(Skills.class, sid);
            if (skills.contains(s)) {
                Collection<JobsPostings> jobs = s.getJobsPostingsCollection();

                jobs.remove(job);
                skills.remove(s);

                job.setSkillsCollection(skills);
                s.setJobsPostingsCollection(jobs);

                em.merge(s);
            }
        }

    }

    //==================Plans==================
    @Override
    public void addPlan(String plan_name, Integer amount, String duration, String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Plans p = new Plans();
        p.setPlanName(plan_name);
        p.setAmount(amount);
        p.setDuration(duration);
        p.setStatus(status);

        em.persist(p);

    }

    @Override
    public void updatePlan(Integer pid, String plan_name, Integer amount, String duration, String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Plans p = em.find(Plans.class, pid);
        p.setPlanName(plan_name);
        p.setAmount(amount);
        p.setDuration(duration);
        p.setStatus(status);

        em.merge(p);

    }

    @Override
    public void removePlan(Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Plans p = em.find(Plans.class, pid);
        em.remove(p);

    }

    @Override
    public Collection<Plans> getAllPlans() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Plans.findAll").getResultList();
    }

    @Override
    public Plans findPlanById(Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Plans.class, pid);

    }

    @Override
    public Collection<Plans> findPlansByPlanName(String plan_name) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("Plans.findByPlanName").setParameter("planName", plan_name).getResultList();
    }

    //=============States=================
    @Override
    public void addState(String name) {

        States state = new States();
        state.setName(name);

        em.persist(state);

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateState(Integer stateid, String name) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        States state = em.find(States.class, stateid);
        state.setName(name);

        em.merge(state);

    }

    @Override
    public void removeState(Integer stateid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        States state = em.find(States.class, stateid);
        em.remove(state);

    }

    @Override
    public States getStateById(Integer stateid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        States state = em.find(States.class, stateid);
        return state;

    }

    @Override
    public Collection<States> getAllStates() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        //return em.createNamedQuery("States.findAll").getResultList();
        //For getting only 10 records
        TypedQuery<States> query = em.createNamedQuery("States.findAll", States.class);
        query.setMaxResults(10);
        Collection<States> results = query.getResultList();
        return results;
    }

    @Override
    public void addCity(String name, Integer stateid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        States state = em.find(States.class, stateid);
        Cities city = new Cities();
        city.setName(name);
        city.setStateId(state);

        em.persist(city);
    }

    @Override
    public void updateCity(Integer cityid, String name, Integer stateid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        States state = em.find(States.class, stateid);
        Cities city = em.find(Cities.class, cityid);
        city.setName(name);
        city.setStateId(state);

        em.merge(city);
    }

    @Override
    public void removeCity(Integer cityid, Integer stateid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        States state = em.find(States.class, stateid);
        Cities city = em.find(Cities.class, cityid);

        Collection<Cities> cities = state.getCitiesCollection();

        if (cities.contains(city)) {
            cities.remove(city);
            state.setCitiesCollection(cities);
            em.remove(city);
        }
//        em.remove(city);

    }

    @Override
    public Cities getCityById(Integer cityid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Cities city = em.find(Cities.class, cityid);
        return city;

    }

    @Override
    public Collection<Cities> getAllCities() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("Cities.findAll").getResultList();
    }

    @Override
    public Collection<Cities> getAllCitiesByStateName(Integer stateid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("Cities.findByStateId").setParameter("stateId", stateid).getResultList();

    }

    //================Rounds==============
    
    @Override
    public void addRound(String name, Integer amount, String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Rounds r = new Rounds();
        r.setName(name);
        r.setAmount(amount);
        r.setStatus(status);

        em.persist(r);
    }

    @Override
    public void updateRound(Integer roundid, String name, Integer amount, String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Rounds r = em.find(Rounds.class, roundid);
        r.setName(name);
        r.setAmount(amount);
        r.setStatus(status);

        em.merge(r);

    }

    @Override
    public void removeRound(Integer roundid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Rounds getRoundById(Integer roundid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Rounds r = em.find(Rounds.class, roundid);
        return r;

    }

    @Override
    public Collection<Rounds> getAllRounds() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("Rounds.findAll").getResultList();
    }

    //===============JobStatus==============
    @Override
    public Integer getLastInsertedJobStatusId() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createQuery("SELECT j.id FROM JobStatus j ORDER BY j.id DESC", Integer.class).setMaxResults(1).getSingleResult();
    }
    @Override
    public void addJobStatus(String status, String reject_reason, Integer uid, Integer cid, Integer roundid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Users u = em.find(Users.class, uid);
        Candidates c = em.find(Candidates.class, cid);
        Rounds r = em.find(Rounds.class, roundid);

        Collection<JobStatus> jobStatusOfUsers = u.getJobStatusCollection();
        Collection<JobStatus> jobStatusOfCandidates = c.getJobStatusCollection();
        Collection<JobStatus> jobStatusOfRounds = r.getJobStatusCollection();

        JobStatus js = new JobStatus();
        js.setStatus(status);
        js.setRejectReason(reject_reason);
        js.setUserId(u);
        js.setCandidateId(c);
        js.setRoundId(r);

        jobStatusOfUsers.add(js);
        jobStatusOfCandidates.add(js);
        jobStatusOfRounds.add(js);

        u.setJobStatusCollection(jobStatusOfUsers);
        c.setJobStatusCollection(jobStatusOfCandidates);
        r.setJobStatusCollection(jobStatusOfRounds);

        em.persist(js);

        em.merge(u);
        em.merge(c);
        em.merge(r);
    }

    @Override
    public void updateJobStatus(Integer jsid, String status, String reject_reason, Integer uid, Integer cid, Integer roundid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Users u = em.find(Users.class, uid);
        Candidates c = em.find(Candidates.class, cid);
        Rounds r = em.find(Rounds.class, roundid);

        Collection<JobStatus> jobStatusOfUsers = u.getJobStatusCollection();
        Collection<JobStatus> jobStatusOfCandidates = c.getJobStatusCollection();
        Collection<JobStatus> jobStatusOfRounds = r.getJobStatusCollection();

        JobStatus js = em.find(JobStatus.class, jsid);

        if (jobStatusOfUsers.contains(js) && jobStatusOfCandidates.contains(js) && jobStatusOfRounds.contains(js)) {

            jobStatusOfUsers.remove(js);
            jobStatusOfCandidates.remove(js);
            jobStatusOfRounds.remove(js);

            js.setStatus(status);
            js.setRejectReason(reject_reason);
            js.setUserId(u);
            js.setCandidateId(c);
            js.setRoundId(r);

            jobStatusOfUsers.add(js);
            jobStatusOfCandidates.add(js);
            jobStatusOfRounds.add(js);

            u.setJobStatusCollection(jobStatusOfUsers);
            c.setJobStatusCollection(jobStatusOfCandidates);
            r.setJobStatusCollection(jobStatusOfRounds);

            em.merge(js);

            em.merge(u);
            em.merge(c);
            em.merge(r);

        }

    }

    @Override
    public void removeJobStatus(Integer jsid, Integer uid, Integer cid, Integer roundid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, uid);
        Candidates c = em.find(Candidates.class, cid);
        Rounds r = em.find(Rounds.class, roundid);

        Collection<JobStatus> jobStatusOfUsers = u.getJobStatusCollection();
        Collection<JobStatus> jobStatusOfCandidates = c.getJobStatusCollection();
        Collection<JobStatus> jobStatusOfRounds = r.getJobStatusCollection();

        JobStatus js = em.find(JobStatus.class, jsid);

        if (jobStatusOfUsers.contains(js) && jobStatusOfCandidates.contains(js) && jobStatusOfRounds.contains(js)) {

            jobStatusOfUsers.remove(js);
            jobStatusOfCandidates.remove(js);
            jobStatusOfRounds.remove(js);

            u.setJobStatusCollection(jobStatusOfUsers);
            c.setJobStatusCollection(jobStatusOfCandidates);
            r.setJobStatusCollection(jobStatusOfRounds);

            em.remove(js);

//            em.merge(u);
//            em.merge(c);
//            em.merge(r);
        }

    }

    @Override
    public JobStatus findJobStatusById(Integer jsid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        JobStatus js = em.find(JobStatus.class, jsid);
        return js;

    }

    @Override
    public Collection<JobStatus> findJobStatusByCandidateId(Integer cid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createQuery(
                "SELECT js FROM JobStatus js WHERE  js.candidateId.id = :cid ORDER BY js.roundId.id DESC",
                JobStatus.class
        )
                .setParameter("cid", cid)
                .setMaxResults(1) // To get only the highest round
                .getResultList();

    }

    @Override
    public Collection<JobStatus> getAllJobStatus() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("JobStatus.findAll").getResultList();

    }

    //============JobTransactions=============
    @Override
    public void addJobTransaction(Integer amount, Integer jid, Integer jsid, Integer uid, String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        JobsPostings j = em.find(JobsPostings.class, jid);
        JobStatus js = em.find(JobStatus.class, jsid);
        Users u = em.find(Users.class, uid);

        Collection<JobTransactions> jobTransactionsOfJobs = j.getJobTransactionsCollection();
        Collection<JobTransactions> jobTransactionsOfJobStatus = js.getJobTransactionsCollection();
        Collection<JobTransactions> jobTransactionsOfUsers = u.getJobTransactionsCollection();

        JobTransactions jt = new JobTransactions();
        jt.setAmount(amount);
        jt.setStatus(status);
        jt.setJobId(j);
        jt.setJobStatusId(js);
        jt.setUserId(u);

        jobTransactionsOfJobs.add(jt);
        jobTransactionsOfJobStatus.add(jt);
        jobTransactionsOfUsers.add(jt);

        j.setJobTransactionsCollection(jobTransactionsOfJobs);
        js.setJobTransactionsCollection(jobTransactionsOfJobStatus);
        u.setJobTransactionsCollection(jobTransactionsOfUsers);

        em.persist(jt);
        em.merge(u);
        em.merge(js);
        em.merge(j);

    }

    @Override
    public void updateJobTransaction(Integer jtid, Integer amount, Integer jid, Integer jsid, Integer uid, String status) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        JobsPostings j = em.find(JobsPostings.class, jid);
        JobStatus js = em.find(JobStatus.class, jsid);
        Users u = em.find(Users.class, uid);

        Collection<JobTransactions> jobTransactionsOfJobs = j.getJobTransactionsCollection();
        Collection<JobTransactions> jobTransactionsOfJobStatus = js.getJobTransactionsCollection();
        Collection<JobTransactions> jobTransactionsOfUsers = u.getJobTransactionsCollection();

        JobTransactions jt = em.find(JobTransactions.class, jtid);
        jt.setAmount(amount);
        jt.setStatus(status);
        jt.setJobId(j);
        jt.setJobStatusId(js);
        jt.setUserId(u);

        jobTransactionsOfJobs.add(jt);
        jobTransactionsOfJobStatus.add(jt);
        jobTransactionsOfUsers.add(jt);

        j.setJobTransactionsCollection(jobTransactionsOfJobs);
        js.setJobTransactionsCollection(jobTransactionsOfJobStatus);
        u.setJobTransactionsCollection(jobTransactionsOfUsers);

        em.merge(jt);
        em.merge(u);
        em.merge(js);
        em.merge(j);
    }

    @Override
    public void removeJobTransaction(Integer jtid, Integer jid, Integer jsid, Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings j = em.find(JobsPostings.class, jid);
        JobStatus js = em.find(JobStatus.class, jsid);
        Users u = em.find(Users.class, uid);

        Collection<JobTransactions> jobTransactionsOfJobs = j.getJobTransactionsCollection();
        Collection<JobTransactions> jobTransactionsOfJobStatus = js.getJobTransactionsCollection();
        Collection<JobTransactions> jobTransactionsOfUsers = u.getJobTransactionsCollection();

        JobTransactions jt = em.find(JobTransactions.class, jtid);

        if (jobTransactionsOfJobs.contains(jt) && jobTransactionsOfJobStatus.contains(jt) && jobTransactionsOfUsers.contains(jt)) {
            jobTransactionsOfJobs.remove(jt);
            jobTransactionsOfJobStatus.remove(jt);
            jobTransactionsOfUsers.remove(jt);

            j.setJobTransactionsCollection(jobTransactionsOfJobs);
            js.setJobTransactionsCollection(jobTransactionsOfJobStatus);
            u.setJobTransactionsCollection(jobTransactionsOfUsers);

            em.remove(jt);

        }

    }

    @Override
    public JobTransactions findJobTransactionById(Integer jtid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(JobTransactions.class, jtid);
    }

    @Override
    public Collection<JobTransactions> getAllJobTransactions() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("JobTransactions.findAll").getResultList();
    }
    
    @Override
    public Collection<JobTransactions> getAllJobTransactionsByUserId(Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users user = em.find(Users.class, uid);
        return user.getJobTransactionsCollection();
    }

    //=========Settings=============
    @Override
    public void addSetting(Integer noOfResumeSubmit, Integer NoOfJobAssign, Integer roleid, Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        RolesPermissions role = em.find(RolesPermissions.class, roleid);
        Users u = em.find(Users.class, uid);

        Collection<Settings> settingsOfRole = role.getSettingsCollection();
        Collection<Settings> settingsOfUser = u.getSettingsCollection();

        Settings s = new Settings();
        s.setNoOfResumeSubmit(noOfResumeSubmit);
        s.setNoOfJobAssign(NoOfJobAssign);
        s.setRoleId(role);
        s.setUserId(u);

        settingsOfRole.add(s);
        settingsOfUser.add(s);

        role.setSettingsCollection(settingsOfRole);
        u.setSettingsCollection(settingsOfUser);

        em.persist(s);
        em.merge(u);
        em.merge(role);

    }

    @Override
    public void updateSetting(Integer setid, Integer noOfResumeSubmit, Integer NoOfJobAssign, Integer roleid, Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        RolesPermissions role = em.find(RolesPermissions.class, roleid);
        Users u = em.find(Users.class, uid);

        Collection<Settings> settingsOfRole = role.getSettingsCollection();
        Collection<Settings> settingsOfUser = u.getSettingsCollection();

        Settings s = em.find(Settings.class, setid);
        s.setNoOfResumeSubmit(noOfResumeSubmit);
        s.setNoOfJobAssign(NoOfJobAssign);
        s.setRoleId(role);
        s.setUserId(u);

        settingsOfRole.add(s);
        settingsOfUser.add(s);

        role.setSettingsCollection(settingsOfRole);
        u.setSettingsCollection(settingsOfUser);

        em.merge(s);
        em.merge(u);
        em.merge(role);

    }

    @Override
    public void removeSetting(Integer setid, Integer roleid, Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        RolesPermissions role = em.find(RolesPermissions.class, roleid);
        Users u = em.find(Users.class, uid);

        Collection<Settings> settingsOfRole = role.getSettingsCollection();
        Collection<Settings> settingsOfUser = u.getSettingsCollection();

        Settings s = em.find(Settings.class, setid);
        if (settingsOfRole.contains(s) && settingsOfUser.contains(s)) {
            settingsOfRole.remove(s);
            settingsOfUser.remove(s);

            role.setSettingsCollection(settingsOfRole);
            u.setSettingsCollection(settingsOfUser);

            em.remove(s);
        }

    }

    @Override
    public Settings findSettingById(Integer setid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.find(Settings.class, setid);
    }

    @Override
    public Collection<Settings> getAllSettings() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("Settings.findAll").getResultList();

    }

    //==============Users_Plans===================
    @Override
    public void addUserPlans(Integer price, Date start_date, Date end_date, Integer payid, Integer uid, Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        PlanTransactions pt = em.find(PlanTransactions.class, payid);
        Users u = em.find(Users.class, uid);
        Plans p = em.find(Plans.class, pid);

        Collection<UserPlans> userPlansOfPlanTransactions = pt.getUserPlansCollection();
        Collection<UserPlans> userPlansOfUsers = u.getUserPlansCollection();
        Collection<UserPlans> userPlansOfPlans = p.getUserPlansCollection();

        UserPlans up = new UserPlans();
        up.setPrice(price);
        up.setStartDate(start_date);
        up.setEndDate(end_date);
        up.setPaymentId(pt);
        up.setUserId(u);
        up.setPlanId(p);

        userPlansOfPlanTransactions.add(up);
        userPlansOfUsers.add(up);
        userPlansOfPlans.add(up);

        pt.setUserPlansCollection(userPlansOfPlanTransactions);
        u.setUserPlansCollection(userPlansOfUsers);
        p.setUserPlansCollection(userPlansOfPlans);

        em.persist(up);

        em.merge(p);
        em.merge(u);
        em.merge(pt);

    }

    @Override
    public void updateUserPlans(Integer userPlansId, Integer price, Date start_date, Date end_date, Integer payid, Integer uid, Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        PlanTransactions pt = em.find(PlanTransactions.class, payid);
        Users u = em.find(Users.class, uid);
        Plans p = em.find(Plans.class, pid);

        Collection<UserPlans> userPlansOfPlanTransactions = pt.getUserPlansCollection();
        Collection<UserPlans> userPlansOfUsers = u.getUserPlansCollection();
        Collection<UserPlans> userPlansOfPlans = p.getUserPlansCollection();

        UserPlans up = em.find(UserPlans.class, userPlansId);
        up.setPrice(price);
        up.setStartDate(start_date);
        up.setEndDate(end_date);
        up.setPaymentId(pt);
        up.setUserId(u);
        up.setPlanId(p);

        userPlansOfPlanTransactions.add(up);
        userPlansOfUsers.add(up);
        userPlansOfPlans.add(up);

        pt.setUserPlansCollection(userPlansOfPlanTransactions);
        u.setUserPlansCollection(userPlansOfUsers);
        p.setUserPlansCollection(userPlansOfPlans);

        em.merge(up);

        em.merge(p);
        em.merge(u);
        em.merge(pt);
    }

    @Override
    public void removeUserPlans(Integer userPlansId, Integer payid, Integer uid, Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        PlanTransactions pt = em.find(PlanTransactions.class, payid);
        Users u = em.find(Users.class, uid);
        Plans p = em.find(Plans.class, pid);

        UserPlans up = em.find(UserPlans.class, userPlansId);

        Collection<UserPlans> userPlansOfPlanTransactions = pt.getUserPlansCollection();
        Collection<UserPlans> userPlansOfUsers = u.getUserPlansCollection();
        Collection<UserPlans> userPlansOfPlans = p.getUserPlansCollection();

        if (userPlansOfPlanTransactions.contains(up) && userPlansOfUsers.contains(up) && userPlansOfPlans.contains(up)) {

            userPlansOfPlanTransactions.add(up);
            userPlansOfUsers.add(up);
            userPlansOfPlans.add(up);

            pt.setUserPlansCollection(userPlansOfPlanTransactions);
            u.setUserPlansCollection(userPlansOfUsers);
            p.setUserPlansCollection(userPlansOfPlans);

            em.remove(up);

        }

    }

    @Override
    public Collection<UserPlans> getAllUserPlans() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("UserPlans.findAll").getResultList();
    }

    @Override
    public UserPlans findUserPlansByUserId(Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.find(UserPlans.class, uid);
    }

    //===============PlanTransactions================
    @Override
    public Integer getLastInsertedPTId() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createQuery("SELECT pt.id FROM PlanTransactions pt ORDER BY pt.id DESC", Integer.class).setMaxResults(1).getSingleResult();
    }
    
    
    @Override
    public void addPlanTransactions(String payment_method, String email, String contact_no, String card_number, String card_holder_name,String expiry_date,String cvv, String bank_name, String bank_transaction_id, String upi_transaction_id, String amount, String error_reason,Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Plans p = em.find(Plans.class, pid);

        Collection<PlanTransactions> planTransactionsOfPlan = p.getPlanTransactionsCollection();

        PlanTransactions pt = new PlanTransactions();
        pt.setPaymentMethod(payment_method);
        pt.setEmail(email);
        pt.setContactNo(contact_no);
        pt.setCardNumber(card_number);
        pt.setCardHolderName(card_holder_name);
        pt.setExpiryDate(expiry_date);
        pt.setCvv(cvv);
        pt.setBankName(bank_name);
        pt.setBankTransactionId(bank_transaction_id);
        pt.setUpiTransactionId(upi_transaction_id);
        pt.setAmount(amount);
        pt.setErrorReason(error_reason);
        pt.setPlanId(p);

        planTransactionsOfPlan.add(pt);

        p.setPlanTransactionsCollection(planTransactionsOfPlan);

        em.persist(pt);
        em.merge(p);

    }

    @Override
    public void updatePlanTransactions(Integer planTransId, String payment_method, String email, String contact_no, String card_number, String card_holder_name, String expiry_date,String cvv, String bank_name, String bank_transaction_id, String upi_transaction_id, String amount, String error_reason,Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Plans p = em.find(Plans.class, pid);

        Collection<PlanTransactions> planTransactionsOfPlan = p.getPlanTransactionsCollection();

        PlanTransactions pt = em.find(PlanTransactions.class, planTransId);
        pt.setPaymentMethod(payment_method);
        pt.setEmail(email);
        pt.setContactNo(contact_no);
        pt.setCardNumber(card_number);
        pt.setCardHolderName(card_holder_name);
        pt.setExpiryDate(expiry_date);
        pt.setCvv(cvv);
        pt.setBankName(bank_name);
        pt.setBankTransactionId(bank_transaction_id);
        pt.setUpiTransactionId(upi_transaction_id);
        pt.setAmount(amount);
        pt.setErrorReason(error_reason);
        pt.setPlanId(p);

        planTransactionsOfPlan.add(pt);

        p.setPlanTransactionsCollection(planTransactionsOfPlan);

        em.merge(pt);
        em.merge(p);
    }

    @Override
    public void removePlanTransactions(Integer planTransId, Integer pid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Plans p = em.find(Plans.class, pid);

        Collection<PlanTransactions> planTransactionsOfPlan = p.getPlanTransactionsCollection();

        PlanTransactions pt = em.find(PlanTransactions.class, planTransId);

        if (planTransactionsOfPlan.contains(pt)) {
            planTransactionsOfPlan.remove(pt);

            p.setPlanTransactionsCollection(planTransactionsOfPlan);

            em.remove(pt);
        }

    }

    @Override
    public Collection<PlanTransactions> getAllPlanTransactions() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("PlanTransactions.findAll").getResultList();
    }

    @Override
    public PlanTransactions findPlanTransactionsById(Integer planTransId) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(PlanTransactions.class, planTransId);
    }

}
