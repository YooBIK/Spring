package hellojpa;

public class MemberDTO {

    public MemberDTO(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    private String userName;
    private int age;

    public String getUserName() {
        return userName;
    }

    public MemberDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public MemberDTO setAge(int age) {
        this.age = age;
        return this;
    }
}
