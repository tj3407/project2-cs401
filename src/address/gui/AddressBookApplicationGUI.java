package address.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookApplicationGUI {
    private JFrame frame;
    protected static ContactScrollPane contactScrollPane = new ContactScrollPane();
    protected static FindEntryPanel findEntryPanel = new FindEntryPanel();
    JButton btnDisplay;
    JPanel displayButtonField;

    /**
     * Launch the main application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AddressBookApplicationGUI window = new AddressBookApplicationGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application
     */
    public AddressBookApplicationGUI() {
        // Set up main frame component using BorderLayout
        frame = new JFrame("Address Book");
        frame.setLayout(new BorderLayout(20, 20));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(780, 600);

        // Create display button which will be in BorderLayout.NORTH
        displayButtonField = new JPanel();
        btnDisplay = new JButton("Display");
        displayButtonField.add(btnDisplay);
        frame.add(displayButtonField, BorderLayout.NORTH);

        frame.add(contactScrollPane.contactScrollPanel, BorderLayout.CENTER);

        frame.add(findEntryPanel.findPanel, BorderLayout.SOUTH);

        // Event listener for Display button
        btnDisplay.addActionListener(new ActionListener() {
            // BASED ON event from hitting display button,
            // Display contact list
            public void actionPerformed(ActionEvent event) {
                contactScrollPane.contactScrollPanel.setVisible(true);
                findEntryPanel.findPanel.setVisible(true);
            }
        });
    }
}
