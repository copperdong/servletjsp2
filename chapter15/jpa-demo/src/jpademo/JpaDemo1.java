package jpademo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaDemo1 {

    public static final String JPA_PU = "jpa-demoPU";
    public void createElephants() {
        Elephant elephant1 = new Elephant();
        elephant1.setName("Kitty");
        Elephant elephant2 = new Elephant();
        elephant2.setName("Minnie");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(JPA_PU);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(elephant1);
        em.persist(elephant2);
        em.getTransaction().commit();
        System.out.println("created with id " + elephant1.getId());
        System.out.println("created with id " + elephant2.getId());
        em.close();
        emf.close();
    }
    
    public List<Elephant> getElephantsByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(JPA_PU);
        EntityManager em = emf.createEntityManager();        
        // read only operation, no need for transaction
        TypedQuery byNameQuery = em.createQuery(
                "SELECT e FROM Elephant e WHERE e.name = '" + name + "'",
                Elephant.class);
        List<Elephant> elephants = byNameQuery.getResultList();
        em.close();
        emf.close();
        return elephants;
    }
    
    public static void main(String[] args) {
        JpaDemo1 demo = new JpaDemo1();
        demo.createElephants();
        // search is case-sensitive
        List<Elephant> elephants = demo.getElephantsByName("Kitty");
        for (Elephant elephant : elephants) {
            System.out.println("id:" + elephant.getId() + ", name:" + elephant.getName());
        }
    }
}
