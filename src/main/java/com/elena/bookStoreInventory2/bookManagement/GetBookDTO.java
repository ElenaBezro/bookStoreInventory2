package com.elena.bookStoreInventory2.bookManagement;


import com.elena.bookStoreInventory2.authorManagement.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetBookDTO {
    private int id;
    @Autowired
    private Author author;
    private String title;
    private Double price;
    private int quantity;

    public GetBookDTO() {
    }

    public GetBookDTO(int id, Author author, String title, Double price, int quantity) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "GetBookDTO{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
