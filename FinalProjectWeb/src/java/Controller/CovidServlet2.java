/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import covid19.SQLHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import covid19.SQLHelper;

/**
 *
 * @author Abby
 */
public class CovidServlet2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            
            //getting all of the user chosen values from the form
            //choice is which option they choose
            //months is which month they want data for
            //country is which country they choose
            //startdate and end date is for the months for use with the SQL class
            int choice = Integer.parseInt(request.getParameter("choice"));
            String choiceStr = request.getParameter("choice");
            int months = Integer.parseInt(request.getParameter("months"));
            String monthsStr = request.getParameter("months");
            String country = request.getParameter("country");
            String startDate = "";
            String endDate = "";
            
            switch (months){//setting dates depending on which month they want
                case 1:
                    startDate = "1/22/2020";
                    endDate = "1/31/2020";
                    break;
                case 2:
                    startDate = "2/01/2020";
                    endDate = "2/29/2020";
                    break;
                case 3:
                    startDate = "3/01/2020";
                    endDate = "3/31/2020";
                    break;
                case 4:
                    startDate = "4/01/2020";
                    endDate = "4/30/2020";
                    break;
            }
            String results = "";
            
            
            //calling the accumulated data method using their choice
            //the country and the dates for the month they want
            results = SQLHelper.AccumulatedData(choice, country, startDate, endDate);
            
            
            //setting attributes for use with the jsp page
            request.setAttribute("results", results);
            request.setAttribute("choice", choiceStr);
            request.setAttribute("months", monthsStr);
            request.setAttribute("country", country);
            
            
            String url = "/results.jsp";//sends the user to the results.jsp page
            
        
        
        // forward request and response objects to specified URL
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
        
        
        
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
