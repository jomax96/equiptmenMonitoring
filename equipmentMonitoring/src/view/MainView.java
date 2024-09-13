package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private EquipmentPanel equipmentPanel ;
	private AlertPanel alertPanel;
	private EquipmentAssignmentPanel equipmentAssignmentPanel;
	private PickupPanel pickupPanel;

    public MainView() {
        // Configuraci�n de la ventana principal
        setTitle("Gesti�n de Equipos y Prealertas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un CardLayout para cambiar entre paneles
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // A�adir los diferentes paneles al CardLayout
        cardPanel.add(alertPanel = new AlertPanel(), "AlertPanel");
        cardPanel.add(new EquipmentTypePanel(), "EquipmentTypePanel");
        cardPanel.add(equipmentPanel = new EquipmentPanel(), "EquipmentPanel");
        cardPanel.add(equipmentAssignmentPanel = new EquipmentAssignmentPanel(), "EquipmentAssignmentPanel");
        cardPanel.add(pickupPanel = new PickupPanel(), "PickupPanel");

        // Men� para cambiar entre vistas
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navegaci�n");

        JMenuItem alertMenuItem = new JMenuItem("Gesti�n de Prealertas");
        JMenuItem equipmentTypeMenuItem = new JMenuItem("Gesti�n de Tipos de Equipos");
        JMenuItem equipmentMenuItem = new JMenuItem("Gesti�n de Equipos");
        JMenuItem assignmentMenuItem = new JMenuItem("Gesti�n de Asignaciones de Equipos");
        JMenuItem pickupMenuItem = new JMenuItem("Gesti�n de Recogidas");

                // A�adir acciones a los elementos del men�
                alertMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel, "AlertPanel");
                    }
                });

                equipmentTypeMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel, "EquipmentTypePanel");
                    }
                });

                equipmentMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	equipmentPanel.refreshEquipmentTypes();
                        cardLayout.show(cardPanel, "EquipmentPanel");
                    }
                });

                assignmentMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	equipmentAssignmentPanel.refreshLoadAlerts();  
                    	equipmentAssignmentPanel.refreshLoadEquiptments();
                        cardLayout.show(cardPanel, "EquipmentAssignmentPanel");
                    }
                });

                pickupMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	pickupPanel.refreshLoadAlerts();  
                    	pickupPanel.refreshLoadEquiptments();
                        cardLayout.show(cardPanel, "PickupPanel");
                    }
                });

                // A�adir elementos al men�
                menu.add(alertMenuItem);
                menu.add(equipmentTypeMenuItem);
                menu.add(equipmentMenuItem);
                menu.add(assignmentMenuItem);
                menu.add(pickupMenuItem);
                menuBar.add(menu);

                // A�adir el men� y el panel con CardLayout a la ventana principal
                setJMenuBar(menuBar);
                add(cardPanel);

                // Mostrar el panel inicial (prealertas)
                cardLayout.show(cardPanel, "AlertPanel");

                // Hacer visible la ventana
                setVisible(true);
            }

            public static void main(String[] args) {
                new MainView();
            }
        }
