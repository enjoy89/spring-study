package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class itemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    /**
     * 각 테스트가 끝날 때마다 실행
     */
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();    // 다음 테스트에 영향이 없도록 데이터를 모두 비움
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item saveItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    void findAll() {
        // given
        Item item_1 = new Item("itemA", 10000, 10);
        Item item_2 = new Item("itemB", 20000, 20);
        Item item_3 = new Item("itemC", 30000, 30);

        itemRepository.save(item_1);
        itemRepository.save(item_2);
        itemRepository.save(item_3);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(item_1, item_2, item_3);
    }

    @Test
    void updateItem() {
        // given 
        Item item = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();

        // when
        Item updateParam = new Item("itemA_2", 20000, 20);
        itemRepository.update(itemId, updateParam);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
