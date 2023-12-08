package com.elena.bookStoreInventory2.authorManagement;

import java.sql.SQLException;
import java.util.List;

public interface AuthorRepository {
    public List<Author> getAllAuthors();

    public Author getAuthorById(int id) throws Exception;

    public boolean addAuthor(Author author);

    public boolean updateAuthor(int id, Author author);

    public boolean deleteAuthor(int id) throws SQLException;
}


