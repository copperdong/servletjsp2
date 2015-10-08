package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import model.Customer;

public class CustomerDAO {
    private DataSource dataSource;
    public CustomerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private static final String GET_CUSTOMERS_SQL = 
            "SELECT * FROM customer";
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement pStatement = 
                        connection.prepareStatement(GET_CUSTOMERS_SQL);
                ResultSet resultSet = pStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddressLine1(resultSet.getString(
                        "addressline1"));
                customer.setAddressLine2(resultSet.getString(
                        "addressline2"));
                customer.setCity(resultSet.getString("city"));
                customer.setZipCode(resultSet.getString("zip"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    private static final String GET_CUSTOMER_SQL = 
            "SELECT * FROM customer WHERE customer_id = ?";
    public Customer getCustomer(int id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement pStatement = 
                        connection.prepareStatement(GET_CUSTOMER_SQL)) {
            pStatement.setInt(1, id);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddressLine1(resultSet.getString(
                        "addressline1"));
                customer.setAddressLine2(resultSet.getString(
                        "addressline2"));
                customer.setCity(resultSet.getString("city"));
                customer.setZipCode(resultSet.getString("zip"));
                return customer;
            }
            // don't worry about closing the ResultSet. It will be closed
            // by the PreparedStatement that generated it.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static final String UPDATE_CUSTOMER_SQL =
            "UPDATE customer SET name=?, addressline1=?, "
            + "addressline2=?, city=?, zip=? WHERE customer_id=?";
    public void updateCustomer(Customer customer) {
        System.out.println("Update id: " + customer.getId());
        try (Connection connection = dataSource.getConnection();
                PreparedStatement pStatement = 
                connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            pStatement.setString(1, customer.getName());
            pStatement.setString(2, customer.getAddressLine1());
            pStatement.setString(3, customer.getAddressLine2());
            pStatement.setString(4, customer.getCity());
            pStatement.setString(5, customer.getZipCode());
            pStatement.setInt(6, customer.getId());
            pStatement.execute();
            System.out.println("update done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
