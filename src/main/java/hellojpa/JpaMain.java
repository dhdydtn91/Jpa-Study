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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);
            Team teamB = new Team();
            team.setName("teamA");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member1");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId());
            List<Member> members= em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

            // 피치 못하게 여러개를 한번에 가져와야 할경우  fetch 조인 사용
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
