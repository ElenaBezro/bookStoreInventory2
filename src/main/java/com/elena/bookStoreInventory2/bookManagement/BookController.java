package com.elena.bookStoreInventory2.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Component
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookDao bookDao;

    @GetMapping
    public List<GetBookDTO> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @GetMapping("/search")
    public List<GetBookDTO> searchBooks(@RequestParam(required = false) String author, @RequestParam(required = false) Double maxPrice) {
        return bookDao.searchBooks(author, maxPrice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id) {
        try {
            GetBookDTO book = bookDao.getBookById(id);
            if (book != null) {
                return new ResponseEntity<>(book, HttpStatus.OK);
                // ???or:       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
            } else {
                return new ResponseEntity<>("Book with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving book", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookDTO book) {
        boolean isAdded = bookDao.addBook(book);
        if (isAdded) {
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error adding book", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody BookDTO updatedBook) {
        boolean isUpdated = bookDao.updateBook(id, updatedBook);
        if (isUpdated) {
            return new ResponseEntity<>("Updated", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Error updating book", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        try {
            boolean isDeleted = bookDao.deleteBook(id);
            if (isDeleted) {
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            return new ResponseEntity<>("Error deleting book", HttpStatus.BAD_REQUEST);
        }
    }
}
