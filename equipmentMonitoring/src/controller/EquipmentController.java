package controller;

import model.Equipment;
import model.EquipmentType;
import model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentController {

    // Crear un nuevo equipo
    public void createEquipment(Equipment equipment) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Equipment (equipment_Type_Id, serial, mac, observations) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, equipment.getEquipmentTypeId());
            statement.setString(2, equipment.getSerial());
            statement.setString(3, equipment.getMac());
            statement.setString(4, equipment.getObservations());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Equipo creado exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al crear el equipo.");
            e.printStackTrace();
        }
    }

    // Leer todos los equipos
    public List<Equipment> getAllEquipments() {
        Connection connection = DatabaseConnection.getConnection();
        List<Equipment> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM Equipment";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Equipment equipment = new Equipment(
                        resultSet.getInt("id"),
                        resultSet.getInt("equipment_Type_Id"),
                        resultSet.getString("serial"),
                        resultSet.getString("mac"),
                        resultSet.getString("observations")
                );
                equipmentList.add(equipment);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al leer los equipos.");
            e.printStackTrace();
        }

        return equipmentList;
    }

   

    // Actualizar un equipo existente
    public void updateEquipment(Equipment equipment) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE Equipment SET serial = ?, mac = ?, observations = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, equipment.getSerial());
            statement.setString(2, equipment.getMac());
            statement.setString(3, equipment.getObservations());
            statement.setInt(4, equipment.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("¡Equipo actualizado exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar el equipo.");
            e.printStackTrace();
        }
    }

    // Eliminar un equipo
    public void deleteEquipment(int id) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Equipment WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Equipo eliminado exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar el equipo.");
            e.printStackTrace();
        }
    }
}
