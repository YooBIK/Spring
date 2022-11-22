package jpabook.jpashop.domain;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

//    /*
//    * 객체지향적이지 않음,
//     */
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    /*
    * Locker에 mapped by 가 없으면 1:1 단방향 연관 관계
     */
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    /*
    * N:M 관계는 중간 테이블을 사용
    * 중간 테이블 수정이 어려움
    * 되도록 사용하지 말자 ! (N:M -> 1 : N , N : 1 관계로 풀어서 사용)
     */
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();

    private String city;
    private String street;
    private String zipcode;

    /*
    * N:1 양방향 연관 관계를 설정
     */
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
