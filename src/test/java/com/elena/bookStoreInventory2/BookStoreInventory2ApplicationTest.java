package com.elena.bookStoreInventory2;

import com.elena.bookStoreInventory2.authorManagement.AuthorController;
import com.elena.bookStoreInventory2.bookManagement.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookStoreInventory2ApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void whenGetBookControllerBean_thenReturnBookControllerReference() {
        BookController bookController = applicationContext.getBean(BookController.class);
        assertNotNull(bookController);
    }

    @Test
    void whenGetAuthorControllerBean_thenReturnAuthorControllerReference() {
        AuthorController authorController = applicationContext.getBean(AuthorController.class);
        assertNotNull(authorController);
    }

}