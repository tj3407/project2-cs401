package address;

import java.sql.*;

public class DatabaseConnect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Load Oracle JDBC Driver
        Class.forName("oracle.jdbc.OracleDriver");

        // Connect to database
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:mcs1028/bPiR8jKZ@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a statement
        Statement stmt = conn.createStatement();

        // Select ALL from ADDRESS_ENTRIES_TABLE
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESS_ENTRIES_TABLE");
        System.out.println(rset);

        // Iterate through result and print employee names
        while (rset.next()) {
            // Visit each column
            for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                if (i == 1) {
                    System.out.println(rset.getString(i) + ":");
                } else {
                    System.out.println(rset.getString(i));
                }
            }
            System.out.println("************************");
        }

        // Close access to everything
        rset.close();
        stmt.close();
        conn.close();
    }
}
