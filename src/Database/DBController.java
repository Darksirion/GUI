package Database;

import Core.Snippet;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Darksirion on 04.10.15.
 */
public class DBController {

    private static final DBController dbcontroller = new DBController();
    protected static final String HOSTNAME = "jdbc:mysql://localhost:3306/Snippet";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "586489";
    protected Connection connection = null;

    protected void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBController getInstance() {
        return dbcontroller;
    }

    public void initDBConnection() {
        try {
            if (connection != null)
                return;
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
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


    public void insertSnippet(Snippet snippet, int directoryID) {
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO Snippets (DirectoryID, SnippetName, Datum, Code, Sprache, Notizen, Quellen, Author) VALUES(?,?,?,?,?,?,?,?);");
            ps.setInt(1, directoryID);
            ps.setString(2, snippet.getSnippetName());
            ps.setString(3, snippet.getDatum());
            ps.setString(4, snippet.getCode());
            ps.setString(5, snippet.getSprache());
            ps.setString(6, snippet.getNotizen());
            ps.setString(7, snippet.getQuellen());
            ps.setString(8, snippet.getAuthor());
            ps.addBatch();
            connection.setAutoCommit(false);
            ps.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Insert failed");
            e.printStackTrace();
        }
    }

    public void deleteSnippet(String snippetID) {

        PreparedStatement pStmt;
        try {
            pStmt = connection.prepareStatement("DELETE FROM Snippets WHERE SnippetID = ?");
            pStmt.setString(1, snippetID);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertDirectory(String directoryName) {
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO Directories (DirectoryName) VALUE(?);");
            ps.setString(1, directoryName);
            ps.addBatch();
            connection.setAutoCommit(false);
            ps.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("insert failed");
            e.printStackTrace();
        }
    }


    public void renameDirectory(String directoryID, String newName) {
        PreparedStatement pStmt;
        try {
            pStmt = connection.prepareStatement(
                    "UPDATE Directories SET DirectoryName = ? WHERE DirectoryID = ?");
            pStmt.setString(1, newName);
            pStmt.setString(2, directoryID);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Directory konnte nicht umbenannt werden.");
            e.printStackTrace();
        }
    }


    public void deleteDirectory(String directoryID) {
        PreparedStatement dirStmt;
        PreparedStatement snipStmt;
        PreparedStatement pStmt;
        try {
            snipStmt = connection.prepareStatement("SELECT * FROM snippets WHERE directoryID = ?");
            snipStmt.setString(1, directoryID);
            ResultSet rs = snipStmt.executeQuery();
            while (rs.next()) {
                deleteSnippet(rs.getString("snippetID"));
            }
            pStmt = connection.prepareStatement("SELECT * FROM directories WHERE parent = ?");
            pStmt.setString(1, directoryID);
            ResultSet set = pStmt.executeQuery();
            while (set.next()) {
                deleteDirectory(rs.getString("directoryID"));
            }
            dirStmt = connection.prepareStatement("DELETE FROM directories WHERE directoryID = ?");
            dirStmt.setString(1, directoryID);
            dirStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Directory konnte nicht geloescht werden.");
            e.printStackTrace();
        }
    }

    public int getDirectoryID(String directoryName) {
        ResultSet rs = null;
        PreparedStatement pStmt;
        int directoryID = 0;
        ArrayList<Integer> DirectoryID = new ArrayList<>();
        try {
            pStmt = connection.prepareStatement(
                    "SELECT DirectoryID FROM Directories WHERE DirectoryName=?");
            pStmt.setString(1, directoryName);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                directoryID = rs.getInt("directoryID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return directoryID;
    }

    public int getSnippetID(String snippetName) {
        ResultSet rs = null;
        PreparedStatement pStmt;
        int snippetID = 0;
        ArrayList<Integer> SnippetID = new ArrayList<>();
        try {
            pStmt = connection.prepareStatement(
                    "SELECT SnippetID FROM Snippets WHERE SnippetName=?");
            pStmt.setString(1, snippetName);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                snippetID = rs.getInt("snippetID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return snippetID;
    }


    protected void closeDBConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

