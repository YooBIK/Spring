package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public Long getId() {
        return id;
    }

    public Delivery setId(Long id) {
        this.id = id;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Delivery setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Delivery setAddress(Address address) {
        this.address = address;
        return this;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public Delivery setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }
}
