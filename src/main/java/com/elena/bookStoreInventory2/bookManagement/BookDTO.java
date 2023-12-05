package com.elena.bookStoreInventory2.bookManagement;

import org.springframework.stereotype.Component;

@Component
public class BookDTO {
    private int authorId;
    private String title;
    private Double price;
    private int quantity;

    public int getAuthorId() {
        return authorId;
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
}
