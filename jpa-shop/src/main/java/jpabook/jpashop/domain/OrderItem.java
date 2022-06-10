package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 여러 종류의 상품이 존재하므로 다대일 관계
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)  // 하나의 주문에 여러개의 상품이 포함될 수 있으므로 다대일 관계
    @JoinColumn(name = "order_id") // 외래키로 order_id를 참조
    private Order order;

    private int orderPrice; // 주문 가격

    private int count;  // 주문 수량

    // 생성 메소드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    // 비즈니스 로직

    /**
     * 주문 취소로 재고 수량을 증가
     */
    public void cancle() {
        getItem().addStock(count);
    }

    /**
     * 주문 상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return orderPrice * count;
    }
}
