package Database.Ausgabe;

import Database.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Darksirion on 02.10.15.
 */
public class AusgabeDirectory extends DBController {


    public ObservableList<String> directoryTree() {
        ObservableList<String> dataTreeAusgabe = FXCollections.observableArrayList();
        try {
            connect();
            //PreparedStatement ps = connection.prepareStatement("SELECT directoryName FROM directories d,snippets s WHERE s.sprache=? AND d.directoryID=s.directoryID GROUP BY directoryName");
            //ps.setString(1,lang);
            PreparedStatement ps = connection.prepareStatement("SELECT DirectoryName FROM Directories");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                    dataTreeAusgabe.add(rs.getString("directoryName"));

            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeDBConnection();
        }
        return dataTreeAusgabe;
    }

    public String grupGetir(int directoryID) {
        String dataTreeAusgabe = "";
        try {
            connect();
            PreparedStatement ps = connection.prepareStatement("Select directoryName from directories "
                    + "where directoryID=?");
            ps.setInt(1, directoryID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            dataTreeAusgabe = rs.getString("directoryID");
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeDBConnection();
        }
        return dataTreeAusgabe;
    }

  /*  public void aktualisierenTreeAusgabe() {
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
    public ObservableList<String> getDataTreeAusgabe() {
        return dataTreeAusgabe;
    }
    */
}
