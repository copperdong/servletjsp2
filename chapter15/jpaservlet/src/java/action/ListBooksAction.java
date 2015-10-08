package action;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ListBooksAction {
    EntityManagerFactory emf;
    
    public ListBooksAction(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public List<Book> getBooks() {
        EntityManager em = emf.createEntityManager();
        List<Book> books = em.createQuery("SELECT e FROM Book e").getResultList();
        em.close();
        return books;
    }
    
}
