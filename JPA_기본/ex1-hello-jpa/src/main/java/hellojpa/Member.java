package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends BaseEntity{

    public Member(){
    }

    /*
    * @Id : DB 테이블 PK 맵핑, 이것만 사용하면 PK 직접 할당
    * @GeneratedValue : 자동 생성
    *   - IDENTITY : PK 생성을 DB에 위임, DB에 넣기 전에 PK를 알 수가 없음
    *                그래서 예외적으로 persist 할 때, 바로 DB에 INSERT Query를 날림
    *   - SEQUENCE : DB의 SEQUENCE Object 사용(Oracle ...)
    *                allocation Size를 통해 성능 최적화 가능!
    *   - TABLE : 키 생성 전용 테이블 생성 -> SEQUENCE와 비슷하게 사용 (모든 DB에 적용 가능 BUT, 성능 별로)
    *             allocation Size를 통해 성능 최적화 가능!
    *   - AUTO : DB 방언에 맞춰 자동 생성(Oracle - sequence)
     */
    @Id
    private Long id;

    /*
    * name : 테이블의 컬럼 명
    * insertable, updatable : 등록 or 변경 가능 여부
    * nullable : null 값 허용 여부, 테이블 생성 시 not null 제약 조건
    * unique : true 설정 시, Unique 제약 조건 but Table에 거는 방식이 더 나음
    * length : String(VARCHAR)의 길이 제한
    * columnDefinition : DB 컬럼 정보를 직접 입력
    *
     */
    @Column(name = "USERNAME")
    private String name;

    private int age;

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

    /*
     * Enum 클래스 사용할 때 사용
     * ORDINARY, STRING
     * ORDINARY를 쓰면, Enum 클래스를 변경할 때, 순서에 의해 원하지 않은 결과가 생길 수 있음
     * -> STRING 사용할 것
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    // DATE, TIME, TIMESTAMP 3가지, 시간 관련
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;

    /*
    * VARCHAR 제한을 넘어서는 경우 사용!
    * String 이면 CLOB, 나머지는 BLOB으로 맵핑
     */
    @Lob
    private String description;

    // 컬럼 맵핑 X
    @Transient
    private int temp;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
