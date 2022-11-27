package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String userName;
    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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
}
