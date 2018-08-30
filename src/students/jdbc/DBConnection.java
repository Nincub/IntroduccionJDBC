/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students.jdbc;


import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class DBConnection {
    //com.mysqljdbc.Driver -> Clase para realizar la conexion a mysql
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //Especificamos la base de datos. user SSL = false es para evitar el warning;
    //que nos indica que no estamos usando el protocolo seguro SSL (Secure Socket Layer)
    private static final String JDBC_URL = "jdbc:mysql://localhost/generation?userSSL=false";
    private static final String JDBC_USER = "root"; //usuario de la DB
    private static final String JDBC_PASS = "admin"; //contraseña de la DB
    private static Driver driver = null;
    
    //syncronized es para que solamente un hilo a la vez realice la conexión
    public static synchronized java.sql.Connection getConnection () throws SQLException {
        if (driver == null) {
            try {
                //levantar y cargar en memoria la clase del driver de mysql
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                //instanciar la clase del driver
                driver = (Driver) jdbcDriverClass.newInstance();
                //Registrar el driver
                DriverManager.registerDriver(driver);
                
            } catch (Exception ex) {
                System.out.println("Failed to load the JDBC driver");
                ex.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS); //Hacer conexión
    }
    
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public static void close(java.sql.Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
}
