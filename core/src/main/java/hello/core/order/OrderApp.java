package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // ApplicationContext는 스프링 컨테이너라고 한다.
        // 기존에는 AppConfig 클래스를 사용해서 직접 객체를 생성하고 DI했지만, 이제부터는 스프링 컨테이너를 사용한다.
        // 스프링 컨테이너는 @Configuration이 붙은 AppConfig를 구성 정보로 사용한다.
        // @Bean이라고 적힌 메소드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이처럼 스프링 컨테이너에 등록된 객체를 스프링 빈이라고 한다.
        // 스프링 컨테이너에게 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order:" + order.toString());
        System.out.println("order.calculatePrice:" + order.calculatePrice());
    }
}
