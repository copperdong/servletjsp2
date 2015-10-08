package action;

import dao.CustomerDAO;
import java.util.List;
import model.Customer;

public class GetCustomersAction {
    private CustomerDAO customerDAO;
    
    public GetCustomersAction(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }
}
