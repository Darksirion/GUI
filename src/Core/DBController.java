package Core;

import java.sql.*;

public class DBController {

    private static final String DB_PATH = "snippet.db";
    private static final DBController dbcontroller = new DBController();
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }

    private DBController() {
    }

    public static DBController getInstance() {
        return dbcontroller;
    }

    public void initDBConnection() {
        try {
            if (connection != null)
                return;
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (connection.isClosed())
                System.out.println("...Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (!connection.isClosed() && connection != null) {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to Database closed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createTable(String name) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE " + name + " (name varchar(10), id int,...);");
        } catch (SQLException e) {
            System.err.println("Tabelle konnte nicht erstellt werden");
            e.printStackTrace();
        }
    }

    public Snippet loadSnippet(String key) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM snippets WHERE primaryKey = " + key + ";");
            String name = rs.getString("name");
            String datum = rs.getString("datum");
            String code = rs.getString("code");
            String sprache = rs.getString("sprache");
            String notizen = rs.getString("notizen");
            String quellen = rs.getString("quellen");
            String author = rs.getString("author");
            String primaryKey = rs.getString("primaryKey");
            rs.close();
            return new Snippet(name, datum, code, sprache, notizen, quellen, author, primaryKey);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public void insert(Snippet snippet) {
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO snippets VALUES(?,?,?,?,?,?,?,?);");
            ps.setString(1, snippet.getName());
            ps.setString(2, snippet.getDatum());
            ps.setString(3, snippet.getCode());
            ps.setString(4, snippet.getSprache());
            ps.setString(5, snippet.getNotizen());
            ps.setString(6, snippet.getQuellen());
            ps.setString(7, snippet.getAuthor());
            ps.setString(8, snippet.getPrimaryKey());
            ps.addBatch();
            connection.setAutoCommit(false);
            ps.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Insert failed");
            e.printStackTrace();
        }
    }

    public void closeDBConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
