package Database.Ausgabe;


import Core.Snippet;
import Database.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Darksirion on 02.10.15.
 */
public class AusgabeSnippets extends DBController {


    public ObservableList<String> snippetTree(String directoryName) {
        ObservableList<String> dataSnippetAusgabe = FXCollections.observableArrayList();
        try {
            connect();
            PreparedStatement ps = con.prepareStatement("Select snippetName from directories d,snippets s where "
                    + "d.directoryName=? and d.directoryID=s.directoryID");
            ps.setString(1, directoryName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataSnippetAusgabe.add(rs.getString("snippetName"));
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeDBConnection();
        }
        return dataSnippetAusgabe;
    }

    public Snippet snippetTree2(String snippetName) {
        Snippet dataSnippetAusgabe = new Snippet();
        try {
            connect();
            PreparedStatement ps = con.prepareStatement("Select * from directories d where "
                    + "d.directoryName=?");
            ps.setString(1, snippetName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dataSnippetAusgabe.setSnippetID(rs.getInt("snippetID"));
                dataSnippetAusgabe.setDirectoryID(rs.getInt("directoryID"));
                dataSnippetAusgabe.setSnippetName(rs.getString("snippetName"));
                dataSnippetAusgabe.setSprache(rs.getString("sprache"));
                dataSnippetAusgabe.setAuthor(rs.getString("author"));
                dataSnippetAusgabe.setCode(rs.getString("code"));
                dataSnippetAusgabe.setDatum(rs.getString("datum"));
                dataSnippetAusgabe.setNotizen(rs.getString("notizen"));
                dataSnippetAusgabe.setQuellen(rs.getString("quellen"));
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeDBConnection();
        }
        return dataSnippetAusgabe;
    }

    /*public void aktualisierenSnippetAusgabe() {
        // Liste zuruecksetzen
        dataSnippetAusgabe.clear();
        // Datenbank auslesen
        ResultSet resultSet = null;
        resultSet = DBController.select("SELECT * FROM directories");
        try {
            while (resultSet.next()) {
                dataSnippetAusgabe.add(resultSet.getString("primaryKey") + "-" + resultSet.getString("name") + "-" +
                        resultSet.getString("datum") + "-" + resultSet.getString("code") + "-" +
                        resultSet.getString("sprache") + "-" + resultSet.getString("notizen") + "-" +
                        resultSet.getString("quellen") + "-" + resultSet.getString("author") + "-" +
                        resultSet.getString("ordner"));
            }
        } catch (SQLException e) {
            System.out.println("Ausgabe: Keine Aussagen vorhanden!");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Ausgabe: Keine Aussagen vorhanden!");
        } finally {
            try {
                resultSet.close();
                DBController.closeDBConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<String> getDataSnippetAusgabe() {
        return dataSnippetAusgabe;
    }
    */
}
