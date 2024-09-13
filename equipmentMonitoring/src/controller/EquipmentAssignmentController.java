package controller;

import model.EquipmentAssignment;
import model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentAssignmentController {

    // Asignar un equipo a una prealerta
    public void createAssignment(EquipmentAssignment assignment) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO EquipmentAssignment (alert_Id, equipment_Id, is_Assigned) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, assignment.getAlertId());
            statement.setInt(2, assignment.getEquipmentId());
            statement.setBoolean(3, assignment.isAssigned());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Equipo asignado exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al asignar el equipo.");
            e.printStackTrace();
        }
    }

    // Leer todas las asignaciones
    public List<EquipmentAssignment> getAllAssignments() {
        Connection connection = DatabaseConnection.getConnection();
        List<EquipmentAssignment> assignmentList = new ArrayList<>();
        String sql = "SELECT * FROM EquipmentAssignment";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EquipmentAssignment assignment = new EquipmentAssignment(
                        resultSet.getInt("id"),
                        resultSet.getInt("alert_Id"),
                        resultSet.getInt("equipment_Id"),
                        resultSet.getBoolean("is_Assigned")
                );
                assignmentList.add(assignment);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al leer las asignaciones.");
            e.printStackTrace();
        }

        return assignmentList;
    }

    // Actualizar una asignación
    public void updateAssignment(EquipmentAssignment assignment) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE EquipmentAssignment SET isAssigned = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, assignment.isAssigned());
            statement.setInt(2, assignment.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("¡Asignación actualizada exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la asignación.");
            e.printStackTrace();
        }
    }

    // Eliminar una asignación
    public void deleteAssignment(int id) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM EquipmentAssignment WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Asignación eliminada exitosamente!");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar la asignación.");
            e.printStackTrace();
        }
    }
}

