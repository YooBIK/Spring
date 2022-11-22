package hellojpa;

import javax.persistence.*;

@Entity
/*
* 구현 클래스마다 테이블을 생성하는 전략은 추천 X
*  WHY? 여러 테이블을 함께 조회할 때, 성능이 안좋음
*       자식 테이블들 통합 쿼리하기가 어려움
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn //SINGLE_TABLE 전략은 해당 애노테이션이 없어도 DTYPE 컬럼이 생성된다.
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
