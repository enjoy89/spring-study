package hello.core.member;

/**
 * 회원 저장소 인터페이스
 */
public interface MemberRepository {

    void save(Member member);   // 회원 정보를 저장

    Member findById(Long memberId); // 회원 아이디 조회
}
