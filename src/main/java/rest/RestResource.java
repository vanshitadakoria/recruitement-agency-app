/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import entities.PlanTransactions;
import entities.Plans;
import entities.Users;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author vanshita
 */

@DeclareRoles({"admin", "free-user","paid-user"})
@Path("rest")
@RequestScoped
public class RestResource {

    @EJB
    UserBeanLocal userBean;
    @EJB
    AdminBeanLocal adminBean;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestResource
     */
    public RestResource() {
    }

    @POST
    @Path("adduser/{name}/{email}/{phone}/{dob}/{password}/{vpa}/{role_id}")
    public void addUser(@PathParam("name") String name,@PathParam("email") String email,@PathParam("phone") String phone_no,@PathParam("dob") String dob,@PathParam("password") String password,@PathParam("vpa") String vpa,@PathParam("role_id") Integer role_id) {

        System.out.print("rest dob : " + dob);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date cdob= null;
        try {
            cdob = sdf.parse(dob);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        userBean.addUser(name, email, phone_no, cdob, password, vpa, role_id);

    }

    @GET
    @Produces("application/json")
    @Path("finduserbyemail/{email}")
    public Users findUserByEmail(@PathParam("email") String email) {
        return userBean.findUserByEmail(email);
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateuser/{uid}/{name}/{email}/{phone}/{dob}/{password}/{vpa}/{role_id}")
    public void updateUser(@PathParam("uid") Integer uid, 
            @PathParam("name") String name,
            @PathParam("email") String email,
            @PathParam("phone") String phone_no,
            @PathParam("dob") String dob,
            @PathParam("password") String password,
            @PathParam("vpa") String vpa,
            @PathParam("role_id") Integer role_id) {
        
        System.out.print("rest dob : " + dob);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date cdob = null;

        try {
            cdob = sdf.parse(dob);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        userBean.updateUser(uid, name, email, phone_no, cdob, password, vpa, role_id);

    }

    @DELETE
    @Path("deleteuser/{uid}/{role_id}")
    public void removeUser(@PathParam("uid") Integer uid, @PathParam("role_id") Integer role_id) {

        userBean.removeUser(uid, role_id);
    }

    @GET
    @Path("finduserbyid/{uid}")
    @Produces("application/json")
    public Users findUserById(@PathParam("uid") Integer uid) {

        return userBean.findUserById(uid);
    }

    @GET
    @Path("getallusers")
    @Produces("application/json")
    public Collection<Users> getAllUsers() {
        return userBean.getAllUsers();
    }

    @GET
    @Path("getusersbyplanid/{pid}")
    @Produces("application/json")
    public Collection<Users> getUsersByPlanId(@PathParam("pid") Integer pid) {
        return userBean.getUsersByPlanId(pid);
    }
    
    
    @POST
    @Path("addplantransactions/{payment_method}/{email}/{contact_no}/{card_number}/{card_holder_name}/{expiry_date}/{cvv}/{bank_name}/{bank_transaction_id}/{upi_transaction_id}/{amount}/{error_reason}/{pid}")
    public void addPlanTransactions(
            @PathParam("payment_method") String payment_method,
            @PathParam("email") String email,
            @PathParam("contact_no") String contact_no,
            @PathParam("card_number") String card_number,
            @PathParam("card_holder_name") String card_holder_name,
            @PathParam("expiry_date") String expiry_date,
            @PathParam("cvv") String cvv,
            @PathParam("bank_name") String bank_name,
            @PathParam("bank_transaction_id") String bank_transaction_id,
            @PathParam("upi_transaction_id") String upi_transaction_id,
            @PathParam("amount") String amount,
            @PathParam("error_reason") String error_reason,
            @PathParam("pid") Integer pid) {

            System.out.println("Rest Add Plan Transactions");
            System.out.println("payment_method : "+ payment_method);
            System.out.println("payment_method : "+ email);
            System.out.println("payment_method : "+ card_number);
            System.out.println("payment_method : "+ card_holder_name);
            System.out.println("payment_method : "+ expiry_date);
            System.out.println("payment_method : "+ cvv);
            System.out.println("payment_method : "+ bank_transaction_id);
            System.out.println("payment_method : "+ upi_transaction_id);
            System.out.println("payment_method : "+ amount);
            System.out.println("payment_method : "+ error_reason);
            System.out.println("payment_method : "+ pid);

            try{
                adminBean.addPlanTransactions(payment_method, email, contact_no, card_number, card_holder_name, expiry_date, cvv, bank_name, bank_transaction_id, upi_transaction_id, amount, error_reason, pid);
   
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    
    
    
    
    

//    @POST
//    @Path("addcandidate/{firstname}/{lastname}/{email}/{resume_file}/{phone_no}/{uid}/{jid}")
//    public void addCandidate(
//            @PathParam("firstname") String firstname,
//            @PathParam("lastname") String lastname,
//            @PathParam("email") String email,
//            @PathParam("resume_file") String resume_file,
//            @PathParam("phone_no") String phone_no,
//            @PathParam("uid") Integer uid,
//            @PathParam("jid") Integer jid) {
//        
//
//    }
//
//    @Override
//    public void updateCandidate(Integer cid, String firstname, String lastname, String email, String resume_file, String phone_no, Integer uid, Integer jid) {
//       
//    }
//
//    @Override
//    public void removeCandidate(Integer cid, Integer jid, Integer uid) {
//    }
//
//    @Override
//    public Candidates findCandidateById(Integer cid) {
//       
//    }
//
//    @Override
//    public Collection<Candidates> getAllCandidates() {
//       
//
//    }
//
//    @Override
//    public Collection<Candidates> getCandidatesByJobId(Integer jid) {
//        
//
//    }
//
//    @Override
//    public Collection<Candidates> getCandidatesByUserId(Integer uid) {
//       
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    /**
//     * Retrieves representation of an instance of rest.RestResource
//     * @return an instance of java.lang.String
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of RestResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
}
