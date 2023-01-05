package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

// @Data ->  핵심 데이터 사용에 위험함
@Getter
@Setter
public class Item {

    // 상품 도메인 모델
    private Long id;            // 상품 id
    private String itemName;    // 상품명
    private Integer price;      // 상품 가격 (가격과 수량이 Integer인 이유: null 값이 존재할 가능성이 있기 때문)
    private Integer quantity;   // 상품 수량
    private String writer;

    /**
     * 기본 생성자
     */
    public Item() {}

    /**
     * 상품 id를 제외한 생성자
     */
    public Item(String itemName, Integer price, Integer quantity, String writer) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.writer = writer;
    }
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
