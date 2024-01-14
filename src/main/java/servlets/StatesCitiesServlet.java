/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entities.Cities;
import entities.States;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vanshita
 */
@WebServlet(name = "StatesCitiesServlet", urlPatterns = {"/StatesCitiesServlet"})
public class StatesCitiesServlet extends HttpServlet {

    @EJB AdminBeanLocal adminBean;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatesCitiesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>");
            
            Collection<States> states = adminBean.getAllStates();
            for(States s : states){
                out.println("Id : "+ s.getId() +"<br>");
                out.println("Name : "+ s.getName() +"<br>");
                
                out.println("Cities : <br>");
                Collection<Cities> cities = s.getCitiesCollection();
                for(Cities c : cities){
                    out.println("Id : " + c.getId() + "  Name : " + c.getName() + "<br>");
                }
                out.println("<hr>");
            }
            
            
            out.println("<hr>");
            out.println("Cities By StateName : <br>");
            States state = adminBean.getStateById(2);
            out.println("StateName : "+ state.getName() +"<br>");

            Collection<Cities> citiesByStateName = adminBean.getAllCitiesByStateName(2);
            for(Cities c : citiesByStateName){
                out.println("Name :" + c.getName() + "<br>");
            }

            
            out.println("</h3>");
            out.println("<h1>Servlet StatesCitiesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
