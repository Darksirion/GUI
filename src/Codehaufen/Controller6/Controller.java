//package Codehaufen.Controller6;
//
//
//import Controll.Close;
//import Controll.Dialog;
//import Core.FileNode;
//import Core.Proxy;
//import Core.Snippet;
//import Database.Ausgabe.AusgabeDirectory;
//import Database.Ausgabe.AusgabeLang;
//import Database.Ausgabe.AusgabeSnippets;
//import Database.DBController;
//import Test.PathItem;
//import Test.PathTreeItem;
//import UI.Programmer;
//import UI.SettingController;
//import UI.TextFieldTreeCellImpl;
//import javafx.beans.property.StringProperty;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.input.*;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//import java.io.IOException;
//import java.net.URL;
//import java.nio.file.Path;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.ResourceBundle;
//
//
//public class Controller6 implements Initializable {
//
//    private static final String Versionsnummer = "0.1b";
//    @FXML
//    TextField textFieldSearch;
//    @FXML
//    TextField textFieldNewSnippet;
//    @FXML
//    TextArea textAreaCode;
//    @FXML
//    TextArea textAreaNote;
//    @FXML
//    TextArea textAreaSource;
//    @FXML
//    TextArea textAreaCreator;
//    @FXML
//    TextField textFieldCreateDate;
//    @FXML
//    TextArea textAreaLegend;
//    @FXML
//    ComboBox<String> comboBoxLang;
//    @FXML
//    Button btnSetting;
//    @FXML
//    Button btnSearch;
//    @FXML
//    Button btnNew;
//    @FXML
//    Button btnSave;
//    @FXML
//    Button btnCancel;
//    @FXML
//    Label labelNote;
//    @FXML
//    Label labelEdit;
//    @FXML
//    MenuItem menuItemNew;
//    @FXML
//    MenuItem menuItemClose;
//    @FXML
//    MenuItem menuItemCopy;
//    @FXML
//    MenuItem menuItemPaste;
//    @FXML
//    MenuItem menuItemDelete;
//    @FXML
//    MenuItem menuItemHelp;
//    @FXML
//    MenuItem menuItemAbout;
//    @FXML
//    TreeView<String> treeData;
//
//    TreeItem<String> root=new TreeItem<String>("DEPO");
//    Snippet sn=null;
//
//
//    List<Programmer> programmers = Arrays.<Programmer>asList(
//            new Programmer("Philipp", "Managment", "C", "", "", "", "", ""),
//            new Programmer("Edward", "Test1", "JavaFX", "Test123", "von Seite ABC", "www.google.de", "Edward", "23.09.2015"),
//            new Programmer("Alexander", "Test2", "VB", "", "", "", "", ""),
//            new Programmer("Matthias", "Test3", "C#", "", "", "", "", ""),
//            new Programmer("Dominik", "Test4", "JavaFX", "", "", "", "Dominik", "22.09.2015"),
//            new Programmer("Sven", "Test5", "C", "", "", "", "", ""),
//            new Programmer("Olbertz", "Admin", "VB", "", "", "", "", "")
//    );
//    private Stage primaryStage;
//    private Proxy proxy;
//    private String selection;
//    private Path rootPath;
//    private Path selectedPath;
//    private SettingController settingController;
//    private StringProperty messageProp;
//    private Connection connection;
//    private DBController dbc;
//  // private Ausgabe ausgabe;
//    private static Statement stat;
//    private PreparedStatement prep;
//    private ObservableList<Snippet> data;
//
//    // the FXML annotation tells the loader to inject this variable before invoking initialize.
//    @FXML
//    private TreeView<PathItem> treeView;
//
//
//    public Controller() {
//        dbc = DBController.getInstance();
//        dbc.initDBConnection();
//
//    }
//
//
//    private void loadSnippets() {
//        FileNode root = proxy.loadTree("pfad");
//    }
//
//    @FXML
//    public void handleButtonSettingAction(ActionEvent e) throws IOException {
//
//        int[] setting2 = new int[]{0, 1, 0, 1};
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("setting.fxml"));
//        fxmlLoader.load();
//        Parent root = fxmlLoader.getRoot();
//
//        Stage setting_modal_dialog = new Stage(StageStyle.DECORATED);
//        setting_modal_dialog.initModality(Modality.WINDOW_MODAL);
//        setting_modal_dialog.initOwner(primaryStage);
//        Scene scene = new Scene(root);
//
//        SettingController settingController = (SettingController) fxmlLoader.getController();
//        settingController.setStage(setting_modal_dialog);
//        settingController.setSetting(setting2);
//        setting_modal_dialog.setScene(scene);
//        setting_modal_dialog.show();
//
//
//    }
//
//
//    public void newMenuClicked() {
//        //set leere Vorgabe
//        newSnippet();
//
//        KeyCode keyCode = KeyCode.getKeyCode("name");
//        menuItemNew.setAccelerator(KeyCombination.keyCombination("A"));
//    }
//
//    public void closeMenuClicked() {
//        Close.closeProgramm();
//    }
//
//    public void aboutMenuClicked() {
//        Dialog.information("About", "Version: " + Versionsnummer);
//    }
//
//    public void helpMenuClicked() {
//        // Help Pop-up öffnen
//        //TODO
//    }
//
//    public void selectAllMenuClicked() {
//        //select Inhalt von Feld Code in textAreaCode
//        //TODO
//    }
//
//    public void copyMenuClicked() {
//        //copy markierter Code in textAreaCode
//        String copyText = "";
//
//        if (Objects.equals(selection, "textAreaCode")) {
//            copyText = textAreaCode.getText();
//        }
//
//        if (Objects.equals(selection, "textAreaNote")) {
//            copyText = textAreaNote.getText();
//        }
//
//        if (Objects.equals(selection, "textAreaSource"))
//            copyText = textAreaSource.getText();
//
//        if (selection == "textAreaCreator")
//            copyText = textAreaCreator.getText();
//
//        System.out.println(copyText);
//        final Clipboard clipboard = Clipboard.getSystemClipboard();
//        System.out.println(clipboard);
//        final ClipboardContent content = new ClipboardContent();
//        content.putString(copyText);
//        clipboard.setContent(content);
//        System.out.println(content);
//    }
//
//    public void testButtonClicked() {
//        //  String test = dbc.getDirectoryName("1");
//        //    System.out.println(test);
//
//        //proxy.addDirectory("Test123");
//        // FileNodeLoadTreeItems(/*fileNode*/);
//    }
//    public void pasteMenuClicked() {
//        //paste Code einfügen in textAreaCode
//        Clipboard clipboard = Clipboard.getSystemClipboard();
//
//        //System.out.println(textAreaCode.getId());
//        //System.out.println(selection);
//
//        if (Objects.equals(selection, "textAreaCode")) {
//            textAreaCode.replaceSelection(clipboard.getString());
//        }
//
//        if (Objects.equals(selection, "textAreaNote"))
//            textAreaNote.replaceSelection(clipboard.getString());
//
//        if (Objects.equals(selection, "textAreaSource"))
//            textAreaSource.replaceSelection(clipboard.getString());
//
//        if (Objects.equals(selection, "textAreaCreator"))
//            textAreaCreator.replaceSelection(clipboard.getString());
//
//        // textAreaCode.replaceSelection(clipboard.getString());
//        // Unterscheidung zwischen AreaCode und textField Quelle
//        //  System.out.println(clipboard);
//
//    }
//
//    public void deleteMenuClicked() {
//        //delete lösche markierten Codeschnipsel und Tree neu initialisieren
//        //TODO
//    }
//
//    public void newButtonClicked() {
//        newSnippet();
//        textFieldNewSnippet.clear();
//        // textAreaCode.clear();
//        //   textAreaCode.setEditable(true);
//        //    textAreaNote.setEditable(true);
//        //    textAreaSource.setEditable(true);
//    }
//
//    public void saveButtonSnippetClicked() {
//        //save Snippet speichern und Tree neu initialisieren
//        //  String name= textFieldSearch.getText();
//        int snippetID=0;
//        int directoryID=0;
//        String name = textFieldNewSnippet.getText();
//        String code = textAreaCode.getText();
//        String note = textAreaNote.getText();
//        String source = textAreaSource.getText();
//        String creator = textAreaCreator.getText();
//        String createDate = textFieldCreateDate.getText();
//        String primaryKey = null;
//        String datum = "01.01.2015";
//
//        //TreeItem<String> selectedItem = (TreeItem)treeView.getSelectionModel().getSelectedItem();
//        //if (selectedItem != null) {
//        //   String pathString = selectedItem.getFullPath();
//        //}
//        // String selectedPath = treeView.getSelectionModel().getSelectedItem().getValue();
//        // ObjectProperty<PathItem> selectedPath = treeView.getSelectionModel().getSelectedItem().getValue();
//        //String test = rootPath.toAbsolutePath().toString();
//        //     String Path = treeView.getSelectionModel().getSelectedItem().getValue().getPath().toString();
//        // System.out.println(test);
//        // System.out.println(Path);
//        //     String selectedParentPath = /*test +"/"+comboBoxLang.getValue()+*/Path/*+name*/;
//        //System.out.println(selectedParentPath);
//        String lang = comboBoxLang.getValue();
//        Snippet snip = new Snippet(snippetID, directoryID,name, datum, code, lang, note, source, creator);
//        String dir = "2";
//      // dbc.insertSnippet(snip, dir);
//
//
//        //      proxy.addSnippet(name, code, note, source, creator, selectedParentPath);
//
//
//        String parent = "test";
//        // System.out.println(selectedParentPath);
//        // System.out.println(name);
//        // System.out.println(lang);
//        // System.out.println(code);
//        // System.out.println(note);
//        // System.out.println(source);
//        // System.out.println(creator);
//        // System.out.println(createDate);
//        //TODO
//        backHomescreen();
////        FileNodeLoadTreeItems();
//
//
//    }
//
//
//    public void cancelButtonSnippetClicked() {
//        //cancel Snippet verwerfen
//        boolean answer = Dialog.confirm("Cancel", "Wollen sie wirklich abbrechen?");
//        if (answer)
//            backHomescreen();
//
//    }
//
//
//    public void searchButtonClicked() {
//        //search Übergabe von textFieldSearch an die Suchmethode
//        String searchObject = textFieldSearch.getText();
//        System.out.println(searchObject);
//    }
//
//
//
//    /*
//    public void DBLoadTreeItems(){
//        TreeItem<String> root = new TreeItem<>("Snippet");
//        treeData.setEditable(true);
//        treeData.setShowRoot(false);
//
//        for (Snippet snippet : snippets){
//            TreeItem<String> snipLeaf = new TreeItem<>(snippet.getSnippetName());
//            boolean found = false;
//            for (TreeItem<String> childNode : root.getChildren()){
//                if(childNode.getValue().contentEquals(snippet.getDir())) {
//                    if (snippet.getSprache().contentEquals(snippet.getDir())) {
//                        childNode.getChildren().add(snipLeaf);
//                        found = true;
//                    }
//                }
//            }
//            if (!found){
//            if(snippet.getSprache().contentEquals(comboBoxLang.getValue())){
//                TreeItem childNote = new TreeItem(snippet.getDir());
//                root.getChildren().add(snipLeaf);
//                }
//
//            }
//        }
//    }
//    */
//
//    // loads some strings into the tree in the application UI.
//    public void FileNodeLoadTreeItems() {
//
//      //  FileNode fileNode = proxy.loadTree(root);
//        //String test2 = fileNode.getPrimaryKey();
//        //System.out.println(test2);
//
//        String test = "./New Directory";
//        //messageProp.setValue(null);
//      //  rootPath = Paths.get(test2);
//        PathItem pathItem = new PathItem(rootPath);
//        treeView.setEditable(true);
//        treeView.setShowRoot(false);
//        treeView.setRoot(createNode(pathItem));
//        treeView.setCellFactory((TreeView<PathItem> p) -> {
//            final TextFieldTreeCellImpl cell = new TextFieldTreeCellImpl(messageProp, proxy);
//
//
//            return cell;
//        });
//
//    }
//
//
//
//    public void loadTreeItems() {
//        TreeItem<String> root = new TreeItem<>("Snippet");
//        treeView.setEditable(true);
//
//        treeView.setShowRoot(false);
//
//
//        for (Programmer programmer : programmers) {
//            TreeItem<String> progLeaf = new TreeItem<>(programmer.getName());
//            boolean found = false;
//            for (TreeItem<String> groupNode : root.getChildren()) {
//                if (groupNode.getValue().contentEquals(programmer.getGroup())) {
//                    if (programmer.getLang().contentEquals(comboBoxLang.getValue())) {
//                        groupNode.getChildren().add(progLeaf);
//                        found = true;
//                    }
//                }
//            }
//            if (!found) {
//                if (programmer.getLang().contentEquals(comboBoxLang.getValue())) {
//                    TreeItem groupNode = new TreeItem(programmer.getGroup());
//                    root.getChildren().add(groupNode);
//                    groupNode.getChildren().add(progLeaf);
//                }
//            }
//        }
//
//        //  treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
//        //    @Override
//        //     public TreeCell<String> call(TreeView<String> p) {
//        //         return new TextFieldTreeCellImpl(proxy);
//        //     }
//        //  });
//
//        //treeView.setRoot(root);
//    }
//
//    public void newSnippet() {
//        btnNew.setVisible(false);
//        btnSearch.setVisible(false);
//        btnSetting.setVisible(false);
//        comboBoxLang.setVisible(false);
//        textFieldSearch.setVisible(false);
//        btnSave.setVisible(true);
//        btnCancel.setVisible(true);
//        textFieldNewSnippet.setVisible(true);
//        textFieldNewSnippet.clear();
//        textAreaCode.clear();
//        textAreaNote.clear();
//        textAreaSource.clear();
//        textAreaCreator.clear();
//        textAreaCode.setEditable(true);
//        textAreaNote.setEditable(true);
//        textAreaSource.setEditable(true);
//        textAreaCreator.setEditable(true);
//
//    }
//
//    public void backHomescreen() {
//        btnNew.setVisible(true);
//        btnSearch.setVisible(true);
//        btnSetting.setVisible(true);
//        comboBoxLang.setVisible(true);
//        textFieldSearch.setVisible(true);
//        btnSave.setVisible(false);
//        btnCancel.setVisible(false);
//        textFieldNewSnippet.setVisible(false);
//        textAreaCode.setEditable(false);
//        textAreaSource.setEditable(false);
//        textAreaCreator.setEditable(false);
//        textAreaNote.setEditable(false);
//    }
//
//    /*public void showCode(TreeItem<PathItem> snippet) throws IOException {
//            if (snippet.getValue().equals(proxy.getSnippet(getSelectedObject()).getSnippetName())) {
//                System.out.println(snippet.getValue());
//                System.out.println(proxy.getSnippet(getSelectedObject()).getSnippetName());
//                textAreaCode.setText(proxy.getSnippet(getSelectedObject()).getCode());
//                textAreaNote.setText(proxy.getSnippet(getSelectedObject()).getNotizen());
//                textAreaSource.setText(proxy.getSnippet(getSelectedObject()).getQuellen());
//                textAreaCreator.setText(proxy.getSnippet(getSelectedObject()).getAuthor());
//                textFieldCreateDate.setText(proxy.getSnippet(getSelectedObject()).getDatum());
//            } else {
//
//            }
//    }
//    */
//
//    public String showCodeIndex() {
//        String select = comboBoxLang.getValue() + "/" + treeView.getSelectionModel().getSelectedIndex();
//        return select;
//    }
//
//    //   public void setMain(StartUI main) {
//    //      this.main = main;
//
//    // Add observable list data to the table
//    // personTable.setItems(mainApp.getPersonData());
//    //}
//
//
//    public String getLang() {
//        String lang = comboBoxLang.getValue();
//        return lang;
//    }
//
//    public int[] getSetting() {
//        int[] setting = settingController.getSetting();
//        return setting;
//    }
//
//    public String getSelectedObject() {
//        String selectedObject = treeView.getSelectionModel().getSelectedItem().getValue().getPath().toString();
//        return selectedObject;
//    }
//
//
//    public String getNewSnippetName() {
//        String newSnippetName = textFieldNewSnippet.getText();
//        return newSnippetName;
//    }
//
//
//    public void SelectionAreaCode() {
//        getSelectionAreaCode();
//    }
//
//    public void SelcetionAreaSource() {
//        getSelectionAreaSource();
//    }
//
//    public void SelectionAreaCreator() {
//        getSelectionCreator();
//    }
//
//    public void SelectionAreaNote() {
//        getSelectionNote();
//    }
//
//    public String getSelectionAreaCode() {
//        selection = textAreaCode.getId();
//        return selection;
//    }
//
//    public String getSelectionAreaSource() {
//        selection = textAreaSource.getId();
//        return selection;
//    }
//
//    public String getSelectionCreator() {
//        selection = textAreaCreator.getId();
//        return selection;
//    }
//
//    public String getSelectionNote() {
//        selection = textAreaNote.getId();
//        return selection;
//    }
//
//    public void setProxy(Proxy proxy) {
//        this.proxy = proxy;
//    }
//
//
//    private TreeItem<PathItem> createNode(PathItem pathItem) {
//        return PathTreeItem.createNode(pathItem);
//    }
//
//    @FXML
//    public void selectedTree(MouseEvent m) {
//        TreeItem<String> page = treeData.getSelectionModel().getSelectedItem();
//        String each = page.getValue();
//        createPage(each,page);
//
//
//    }
//
//    private void createPage(String snip, TreeItem<String> page) {
//        AusgabeSnippets as = new AusgabeSnippets();
//        ObservableList<String> snippetTree = as.snippetTree(snip);
//        if (snippetTree.size()>0){
//            for(int i=0;i<root.getChildren().size();i++)
//                if(root.getChildren().get(i).getChildren().size()>0){
//                    root.getChildren().get(i).getChildren().clear();
//                    break;
//                }
//            for(String snippetElement:snippetTree)
//                page.getChildren().add(new TreeItem<String>(snippetElement));
//            page.setExpanded(true);
//        }
//        else{
//            sn=as.snippetTree2(snip);
//            if(sn.getSnippetID() !=0){
//                textAreaCode.setText(sn.getCode());
//                textAreaCreator.setText(sn.getAuthor());
//                textAreaNote.setText(sn.getNotizen());
//                textAreaSource.setText(sn.getQuellen());
//                textFieldCreateDate.setText(sn.getDatum());
//
//            }
//        }
//    }
//
//    public void initialize(URL url, ResourceBundle rb) {
//
//        AusgabeDirectory ad = new AusgabeDirectory();
//        ObservableList<String> gelen=ad.directoryTree();
//        for(String groupElement : gelen)
//                root.getChildren().add(new TreeItem<>(groupElement));
//        treeData.setRoot(root);
//        treeData.setShowRoot(false);
//
//        //  ausgabe.aktualisierenLangAusgabe();
//        //  ausgabe.aktualisierenSnippetAusgabe();
//        //  ausgabe.aktualisierenTreeAusgabe();
//
//        /*
//        try {
//            connection = DBController.getConnection();
//            stat = connection.createStatement();
//            //stat.executeUpdate("drop table if exists user");
//            stat.executeUpdate("create table if not exists user(Name varchar(50),Email varchar(50));");
//            data = FXCollections.observableArrayList();
//            ResultSet rs = connection.createStatement().executeQuery("select * from user");
//            while (rs.next()) {
//                data.add(new UserData(rs.getString("Name"), rs.getString("Email")));
//            }
//            column1.setCellValueFactory(new PropertyValueFactory("Name"));
//            column2.setCellValueFactory(new PropertyValueFactory("Email"));
//            treeView.setItems(null);
//            treeView.setItems(data);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//        */
//
//
//        //list_of_snippet = new ArrayList<>();
//
//
////        loadTreeItems();
//        //loadTreeItems("1", "2", "3","4","5");
//
//        /*
//       treeView.getSelectionModel().selectedItemProperty().addListener(
//                (selected, oldValue, newValue) -> {
//                    try {
//                        showCode(newValue);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//
//*/
//
//        assert comboBoxLang != null : "fx:id=\"comboBoxLang\" was not injected: check your FXML file 'main.fxml'.";
//
//        /*comboBoxLang.getItems().addAll(
//                "VB",
//                "JavaFX",
//                "C",
//                "C#"
//
//        );
//        */
//        /*
//         *  ComboBoxLang wird mit ELementen aus der ObservableList DataLangAusgabe gefüllt
//         */
//        AusgabeLang al = new AusgabeLang();
//        ObservableList<String> langList = al.langTree();
//        comboBoxLang.setItems(langList);
//
//
//        comboBoxLang.getSelectionModel().selectedItemProperty().addListener((selected, oldLang, newLang) -> {
//            //    if (oldLang != null) {
//            //        switch (oldLang) {
//            //              case "Java":
//            //
//            //                break;
//            //            case "C":
//            //                break;
//            //            case "C#":
//            //                break;
//            //        }
//            //    }
//            //
//
//            if (newLang != null) {
//                //System.out.println(newLang);
//                switch (newLang) {
//                    case "JavaFX":
//                        String lang = newLang;
//                        //System.out.println(newLang);
//                        //FileNodeLoadTreeItems();
//                        //loadTreeItems();
//                        break;
//                    case "C":
//                        lang = newLang;
//                        //System.out.println(newLang);
//
//                        //loadTreeItems();
//                        break;
//                    case "C#":
//                        lang = newLang;
//                        //System.out.println(newLang);
//
//                        //loadTreeItems();
//                        break;
//                    case "VB":
//                        lang = newLang;
//                        //loadTreeItems();
//                }
//            }
//
//
//        });
//
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
