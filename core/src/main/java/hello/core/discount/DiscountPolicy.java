package hello.core.discount;

import hello.core.member.Member;

/**
 * 할인 정책 인터페이스
 * 회원 등급에 따른 할인 금액 반환
 */
public interface DiscountPolicy {
    int discount(Member member, int price);
}
