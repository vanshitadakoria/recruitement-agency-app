/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import ejb.UserBeanLocal;
import entities.RolesPermissions;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author vanshita
 */
@Named(value = "rolesMB")
@ViewScoped
public class RolesMB implements Serializable {

    @EJB
    private UserBeanLocal userBean;

    
    

    /**
     * Creates a new instance of RolesMB
     */
    public RolesMB() {
    }
    
    
    public Collection<RolesPermissions> getAllRoles(){
        Collection<RolesPermissions> roles = userBean.getAllRoles();
        return roles;
    }
    
    public Collection<RolesPermissions> getAllRolesOtherThanAdmin() {
        Collection<RolesPermissions> roles = userBean.getAllRolesOtherThanAdmin();
        return roles;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
