package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 테이블 이름을 지정
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;    // 주문 id

    @ManyToOne(fetch = FetchType.LAZY)  // 주문은 한 명의 상품이 여러 상품을 할 수 있으므로 다대일 관계
    @JoinColumn(name = "member_id") // 외래키로 member_id를 참조
    private Member member;  // 주문 회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")   // 연관관계의 주인
    private Delivery delivery;  // 배송 정보

    private LocalDateTime orderDate;    // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    // 연관관계 메소드 (양방향 관계일 때 한 쪽만 셋팅하면 다른쪽은 알아서 셋팅)
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}