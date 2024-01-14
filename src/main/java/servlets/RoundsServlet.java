/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entities.Plans;
import entities.Rounds;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "RoundsServlet", urlPatterns = {"/RoundsServlet"})
public class RoundsServlet extends HttpServlet {

    @EJB
    AdminBeanLocal adminBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RoundsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>");
            out.println("Rounds : <br>");

            // Get the current date
            LocalDate currentDate = LocalDate.now();

// Set the end date as 90 days plus the current date
            LocalDate endDate = currentDate.plusDays(90);

// Format dates if needed
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String startDateString = currentDate.format(formatter);
            String endDateString = endDate.format(formatter);
            out.println("<h1>startDateString at " + startDateString + "</h1>");
            out.println("<h1>endDateString at " + endDateString + "</h1>");

//            Plans plan = adminBean.findPlanById(1);
//
//            Long days = Long.valueOf(plan.getDuration());
//
//            LocalDate end = currentDate.plusDays(days);
//
//            out.println("<h1>days at " + days + "</h1>");
//            
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Date end_date = java.sql.Date.valueOf(end);
//            out.println("<h1>end_date at " + end_date + "</h1>");

            
            
            
//            adminBean.addRound("Telephonic Interview", 50, "active");
//            adminBean.updateRound(1,"Shortlisted", 100, "active");
//            Collection<Rounds> rounds = adminBean.getAllRounds();
//            for (Rounds r : rounds) {
//                out.println("Id : " + r.getId() + "<br>");
//                out.println("Name : " + r.getName() + "<br>");
//                out.println("Amount : " + r.getAmount() + "<br>");
//
//                out.println("<hr>");
//
//            }




            out.println("</h3>");
            out.println("<h1>Servlet RoundsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }    }

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
