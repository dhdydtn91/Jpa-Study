package hellojpa;

        import javax.persistence.*;
        import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city","street","100");

            Member member = new Member();
            member.setUsername("용수");
            member.setHomeAddress(address);
            em.persist(member);

            Address newAddress = new Address("NewCity",address.getStreet(),address.getZipcode());
            member.setHomeAddress(newAddress);

            tx.commit();
        } catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
