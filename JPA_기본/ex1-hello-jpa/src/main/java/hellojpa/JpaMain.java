package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        //EntityManagerFactory 가져오기, persistenceUnitName은 persistence.xml에 명시
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager 가져오기
        EntityManager em = emf.createEntityManager();

        //JPA의 모든 데이터 변경은 Transaction 안에서 이루어져야함
        EntityTransaction tx = em.getTransaction();

        /*
        ** 회원 저장 로직
         */
        tx.begin();
        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("helloA");

            em.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            //em.close();
        }

        /*
        ** 조회(find) & 수정()
         */
        tx.begin();
        try {
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloUpdated");
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
