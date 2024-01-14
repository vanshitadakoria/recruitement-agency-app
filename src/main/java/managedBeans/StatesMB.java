/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import ejb.AdminBeanLocal;
import entities.Cities;
import entities.States;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;

/**
 *
 * @author vanshita
 */
@Named(value = "statesMB")
@ViewScoped
public class StatesMB implements Serializable {
    
    @EJB AdminBeanLocal adminBean;

    Collection<States> states;
    Collection<Cities> cities;
    
    

    public Collection<States> getStates() {
        states = adminBean.getAllStates();
        return states;
    }

    public void setStates(Collection<States> states) {
        this.states = states;
    }

    public Collection<Cities> getCities() {
        cities = adminBean.getAllCities();
        return cities;
    }

    public void setCities(Collection<Cities> cities) {
        this.cities = cities;
    }
    
    
    
    /**
     * Creates a new instance of StatesMB
     */
    public StatesMB() {
    }
    
}
