package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();  // key = 회원 아이디, value = 회원
    private static long sequence = 0L;

    // 싱글톤으로 구현
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    // 회원 등록
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 회원 단일 조회
    public Member findById(Long id) {
        return store.get(id);
    }

    // 회원 전체 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
