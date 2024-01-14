/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entities.Candidates;
import entities.JobsPostings;
import entities.RolesPermissions;
import entities.Users;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
//import sun.security.util.IOUtils;

/**
 *
 * @author vanshita
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "recruitment_pu")
    private EntityManager em;
//    @Resource
//    private javax.transaction.UserTransaction utx;

    @Override
    public Collection<RolesPermissions> getAllRoles() {

        Collection<RolesPermissions> roles = em.createNamedQuery("RolesPermissions.findAll", RolesPermissions.class).getResultList();
        return roles;

    }

    @Override
    public Collection<RolesPermissions> getAllRolesOtherThanAdmin() {

        TypedQuery<RolesPermissions> otherRoles = em.createQuery("Select r from RolesPermissions r where r.roleName <> 'admin'", RolesPermissions.class);
        return otherRoles.getResultList();

    }

    //-------For login---------
//    @Override
//    public Boolean findUserByEmailAndPassword(String email, String password) {
//        boolean valid = false;
//        try {
//            TypedQuery<Users> qry = em.createNamedQuery("Users.findByEmailAndPassword", Users.class);
//            qry.setParameter("email", email);
//            qry.setParameter("password", password);
//
//            Users user = qry.getSingleResult();
//            if (user != null) {
//                valid = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return valid;
//    }
    //--------For registration of new user---------
    @Override
    public void addUser(Users user, Integer roleId) {

        RolesPermissions role = em.find(RolesPermissions.class, roleId);

        user.setRoleId(role);
        em.persist(user);
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Other way 
    @Override
    public void addUser(String name, String email, String phone_no, Date dob, String password, String vpa, Integer roleId) {

        try {
            System.out.print("=========EJB========");
            System.out.print("name :" + name);
            System.out.print("email :" + email);
            System.out.print("phn :" + phone_no);
            System.out.print("dob :" + dob);
            System.out.print("pass :" + password);
            System.out.print("hash pass :" + BCrypt.hashpw(password, BCrypt.gensalt()));
            System.out.print("vpa :" + vpa);
            System.out.print("rid :" + roleId);
            RolesPermissions role = em.find(RolesPermissions.class, roleId);

            Collection<Users> users = role.getUsersCollection();

            Users u = new Users();
            u.setName(name);
            u.setEmail(email);
            u.setPhoneNo(phone_no);
            u.setDob(dob);
            u.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            u.setVpaAddress(vpa);

            u.setRoleId(role);

            users.add(u);

            role.setUsersCollection(users);

            em.persist(u);
            em.merge(role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Users findUserByEmail(String email) {

//        Users user = em.createNamedQuery("Users.findByEmail", Users.class)
//                .setParameter("email", email)
//                .getSingleResult();
//        System.out.print("ejb user :" + user);
//        if (user != null) {
//            return user;
//        } else {
//            return null;
//        }
        try {
            Users user = em.createNamedQuery("Users.findByEmail", Users.class)
                    .setParameter("email", email)
                    .getSingleResult();

            System.out.print("ejb user: " + user);
            return user;
        } catch (NoResultException e) {
            // Log the exception or handle it as needed
            System.out.print("User not found for email: " + email);
            return null; // Return null when the user is not found
        }

//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateUser(Integer uid, String name, String email, String phone_no, Date dob, String password, String vpa, Integer role_id) {

        try {
            RolesPermissions role = em.find(RolesPermissions.class, role_id);

            Collection<Users> users = role.getUsersCollection();

            Users u = em.find(Users.class, uid);
            u.setName(name);
            u.setEmail(email);
            u.setPhoneNo(phone_no);
            u.setDob(dob);
            u.setPassword(password);
            u.setVpaAddress(vpa);

            u.setRoleId(role);

            users.add(u);

            role.setUsersCollection(users);

            em.merge(u);
            em.merge(role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUser(Integer uid, Integer role_id) {
        RolesPermissions role = em.find(RolesPermissions.class, role_id);

        Collection<Users> users = role.getUsersCollection();

        Users u = em.find(Users.class, uid);

        if (users.contains(u)) {
            users.remove(u);

            role.setUsersCollection(users);

            em.remove(u);
        }

//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Users findUserById(Integer uid) {

        Users user = em.find(Users.class, uid);
        return user;

    }

    @Override
    public Integer getLastInsertedUserId() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createQuery("SELECT u.id FROM Users u ORDER BY u.id DESC", Integer.class).setMaxResults(1).getSingleResult();
    }

    @Override
    public Collection<Users> getAllUsers() {

//        Collection<Users> users = em.createNamedQuery("Users.findAll").getResultList();
//        return users;
        return em.createNamedQuery("Users.findAll").getResultList();
    }

    @Override
    public Collection<Users> getUsersByPlanId(Integer pid) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //---------------Candidates------------------
    @Override
    public void addCandidate(String firstname, String lastname, String email, String resume_file, String phone_no, Integer uid, Integer jid) {

        try {

            Users user = em.find(Users.class, uid);
            JobsPostings job = em.find(JobsPostings.class, jid);

            Collection<Candidates> candidatesOfJob = job.getCandidatesCollection();
            Collection<Candidates> candidatesOfUser = user.getCandidatesCollection();

            Candidates c = new Candidates();
            c.setFirstname(firstname);
            c.setLastname(lastname);
            c.setEmail(email);
            c.setPhoneNo(phone_no);
            c.setUserId(user);
            c.setJobId(job);

            c.setResumeFile(resume_file);

            candidatesOfUser.add(c);
            candidatesOfJob.add(c);

            job.setCandidatesCollection(candidatesOfJob);
            user.setCandidatesCollection(candidatesOfUser);

            em.persist(c);
            em.merge(job);
            em.merge(user);

        } catch (Exception ex) {
            //Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @Override
    public String uploadFile(InputStream fileInputStream, String uploadFolderPath, String fileName) throws IOException {
        System.out.println("uploadFolderPath: " + uploadFolderPath);
        System.out.println("fileName: " + fileName);

        // Create the folder if it doesn't exist
        Path folder = Path.of(uploadFolderPath);
        if (Files.notExists(folder)) {
            Files.createDirectories(folder);
        }

        // Resolve the file path
        Path filePath = folder.resolve(fileName);

        // Copy the input stream to the file
        Files.copy(fileInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        // Return the file path
        return filePath.toString();

    }

    @Override
    public void updateCandidate(Integer cid, String firstname, String lastname, String email, String resume_file, String phone_no, Integer uid, Integer jid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Users user = em.find(Users.class, uid);
        JobsPostings job = em.find(JobsPostings.class, jid);

        Collection<Candidates> candidatesOfJob = job.getCandidatesCollection();
        Collection<Candidates> candidatesOfUser = user.getCandidatesCollection();

        Candidates c = em.find(Candidates.class, cid);

        c.setFirstname(firstname);
        c.setLastname(lastname);
        c.setEmail(email);
        c.setResumeFile(resume_file);
        c.setPhoneNo(phone_no);
        c.setUserId(user);
        c.setJobId(job);

//        candidatesOfUser.add(c);
        candidatesOfJob.add(c);

        job.setCandidatesCollection(candidatesOfJob);
//        user.setCandidatesCollection(candidatesOfUser);

        em.merge(c);
        em.merge(job);
        em.merge(user);

    }

    @Override
    public void removeCandidate(Integer cid, Integer jid, Integer uid) {

        Candidates c = em.find(Candidates.class, cid);
        JobsPostings job = em.find(JobsPostings.class, jid);
        Users user = em.find(Users.class, uid);

        Collection<Candidates> candidatesOfJob = job.getCandidatesCollection();
        Collection<Candidates> candidatesOfUser = user.getCandidatesCollection();

        if (candidatesOfJob.contains(c) && candidatesOfUser.contains(c)) {

            candidatesOfJob.remove(c);
            candidatesOfUser.remove(c);

            job.setCandidatesCollection(candidatesOfJob);
            user.setCandidatesCollection(candidatesOfUser);

            em.remove(c);
        }

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Candidates findCandidateById(Integer cid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Candidates c = em.find(Candidates.class, cid);
        return c;
    }

    @Override
    public Collection<Candidates> getAllCandidates() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Collection<Candidates> candidates = em.createNamedQuery("Candidates.findAll").getResultList();
        return candidates;

    }

    @Override
    public Collection<Candidates> getCandidatesByJobId(Integer jid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        JobsPostings job = em.find(JobsPostings.class, jid);
        return job.getCandidatesCollection();

    }

    @Override
    public Collection<Candidates> getCandidatesByUserId(Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Users u = em.find(Users.class, uid);
        return u.getCandidatesCollection();
    }

    @Override
    public Integer getCandidatesCountsOfUser(Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, uid);
        Integer count = u.getCandidatesCollection().size();

        return count;
    }
}
