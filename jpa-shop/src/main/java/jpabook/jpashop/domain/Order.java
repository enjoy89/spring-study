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

    /**
     * 주문 엔티티 생성
     * 주문 회원, 배송정보, 주문상품의 정보를 받아서 실제 주문 엔티티를 생성한다.
     *
     * @param member
     * @param delivery
     * @param orderItems
     * @return
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    // 비즈니즈 로직
    /**
     * 주문 취소
     * 주문 상태를 취소로 변경하고, 주문상품에 주문 취소를 알림
     * 만약 이미 배송을 완료한 상품이면 주문을 취소하지 못하도록 예외를 발생시킨
     */
    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancle();
        }
    }

    /**
     * 전체 주문 가격 조회
     *
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
