package UI;


import Controll.Close;
import Controll.Dialog;
import Core.Proxy;
import Core.Snippet;
import Database.Ausgabe.AusgabeDirectory;
import Database.Ausgabe.AusgabeLang;
import Database.Ausgabe.AusgabeSnippets;
import Database.DBController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private static final String Versionsnummer = "0.1b";
    @FXML
    public
    TextField textFieldNewSnippet;
    @FXML
    TextField textFieldSearch;
    @FXML
    TextArea textAreaCode;
    @FXML
    TextArea textAreaNote;
    @FXML
    TextArea textAreaSource;
    @FXML
    TextArea textAreaCreator;
    @FXML
    TextField textFieldCreateDate;
    @FXML
    TextArea textAreaLegend;
    @FXML
    ComboBox<String> comboBoxLang;
    @FXML
    Button btnSetting;
    @FXML
    Button btnSearch;
    @FXML
    Button btnNew;
    @FXML
    Button btnSave;
    @FXML
    Button btnCancel;
    @FXML
    Label labelNote;
    @FXML
    Label labelEdit;
    @FXML
    MenuItem menuItemNew;
    @FXML
    MenuItem menuItemClose;
    @FXML
    MenuItem menuItemCopy;
    @FXML
    MenuItem menuItemPaste;
    @FXML
    MenuItem menuItemDelete;
    @FXML
    MenuItem menuItemHelp;
    @FXML
    MenuItem menuItemAbout;
    @FXML
    TreeView<String> treeData;

    TreeItem<String> root = new TreeItem<String>("DEPO");
    Snippet sn = null;


    private Stage primaryStage;
    private Proxy proxy;
    private String selection;
    private SettingController settingController;
    private DBController dbc;

    // the FXML annotation tells the loader to inject this variable before invoking initialize.


    public Controller() {
        dbc = DBController.getInstance();
        dbc.initDBConnection();

    }


    @FXML
    public void handleButtonSettingAction(ActionEvent e) throws IOException {

        int[] setting2 = new int[]{0, 1, 0, 1};
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("setting.fxml"));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();

        Stage setting_modal_dialog = new Stage(StageStyle.DECORATED);
        setting_modal_dialog.initModality(Modality.WINDOW_MODAL);
        setting_modal_dialog.initOwner(primaryStage);
        Scene scene = new Scene(root);

        SettingController settingController = (SettingController) fxmlLoader.getController();
        settingController.setStage(setting_modal_dialog);
        settingController.setSetting(setting2);
        setting_modal_dialog.setScene(scene);
        setting_modal_dialog.show();


    }


    public void newMenuClicked() {
        //set leere Vorgabe
        newSnippet();

    }

    public void closeMenuClicked() {
        Close.closeProgramm();
    }

    public void aboutMenuClicked() {
        Dialog.information("About", "Version: " + Versionsnummer);
    }

    public void helpMenuClicked() {
        // Help Pop-up öffnen
        //TODO
    }

    public void selectAllMenuClicked() {
        //select Inhalt von Feld Code in textAreaCode
        //TODO
    }

    public void copyMenuClicked() {
        //copy markierter Code in textAreaCode
        String copyText = "";

        if (Objects.equals(selection, "textAreaCode")) {
            copyText = textAreaCode.getText();
        }

        if (Objects.equals(selection, "textAreaNote")) {
            copyText = textAreaNote.getText();
        }

        if (Objects.equals(selection, "textAreaSource"))
            copyText = textAreaSource.getText();

        if (selection == "textAreaCreator")
            copyText = textAreaCreator.getText();

        //  System.out.println(copyText);
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        //  System.out.println(clipboard);
        final ClipboardContent content = new ClipboardContent();
        content.putString(copyText);
        clipboard.setContent(content);
        //  System.out.println(content);
    }

    public void pasteMenuClicked() {
        //paste Code einfügen in textAreaCode
        Clipboard clipboard = Clipboard.getSystemClipboard();

        //System.out.println(textAreaCode.getId());
        //System.out.println(selection);

        if (Objects.equals(selection, "textAreaCode")) {
            textAreaCode.replaceSelection(clipboard.getString());
        }

        if (Objects.equals(selection, "textAreaNote"))
            textAreaNote.replaceSelection(clipboard.getString());

        if (Objects.equals(selection, "textAreaSource"))
            textAreaSource.replaceSelection(clipboard.getString());

        if (Objects.equals(selection, "textAreaCreator"))
            textAreaCreator.replaceSelection(clipboard.getString());

        // textAreaCode.replaceSelection(clipboard.getString());
        // Unterscheidung zwischen AreaCode und textField Quelle
        //  System.out.println(clipboard);

    }

    public void deleteMenuClicked() {
        //delete lösche markierten Codeschnipsel und Tree neu initialisieren
        //TODO
    }

    public void newButtonClicked() {
        newSnippet();

    }

    public void saveButtonSnippetClicked() {
        //save Snippet speichern und Tree neu initialisieren
        int snippetID = 0;

        String name = textFieldNewSnippet.getText();
        String code = textAreaCode.getText();
        String note = textAreaNote.getText();
        String source = textAreaSource.getText();
        String creator = textAreaCreator.getText();
        String createDate = textFieldCreateDate.getText();
        String selectedObject = treeData.getSelectionModel().getSelectedItem().getValue();
        int directoryID = getDirectoryID();

        if (directoryID != 0) {
            String lang = comboBoxLang.getValue();
            Snippet snip = new Snippet(snippetID, directoryID, name, createDate, code, lang, note, source, creator);
            dbc.insertSnippet(snip, directoryID);
            backHomescreen();
        } else {
            Dialog.information("Unvollständig", "Das ausgewählte Objekt " + selectedObject + "ist kein Ordner \n sondern ein Snippet!");
        }


    }


    public void cancelButtonSnippetClicked() {
        //cancel Snippet verwerfen
        boolean answer = Dialog.confirm("Cancel", "Wollen sie wirklich abbrechen?");
        if (answer)
            backHomescreen();

    }


    public void searchButtonClicked() {
        //search Übergabe von textFieldSearch an die Suchmethode
        String searchObject = textFieldSearch.getText();
        System.out.println(searchObject);
    }


    // loads some strings into the tree in the application UI.


    public void newSnippet() {
        btnNew.setVisible(false);
        btnSearch.setVisible(false);
        btnSetting.setVisible(false);
        comboBoxLang.setVisible(false);
        textFieldSearch.setVisible(false);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        textFieldNewSnippet.setVisible(true);
        textFieldNewSnippet.clear();
        textAreaCode.clear();
        textAreaNote.clear();
        textAreaSource.clear();
        textAreaCreator.clear();
        textAreaCode.setEditable(true);
        textAreaNote.setEditable(true);
        textAreaSource.setEditable(true);
        textAreaCreator.setEditable(true);

    }

    public void backHomescreen() {
        btnNew.setVisible(true);
        btnSearch.setVisible(true);
        btnSetting.setVisible(true);
        comboBoxLang.setVisible(true);
        textFieldSearch.setVisible(true);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        textFieldNewSnippet.setVisible(false);
        textAreaCode.setEditable(false);
        textAreaSource.setEditable(false);
        textAreaCreator.setEditable(false);
        textAreaNote.setEditable(false);
    }


    public String getLang() {
        String lang = comboBoxLang.getValue();
        return lang;
    }

    public int[] getSetting() {
        int[] setting = settingController.getSetting();
        return setting;
    }


    public String getNewSnippetName() {
        String newSnippetName = textFieldNewSnippet.getText();
        return newSnippetName;
    }


    public void SelectionAreaCode() {
        getSelectionAreaCode();
    }

    public void SelcetionAreaSource() {
        getSelectionAreaSource();
    }

    public void SelectionAreaCreator() {
        getSelectionCreator();
    }

    public void SelectionAreaNote() {
        getSelectionNote();
    }

    public String getSelectionAreaCode() {
        selection = textAreaCode.getId();
        return selection;
    }

    public String getSelectionAreaSource() {
        selection = textAreaSource.getId();
        return selection;
    }

    public String getSelectionCreator() {
        selection = textAreaCreator.getId();
        return selection;
    }

    public String getSelectionNote() {
        selection = textAreaNote.getId();
        return selection;
    }

    public int getDirectoryID() {
        TreeItem<String> page = treeData.getSelectionModel().getSelectedItem();
        String pageID = page.getValue();
        int directoryID = dbc.getDirectoryID(pageID);

        return directoryID;
    }

    public int getSnippetID() {
        TreeItem<String> page = treeData.getSelectionModel().getSelectedItem();
        String pageID = page.getValue();
        int snippetID = dbc.getSnippetID(pageID);

        return snippetID;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    @FXML
    protected void selectedTree(MouseEvent m) {
        TreeItem<String> page = treeData.getSelectionModel().getSelectedItem();
        String each = page.getValue();
        createPage(each, page);


    }

    private void createPage(String snip, TreeItem<String> page) {
        AusgabeSnippets as = new AusgabeSnippets();
        ObservableList<String> snippetTree = as.snippetTree(snip);
        if (snippetTree.size() > 0) {
            for (int i = 0; i < root.getChildren().size(); i++)
                if (root.getChildren().get(i).getChildren().size() > 0) {
                    root.getChildren().get(i).getChildren().clear();
                    break;
                }
            for (String Element : snippetTree)
                page.getChildren().add(new TreeItem<>(Element));
            page.setExpanded(true);
        } else {
            sn = as.snippetTree2(snip);
            if (sn.getSnippetID() != 0) {
                textAreaCode.setText(sn.getCode());
                textAreaCreator.setText(sn.getAuthor());
                textAreaNote.setText(sn.getNotizen());
                textAreaSource.setText(sn.getQuellen());
                textFieldCreateDate.setText(sn.getDatum());
                //  System.out.println(sn.getDatum());

            }
        }
    }

    public void initialize(URL url, ResourceBundle rb) {

        AusgabeDirectory ad = new AusgabeDirectory();

        ObservableList<String> snip = ad.directoryTree();
        for (String Element : snip)
            root.getChildren().add(new TreeItem<>(Element));

        treeData.setRoot(root);
        treeData.setShowRoot(false);
        treeData.setEditable(true);
        treeData.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCellImpl(proxy);
            }
        });


        assert comboBoxLang != null : "fx:id=\"comboBoxLang\" was not injected: check your FXML file 'main.fxml'.";

        AusgabeLang al = new AusgabeLang();
        ObservableList<String> langList = al.langTree();
        comboBoxLang.setItems(langList);


        comboBoxLang.getSelectionModel().selectedItemProperty().addListener((observable, oldLang, newLang) -> {
            if (newLang != null) {
                String lang = newLang;

            }

        });

//        comboBoxLang.getSelectionModel().selectedItemProperty().addListener((selected, oldLang, newLang) -> {
//
//            if (newLang != null) {
//                switch (newLang) {
//                    case "JavaFX":
//                        String lang = newLang;
//                        break;
//                    case "C":
//                        lang = newLang;
//                        break;
//                    case "C#":
//                        lang = newLang;
//                        break;
//                    case "VB":
//                        lang = newLang;
//
//                }
//            }
//        });
    }
}













