/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package keagamaan;
 
 import java.sql.Driver;
 import java.sql.DriverManager;
 import java.sql.Connection;
 import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class DBcrud {
    String jdbcUrl = "jdbc:mysql://localhost:3306/2210010133_pbo2";
    String username = "root";
    String password = "";

    public DBcrud() {
        try (Connection KoneksiDB = DriverManager.getConnection(jdbcUrl, username, password)) {
            Driver mysqlDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);

            System.out.println("Berhasil!");
        } catch (SQLException error) {
            System.err.println(error.toString());
        }
    }
}


