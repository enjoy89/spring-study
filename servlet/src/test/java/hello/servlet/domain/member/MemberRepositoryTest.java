package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberrepository = MemberRepository.getInstance();   // 싱글톤

    @AfterEach
    void afterEach() {
        memberrepository.clearStore();
    }

    @Test
    void getInstance() {
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member saveMember = memberrepository.save(member);

        //then
        Member findMember = memberrepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("hello1", 20);
        Member member2 = new Member("hello2", 30);

        memberrepository.save(member1);
        memberrepository.save(member2);

        //when
        List<Member> result = memberrepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1, member2);
    }

    @Test
    void clearStore() {
    }
}