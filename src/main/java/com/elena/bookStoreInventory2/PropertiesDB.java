package com.elena.bookStoreInventory2;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;
@Component
public class PropertiesDB {
    private String url = "";
    private String user = "";
    private String password = "";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public PropertiesDB() {
        setUpProperties();
    }

    private void setUpProperties() {
        Properties prop = new Properties();
        try (
                InputStream input = PropertiesDB.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            // Load a properties file from class path, inside static method
            prop.load(input);

            // Get values from properties file
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.username");
            password = prop.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
