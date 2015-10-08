package action;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SaveBookAction {
    EntityManagerFactory emf;
    
    public SaveBookAction(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public void saveBook(Book book) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();
    }
}
