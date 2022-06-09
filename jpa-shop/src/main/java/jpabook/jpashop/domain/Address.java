package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

// 내장 타입. 회원과 배송에서 사용한다.
@Embeddable
@Getter
@Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
