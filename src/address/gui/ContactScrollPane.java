package address.gui;

import address.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
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
    AddressBook addressBook = new AddressBook();

    public ContactScrollPane() {
        // Read data from file and add to AddressBook
        init("../../../AddressInputDataFile2.txt");
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
}
