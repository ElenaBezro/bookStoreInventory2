package com.elena.bookStoreInventory2.authorManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id) {
        try {
            Author author = authorDao.getAuthorById(id);
            if (author != null) {
                return new ResponseEntity<>(author, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Author with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving author", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        boolean isAdded = authorDao.addAuthor(author);
        if (isAdded) {
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error adding author", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable int id, @RequestBody Author updatedAuthor) {
        boolean isUpdated = authorDao.updateAuthor(id, updatedAuthor);
        if (isUpdated) {
            return new ResponseEntity<>("Updated", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Error updating author", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        try {
            boolean isDeleted = authorDao.deleteAuthor(id);
            if (isDeleted) {
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Author with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            return new ResponseEntity<>("Error deleting author", HttpStatus.BAD_REQUEST);
        }
    }
}


