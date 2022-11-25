package hellojpa;

import javax.persistence.Embeddable;

/*
임베디드 타입이라는 것을 명시,
기본 생성자 필수 !
 */
@Embeddable
public class Address {

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    private String city;
    private String street;
    private String zipcode;

    public String getCity() {
        return city;
    }


    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    /*
    같은 값타입(객체)를 가리키게 되면 예기치 못한 부작용이 발생할 수 있음!! 원하지 않는 변경이 생김
    그걸 막으려면 수정자(Setter)를 없애거나, private 으로 선언! 
     */
    private Address setCity(String city) {
        this.city = city;
        return this;
    }

    private Address setStreet(String street) {
        this.street = street;
        return this;
    }

    private Address setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }
}
