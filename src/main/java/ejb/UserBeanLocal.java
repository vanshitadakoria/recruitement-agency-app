/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entities.Candidates;
import entities.RolesPermissions;
import entities.Users;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;
import javax.servlet.http.Part;

/**
 *
 * @author vanshita
 */
@Local
public interface UserBeanLocal {
    
    //------------Roles--------------
    Collection<RolesPermissions> getAllRoles();
    public Collection<RolesPermissions> getAllRolesOtherThanAdmin();

    
   
    //--------------Users---------------
    //uid
    void addUser(Users user,Integer role_id);
    void addUser(String name,String email,String phone_no,Date dob,String password,String vpa,Integer role_id);
    void updateUser(Integer uid,String name,String email,String phone_no,Date dob,String password,String vpa,Integer role_id);
    void removeUser(Integer uid,Integer role_id);
    Users findUserById(Integer uid);
    Users findUserByEmail(String email);
    Integer getLastInsertedUserId();
    Collection<Users> getAllUsers();
    Collection<Users> getUsersByPlanId(Integer pid);
    
    //Boolean findUserByEmailAndPassword(String email,String password);
    
    
    //--------------Candidates---------------
    //cid
    void addCandidate(String firstname,String lastname,String email,String resume_file,String phone_no,Integer uid,Integer jid);
    void updateCandidate(Integer cid,String firstname,String lastname,String email,String resume_file,String phone_no,Integer uid,Integer jid);
    void removeCandidate(Integer cid,Integer jid,Integer uid);
    Candidates findCandidateById(Integer cid);
    Collection<Candidates> getAllCandidates();
    Collection<Candidates> getCandidatesByJobId(Integer jid);
    Collection<Candidates> getCandidatesByUserId(Integer uid);
    Integer getCandidatesCountsOfUser(Integer uid);
    
    public String uploadFile(InputStream fileInputStream, String uploadFolderPath, String fileName) throws IOException;

    
    
}
