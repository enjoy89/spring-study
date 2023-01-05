package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 상품 관리 기능: 상품 목록 / 상품 상세 / 상품 등록 / 상품 수정
@Repository
public class ItemRepository {

    //    private static final Map<Long, Item> store = new ConcurrentHashMap<>();   동시 멀티스레드 사용일 경우
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    /**
     * 상품 등록
     */
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    /**
     * 상품 조회
     */
    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     * 전체상품 조회
     */
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 상품 수정
     * 소규모 프로젝트이므로 따로 DTO를 만들지 않음
     */
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
<<<<<<< HEAD
        findItem.setWriter((updateParam.getWriter()));
=======
>>>>>>> 95d936f996c95c4afca81b1fa7f7df871d1433bf
    }

    /**
     * 상품 삭제
     */
    public void clearStore() {
        store.clear();
    }
}
