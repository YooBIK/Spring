package hellojpa;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Member {

    public Member(){
    }

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

    /*
     * Enum 클래스 사용할 때 사용
     * ORDINARY, STRING
     * ORDINARY를 쓰면, Enum 클래스를 변경할 때, 순서에 의해 원하지 않은 결과가 생길 수 있음
     * -> STRING 사용할 것
     */

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // DATE, TIME, TIMESTAMP 3가지, 시간 관련
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /*
    * VARCHAR 제한을 넘어서는 경우 사용!
    * String 이면 CLOB, 나머지는 BLOB으로 맵핑
     */
    @Lob
    private String description;


    // 컬럼 맵핑 X
    @Transient
    private int temp;

}
