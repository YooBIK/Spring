package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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


//            //INSERT 쿼리는 commit() 할 때 보낸다. 정확히는 flush, (commit 또는 JPQL 실행 시)
//            Member member1 = new Member(150L, "memberA");
//            Member member2 = new Member(160L, "memberB");
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("====================");

            /*
             * detach 하면 영속성 컨테이너가 더이상 관리하지 않는다.
             * 따라서, 변경이 일어나도 변경 감지 대상이 아님.
             */
//            Member findMember = em.find(Member.class,1L);
//            findMember.setName("updateName");
//            em.detach(findMember);

//            Movie movie = new Movie();
//            movie.setDirector("directorA");
//            movie.setActor("actorA");
//            movie.setName("nameA");
//            movie.setPrice(1000);
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);


//             /*
//            2. 연관 관계 주인인 Member에서 Team을 추가해야함!!
//             */
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("helloA");
//            member.setTeam(team);
//            em.persist(member);

            /*
             3. 하지만, 영속성 컨텍스트에 담겨있을때는, LIST가 비어있음!! (순수한 객체 상태이다.)
                !! 웬만하면 양방향 연관 관계에서 양쪽 다 값을 세팅하자 !!
                !! 이 때, 연관관계 편의 메소드를 작성하자. !!
                여기선 Member의 setTeam에 로직을 추가했다. setter를 사용하기 보단 새로 정의하자 !!
             */
            //team.getMembers().add(member);

//            /*
//              **연관관계의 주인에 값을 입력해야함**
//              ** 1. Team은 연관관계의 주인이 아니기 때문에, 읽기 전용이다. 즉 DB에 입력한 사항이 반영되지 않음!!
//             */
//            Team team = new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member);
//            entityManager.persist(team);
//
//            Member member = new Member();
//            member.setName("hello");
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//
//
//            Member findMember = em.find(Member.class, member.getId());

//            /*
//            * getReference의 결과로 프록시 객체 생성(해당 엔티티가 영속성 컨텍스트에 없는 경우)
//            * 프록시 객체는 원본 엔티티를 상속받아서 생성(원본 엔티티 클래스와 프록시는 == 비교 X(항상 FALSE)) 타입 체크시 instanceof 사용!!
//            * 프록시 객체는 원본 엔티티를 가리키고 있고, 실제 메서드 호출, 값 조회 등이 일어날 때, 영속성 컨텍스트에 초기화를 요청한다.
//            * 사용자가 엔티티 클래스의 함수 호출 -> 프록시 객체는 초기화 요청 -> 영속성 컨텍스트는 DB를 조회해서 실제 엔티티 생성
//            * -> 프록시 객체는 실제 엔티티 객체를 가리킴 -> 실제 엔티티의 함수를 실행함
//            * 만약 영속성 컨텍스트에 해당 엔티티가 있으면 프록시가 아닌 엔티티를 반환
//            *   - 최적화 측면에서도 엔티티 반환이 이득
//            *   - JPA는 같은 트랜젝션 안에서 조회한 엔티티는 == 비교시 TRUE를 보장함!
//             */
//            Member refMember = em.getReference(Member.class, member.getId());  //proxy
//            System.out.println("findMember = " + refMember.getClass());
//
//
//            // 초기화 여부 확인
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
//
//            // 강제 초기화
//            Hibernate.initialize(refMember);
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
//
//
//
//            /*
//            * 영속성 컨텍스트가 관리하지 않는 프록시 객체를 초기화하려 하면, 에러 발생
//             */
//            em.clear();
//            //refMember.getName();

//            /*
//             * 즉시 로딩으로 설정하면 JPQL 사용할 때, N+1 문제 발생, 1번의 쿼리에 결과에 각각 SELECT 쿼리 (조회결과 N개에 각각 그래서 N+1 문제)
//             */
//            Team teamA = new Team();
//            teamA.setName("teamA");
//
//            Team teamB = new Team();
//            teamB.setName("teamB");
//
//            em.persist(teamA);
//            em.persist(teamB);
//
//            Member memberA = new Member();
//            memberA.setName("memberA");
//            memberA.setTeam(teamA);
//
//            Member memberB = new Member();
//            memberB.setName("memberB");
//            memberB.setTeam(teamB);
//
//            em.persist(memberA);
//            em.persist(memberB);
//
//            em.flush();
//            em.clear();
//
//            /*
//             * 즉시 로딩으로 설정하면 JPQL 사용할 때, N+1 문제 발생, 1번의 쿼리에 결과에 각각 SELECT 쿼리 (조회결과 N개에 각각 그래서 N+1 문제)
//             */
//            List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
//
//
//
//            /*
//             * 지연 로딩으로 설정하면, 해당 엔티티를 프록시로 받아옴
//             * 즉시 로딩으로 설정하면, 한번의 쿼리로 Member,Team 다 가져옴
//             */
//            Member findMember = em.find(Member.class, memberB.getId());
//            System.out.println("findMember.getTeam() = " + findMember.getTeam().getClass());
//
//            // 프록시의 값을 사용할 때, 프록시를 초기화함, (쿼리가 발생한다. (프록시를 가져올 때가 아닌 메서드나 필드값을 사용할 때임!))
//            System.out.println("=================");
//            findMember.getTeam().getName(); // 이때 초기화됨!
//            System.out.println("=================");



