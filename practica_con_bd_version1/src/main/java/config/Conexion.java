package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dwi_26_ejemplo",
            "dwi", "dwi");
    }
}