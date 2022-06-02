package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 회원 서비스 구현체
 */
@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;    // 구현체에 의존하는 것이 아닌, 추상화에만 의존한다.

    @Autowired  // 의존관계를 자동으로 주입함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
