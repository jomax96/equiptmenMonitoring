package view;

import controller.PickupController;
import controller.AlertController;
import controller.EquipmentController;
import model.Pickup;
import model.Alert;
import model.Equipment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PickupPanel extends JPanel {

    private JComboBox<Alert> comboAlertId;  // Cambiado de JTextField a JComboBox
    private JComboBox<Equipment> comboEquipmentId;  // Cambiado de JTextField a JComboBox
    private JCheckBox chkIsPickedUp;
    private JButton btnPickupEquipment;
    private JTable tablePickups;
    private DefaultTableModel tableModel;
    private PickupController pickupController;
    private AlertController alertController;
    private EquipmentController equipmentController;

    public PickupPanel() {
        pickupController = new PickupController();
        alertController = new AlertController();  // Controlador para las alertas
        equipmentController = new EquipmentController();  // Controlador para los equipos

        setLayout(new BorderLayout());

        // Panel superior para registrar nuevas recogidas
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(4, 2));

        panelTop.add(new JLabel("ID de la Prealerta:"));
        comboAlertId = new JComboBox<>();
        panelTop.add(comboAlertId);

        panelTop.add(new JLabel("ID del Equipo:"));
        comboEquipmentId = new JComboBox<>();
        panelTop.add(comboEquipmentId);

        chkIsPickedUp = new JCheckBox("¿Recogido?");
        panelTop.add(chkIsPickedUp);

        btnPickupEquipment = new JButton("Registrar Recogida");
        panelTop.add(btnPickupEquipment);

        // Añadir funcionalidad al botón de registrar recogida
        btnPickupEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickupEquipment();
            }
        });

        add(panelTop, BorderLayout.NORTH);

        // Panel inferior para mostrar las recogidas
        tableModel = new DefaultTableModel(new String[]{"ID", "ID Prealerta", "ID Equipo", "Recogido"}, 0);
        tablePickups = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePickups);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar y mostrar todas las recogidas al inicio
        loadPickups();
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
    
    public void refreshLoadAlerts() {
    	loadAlerts();
    }
    public void refreshLoadEquiptments() {
    	loadEquipments();
    }

    // Método para cargar los equipos en el JComboBox
    private void loadEquipments() {
        List<Equipment> equipments = equipmentController.getAllEquipments();  // Supone que existe un método getAllEquipments
        comboEquipmentId.removeAllItems();  // Limpiar el JComboBox antes de cargar
        for (Equipment equipment : equipments) {
            comboEquipmentId.addItem(equipment);  // Añadir cada equipo al JComboBox
        }
    }

    // Método para registrar la recogida de un equipo
    private void pickupEquipment() {
        Alert selectedAlert = (Alert) comboAlertId.getSelectedItem();  // Obtener alerta seleccionada
        Equipment selectedEquipment = (Equipment) comboEquipmentId.getSelectedItem();  // Obtener equipo seleccionado
        boolean isPickedUp = chkIsPickedUp.isSelected();

        if (selectedAlert == null || selectedEquipment == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una alerta y un equipo.");
            return;
        }

        int alertId = selectedAlert.getId();  // Obtener el ID de la alerta seleccionada
        int equipmentId = selectedEquipment.getId();  // Obtener el ID del equipo seleccionado

        Pickup pickup = new Pickup(0, alertId, equipmentId, new java.util.Date(), isPickedUp);
        pickupController.createPickup(pickup);

        // Limpiar los campos
        comboAlertId.setSelectedIndex(0);
        comboEquipmentId.setSelectedIndex(0);
        chkIsPickedUp.setSelected(false);

        // Recargar la tabla con las recogidas
        loadPickups();
    }

    // Método para cargar todas las recogidas en la tabla
    private void loadPickups() {
        tableModel.setRowCount(0);  // Limpiar la tabla
        List<Pickup> pickups = pickupController.getAllPickups();

        for (Pickup pickup : pickups) {
            tableModel.addRow(new Object[]{pickup.getId(), pickup.getAlertId(), pickup.getEquipmentId(), pickup.isPickedUp()});
        }
    }
}
