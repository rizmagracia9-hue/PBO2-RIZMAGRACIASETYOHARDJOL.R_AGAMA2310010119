/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ThinkPad
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pbo_2310010119?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // ubah sesuai MySQL kamu
    private static final String PASS = "";     // ubah sesuai MySQL kamu

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
   }
}
