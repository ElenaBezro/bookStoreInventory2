package com.elena.bookStoreInventory2.authorManagement;


import com.elena.bookStoreInventory2.DBConnection;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorDao {
    boolean isCreated = false;
    boolean isReadyForDestroy = false;
    private final DBConnection dbConnection;

    @Autowired
    public AuthorDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @PostConstruct
    public void init() {
        isCreated = true;
        System.out.println("AuthorDao has been created");
    }

    @PreDestroy
    public  void destroy() {
        isReadyForDestroy = true;
        System.out.println("AuthorDao will be destroyed");
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT * FROM Authors;";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet result = statement.executeQuery(sql)) {
                    while (result.next()) {
                        Author author = new Author();
                        author.setId(result.getInt("author_id"));
                        author.setName(result.getString("name"));
                        author.setEmail(result.getString("email"));
                        authors.add(author);
                    }
                }
            }
            System.out.println(authors);
            return authors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Author getAuthorById(int id) throws Exception {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT * FROM Authors WHERE author_id = ?;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        Author author = new Author();
                        author.setId(result.getInt("author_id"));
                        author.setName(result.getString("name"));
                        author.setEmail(result.getString("email"));

                        System.out.println(author);
                        return author;
                    } else {
                        throw new Exception("Invalid id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addAuthor(Author author) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "INSERT INTO Authors (name, email) VALUES(?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getEmail());

                int addedRecords = preparedStatement.executeUpdate();
                return addedRecords > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAuthor(int id, Author author) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "UPDATE Authors SET  name = ?, email = ? WHERE author_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getEmail());
                preparedStatement.setInt(3, id);


                int affectedRecords = preparedStatement.executeUpdate();
                return affectedRecords > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAuthor(int id) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "DELETE FROM Authors WHERE author_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                int addedRecords = preparedStatement.executeUpdate();
                return addedRecords > 0;
            }
        } catch (SQLException e) {
            throw new SQLException("Error occurred during the delete operation");
        }
    }

}
