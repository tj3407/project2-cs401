package address;

import java.sql.*;

/**
 * Class to connect to database containing
 * AddressBook entries
 *
 * @author Tey Jon Sornet
 * @since March 2021
 */
public class DatabaseConnect {
    /**
     * Main method that runs the application
     * @param args String arguments
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Load Oracle JDBC Driver
        Class.forName("oracle.jdbc.OracleDriver");

        // Connect to database
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:mcs1028/bPiR8jKZ@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a statement
        Statement stmt = conn.createStatement();

        // Select ALL from ADDRESS_ENTRIES_TABLE
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");
        System.out.println(rset);

        // Iterate through result and print employee names
        while (rset.next()) {
            // Visit each column and then print
            for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                System.out.print(rset.getString(i).trim() + " | ");
            }
            // Next line and print horizontal divider
            System.out.println("");
            System.out.println("************************");
        }

        // Close access to everything
        rset.close();
        stmt.close();
        conn.close();
    }
}
