package view;

import controller.EquipmentTypeController;
import model.EquipmentType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipmentTypePanel extends JPanel {

    private JTextField txtName;
    private JTextField txtSerialLength;
    private JTextField txtMacLength;
    private JButton btnCreateType;
    private JTable tableEquipmentTypes;
    private DefaultTableModel tableModel;
    private EquipmentTypeController equipmentTypeController;

    public EquipmentTypePanel() {
        equipmentTypeController = new EquipmentTypeController();

        setLayout(new BorderLayout());

        // Panel superior para crear nuevos tipos de equipos
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(4, 2));

        panelTop.add(new JLabel("Nombre del Tipo de Equipo:"));
        txtName = new JTextField();
        panelTop.add(txtName);

        panelTop.add(new JLabel("Longitud del Serial:"));
        txtSerialLength = new JTextField();
        panelTop.add(txtSerialLength);

        panelTop.add(new JLabel("Longitud del MAC:"));
        txtMacLength = new JTextField();
        panelTop.add(txtMacLength);

        btnCreateType = new JButton("Crear Tipo de Equipo");
        panelTop.add(btnCreateType);

        // Añadir funcionalidad al botón de crear tipo de equipo
        btnCreateType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEquipmentType();
            }
        });

        add(panelTop, BorderLayout.NORTH);

        // Panel inferior para mostrar los tipos de equipos
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Longitud del Serial", "Longitud del MAC"}, 0);
        tableEquipmentTypes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEquipmentTypes);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar y mostrar todos los tipos de equipos al inicio
        loadEquipmentTypes();
    }

    // Método para crear un nuevo tipo de equipo
    private void createEquipmentType() {
        String name = txtName.getText();
        int serialLength = Integer.parseInt(txtSerialLength.getText());
        int macLength = Integer.parseInt(txtMacLength.getText());

        EquipmentType equipmentType = new EquipmentType(0, name, serialLength, macLength);
        equipmentTypeController.createEquipmentType(equipmentType);

        // Limpiar los campos
        txtName.setText("");
        txtSerialLength.setText("");
        txtMacLength.setText("");

        // Recargar la tabla con los tipos de equipos
        loadEquipmentTypes();
    }

    // Método para cargar todos los tipos de equipos en la tabla
    private void loadEquipmentTypes() {
        tableModel.setRowCount(0); // Limpiar la tabla
        List<EquipmentType> equipmentTypes = equipmentTypeController.getAllEquipmentTypes();

        for (EquipmentType type : equipmentTypes) {
            tableModel.addRow(new Object[]{type.getId(), type.getName(), type.getSerialLength(), type.getMacLength()});
        }
    }
}
