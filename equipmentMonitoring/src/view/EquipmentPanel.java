package view;

import controller.EquipmentController;
import controller.EquipmentTypeController;
import model.Equipment;
import model.EquipmentType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentPanel extends JPanel {

    private JTextField txtSerial;
    private JTextField txtMac;
    private JTextField txtObservations;
    private JButton btnCreateEquipment;
    private JTable tableEquipments;
    private DefaultTableModel tableModel;
    private EquipmentController equipmentController;
    private JComboBox<EquipmentType> comboEquipmentType;  // JComboBox para seleccionar el tipo de equipo
    private EquipmentTypeController equipmentTypeController;

    public EquipmentPanel() {
        equipmentController = new EquipmentController();
        equipmentTypeController = new EquipmentTypeController();

        setLayout(new BorderLayout());

        // Panel superior para crear nuevos equipos
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(5, 2));  

        panelTop.add(new JLabel("Serial del Equipo:"));
        txtSerial = new JTextField();
        panelTop.add(txtSerial);

        panelTop.add(new JLabel("MAC del Equipo:"));
        txtMac = new JTextField();
        panelTop.add(txtMac);

        panelTop.add(new JLabel("Observaciones:"));
        txtObservations = new JTextField();
        panelTop.add(txtObservations);

        // JComboBox para seleccionar el tipo de equipo
        panelTop.add(new JLabel("Tipo de Equipo:"));
        comboEquipmentType = new JComboBox<>();
        panelTop.add(comboEquipmentType);

        btnCreateEquipment = new JButton("Crear Equipo");
        panelTop.add(btnCreateEquipment);

        // Añadir funcionalidad al botón de crear equipo
        btnCreateEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEquipment();
            }
        });

        add(panelTop, BorderLayout.NORTH);

        // Panel inferior para mostrar los equipos
        tableModel = new DefaultTableModel(new String[]{"ID", "Serial", "MAC", "Observaciones", "Tipo de Equipo"}, 0);
        tableEquipments = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEquipments);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar y mostrar todos los equipos al inicio
        loadEquipments();
        refreshEquipmentTypes();  // Cargar tipos de equipo en el JComboBox
    }

    // Método para cargar los tipos de equipo desde la base de datos
    private void loadEquipmentTypes() {
        comboEquipmentType.removeAllItems(); // Limpiar el JComboBox
        List<EquipmentType> equipmentTypes = equipmentTypeController.getAllEquipmentTypes();
        for (EquipmentType type : equipmentTypes) {
            comboEquipmentType.addItem(type);
        }
    }

    // Método para actualizar los tipos de equipo cada vez que se ingresa al panel
    public void refreshEquipmentTypes() {
        loadEquipmentTypes();  // Volver a cargar la lista de tipos de equipo
    }

    // Método para crear un nuevo equipo
    private void createEquipment() {
        String serial = txtSerial.getText();
        String mac = txtMac.getText();
        String observations = txtObservations.getText();
        EquipmentType selectedType = (EquipmentType) comboEquipmentType.getSelectedItem();  // Obtener tipo de equipo seleccionado

        if (serial.isEmpty() || mac.isEmpty() || selectedType == null) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.");
            return;
        }

        Equipment equipment = new Equipment(0, selectedType.getId(), serial, mac, observations);  // Usar ID del tipo de equipo
        equipmentController.createEquipment(equipment);

        // Limpiar los campos
        txtSerial.setText("");
        txtMac.setText("");
        txtObservations.setText("");
        comboEquipmentType.setSelectedIndex(0);

        // Recargar la tabla con los equipos
        loadEquipments();
    }

    // Método para cargar todos los equipos en la tabla
    private void loadEquipments() {
        tableModel.setRowCount(0); // Limpiar la tabla
        List<Equipment> equipments = equipmentController.getAllEquipments();

        for (Equipment equipment : equipments) {
            EquipmentType type = equipmentTypeController.getEquipmentTypeById(equipment.getEquipmentTypeId());  // Obtener tipo de equipo por ID
            tableModel.addRow(new Object[]{equipment.getId(), equipment.getSerial(), equipment.getMac(), equipment.getObservations(), type.getName()});
        }
    }
}
