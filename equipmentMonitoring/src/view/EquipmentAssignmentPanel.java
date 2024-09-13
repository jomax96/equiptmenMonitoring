package view;

import controller.EquipmentAssignmentController;
import controller.AlertController;
import controller.EquipmentController;
import model.EquipmentAssignment;
import model.Alert;
import model.Equipment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentAssignmentPanel extends JPanel {

    private JComboBox<Alert> comboAlertId;  // Cambiado de JTextField a JComboBox
    private JComboBox<Equipment> comboEquipmentId;  // Cambiado de JTextField a JComboBox
    private JCheckBox chkIsAssigned;
    private JButton btnAssignEquipment;
    private JTable tableAssignments;
    private DefaultTableModel tableModel;
    private EquipmentAssignmentController assignmentController;
    private AlertController alertController;
    private EquipmentController equipmentController;

    public EquipmentAssignmentPanel() {
        assignmentController = new EquipmentAssignmentController();
        alertController = new AlertController();  // Para cargar alertas
        equipmentController = new EquipmentController();  // Para cargar equipos

        setLayout(new BorderLayout());

        // Panel superior para crear nuevas asignaciones
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(4, 2));

        panelTop.add(new JLabel("ID de la Prealerta:"));
        comboAlertId = new JComboBox<>();
        panelTop.add(comboAlertId);

        panelTop.add(new JLabel("ID del Equipo:"));
        comboEquipmentId = new JComboBox<>();
        panelTop.add(comboEquipmentId);

        chkIsAssigned = new JCheckBox("¿Asignado?");
        panelTop.add(chkIsAssigned);

        btnAssignEquipment = new JButton("Asignar Equipo");
        panelTop.add(btnAssignEquipment);

        // Añadir funcionalidad al botón de asignar equipo
        btnAssignEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignEquipment();
            }
        });

        add(panelTop, BorderLayout.NORTH);

        // Panel inferior para mostrar las asignaciones
        tableModel = new DefaultTableModel(new String[]{"ID", "ID Prealerta", "ID Equipo", "Asignado"}, 0);
        tableAssignments = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableAssignments);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar y mostrar todas las asignaciones al inicio
        loadAssignments();
        loadAlerts();  // Cargar las alertas en el JComboBox
        loadEquipments();  // Cargar los equipos en el JComboBox
    }

    // Método para cargar las alertas en el JComboBox
    private void loadAlerts() {
        List<Alert> alerts = alertController.getAllAlerts();  // Supone que existe un método getAllAlerts
        comboAlertId.removeAllItems();  // Limpiar el JComboBox antes de cargar
        for (Alert alert : alerts) {
            comboAlertId.addItem(alert);  // Añadir cada alerta al JComboBox
        }
    }

    // Método para cargar los equipos en el JComboBox
    private void loadEquipments() {
        List<Equipment> equipments = equipmentController.getAllEquipments();  // Supone que existe un método getAllEquipments
        comboEquipmentId.removeAllItems();  // Limpiar el JComboBox antes de cargar
        for (Equipment equipment : equipments) {
            comboEquipmentId.addItem(equipment);  // Añadir cada equipo al JComboBox
        }
    }
    
    public void refreshLoadAlerts() {
    	loadAlerts();
    }
    public void refreshLoadEquiptments() {
    	loadEquipments();
    }

    // Método para asignar un equipo a una prealerta
    private void assignEquipment() {
        Alert selectedAlert = (Alert) comboAlertId.getSelectedItem();  // Obtener alerta seleccionada
        Equipment selectedEquipment = (Equipment) comboEquipmentId.getSelectedItem();  // Obtener equipo seleccionado
        boolean isAssigned = chkIsAssigned.isSelected();

        if (selectedAlert == null || selectedEquipment == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una alerta y un equipo.");
            return;
        }

        int alertId = selectedAlert.getId();  // Obtener el ID de la alerta seleccionada
        int equipmentId = selectedEquipment.getId();  // Obtener el ID del equipo seleccionado

        EquipmentAssignment assignment = new EquipmentAssignment(0, alertId, equipmentId, isAssigned);
        assignmentController.createAssignment(assignment);

        // Limpiar los campos
        comboAlertId.setSelectedIndex(0);
        comboEquipmentId.setSelectedIndex(0);
        chkIsAssigned.setSelected(false);

        // Recargar la tabla con las asignaciones
        loadAssignments();
    }

    // Método para cargar todas las asignaciones en la tabla
    private void loadAssignments() {
        tableModel.setRowCount(0);  // Limpiar la tabla
        List<EquipmentAssignment> assignments = assignmentController.getAllAssignments();

        for (EquipmentAssignment assignment : assignments) {
            tableModel.addRow(new Object[]{assignment.getId(), assignment.getAlertId(), assignment.getEquipmentId(), assignment.isAssigned()});
        }
    }
}
