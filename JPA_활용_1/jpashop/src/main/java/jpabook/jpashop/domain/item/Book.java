package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;



    public static Book createBook(String name, int price, int stockQuantity, String author, String isbn){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return book;
    }

    public void changeBookInfo(String name, int price, int stockQuantity,String author, String isbn) {
        super.changeItemInfo(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
