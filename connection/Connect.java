package connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {

    public static Connection con;
    public static Connection conn = null;

    public static void connects() {

        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String URL = "jdbc:mysql://localhost:3306/online_book_store";

            //  Database credentials
            String USER = "root";
            String PASSWORD = "root";
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Established");

        } catch (Exception ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
