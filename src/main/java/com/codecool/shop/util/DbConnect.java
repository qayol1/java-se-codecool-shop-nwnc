package com.codecool.shop.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by peter on 2017.05.08..
 */
public class DbConnect {

    private String DatabaseName;
    private String URL;
    private String DATABASE;
    private String DB_USER;
    private String DB_PASSWORD;
    private String fileName = "src/main/resources/connection/properties/connectionProperties.txt";

    public DbConnect() {
        readProperties();
        DATABASE = "jdbc:postgresql://"+URL+"/"+DatabaseName;
    }

    private String readProperties () {
        try {
            Scanner s = new Scanner(new File(fileName));
            while (s.hasNext()) {
                String nextLine = s.next();
                if (nextLine.equals("url:")) {
                    URL =s.next();
                }
                if (nextLine.equals("database:")) {
                    DatabaseName =s.next();
                }
                if (nextLine.equals("user:")) {
                    DB_USER =s.next();
                }
                if (nextLine.equals("password:")) {
                    DB_PASSWORD =s.next();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        readProperties();
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public void connect() {
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
