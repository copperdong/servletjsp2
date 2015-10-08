package jpademo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaDemo2 {

    public static final String JPA_PU = "jpa-demoPU";

    public static void main(String[] args) {
        Office office1 = new Office();
        office1.setName("Head Office");
        Office office2 = new Office();
        office2.setName("Sales Office");
        
        Employee emp1 = new Employee();
        emp1.setEmpName("Jimmy Yesman");
        Employee emp2 = new Employee();
        emp2.setEmpName("Rose Biteman");
        Employee emp3 = new Employee();
        emp3.setEmpName("Angie Rosintia");
        Employee emp4 = new Employee();
        emp4.setEmpName("Joshua Bitterman");
        
        office1.getEmployees().add(emp1);
        office1.getEmployees().add(emp2);
        office2.getEmployees().add(emp3);
        office2.getEmployees().add(emp4);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(JPA_PU);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(office1);
        em.persist(office2);
        em.getTransaction().commit();
        
        List<Office> offices = em.createQuery("SELECT o FROM Office o")
                .getResultList();
        
        for (Office office : offices) {
            System.out.println("Office:" + office.getName());
            List<Employee> employees = office.getEmployees();
            for (Employee employee : employees) {
                System.out.println("Emp:" + employee.getEmpName());
            }
            System.out.println("===================================");
        }
        
        
        em.close();
        emf.close();
    }
}
