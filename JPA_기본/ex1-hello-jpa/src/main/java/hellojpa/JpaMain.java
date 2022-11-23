package hellojpa;

import org.hibernate.Hibernate;

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

            Member member = new Member();
            member.setName("hello");

            em.persist(member);

            em.flush();
            em.clear();



            //Member findMember = em.find(Member.class, member.getId());

            /*
            * getReference의 결과로 프록시 객체 생성(해당 엔티티가 영속성 컨텍스트에 없는 경우)
            * 프록시 객체는 원본 엔티티를 상속받아서 생성(원본 엔티티 클래스와 프록시는 == 비교 X(항상 FALSE)) 타입 체크시 instanceof 사용!!
            * 프록시 객체는 원본 엔티티를 가리키고 있고, 실제 메서드 호출, 값 조회 등이 일어날 때, 영속성 컨텍스트에 초기화를 요청한다.
            * 사용자가 엔티티 클래스의 함수 호출 -> 프록시 객체는 초기화 요청 -> 영속성 컨텍스트는 DB를 조회해서 실제 엔티티 생성
            * -> 프록시 객체는 실제 엔티티 객체를 가리킴 -> 실제 엔티티의 함수를 실행함
            * 만약 영속성 컨텍스트에 해당 엔티티가 있으면 프록시가 아닌 엔티티를 반환
            *   - 최적화 측면에서도 엔티티 반환이 이득
            *   - JPA는 같은 트랜젝션 안에서 조회한 엔티티는 == 비교시 TRUE를 보장함!
             */
            Member refMember = em.getReference(Member.class, member.getId());  //proxy
            System.out.println("findMember = " + refMember.getClass());


            // 초기화 여부 확인
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            // 강제 초기화
            Hibernate.initialize(refMember);
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));



            /*
            * 영속성 컨텍스트가 관리하지 않는 프록시 객체를 초기화하려 하면, 에러 발생
             */
            em.clear();
            //refMember.getName();

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
