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

import static org.junit.Assert.*;

@Transactional
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

    @Test
    public void 중복회원예외()throws Exception{
        //given
        //when
        //then
    }

}