package com.elena.bookStoreInventory2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

    private PropertiesDB properties;

    @Autowired
    public DBConnection(PropertiesDB properties) {
        this.properties = properties;
    }

    public Connection getConnection() throws SQLException {
        String url = properties.getUrl();
        String user = properties.getUser();
        String password = properties.getPassword();

        return DriverManager.getConnection(url, user, password);
    }
}
