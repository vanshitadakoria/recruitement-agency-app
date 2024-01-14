/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.UserBeanLocal;
import entities.Candidates;
import entities.JobsPostings;
import entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    @EJB UserBeanLocal userBean;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse("3-02-2000");
            
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>");
            
//            Users user = new Users("Prisha","prisha@abc.com","9909909909", date,"prisha@123","prisha@paytm","active");
//            userBean.addUser(user, 2);

              userBean.addUser("Vanshi", "vanshi@abc.com", "1234567899", date, "vanshi@123", "vanshi@paytm", 3);
            
//            userBean.updateUser(8, "Prisha Jariwala", "prisha@abc.com", "9999998888", date, "prisha@123", "prisha@kotak", 2);
            
//            userBean.removeUser(8);


            //===================Candidates=================
            
//            userBean.addCandidate("Jay", "Soni", "jay@gmail.com", "xyz", "1122334455", 1, 1);
            //userBean.addCandidate("Ketki", "Patel", "ketki@gmail.com", "abc", "1122334455", 1, 1);


            //userBean.updateCandidate(2, "Ketki", "Shah", "ketki@abc.com", "file", "1010101010", 1, 1);
            
            //userBean.removeCandidate(2, 1);


            Collection<Users> users = userBean.getAllUsers();
            for(Users u : users){
                out.println("ID : "+ u.getId() +"<br>");
                out.println("Name : "+ u.getName() +"<br>");
                out.println("Email : "+ u.getEmail() +"<br>");
                out.println("Phone No : "+ u.getPhoneNo() +"<br>");
                out.println("VPA Address : "+ u.getVpaAddress() +"<br><br>");

//                out.println("========JobsOfUser=======<br>");
//                Collection<JobsPostings> jobs = u.getJobsPostingsCollection();
//                for(JobsPostings j : jobs){
//                    out.println("ID : " + j.getId() + "<br>");
//                    out.println("Title : " + j.getTitle() + "<br>");
//                    out.println("Description : " + j.getDescription() + "<br>");
//                    out.println("JobType : " + j.getJobType() + "<br>");
//
//                }
//                
//                out.println("========CandidatesOfUser=======<br>");
//                Collection<Candidates> candidates = u.getCandidatesCollection();
//                for (Candidates c : candidates) {
//                    out.println("ID : " + c.getId() + "<br>");
//                    out.println("Name : " + c.getFirstname() +" " + c.getLastname() + "<br>");
//                    out.println("Email : " + c.getEmail() + "<br>");
//                    out.println("Job : " + c.getJobId().getTitle() + "<br>");
//
//                }
//                
                
                out.println("<hr>");

            }
            
            out.println("<hr>");

            
            out.println("</h3>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }catch(ParseException e){
            e.printStackTrace();
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
