package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static Connection getConnection() {
		Connection connection = null;
        // Par�metros de conexi�n a SQL Server
        String url = "jdbc:sqlserver://JOSE;databaseName=equipmentMonitoring;integratedSecurity=true;encrypt=false;";
        //String username = "JOSE";  // Cambia por tu usuario de SQL Server
       // String password = "4512";  // Cambia por tu contrase�a de SQL Server

        try {
            // Establecer la conexi�n
        	 connection = DriverManager.getConnection(url);
            System.out.println("Conexi�n exitosa a la base de datos.");
        } catch (SQLException e) {
            // Manejo de excepciones si hay un error en la conexi�n
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }

        return connection;
    }
}
