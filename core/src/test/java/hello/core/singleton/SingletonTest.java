package hello.core.singleton;

import hello.core.member.MemberService;
import hello.core.order.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 조회 1: 호출할 때 마다 다른 객체 생성
        MemberService memberService1 = appConfig.memberService();

        // 조회 2: 호출할 때 마다 다른 객체 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 서로 다른 것을 확인
        // 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청할 때 마다 새로운 객체를 생성한다.
        // 이는 메모리 낭비가 심하다. 따라서 해당 객체 1개만 생성하고, 인스턴스를 공유하도록 설계하면 된다. -> 싱글톤 패턴
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        assertThat(memberService1).isNotEqualTo(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // 생성자를 private으로 막아두었기 때문에 에러 발생
        // SingletonService singletonService = new SingletonService();

        // 조회 1: 호출할 때 마다 같은 객체 생성
        SingletonService singletonService1 = SingletonService.getInstance();

        // 조회 2: 호출할 때 마다 같은 객체 생성
        SingletonService singletonService2 = SingletonService.getInstance();


        // 참조값이 서로 같은 것을 확인
        // 싱글톤 패턴을 적용하면 고객의 요청이 들어올 때마다 객체를 생성하는 것이 아니라
        // 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        assertThat(singletonService1).isSameAs(singletonService2);
    }
}
