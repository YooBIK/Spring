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
//            Member member = new Member();
//            member.setUserName("member1");
//            em.persist(member);
//
//            /*
//            * "select m from Member as m where m.age > 18"
//            * JPQL에서, Entity와 속성은 대소문자를 구분 O
//            * 하지만 키워드는 대소문자 구분 X
//            * 별칭 지정 필수(as 는 생략 가능)
//             */
//
//            /*
//            * TypeQuery : 반환 형식이 명활할 때 사용
//            * Query : 반환 형식이 명확하지 않을 때 사용
//             */
////            TypedQuery<Member> typeQueryResult = em.createQuery("select m from Member m", Member.class);    // 반환 타입 명시
////            Query queryResult = em.createQuery("select m.age, m.userName from Member m");                                             // 반환 타입 명시 X
//
////            /*
////            * 결과 값이 여러개
////            * 결과가 없다면 빈 List 반환
////             */
////            List<Member> resultList = typeQueryResult.getResultList();
//
////            /*
////            * 결과 값이 정확하게 하나
////            * 결과가 없거나, 여러개면 예외 발생
////             */
////            Member result = typeQueryResult.getSingleResult();
//
//            /*
//            * Chaining 으로 쓰자 이게 더 편하다 !
//            * 파라미터 바인딩은 이름 기준, 위치 기준 모두 제공
//            *   - 이름 기준이 훨씬 명확하고 이후 유지보수에 용이!!
//             */
//            List<Member> resultList = em.createQuery("select m from Member m where m.userName = :param and m.age > ?1 ", Member.class)
//                    .setParameter("param", "member1").setParameter(1,18L)
//                    .getResultList();

            Member member = new Member();
            member.setUserName("memberA");
            em.persist(member);

            /*
            * -프로젝션
            *   - SELECT 절에 조회 대상을 지정하는 것
            *   - Entity, Embedded, Scala(그냥 값) 가능
            *   - distinct로 중복 제거 가능!
            */


            /*
            * Entity 프로젝션
            *   -Entity 프로젝션으로 가져온 결과는 모두 영속성 컨텍스트에 관리가 된다 !
             */
            List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
            Member findMember = resultList.get(0);
            findMember.setAge(20);

            /*
            * Embedded 프로젝션
             */
            List<Address> addressList = em.createQuery("select o.address from Order o", Address.class).getResultList();

            /*
            * Scala 프로젝션
            *   - 일반 SQL과 흡사
             */
            List queryList = em.createQuery("select distinct m.userName, m.age from Member m").getResultList();
            // Query 타입으로 조회
            Object o =  queryList.get(0);
            Object[] queryResult = (Object[]) o;
            System.out.println("userName : " + queryResult[0]);
            System.out.println("age : " + queryResult[1]);

            // Object[] 타입으로 조회
            List<Object[]> objectList = em.createQuery("select distinct m.userName, m.age from Member m").getResultList();
            Object[] objectResult = objectList.get(0);
            System.out.println("userName : " + objectResult[0]);
            System.out.println("age : " + objectResult[1]);

            /*
            * new 명령어를 사용하여 조회
            *   - 단순 값을 DTO로 조회
            *   - 패키지 명을 포함한 전체 클래스 명을 입력해야함
            *   - 순서와 타입이 일치하는 생성자 필수 !
             */
            List<MemberDTO> memberDtoList = em.createQuery("select new hellojpa.MemberDTO(m.userName, m.age) from Member m", MemberDTO.class).getResultList();
            for(MemberDTO m : memberDtoList){
                System.out.println("userName : " + m.getUserName());
                System.out.println("age : " + m.getAge());
            }


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
