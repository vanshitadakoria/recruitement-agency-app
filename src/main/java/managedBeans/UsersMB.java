/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import client.RestClient;
import ejb.UserBeanLocal;
import entities.Users;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vanshita
 */
@Named(value = "usersMB")
@ViewScoped
public class UsersMB implements Serializable {

    @EJB
    UserBeanLocal userBean;

    RestClient cl;
    Response res;

    Collection<Users> users;
    GenericType<Collection<Users>> gusers;

    private Users u;
    private Integer roleId;
    private String confirmPassword;
    private String dob;
    private Date dobDate;

    public Date getDobDate() {
        return dobDate;
    }

    public void setDobDate(Date dobDate) {
        this.dobDate = dobDate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }

    public Collection<Users> getUsers() {
        res = cl.getAllUsers(Response.class);
        users = res.readEntity(gusers);
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    public void addUser(String email, String pass) {

//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        String email = facesContext.getExternalContext().getRequestParameterMap().get("email");
//        String pass = facesContext.getExternalContext().getRequestParameterMap().get("pass");
        System.out.print("Email : " + email);
        System.out.print("Pass : " + pass);
        System.out.print("conf Pass : " + confirmPassword);

        if (!pass.equals(confirmPassword)) {
            // For printing error message back on JSF form
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password must be identical", null));
        } else {
            System.out.print("User test : " + email);
            Users user = userBean.findUserByEmail(email);

            if (user != null) {
                System.out.print("Email already exixts");

                //For adding message from cdi to jsf page
                FacesMessage emailExistError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Already Exists", null);
                FacesContext.getCurrentInstance().addMessage(null, emailExistError);

            } else {

                System.out.print("Date : " + dob);
                System.out.print("Role Id : " + roleId);
                System.out.print("name : " + u.getName());
                System.out.print("email: " + u.getEmail());
                System.out.print("phone : " + u.getPhoneNo());
                System.out.print("pass : " + u.getPassword());
                System.out.print("vpa : " + u.getVpaAddress());
                try {

                    cl.addUser(u.getName(), u.getEmail(), u.getPhoneNo(), dob, u.getPassword(), u.getVpaAddress(), roleId.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (roleId == 2) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("pricing_page.jsf");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        //            page="login.xhtml";
                        FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
                    } catch (IOException ex) {
                        Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                u = new Users();
                roleId = null;

            }

        }
//        return page;
    }

    public void updateUser(Users user) {
        this.u = user;
        dobDate = user.getDob();
        //this.dob = user.getDob().toString();
        this.roleId = user.getRoleId().getId();
    }

    public String update() {
        String page = null;

        System.out.print("Update Date : " + dobDate);

        System.out.print("Role Id : " + roleId);
        System.out.print("id : " + u.getId());
        System.out.print("name : " + u.getName());
        System.out.print("email: " + u.getEmail());
        System.out.print("phone : " + u.getPhoneNo());
        System.out.print("pass : " + u.getPassword());
        System.out.print("vpa : " + u.getVpaAddress());
        
        userBean.updateUser(u.getId(), u.getName(), u.getEmail(), u.getPhoneNo(), dobDate, u.getPassword(), u.getVpaAddress(), roleId);


        // Get the current request URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request.getRequestURI().contains("admin_side")){
            u = new Users();
            dobDate = null;
            roleId = null;
            page = "users.jsf";
        } else {
            page = "profile.jsf";
        }

        return page;
    }

    public void deleteUser(Users user) {
        System.out.print("Delete Role Id : " + user.getRoleId().getId().toString());
        System.out.print("User Id : " + user.getId().toString());

        String userId = user.getId().toString();
        String role_id = user.getRoleId().getId().toString();

        if (!userId.isEmpty() && !role_id.isEmpty()) {
            cl.removeUser(userId, role_id);
        } else {
            System.out.print("Delete User: Error in value");
        }
    }

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String user_email = (String) request.getSession().getAttribute("user-email");
        System.out.print("UsersMB constructor user_email : " + user_email);
        if (user_email != null) {
            u = userBean.findUserByEmail(user_email);
            dob = u.getDob().toString();
            dobDate = u.getDob();
            roleId = u.getRoleId().getId();
            System.out.print("UsersMB constructor user : " + u);

        } else {
            System.out.print("UsersMB : User not found");
        }

    }

    /**
     * Creates a new instance of usersBean
     */
    public UsersMB() {

        cl = new RestClient();
        u = new Users();

        users = new ArrayList<>();
        gusers = new GenericType<Collection<Users>>() {
        };
    }

}
