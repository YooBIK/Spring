package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{

            /*
            2. 연관 관계 주인인 Member에서 Team을 추가해야함!!
             */
            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setName("helloA");
            member.setTeam(team);
            entityManager.persist(member);

            /*
             3. 하지만, 영속성 컨텍스트에 담겨있을때는, LIST가 비어있음!! (순수한 객체 상태이다.)
                !! 웬만하면 양방향 연관 관계에서 양쪽 다 값을 세팅하자 !!
                !! 이 때, 연관관계 편의 메소드를 작성하자. !!
                여기선 Member의 setTeam에 로직을 추가했다.
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


            entityManager.flush();
            entityManager.clear();

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
