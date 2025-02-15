package jpabook.shoppj.service;

import jpabook.shoppj.domain.Member;
import jpabook.shoppj.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(false)
    void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");

        long savedId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    void 중복회원() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}