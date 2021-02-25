package address;

import address.data.AddressEntry;

import java.util.*;
import java.util.stream.Stream;

/**
 * Holds a collection of objects of the class AddressEntry
 * Contains an add method to add new AddressEntry
 * Contains an list method to iterate through an ArrayList of AddressEntry
 *
 * @author Tey Jon Sornet
 * @since February 2021
 */
public class AddressBook {
    /**
     * ArrayList of AddressEntry which will be the Address Book
     */
    private Vector<AddressEntry> addressEntryList = new Vector<>();

    /**
     * Method to list all records of AddressBook
     */
    public void list() {
        // Iterate through AddressEntry and print out the data
        for (int i = 1; i <= addressEntryList.size(); i++) {
            System.out.print(i + ": ");
            System.out.println(addressEntryList.get(i-1).toString());
        }
    }

    /**
     * Method to add a single AddressEntry into AddressBook
     * @param addressEntry AddressEntry object
     * @return
     */
    public boolean add(AddressEntry addressEntry) {
        addressEntry.setId(addressEntryList.size());
        addressEntryList.add(addressEntry);
        return false;
    }

    /**
     * Method to remove an AddressEntry from AddressBook
     * @param entry AddressEntry object
     */
    public void remove(AddressEntry entry) {
        addressEntryList.remove(entry);
    }

    /**
     * Method to find collection of AddressEntry that matches a given last name
     * @param startOf_lastName String of a user's last name
     * @return collection of AddressEntry items matching last name
     */
    public Vector<AddressEntry> find(String startOf_lastName) {
        // Iterate through all entries and check for matching last name (partial or full)
        Stream<AddressEntry> stream = addressEntryList.stream().filter(entry -> entry.getName().getLastName().toLowerCase().startsWith(startOf_lastName.toLowerCase()));
        Vector<AddressEntry> result = new Vector<>(); // collection to be returned

        // Add matching entries to collection
        stream.forEach(s -> result.add(s));
        result.sort(Comparator.comparing(AddressEntry::getLastName));
        return result;
    }

    /**
     * Method to retrieve AddressBook
     * @return
     */
    public Vector<AddressEntry> getAddressEntryList() {
        addressEntryList.sort(Comparator.comparing(AddressEntry::getLastName));
        return addressEntryList;
    }
}

