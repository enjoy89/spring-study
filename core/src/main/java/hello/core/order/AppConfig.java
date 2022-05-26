package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

/**
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다. (= 기획자)
 * 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다. (관심사의 분리)
 */
public class AppConfig {

    // MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부인 AppConfig에서 결정된다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자를 통해서 객체를 주입(연결) -> DI(Dependency Injection)
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 마찬가지로 OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부인 AppConfig에서 결정된다.
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy()); // 생성자를 통해서 객체를 주입(연결) -> DI(Dependency Injection)
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();  // 할인 정책 변경 시 이 코드만 수정하면 바로 적용 가능
    }
}
