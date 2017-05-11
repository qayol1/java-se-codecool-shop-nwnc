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

    //filename = "src/main/resources/connection/properties/connectionProperties.txt" for the real db
    //filename = "src/main/resources/connection/properties/connectionPropertiesForTest.txt" for the testing db


    private static String databaseName;
    private static String url;
    private static String DATABASE;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String fileName;


    public DbConnect(String fileName) {
        this.fileName = fileName;
        readProperties();
        DATABASE = "jdbc:postgresql://"+url+"/"+databaseName;
    }

    private static String readProperties () {
        try {
            Scanner s = new Scanner(new File(fileName));
            while (s.hasNext()) {
                String nextLine = s.next();
                if (nextLine.equals("url:")) {
                    url =s.next();
                }
                if (nextLine.equals("database:")) {
                    databaseName =s.next();
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

    public static Connection getConnection() throws SQLException {
        readProperties();
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

}

