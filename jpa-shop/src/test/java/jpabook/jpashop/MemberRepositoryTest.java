//package jpabook.jpashop;
//
//import jpabook.jpashop.domain.Member;
//import jpabook.jpashop.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)    // 스프링 관련된 테스트를 할 것임을 명시
//@SpringBootTest
//public class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMember() {
//        // given
//        Member member = new Member();
////        member.setUsername("memberA");
//
//        // when
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        // then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
////        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member);    // 동일성 보장
//    }
//
//}