package servlet;

import action.GetCustomerAction;
import action.GetCustomersAction;
import action.UpdateCustomerAction;
import dao.CustomerDAO;
import java.io.IOException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.Customer;

@WebServlet(urlPatterns = {"/customers", "/edit-customer", 
        "/update-customer"})
public class ControllerServlet extends HttpServlet {
    private DataSource dataSource;
    @Override
    public void init() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup(
                    "java:comp/env/jdbc/sampleDataSource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }
    
    private void process(HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/css/")) {
            return;
        }
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        boolean redirect = false;
        switch (action) {
            case "customers":
                dispatchUrl = "/jsp/Customers.jsp";
                GetCustomersAction getCustomersAction = 
                        new GetCustomersAction(
                                new CustomerDAO(dataSource));
                List<Customer> customers = getCustomersAction.getCustomers();
                request.setAttribute("customers", customers);
                break;
            case "edit-customer":
                dispatchUrl = "/jsp/EditCustomer.jsp";
                int id = 0;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                GetCustomerAction getCustomerAction = 
                        new GetCustomerAction(
                                new CustomerDAO(dataSource));
                Customer customer = getCustomerAction.getCustomer(
                        id);
                request.setAttribute("customer", customer);
                break;
            case "update-customer":
                redirect = true;
                id = 0;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                }
                customer = new Customer();
                customer.setId(id);
                customer.setName(request.getParameter("name"));
                customer.setAddressLine1(
                        request.getParameter("addressLine1"));
                customer.setAddressLine2(
                        request.getParameter("addressLine2"));
                customer.setCity(request.getParameter("city"));
                customer.setZipCode(request.getParameter("zipCode"));
                UpdateCustomerAction updateCustomerAction = 
                        new UpdateCustomerAction(
                                new CustomerDAO(dataSource));
                updateCustomerAction.updateCustomer(customer);
                break;
        }
        if (redirect) {
            response.sendRedirect("customers");
        } else {
            RequestDispatcher rd
                    = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}