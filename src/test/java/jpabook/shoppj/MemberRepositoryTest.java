package jpabook.shoppj;

import jpabook.shoppj.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {
//    @Autowired
//    private MemberRepository memberRepository;

//    @Test
//    @Transactional
//    public void memberTest() {
//        Member member = new Member();
//        member.setUserName("memberA");
//        long id = memberRepository.save(member);
//
//        Member findMember = memberRepository.find(id);
//
//        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
//        Assertions.assertThat(member.getUserName()).isEqualTo(findMember.getUserName());
//    }
//
//    @Test
//    void find() {
//    }
}