package view;

import controller.AlertController;
import model.Alert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AlertPanel extends JPanel {

    private JTextField txtName;
    private JTextField txtTrackingNumber;
    private JButton btnCreateAlert;
    private JTable tableAlerts;
    private DefaultTableModel tableModel;
    private AlertController alertController;

    public AlertPanel() {
        alertController = new AlertController();

        setLayout(new BorderLayout());

        // Panel superior para crear nuevas alertas
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(3, 2));

        panelTop.add(new JLabel("Nombre:"));
        txtName = new JTextField();
        panelTop.add(txtName);

        panelTop.add(new JLabel("Número de Guía:"));
        txtTrackingNumber = new JTextField();
        panelTop.add(txtTrackingNumber);

        btnCreateAlert = new JButton("Crear Prealerta");
        panelTop.add(btnCreateAlert);

        // Añadir funcionalidad al botón de crear alerta
        btnCreateAlert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAlert();
            }
        });

        add(panelTop, BorderLayout.NORTH);

        // Panel inferior para mostrar las alertas
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Número de Guía", "Finalizada"}, 0);
        tableAlerts = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableAlerts);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar y mostrar todas las alertas al inicio
        loadAlerts();
    }

    // Método para crear una nueva alerta
    private void createAlert() {
        String name = txtName.getText();
        String trackingNumber = txtTrackingNumber.getText();

        if (name.isEmpty() || trackingNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.");
            return;
        }

        Alert alert = new Alert(0, name, trackingNumber, new java.util.Date(), false);
        alertController.createAlert(alert);

        // Limpiar los campos
        txtName.setText("");
        txtTrackingNumber.setText("");

        // Recargar la tabla con las alertas
        loadAlerts();
    }

    // Método para cargar todas las alertas en la tabla
    private void loadAlerts() {
        tableModel.setRowCount(0); // Limpiar la tabla
        List<Alert> alerts = alertController.getAllAlerts();

        for (Alert alert : alerts) {
            tableModel.addRow(new Object[]{alert.getId(), alert.getName(), alert.getTrackingNumber(), alert.isFinished()});
        }
    }
}
