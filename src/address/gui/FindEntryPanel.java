package address.gui;

import address.data.AddressEntry;
import address.gui.event.TextChangeDocumentListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI Class for the Find entry component
 */
public class FindEntryPanel {
    /**
     * Find button
     */
    private JButton btnFind;
    private JTextField findTextField;
    private String lastName;
    protected static JPanel findPanel;
    JScrollPane findScrollPane;
    DefaultListModel<AddressEntry> myAddressEntryListModel = new DefaultListModel<>();
    JList<AddressEntry> addressEntryJList = new JList<>(myAddressEntryListModel);

    public FindEntryPanel() {
        findPanel = new JPanel();
        findTextField = new JTextField(30);
        findPanel.add(findTextField);

        findTextField.getDocument().addDocumentListener(
                new TextChangeDocumentListener() {
                    @Override
                    public void update(DocumentEvent e) {
                        lastName = findTextField.getText();
                    }
                }
        );

        btnFind = new JButton("Find");
        findPanel.add(btnFind);

        btnFind.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myAddressEntryListModel = new DefaultListModel<>();
                        myAddressEntryListModel.addAll(ContactScrollPane.addressBook.find(lastName));
                        addressEntryJList.setModel(myAddressEntryListModel);
                        addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                    }
                }
        );

        findScrollPane = new JScrollPane(addressEntryJList);
        findScrollPane.setPreferredSize(new Dimension(600, 200));

        findPanel.add(findScrollPane);
        findPanel.setVisible(false);
    }
}
