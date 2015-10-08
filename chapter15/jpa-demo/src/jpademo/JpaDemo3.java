package jpademo;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaDemo3 {

    public static final String JPA_PU = "jpa-demoPU";

    public static void main(String[] args) {
        JpaDemo3 demo = new JpaDemo3();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(JPA_PU);
        EntityManager em = emf.createEntityManager();
        Map<String, Object> properties = emf.getProperties();
        properties.forEach((key, value)-> {
            System.out.println(key + ":" + value);
        });
        em.close();
        emf.close();
    }
}
