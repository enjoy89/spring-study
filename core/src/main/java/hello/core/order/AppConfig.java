package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
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
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성자를 통해서 객체를 주입(연결) -> DI(Dependency Injection)
    }

    // 마찬가지로 OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부인 AppConfig에서 결정된다.
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(),
                new FixDiscountPolicy()); // 생성자를 통해서 객체를 주입(연결) -> DI(Dependency Injection)
    }
}
