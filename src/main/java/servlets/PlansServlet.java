/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entities.Plans;
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
@WebServlet(name = "PlansServlet", urlPatterns = {"/PlansServlet"})
public class PlansServlet extends HttpServlet {

   @EJB AdminBeanLocal adminBean;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PlansServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>");
            
//            adminBean.addPlan("Advanced", 4500, "260", "active");
//            adminBean.updatePlan(1,"Basic Plan", 2000, "100", "active");

//            adminBean.removePlan(1);
            
            
            Collection<Plans> plans = adminBean.getAllPlans();
            for(Plans p : plans){
                out.println("Id: "+ p.getId() +"<br>");
                out.println("Plan Name: "+ p.getPlanName() +"<br>");
                out.println("Amount: "+ p.getAmount() +"<br>");
                out.println("Duration: "+ p.getDuration() +"<br>");

                out.println("<hr>");

            }
            
            out.println("</h3>");
            out.println("<hr><hr>");
            
            out.println("<h2>Plans BY PlanName : </h2>");
            
            Collection<Plans> plansByPlanName = adminBean.findPlansByPlanName("basic");
            for (Plans p : plansByPlanName) {
                out.println("Id: " + p.getId() + "<br>");
                out.println("Plan Name: " + p.getPlanName() + "<br>");
                out.println("Amount: " + p.getAmount() + "<br>");
                out.println("Duration: " + p.getDuration() + "<br>");

                out.println("<hr>");

            }
            
            
            
            out.println("<h1>Servlet PlansServlet at " + request.getContextPath() + "</h1>");
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
