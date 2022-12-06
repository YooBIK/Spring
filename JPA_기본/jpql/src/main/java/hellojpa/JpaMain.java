package hellojpa;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try {

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUserName("memberA");
//            member.setAge(10);
//            member.setType(MemberType.ADMIN);
//            member.changeTeam(team);
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
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


//
//            /*
//            * -프로젝션
//            *   - SELECT 절에 조회 대상을 지정하는 것
//            *   - Entity, Embedded, Scala(그냥 값) 가능
//            *   - distinct로 중복 제거 가능!
//            */
//
//
//            /*
//            * Entity 프로젝션
//            *   -Entity 프로젝션으로 가져온 결과는 모두 영속성 컨텍스트에 관리가 된다 !
//             */
//            List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
//            Member findMember = resultList.get(0);
//            findMember.setAge(20);
//
//            /*
//            * Embedded 프로젝션
//             */
//            List<Address> addressList = em.createQuery("select o.address from Order o", Address.class).getResultList();
//
//            /*
//            * Scala 프로젝션
//            *   - 일반 SQL과 흡사
//             */
//            List queryList = em.createQuery("select distinct m.userName, m.age from Member m").getResultList();
//            // Query 타입으로 조회
//            Object o =  queryList.get(0);
//            Object[] queryResult = (Object[]) o;
//            System.out.println("userName : " + queryResult[0]);
//            System.out.println("age : " + queryResult[1]);
//
//            // Object[] 타입으로 조회
//            List<Object[]> objectList = em.createQuery("select distinct m.userName, m.age from Member m").getResultList();
//            Object[] objectResult = objectList.get(0);
//            System.out.println("userName : " + objectResult[0]);
//            System.out.println("age : " + objectResult[1]);
//
//            /*
//            * new 명령어를 사용하여 조회
//            *   - 단순 값을 DTO로 조회
//            *   - 패키지 명을 포함한 전체 클래스 명을 입력해야함
//            *   - 순서와 타입이 일치하는 생성자 필수 !
//             */
//            List<MemberDTO> memberDtoList = em.createQuery("select new hellojpa.MemberDTO(m.userName, m.age) from Member m", MemberDTO.class).getResultList();
//            for(MemberDTO m : memberDtoList){
//                System.out.println("userName : " + m.getUserName());
//                System.out.println("age : " + m.getAge());
//            }

//            /*
//             * Paging
//             * - setFirstResult, setMaxResults 메서드를 사용해서 페이징을 쉽게 처리할 수 있다.
//             * - 각 DB 방언으로 페이징을 처리함
//             *      - H2, MYSQL은 limit, offset을 사용
//             *      - Oracle은 rownum을 사용한 복잡한 쿼리를 자동으로 생성 및 실행해줌!!
//             */
//
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUserName("member" + i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//            em.flush();
//            em.clear();
//
//            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(20)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("resultList.size() = " + resultList.size());
//            for (Member m : resultList) {
//                System.out.println("member = " + m);
//            }

//            /*
//             * Join
//             */
//
//            //Inner Join
//            String innerJoinJPQL = "select m from Member m inner join m.team t"; // inner 생략 가능
//            List<Member> innerJoinResult = em.createQuery(innerJoinJPQL, Member.class).getResultList();
//
//            //Outer Join
//            String outerJoinJPQL = "select m from Member m left outer join m.team t"; // outer 생략 가능
//            List<Member> outerJoinResult = em.createQuery(outerJoinJPQL, Member.class).getResultList();
//
//            //Theta Join
//            String thetaJoinJPQL = "select m from Member m,Team t where m.userName = t.name";
//            em.createQuery(thetaJoinJPQL, Member.class).getResultList();

            /*
            * Sub Query
            * 대부분의 기능 제공
            * SELECT(JPA는 지원 X,하이버네이트에서 지원), WHERE, HAVING 절에서 서브쿼리 가능
            *   - FROM 절은 현재 불가능
            *   - 조인을 활용하자, 그래도 안된다면 Native Query, 혹은 각각의 Query의 결과를 Java Application에서 조합 및 해결
             */

//            /*
//            * JPQL 타입 표현
//            * - ENUM 클래스의 경우 패키지 경로를 모두 명시해줘야함!!
//            * - 그래서 보통 파라미터 바인딩을 해서 간략화함!
//            *
//             */
//
//            String jpql1 = "select m.userName, 'HELLO', true, m.type from Member m where m.type = hellojpa.MemberType.ADMIN";
//            String jpql2 = "select m.userName, 'HELLO', true, m.type from Member m where m.type = :memberType";
//            List<Object[]> resultList1 = em.createQuery(jpql1).getResultList();
//            List<Object[]> resultList2 = em.createQuery(jpql2).setParameter("memberType",MemberType.ADMIN).getResultList();
//
//            for(Object[] objects : resultList2){
//                System.out.println("objects[0] = " + objects[0]);
//                System.out.println("objects[1] = " + objects[1]);
//                System.out.println("objects[2] = " + objects[2]);
//                System.out.println("objects[3] = " + objects[3]);
//            }

//            /*
//            * CASE 식
//             */
//            String caseJpql = "select " +
//                    "case when m.age <= 10 then '학생요금' " +
//                    "when m.age >= 60 then '경로 요금' " +
//                    "else '일반요금' end " +
//                    "from Member m ";
//            List<String> resultList1 = em.createQuery(caseJpql, String.class).getResultList();
//            for (String s : resultList1){
//                System.out.println("s = " + s);
//            }
//
//            /*
//            * Coalesce
//            * 하나씩 조회해서 null이 아닌 값이 있으면 반환
//            *   - 이 경우 m.userName을 다 뒤져보고 있으면 그 값을 반환하고 없으면 '이름 없는 회원' 이 반환됨
//             */
//            String coalesceJpql = "select coalesce(m.userName,'이름 없는 회원') from Member m";
//            List<String> resultList2 = em.createQuery(coalesceJpql, String.class).getResultList();
//            for(String s : resultList2){
//                System.out.println("s = " + s);
//            }
//
//            /*
//            * NullIf
//            * 두 값이 일치하면 null 반환 다르면 첫째값 반환
//             */
//            String nullIfJpql = "select nullif(m.userName,'memberA') from Member m ";
//            List<String> resultList3 = em.createQuery(nullIfJpql, String.class).getResultList();
//            for(String s : resultList3){
//                System.out.println("s = " + s);
//            }

//            /*
//            * 경로 표현식
//             */
//            Team newTeam = new Team();
//            newTeam.setName("newTeam");
//            em.persist(newTeam);
//
//            Member newMember = new Member();
//            newMember.setUserName("newMember");
//            newMember.setTeam(newTeam);
//            em.persist(newMember);
//
//            /*
//             * 상태 필드에서는 더이상 탐색이 불가능 ex) m.userName.xxx -> 불가능
//             * 상태 필드에 대한 jpql은 예상한대로 SQL이 생성됨
//             */
//            String jpql1 = "select m.userName from Member m";
//            List<String> resultList1 = em.createQuery(jpql1, String.class).getResultList();
//            for(String s : resultList1){
//                System.out.println("s = " + s);
//            }
//
//            /*
//             * 단일 값 연관 경로의 경우 join이 발생한다. 또한 추가 탐색이 가능 ex)m.team.name
//             * 이를 묵시적 내부 조인이라고 한다.
//             * 되도록 묵시적 내부 조인이 발생하지 않도록 JPQL을 작성해야 한다.
//             *  - Query를 추적하는것이 굉장히 어렵고, 성능을 위한 튜닝이 어렵다.
//             */
//            String jpql2 = "select m.team from Member m";
//            List<Team> resultList2 = em.createQuery(jpql2, Team.class).getResultList();
//            for(Team t : resultList2){
//                System.out.println("t.getName() = " + t.getName());
//            }
//
//            /*
//             * 컬렉션 값 연관 경로도 마찬가지로 묵시적인 내부 조인이 발생한다.
//             * 하지만 이후 탐색을 할 수 없음
//             *  why? 컬렉션에서 어떤 값의 어떤 필드인지를 명시하는 것은 모호함 그래서 하이버네이트는 제약을 걸어둠
//             *  size는 가능
//             */
//            String jpql3 = "select t.members from Team t";
//            List<Collection> resultList3 = em.createQuery(jpql3, Collection.class).getResultList();
//            System.out.println("resultList3 = " + resultList3);

//            /*
//            * Fetch Join # 굉장히 중요함!!
//            * - JPQL에서 성능 최적화를 위해 제공하는 기능(SQL에는 존재하지 않음)
//            * - 연관된 엔티티와 컬렉션을 SQL 한번으로 함께 조회하는 기능
//            * - N + 1 문제를 해결하는 방법
//             */
//
//            Team teamA = new Team();
//            teamA.setName("teamA");
//            em.persist(teamA);
//
//            Team teamB = new Team();
//            teamB.setName("teamB");
//            em.persist(teamB);
//
//            Member member1 = new Member();
//            member1.setUserName("member1");
//            member1.setTeam(teamA);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUserName("member2");
//            member2.setTeam(teamA);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setUserName("member3");
//            member3.setTeam(teamB);
//            em.persist(member3);
//
//
//            em.flush();
//            em.clear();
//
//            /*
//            * 지연 로딩으로 설정했지만, N+1문제 발생!
//            *   resultList의 각 Member 객체마다 Team의 정보를 요청
//             */
//            String jpql = "select m from Member m";
//            List<Member> resultList1 = em.createQuery(jpql, Member.class).getResultList();
//            for(Member m : resultList1){
//                System.out.println("member = " + m.getUserName() + ", " + m.getTeam().getName());
//            }
//
//            em.clear();
//
//            /*
//            * fetch join을 사용하면 member의 team도 join을 통해 함께 영속성 컨텍스트로 가져옴
//            * 즉, Team 객체는 프록시가 아닌 실제 영속 엔티티임
//             */
//            String fetchJoinJpql1 = "select m from Member m join fetch m.team";
//            List<Member> resultList2 = em.createQuery(fetchJoinJpql1, Member.class).getResultList();
//            for(Member m : resultList2){
//                System.out.println("member = " + m.getUserName() + ", " + m.getTeam().getName());
//            }
//
//            /*
//             * 컬렉션도 fetch join 할 수 있음 // 하나의 컬렉션만 페치 조인 가능하다 !!
//             * DB 관점에서는 한 팀에 여러 멤버가 있으면, 여러 행으로 표현하는 방법 밖에 없다.
//             *  ex) 한 팀에 소속된 회원이 3명이면 SQL의 결과는 3줄이 나온다. 그렇기에 JPQL의 결과인 resultList3의 크기는 3이 나온다!!
//             *  -> distinct를 사용
//             *          - SQL의 DINTINCT와 함께 중복된(같은 식별자를 가진) 엔티티도 제거한다.
//             */
//            String fetchJoinJpql2 = "select DISTINCT t from Team t join fetch t.members";
//            List<Team> resultList3 = em.createQuery(fetchJoinJpql2, Team.class).getResultList();
//            for(Team t : resultList3){
//                System.out.println("t.getName() = " + t.getName());
//                for(Member m : t.getMembers()){
//                    System.out.println("m = " + m);
//                }
//            }

            /*
            * 다형성 쿼리
            * - 상속 관계 맵핑을 했을 때
            *       TYPE 명령어를 써서 특정 자식만 조회할 수 있음
            *       TREAT 명령어를 사용해 특정 자식 타입으로 변환해서 받을 수 있음(다운캐스팅과 유사함)
             */



            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
