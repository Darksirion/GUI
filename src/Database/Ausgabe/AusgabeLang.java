package Database.Ausgabe;

import Database.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Darksirion on 02.10.15.
 */
public class AusgabeLang extends DBController {


    public ObservableList<String> langTree() {
        ObservableList<String> dataLangAusgabe = FXCollections.observableArrayList();
        try {
            connect();
            PreparedStatement ps = connection.prepareStatement("Select sprache from snippets");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!dataLangAusgabe.equals(rs.getString("sprache"))) {
                    dataLangAusgabe.add(rs.getString("sprache"));
                }
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeDBConnection();
        }
        return dataLangAusgabe;
    }
   /*
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






    public ObservableList<String> getDataLangAusgabe() {
        return dataLangAusgabe;
    }
    */
}
