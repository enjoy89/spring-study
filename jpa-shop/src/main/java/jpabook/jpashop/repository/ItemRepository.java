package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager entityManager;

    /**
     * 상품 엔티티 저장
     * 상품 id가 없으면 신규로 보고 persist() 실행. 없으면 이미 데이터베이스에 저장된 엔티티를 수정한다고 보고 merge()를 실행.
     * @param item
     */
    public void save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
        } else {
            entityManager.merge(item);
        }
    }

    /**
     * 상품 하나 조회
     *
     * @param id
     */
    public Item findOne(Long id) {
        return entityManager.find(Item.class, id);
    }

    /**
     * 상품 모두 조회
     *
     * @return
     */
    public List<Item> findAll() {
        return entityManager.createQuery("select m from Item m", Item.class).getResultList();
    }
}
