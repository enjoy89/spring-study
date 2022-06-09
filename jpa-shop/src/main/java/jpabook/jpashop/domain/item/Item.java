package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")    // book, album, movie을 구분
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;    // 상품 id

    private String name;    // 상품 이름

    private int price;  // 상품 가격

    private int stockQuantity;  // 재고수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
