package hello.core.member;

/**
 * 회원 서비스 구현체
 */
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;    // 구현체에 의존하는 것이 아닌, 추상화에만 의존한다.

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
}
