package address.gui;

import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookApplicationGUI {
    private JFrame frame;
    protected static ContactScrollPane contactScrollPane = new ContactScrollPane();
    protected static JList<AddressEntry> addressEntryJList;
    protected static DefaultListModel<AddressEntry> myAddressEntryListModel = new DefaultListModel<>();
    JButton btnRemove;
    JButton btnAdd;
    JButton btnDisplay;
    JScrollPane scrollPane;

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
        this.myAddressEntryListModel.addAll(contactScrollPane.addressBook.getAddressEntryList());

        // Create instance of JList for ScrollPane
        addressEntryJList = new JList<>(this.myAddressEntryListModel);

        // Set up the look of the JList
        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.addressEntryJList.setVisibleRowCount(-1);
        this.addressEntryJList.addListSelectionListener(
                new ListSelectionListener() {
                    // If one of the list is selected, enable Remove button
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (addressEntryJList.isSelectionEmpty()) {
                            btnRemove.setEnabled(false);
                        } else {
                            btnRemove.setEnabled(true);
                        }
                    }
                }
        );

        //setup GUI and use the JList we created
        initialize();
    }

    /**
     * Initialize the contents of the frame
     */
    private void initialize() {
        // Set up frame component
        frame = new JFrame("Address Book");
        frame.setLayout(new FlowLayout());
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(780, 960);

        // Create display button
        btnDisplay = new JButton("Display");
        frame.add(btnDisplay);

        // Create add button
        btnAdd = new JButton("New");
        frame.add(btnAdd);

        // Create scrollPane associated with JList
        scrollPane = new JScrollPane(this.addressEntryJList);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVisible(false);
        btnRemove = new JButton("Remove");
        btnRemove.setEnabled(false);
        btnRemove.setVisible(false);
        frame.add(btnRemove);

        // Event listener for Remove button
        btnRemove.addActionListener(new ActionListener() {
            // BASED ON event from hitting remove button,
            // Remove item from our JList's ListModel
            public void actionPerformed(ActionEvent arg0) {
                int index = addressEntryJList.getSelectedIndex();

                //something is selected otherwise do nothing
                if (index != -1) {
                    // Retrieve the DefaultListModel associated
                    // with our JList and remove from it the AddressEntry at this index
                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);

                    // NOTE in your project 2 you will also remove it from your AddressBook.addressEntryList
                    // AND ALSO remove it from the associated database table
                }
            }
        });

        // Event listener for Add button
        btnAdd.addActionListener(new ActionListener() {
            // BASED ON event from hitting add button,
            // Add item to our JList's ListModel
            public void actionPerformed(ActionEvent event) {
                AddEntryForm addEntryForm = new AddEntryForm();
                addEntryForm.setVisible(true);
            }
        });

        // Event listener for Display button
        btnDisplay.addActionListener(new ActionListener() {
            // BASED ON event from hitting display button,
            // Display contact list
            public void actionPerformed(ActionEvent event) {
                scrollPane.setVisible(true);
                btnRemove.setVisible(true);
            }
        });
    }
}