//            Parent parent = new Parent();
//            Child childA = new Child();
//            Child childB = new Child();
//            parent.addChild(childA);
//            parent.addChild(childB);
//
//            /*
//            * CASCADE 옵션을 사용하지 않으면 하나하나 persist 해줘야함!
//            * CASCADE 옵션을 쓰면 parent만 persist해도 나머지도 됨!
//             */
//            em.persist(parent);
//            em.persist(childA);
//            em.persist(childB);
//
//            /*
//            orphanRemoval 옵션을 사용하면 부모가 관리하지 않는 객체는 자동으로 지워버림!
//             */
//            parent.getChildList().remove(0);


//            Member member = new Member();
//            member.setName("member1");
//            member.setPeriod(new Period(LocalDateTime.of(1996,5,25,19,30), LocalDateTime.now()));
//            member.setHomeAddress(new Address("서울","독막로","20길"));
//            em.persist(member);


//            /*
//            값 타입을 공유해버리면, 의도치 않게 문제가 발생할 수 있음, 찾기 매우 어려움
//            그때그때 값을 복사해서 사용하자!
//            수정자를 막았으면, 수정할 때 마다 새로운 객체를 생성해서 넣어주자
//             */
//            Address address = new Address("서울", "길거리", "10000");
//            Member member1 = new Member();
//            member1.setName("member1");
//            member1.setHomeAddress(address);
//
//            em.persist(member1);
//
//            /*
//            값을 복사한 새로운 객체 생성
//             */
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//            Member member2 = new Member();
//            member2.setName("member2");
//            member2.setHomeAddress(copyAddress);
//
//            System.out.println("========================");
//            System.out.println(address.equals(copyAddress));
//            System.out.println("========================");
//
//            em.persist(member2);


//            /*
//            * 값 타입 컬렉션 사용 예제
//            * member 만 persist 해도 모두 반영됨(persist 됨)
//            * 영속성 전이, 고아 객체 제거 기능이 포함되어 있다고 볼 수 있음
//             */
//            Member member = new Member();
//            member.setName("member1");
//            member.setHomeAddress(new Address("homeCity", "homeStreet", "10000"));
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("족발");
//
//            member.getAddressHistory().add(new AddressEntity("old1", "old1", "10000"));
//            member.getAddressHistory().add(new AddressEntity("old2", "old2", "10000"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//
//            /*
//            * 값 타입 컬렉션의 경우, 지연 로딩 전략 사용!!(그냥 값 타입은 X)
//             */
//            System.out.println("================START==================");
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("================ END ==================");
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            System.out.println("=========================================");
//            for(String favoriteFood : favoriteFoods){
//                System.out.println("favorite food = " + favoriteFood);
//            }
//            List<AddressEntity> addressHistory = findMember.getAddressHistory();
//            System.out.println("=========================================");
//            for(AddressEntity address : addressHistory){
//                System.out.println("address = " + address.getAddress().getCity());
//            }
//
//            /*
//            * 값 타입 수정
//            * 값 타입의 수정은 객체 자체를 바꿔야 안전하다.
//            * 값 타입 컬렉션의 경우도 마찬가지 ! 지우고 새로 넣자 !
//            * 값 타입은 좋은 방법이 아님, 굳이 사용할 필요 없음 !
//             */
//            findMember.setHomeAddress(new Address("newCity","newStreet","newZipcode"));
//
//
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("피자");
//
//            /*
//            * 대부분의 자바 컬렉션은 내용물을 찾을 때 ,equals or hashcode 사용! 꼭 구현 잘 해두자!
//             */
//            findMember.getAddressHistory().remove(new AddressEntity("old1", "old1", "10000"));
//            findMember.getAddressHistory().add(new AddressEntity("newCity1","newStreet1","newZipcode1"));


            /*
            * JPQL : SQL을 추상화 !! 테이블이 아닌 엔티티를 대상으로 하는 쿼리(객체 지향 쿼라)
            *        특정 DB에 의존 X
             */
            List<Member> resultList = em.createQuery("select m from Member m where m.name like '%member%'", Member.class).getResultList();
            for(Member m : resultList){
                System.out.println(m.getName());
            }

            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            /*
            * 순수 Query도 사용 가능!
            * flush,commit 그리고 Entity Manager가 쿼리를 실행할 때, 영속성 컨텍스트의 쿼리들을 실행함.
            * 그러므로 JPA에서 제공하는 기능이 아닌 다른 방법(JDBC, MYBATIS 등)으로 DB에 접근할 때는 미리 flush해서 DB에 반영시켜 둬야함!!
             */
            List<Member> nativeQueryResultList = em.createNativeQuery("select * from MEMBER", Member.class).getResultList();


            for(Member m : nativeQueryResultList){
                System.out.println(m.getName());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
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
        emf.close();
    }
}
