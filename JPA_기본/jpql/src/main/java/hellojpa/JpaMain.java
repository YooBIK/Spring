package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try{
            Member member = new Member();
            member.setUserName("asdf");
            em.persist(member);

            /*
            * "select m from Member as m where m.age > 18"
            * JPQL에서, Entity와 속성은 대소문자를 구분 O
            * 하지만 키워드는 대소문자 구분 X
            * 별칭 지정 필수(as 는 생략 가능)
             */

            /*
            * TypeQuery : 반환 형식이 명활할 때 사용
            * Query : 반환 형식이 명확하지 않을 때 사용
             */
//            TypedQuery<Member> typeQueryResult = em.createQuery("select m from Member m", Member.class);    // 반환 타입 명시
//            Query queryResult = em.createQuery("select m.age, m.userName from Member m");                                             // 반환 타입 명시 X

//            /*
//            * 결과 값이 여러개
//            * 결과가 없다면 빈 List 반환
//             */
//            List<Member> resultList = typeQueryResult.getResultList();

//            /*
//            * 결과 값이 정확하게 하나
//            * 결과가 없거나, 여러개면 예외 발생
//             */
//            Member result = typeQueryResult.getSingleResult();

            /*
            * Chaining 으로 쓰자 이게 더 편하다 !
            * 파라미터 바인딩은 이름 기준, 위치 기준 모두 제공
            *   - 이름 기준이 훨씬 명확하고 이후 유지보수에 용이!!
             */
            List<Member> resultList1 = em.createQuery("select m from Member m where m.userName = :param and m.age > ?1 ", Member.class)
                    .setParameter("param", "member1").setParameter(1,18L)
                    .getResultList();


            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}
