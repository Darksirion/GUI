package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Darksirion on 30.09.15.
 */
public class Ausgabe {
    static ObservableList<String> dataTreeAusgabe = FXCollections.observableArrayList("leer");
    static ObservableList<String> dataSnippetAusgabe = FXCollections.observableArrayList("leer");
    static ObservableList<String> dataLangAusgabe = FXCollections.observableArrayList("leer");

    public void aktualisierenTreeAusgabe() {
        // Liste zuruecksetzen
        dataTreeAusgabe.clear();
        // Datenbank auslesen
        ResultSet resultSet = null;
        resultSet = DBController.select("SELECT * FROM directories");
        try {
            while (resultSet.next()) {
                dataTreeAusgabe.add(resultSet.getString("key") + "-" + resultSet.getString("name") + "-" + resultSet.getString("parent"));
                System.out.println(dataTreeAusgabe);
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

    public void aktualisierenSnippetAusgabe() {
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

    public void aktualisierenLangAusgabe() {
        // Liste zuruecksetzen
        dataLangAusgabe.clear();
        // Datenbank auslesen
        ResultSet resultSet = null;
        resultSet = DBController.select("SELECT sprache FROM snippets");
        try {
            while (resultSet.next()) {
                dataLangAusgabe.add(resultSet.getString("primaryKey") + "-" + resultSet.getString("sprache"));
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


    public ObservableList<String> getDataTreeAusgabe() {
        return dataTreeAusgabe;
    }

    public ObservableList<String> getDataSnippetAusgabe() {
        return dataSnippetAusgabe;
    }

    public ObservableList<String> getDataLangAusgabe() {
        return dataLangAusgabe;
    }
}
