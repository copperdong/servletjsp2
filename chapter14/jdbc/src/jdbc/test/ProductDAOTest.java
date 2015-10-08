package jdbc.test;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import jdbc.dao.DAOException;
import jdbc.dao.ProductDAO;
import jdbc.dao.ProductDAOImpl;
import jdbc.model.Product;

public class ProductDAOTest {
    private static final String CREATE_TABLE_SQL = 
            "CREATE TABLE products ("
            + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,"
            + "name VARCHAR(255) NOT NULL,"
            + "description VARCHAR(1000) default NULL,"
            + "price DECIMAL(10,2) NOT NULL,"
            + "PRIMARY KEY  (id))";
    private static void createDatabase() {
        String dbUrl = "jdbc:derby:daotest;create=true";
        try (Connection connection = 
                DriverManager.getConnection(dbUrl);
                Statement statement = 
                        connection.createStatement()) {
            statement.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createDatabase();
        
        Product product = new Product();
        product.setName("Kiano tablet keyboard");
        product.setDescription("Low cost tablet keyboard, "
                + "compatible will all Android devices");
        product.setPrice(new BigDecimal(24.95));
        
        ProductDAO productDAO = new ProductDAOImpl();
        try {
            productDAO.insert(product);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        
        List<Product> products = null;
        try {
            products = productDAO.getProducts();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        products.stream().forEach(System.out::println);
    }
}