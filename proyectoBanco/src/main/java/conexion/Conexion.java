package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // URL de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/Banco";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para obtener la conexión
    public static Connection conectar(){
        Connection conexion = null;
        try {

            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
        return conexion;
    }


}