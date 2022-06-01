package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: 사용자 A 10000월 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB: 사용자 B 20000월 주문
        int userBPrice =  statefulService2.order("userB", 20000);

        // 사용자 A 주문 금액을 조회한 결과
        // 예상과 다르게 20000이 출력되는 문제 발생!
//        int price = statefulService1.getPrice();
//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}