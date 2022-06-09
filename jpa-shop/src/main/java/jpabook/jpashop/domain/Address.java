package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

// 내장 타입. 회원과 배송에서 사용한다.
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 값 타입은 변경 불가능하게 설계헤야 한다.
    // @Setter를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스로 만들어야 한다.
    // JPA는 엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 protected로 설정해야한다.
    // 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문이다.
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
