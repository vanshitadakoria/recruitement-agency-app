/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import entities.JobsPostings;
import entities.Skills;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class JobsServlet extends HttpServlet {

    @EJB AdminBeanLocal adminBean;
    @EJB UserBeanLocal userBean;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse("15-10-2023");
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Jobs<h3>");
            out.println("<h4>");
            
            //adminBean.addJobsPosting("PHP", "fulltime", "Core php knowledge", "2", 12000, 15000, date, 1, 1, 2,"active");
            
            //adminBean.addJobsPosting("Laravel", "fulltime", "Core php knowledge with laravel", "3", 15000, 20000, date, 2, 3, 1,"active");
            
            //adminBean.removeJobsPosting(3, 2, 3 ,1);
            
            //adminBean.updateJobsPosting(2, "PHP Laravel", "partime", "Laravel framework", "2", 12000,20000, date, 2,3, 1, "active");
            
           
            
            //==================JobsSkills===============================
            
            ArrayList<Integer> sids = new ArrayList<>();
            sids.add(1);
            sids.add(3);
            
            //adminBean.addSkillsToJob(2, sids);
            
            
            
            
            
            Collection<JobsPostings> jobs = adminBean.getAllJobsPostings();
            for(JobsPostings j : jobs){
                out.println("ID: "+ j.getId()+"<br>");
                out.println("Title: "+ j.getTitle()+"<br>");
                out.println("Description: "+ j.getDescription()+"<br>");
                out.println("JOb_Type: "+ j.getJobType()+"<br>");
                out.println("Experience: "+ j.getExperience()+"<br>");
                out.println("min sal: "+ j.getMaxSalary()+"<br>");
                out.println("max sal: "+ j.getMaxSalary()+"<br>");
                out.println("deadline: "+ j.getDeadline()+"<br>");
                out.println("state: "+ j.getStateId().getName() +"<br>");
                out.println("city: "+ j.getCityId().getName()+"<br>");
                out.println("user: "+ j.getUserId().getName()+"<br>");
                
                
                out.println("====SkillsOfJob=====<br>");
                Collection<Skills> skills = adminBean.getAllSkillsOfJob(j.getId());
                for(Skills s : skills){
                    out.println("Sid: " + s.getId() + "<br>");
                    out.println("Skill Name: " + s.getName() + "<br>");
                    out.println("Description: " + s.getDescription() + "<br>");
                }
                
                
                
                out.println("<hr>");

            }
            
            
            
            
            
            
            
            out.println("<hr>");
            out.println("</h4>");
            
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e){
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(JobsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(JobsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
