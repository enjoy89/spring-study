package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 회원을 저장소에 저장
     * @param member
     */
    public void save(Member member) {
        entityManager.persist(member);
    }

    /**
     * 회원 하나를 조회
     * @param id
     * @return 조회된 회원의 정보
     */
    public Member findOne(Long id) {
        return entityManager.find(Member.class, id);
    }

    /**
     * 회원 전체를 조회
     * @return 회원 전체의 리스트
     */
    public List<Member> findAll() {
        return entityManager.createQuery("select m from Member m", Member.class).getResultList();
    }

    /**
     * 회원 이름으로 조회
     * @param name
     * @return 이름으로 조회된 회원의 정보 리스트
     */
    public List<Member> findByName(String name) {
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
