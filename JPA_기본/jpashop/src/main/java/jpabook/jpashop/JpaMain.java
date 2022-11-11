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
            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setName("helloA");
            member.setTeam(team);
            entityManager.persist(member);

            /*
            영속성 컨텍스트의 쿼리들을 DB에 전송 + 영속성 컨택스트 초기화
             */
            entityManager.flush();
            entityManager.clear();

            /*
            연관관계 매핑을 사용해 컬렉션에서 객체를 꺼내는 것처럼 사용 가능
            mapped by 를 활용해 서로 참조 가능!!
             */
            Member findMember = entityManager.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(var v : members){
                System.out.println("member = " + v.getName());
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
