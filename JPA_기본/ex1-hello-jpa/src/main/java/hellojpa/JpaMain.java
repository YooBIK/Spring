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
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloA");

            /*
             * 여기까지는 아무 상태도 아님(비영속)
             */

            //여기서부터 영속 상태
            // DB에 저장된 것은 아님!
//            System.out.println("==== BEFORE ====");
//            em.persist(member);
//            System.out.println("==== AFTER ====");

//            // 1차 캐시로 인해 1번의 쿼리만 발생!
//            Member findMember1 = em.find(Member.class, 2L);
//            Member findMember2 = em.find(Member.class, 2L);
//            //동일성 보장
//            System.out.println("result = " + (findMember1 == findMember2));


            // INSERT 쿼리는 commit() 할 때 보낸다
            Member member1 = new Member(150L, "memberA");
            Member member2 = new Member(160L, "memberB");
            em.persist(member1);
            em.persist(member2);
            System.out.println("====================");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

//        /*
//        ** 조회(find) & 수정()
//         */
//        tx.begin();
//        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("helloUpdated");
//            tx.commit();
//        }catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//        emf.close();
    }
}
