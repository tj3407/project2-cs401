package address.gui;

import address.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that holds the AddressBook instance for the application
 *
 * @author Tey Jon Sornet
 * @since February 2021
 */
public class ContactScrollPane extends JFrame {
    /**
     * Create AddressBook instance
     */
    protected static AddressBook addressBook = new AddressBook();
    protected static DefaultListModel<AddressEntry> myAddressEntryListModel = new DefaultListModel<>();
    protected static JList<AddressEntry> addressEntryJList;
    protected static JPanel contactScrollPanel;
    JButton btnRemove;
    JButton btnAdd;
    JButton btnUpdate;
    JScrollPane scrollPane;
    JPanel buttonPanel;

    public ContactScrollPane() {
        // Read data from file and add to AddressBook
        init("./AddressInputDataFile1.txt");
        initialize();
    }

    /**
     * Method to read entries from a file and creates a new AddressEntry
     * instance for each entry. Also adds the AddressEntry into the
     * AddressBook instance that was passed as a parameter
     *
     * @param filename a String object
     */
    public void init(String filename) {
        try {
            // Create instance of File
            File file = new File(filename);
            Scanner input = new Scanner(file);
            int count = 0;
            ArrayList info = new ArrayList();

            // Iterate through txt file
            while (input.hasNextLine()) {
                while (count < 8) {
                    info.add(input.nextLine());
                    count++;
                }
                count = 0;
                // Create AddressEntry instance then add to AddressBook instance
                AddressEntry entry = new AddressEntry(count, info.get(0).toString(), info.get(1).toString(), info.get(2).toString(), info.get(3).toString(), info.get(4).toString(), Integer.parseInt(info.get(5).toString()), info.get(6).toString(), info.get(7).toString());
                addressBook.add(entry);
                info = new ArrayList();
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        contactScrollPanel = new JPanel();
        myAddressEntryListModel.addAll(addressBook.getAddressEntryList());
        addressEntryJList = new JList<>(myAddressEntryListModel);

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
                            btnUpdate.setEnabled(false);
                        } else {
                            btnRemove.setEnabled(true);
                            btnUpdate.setEnabled(true);
                        }
                    }
                }
        );

        contactScrollPanel.setLayout(new BoxLayout(contactScrollPanel, BoxLayout.Y_AXIS));
        // Create add, remove and update button which will be in BorderLayout.EAST
        buttonPanel = new JPanel();
        btnAdd = new JButton("New");
        btnUpdate = new JButton("Update");
        btnRemove = new JButton("Remove");
        btnRemove.setEnabled(false);
        btnUpdate.setEnabled(false);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnRemove);

        // Create scrollPane associated with JList which will be in BorderLayout.CENTER
        JPanel scrollPanel = new JPanel();
        scrollPane = new JScrollPane(this.addressEntryJList);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        scrollPanel.add(scrollPane);

        contactScrollPanel.add(scrollPanel);
        contactScrollPanel.add(buttonPanel);
        contactScrollPanel.setVisible(false);

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

        // Event listener for Update button
        btnUpdate.addActionListener(new ActionListener() {
            // BASED ON event from hitting add button,
            // Add item to our JList's ListModel
            public void actionPerformed(ActionEvent event) {
                int indexToUpdate = addressEntryJList.getSelectedIndex();

                //something is selected otherwise do nothing
                if (indexToUpdate != -1) {
                    // Retrieve the DefaultListModel associated
                    // with our JList and open AddEntryForm GUI with
                    // selected entry. Them remove entry from list
                    AddEntryForm addEntryForm = new AddEntryForm(addressEntryJList.getModel().getElementAt(indexToUpdate));
                    addEntryForm.setVisible(true);
                    addEntryForm.addBtn.setText("Update");
                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(indexToUpdate);
                }
            }
        });
    }
}
