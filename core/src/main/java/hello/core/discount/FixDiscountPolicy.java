package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 정액 할인 정책 구현체
 */
public class FixDiscountPolicy implements DiscountPolicy {

    private int FixDiscountAmount = 1000;

    /**
     * 회원 등급이 VIP인 경우에만 할인 정책 1000원이 적용됨
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return FixDiscountAmount;
        } else {
            return 0;
        }
    }
}
