package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded   //내장 타입
    private Address address;

    //mappedBy : 매핑 거울, 읽기 전용
    //여기 값을 변경한다해도, Order 에 Member 값은 변하지 X
    @OneToMany(mappedBy = "member")  //하나의 회원이 여러개의 상품 주문
    private List<Order> orders = new ArrayList<>();
}
