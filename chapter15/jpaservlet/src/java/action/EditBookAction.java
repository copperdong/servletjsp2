package action;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EditBookAction {
    EntityManagerFactory emf;
    
    public EditBookAction(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Book getBook(long bookId) {
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, bookId);
        em.close();
        return book;
    }
}
