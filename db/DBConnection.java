package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/supermarket_DB";
    private static final String USER = "root";
    private static final String PASS = "12*20MySQL*6*4*2024";
  
//    private static final String PASS = "1234"; // password de mi mysql -> Joel


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Error al conectar con MySQL: " + e.getMessage());
            return null;
        }
    }
}
