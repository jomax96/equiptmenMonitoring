package controller;

import model.Alert;
import model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlertController {

    // Crear una nueva alerta
    public void createAlert(Alert alert) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Alert (name, tracking_Number, created_At, is_Finished) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, alert.getName());
            statement.setString(2, alert.getTrackingNumber());
            statement.setDate(3, new java.sql.Date(alert.getCreatedAt().getTime()));
            statement.setBoolean(4, alert.isFinished());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Alerta creada exitosamente!");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al crear la alerta.");
            e.printStackTrace();
        }
    }

    // Leer todas las alertas
    public List<Alert> getAllAlerts() {
        Connection connection = DatabaseConnection.getConnection();
        List<Alert> alertList = new ArrayList<>();
        String sql = "SELECT * FROM Alert";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Alert alert = new Alert(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("tracking_Number"),
                        resultSet.getDate("created_At"),
                        resultSet.getBoolean("is_Finished")
                );
                alertList.add(alert);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al leer las alertas.");
            e.printStackTrace();
        }

        return alertList;
    }

    // Actualizar una alerta existente
    public void updateAlert(Alert alert) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE Alert SET name = ?, trackingNumber = ?, isFinished = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, alert.getName());
            statement.setString(2, alert.getTrackingNumber());
            statement.setBoolean(3, alert.isFinished());
            statement.setInt(4, alert.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("¡Alerta actualizada exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la alerta.");
            e.printStackTrace();
        }
    }

    // Eliminar una alerta
    public void deleteAlert(int id) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Alert WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Alerta eliminada exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar la alerta.");
            e.printStackTrace();
        }
    }
}
