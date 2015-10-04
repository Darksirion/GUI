package Database;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Created by Darksirion on 04.10.15.
 */
public class DBController2 {
    protected static final String HOSTNAME = "jdbc:mysql://localhost:3306/Snippet";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "586489";
    protected Connection con = null;

    protected void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void closeDBConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

