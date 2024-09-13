package controller;

import model.Pickup;
import model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PickupController {

    // Registrar la recogida de un equipo
    public void createPickup(Pickup pickup) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Pickup (alert_Id, equipment_Id, picked_up_at, is_Picked_Up) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pickup.getAlertId());
            statement.setInt(2, pickup.getEquipmentId());
            statement.setDate(3, new java.sql.Date(pickup.getPickupDate().getTime()));
            statement.setBoolean(4, pickup.isPickedUp());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Equipo recogido exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al registrar la recogida del equipo.");
            e.printStackTrace();
        }
    }

    // Leer todas las recogidas
    public List<Pickup> getAllPickups() {
        Connection connection = DatabaseConnection.getConnection();
        List<Pickup> pickupList = new ArrayList<>();
        String sql = "SELECT * FROM Pickup";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pickup pickup = new Pickup(
                        resultSet.getInt("id"),
                        resultSet.getInt("alert_Id"),
                        resultSet.getInt("equipment_Id"),
                        resultSet.getDate("picked_up_at"),
                        resultSet.getBoolean("is_Picked_Up")
                );
                pickupList.add(pickup);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al leer las recogidas.");
            e.printStackTrace();
        }

        return pickupList;
    }

    // Actualizar una recogida
    public void updatePickup(Pickup pickup) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE Pickup SET isPickedUp = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, pickup.isPickedUp());
            statement.setInt(2, pickup.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("¡Recogida actualizada exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la recogida.");
            e.printStackTrace();
        }
    }

    // Eliminar una recogida
    public void deletePickup(int id) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Pickup WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Recogida eliminada exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar la recogida.");
            e.printStackTrace();
        }
    }
}
