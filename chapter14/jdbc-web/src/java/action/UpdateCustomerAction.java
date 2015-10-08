package action;

import dao.CustomerDAO;
import model.Customer;

public class UpdateCustomerAction {
    private CustomerDAO customerDAO;
    
    public UpdateCustomerAction(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }
}
