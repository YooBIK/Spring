package hellojpa;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "Member.findByUserName",
        query = "select m from Member m where m.userName =:userName"
)
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String userName;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    public Long getId() {
        return id;
    }

    public Member setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Member setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Member setAge(int age) {
        this.age = age;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    /*
            toString 매서드를 정의할 때,
            양방향 연관관계인 필드를 추가하면, 무한 루프에 빠지는 등 에러 발생 가능!! 주의하자 !!
             */
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
