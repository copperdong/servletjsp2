package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address;

@WebServlet(name = "DemoServlet", urlPatterns = {"/demo"})
public class DemoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<String> cities = new ArrayList<>();
        cities.add("Paris");
        cities.add("Strasbourg");
        cities.add("London");
        cities.add("New York");
        cities.add("Beijing");
        cities.add("Amsterdam");
        cities.add("San Francisco");
        request.setAttribute("cities", cities);
        
        Address a1 = new Address("100 Rue Lilas", "Ottawa", "10004");
        Address a2 = new Address("99 Aloha Street", "Montreal", "20940");
        List<Address> addresses = new ArrayList<>();
        addresses.add(a1);
        addresses.add(a2);
        request.setAttribute("addresses", addresses);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        rd.forward(request, response);

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
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
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
