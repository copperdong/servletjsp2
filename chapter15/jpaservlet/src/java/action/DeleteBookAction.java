package action;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DeleteBookAction {
    EntityManagerFactory emf;
    
    public DeleteBookAction(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public void deleteBook(long bookId) {
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, bookId);
        if (book != null) {
            em.getTransaction().begin();
            em.remove(book);
            em.getTransaction().commit();
        }
        em.close();
    }
    
}
