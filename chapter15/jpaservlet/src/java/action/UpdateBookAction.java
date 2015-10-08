package action;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UpdateBookAction {
    EntityManagerFactory emf;
    
    public UpdateBookAction(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public void updateBook(Book book) {
        EntityManager em = emf.createEntityManager();
        Book savedBook = em.find(Book.class, book.getId());
        if (savedBook != null) {
            em.getTransaction().begin();
            savedBook.setIsbn(book.getIsbn());
            savedBook.setTitle(book.getTitle());
            savedBook.setAuthor(book.getAuthor());
            savedBook.setPrice(book.getPrice());
            em.getTransaction().commit();
        }
        em.close();
    }
}
