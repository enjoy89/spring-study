package hello.core.order;

/**
 * 주문 서비스 인터페이스
 */
public interface OrderService {
    /**
     * 회원 아이디, 상품명, 가격을 넘겨 받고 주문 결과를 반환
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
