package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@Transactional  //테스트의 경우 테스트 이후 롤백
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입()throws Exception{
        //given
        Member member = new Member();
        member.setName("MemberA");

        //when
        Long savedId = memberService.join(member);
        //then
        assertEquals(member,memberRepository.findOne(savedId));

    }

    @Test(expected= IllegalStateException.class)
    public void 중복회원예외()throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("yoo");

        Member member2 = new Member();
        member2.setName("yoo");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외 발생 해야함!


        //then
        fail("예외가 발생해야 한다.");
    }

}