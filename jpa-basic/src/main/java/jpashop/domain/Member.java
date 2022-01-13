package jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//편리하게 조회를 위해 양방향 추가(list 없이 단방향 설계만으로도 가능!)
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    //비즈니스상 없는 것(양방향 X)이 설계에 좋음
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
