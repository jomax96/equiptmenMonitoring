package controller;

import model.EquipmentType;
import model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentTypeController {

    // Crear un nuevo tipo de equipo
    public void createEquipmentType(EquipmentType equipmentType) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO EquipmentType (name, serial_Length, mac_Length) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, equipmentType.getName());
            statement.setInt(2, equipmentType.getSerialLength());
            statement.setInt(3, equipmentType.getMacLength());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Tipo de equipo creado exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al crear el tipo de equipo.");
            e.printStackTrace();
        }
    }

    // Leer todos los tipos de equipos
    public List<EquipmentType> getAllEquipmentTypes() {
        Connection connection = DatabaseConnection.getConnection();
        List<EquipmentType> equipmentTypeList = new ArrayList<>();
        String sql = "SELECT * FROM EquipmentType";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EquipmentType equipmentType = new EquipmentType(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("serial_Length"),
                        resultSet.getInt("mac_Length")
                );
                equipmentTypeList.add(equipmentType);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al leer los tipos de equipos.");
            e.printStackTrace();
        }

        return equipmentTypeList;
    }
    // Obtener un tipo de equipo por su ID
    public EquipmentType getEquipmentTypeById(int id) {
        Connection connection = DatabaseConnection.getConnection();
        EquipmentType equipmentType = null;
        String sql = "SELECT * FROM EquipmentType WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                equipmentType = new EquipmentType(
                		 resultSet.getInt("id"),
                         resultSet.getString("name"),
                         resultSet.getInt("serial_Length"),
                         resultSet.getInt("mac_Length")
                );
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener el tipo de equipo.");
            e.printStackTrace();
        }

        return equipmentType;
    }

    // Actualizar un tipo de equipo existente
    public void updateEquipmentType(EquipmentType equipmentType) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE EquipmentType SET name = ?, serialLength = ?, macLength = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, equipmentType.getName());
            statement.setInt(2, equipmentType.getSerialLength());
            statement.setInt(3, equipmentType.getMacLength());
            statement.setInt(4, equipmentType.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("¡Tipo de equipo actualizado exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar el tipo de equipo.");
            e.printStackTrace();
        }
    }

    // Eliminar un tipo de equipo
    public void deleteEquipmentType(int id) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM EquipmentType WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Tipo de equipo eliminado exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar el tipo de equipo.");
            e.printStackTrace();
        }
    }
}

