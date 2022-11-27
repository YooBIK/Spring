package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int stockAmount;

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Product setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public Product setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
        return this;
    }
}
