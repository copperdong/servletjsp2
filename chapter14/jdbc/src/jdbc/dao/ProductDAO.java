package jdbc.dao;
import java.util.List;
import jdbc.model.Product;

public interface ProductDAO extends DAO {
    List<Product> getProducts() throws DAOException;
    void insert(Product product) throws DAOException;
}