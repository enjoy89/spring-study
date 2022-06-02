package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 주문 서비스 구현체
 */
@Component
public class OrderServiceImpl implements OrderService {

    // 인터페이스에만 의존하며 DIP를 지키고 있음
    // OrderServiceImpl는 할인 정책으로 정액 할인 정책이 적용될지, 정률 할인 정책이 적용될지 전혀 모르고 상관하지도 않음
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * FixDiscountPolicy에서 RateDiscountPolicy로 변경하면 할인 정책도 함께 변경할 수 있다?
     * 이러한 방식도 틀린 것은 아니다. 하지만 DIP를 위반하므로 바람직하지 않다.
     * 주문 서비스 구현체가 DiscountPolicy의 구현체인 FixDiscountPolicy와 RateDiscountPolicy까지 의존하고 있기 때문에 DIP를 위반함.
     * 주문 서비스 구현체는 인터페이스인 DiscountPolicy(추상화)만 의존하면 된다.
     */

    @Autowired // 여러 의존관존도 한번에 주입받을 수 있다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 주문 생성 요청이 오면
     * 1. 회원 정보를 조회한다.
     * 2. 할인 정책을 적용한다.
     * 3. 주문 객체를 생성하여 반환한다.
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
