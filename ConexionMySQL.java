package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    // Datos de conexión a la base de datos MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/tienda_abarrotes";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para establecer la conexión
    public static Connection conectar() throws SQLException {
        Connection conn = null;
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida correctamente.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }
}
