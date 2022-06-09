package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id") // pk 컬럼명은 member_id를 사용함
    private Long id;    // 회원 id (pk)

    private String name;    // 회원 이름

    @Embedded   // 내장 타입을 포함했다고 매핑함
    private Address address;

    // 한 명의 회원은 여러 상품을 주문할 수 있으므로 일대다 관계
    // 문제는 양방향 참조 관계임 (order 테이블의 member값에 의해 변경될 수 있도록 설정 == 연관관계의 주인이 아님)
    // order 테이블에 있는 member 필드에 의해 매핑되는 거울이라는 표현. (읽기 전용)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
