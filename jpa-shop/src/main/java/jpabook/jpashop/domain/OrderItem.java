package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne  // 여러 종류의 상품이 존재하므로 다대일 관계
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne  // 하나의 주문에 여러개의 상품이 포함될 수 있으므로 다대일 관계
    @JoinColumn(name="order_id") // 외래키로 order_id를 참조
    private Order order;

    private int orderPrice; // 주문 가격

    private int count;  // 주문 수량
}
