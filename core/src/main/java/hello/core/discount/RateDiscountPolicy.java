package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 정률 할인 정책 구현체
 */

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;   // 10% 할인

    /**
     * VIP 고객의 주문금액의 10%만 할인 적용
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
