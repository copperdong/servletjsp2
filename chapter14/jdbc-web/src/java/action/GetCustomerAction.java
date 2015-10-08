package action;

import dao.CustomerDAO;
import model.Customer;

public class GetCustomerAction {
    private CustomerDAO customerDAO;
    
    public GetCustomerAction(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    
    public Customer getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }
}
