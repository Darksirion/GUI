package UI;

import Controll.Dialog;
import Core.Proxy;
import Test.PathItem;
import UI.Controller;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Darksirion on 16.09.15.
 */
public class TextFieldTreeCellImpl extends TreeCell<PathItem> {

    private TextField textField;
    private Proxy proxy;
    private StringProperty messageProp;
    private Path editingPath;
    private Controller controller;
    private ContextMenu FileMenu = new ContextMenu();
    private ContextMenu DirMenu = new ContextMenu();

    public TextFieldTreeCellImpl(final StringProperty messageProp,Proxy proxy) {
        this.messageProp = messageProp;
        MenuItem editFileItem = new MenuItem("Edit File");
        MenuItem deleteFileItem = new MenuItem("Delete File");
        MenuItem addDirItem = new MenuItem("Add Directory");
        MenuItem deleteDirItem = new MenuItem("Delete Directory");
        FileMenu.getItems().addAll(editFileItem,deleteFileItem);
        DirMenu.getItems().addAll(addDirItem,deleteDirItem);
        /*addFileItem.setOnAction(new EventHandler() {
            public void handle(Event t) {
                /*String newSnippetName = "Test";
                //controller.getNewSnippetName();

                TreeItem newProgrammer =
                        new TreeItem<String>(newSnippetName);
                getTreeItem().getChildren().add(newProgrammer);

                Path newDir = createNewDirectory();
                if (newDir != null) {
                    TreeItem<PathItem> addItem = PathTreeItem.createNode(new PathItem(newDir));
                    getTreeItem().getChildren().add(addItem);
                }
            }
            private Path createNewDirectory() {
                Path newDir = null;
                while (true) {
                    Path path = getTreeItem().getValue().getPath();
                    newDir = Paths.get(path.toAbsolutePath().toString(), "newDirectory" + String.valueOf(getItem().getCountNewDir()));
                    try {
                        proxy.addDirectory(String.valueOf(newDir));
                        Files.createDirectory(newDir);
                        break;
                    } catch (FileAlreadyExistsException ex) {
                        continue;
                    } catch (IOException ex) {
                        cancelEdit();
                        messageProp.setValue(String.format("Creating directory(%s) failed", newDir.getFileName()));
                        break;
                    }
                }
                return newDir;
            }


        });
*/
        editFileItem.setOnAction(t -> {
            controller.newSnippet();
            controller.textFieldNewSnippet.setText(getTreeView().getSelectionModel().getSelectedItem().getValue().toString());

        });
        addDirItem.setOnAction(t -> {
            String showCodeIndex = getTreeView().getSelectionModel().getSelectedItem().getValue().getPath().toString();
            TreeItem newProgrammer =
                    new TreeItem<>("New Directory");
            getTreeItem().getParent().getChildren().add(newProgrammer);
            //String Directory = String.valueOf(newProgrammer.getValue());
            System.out.println(showCodeIndex);
            proxy.addDirectory(showCodeIndex);
        });
        deleteFileItem.setOnAction(event -> {
            String showCodeIndex = getTreeView().getSelectionModel().getSelectedItem().getValue().getPath().toString();
            String deleteObject =getTreeItem().getValue().toString();
            System.out.println(showCodeIndex);
            boolean answer = Dialog.confirm("Löschung", "Wollen Sie " + deleteObject + " wirklich löschen?");
            if (answer) {
                TreeItem<PathItem> selectedNode = getTreeItem();
                if (selectedNode != null) {
                    TreeItem<PathItem> parentNode = selectedNode.getParent();
                    if (parentNode != null) {
                        System.out.println(selectedNode);
                        parentNode.getChildren().remove(selectedNode);
                    }
                }
                proxy.deleteSnippet(showCodeIndex);
            }

        });
        deleteDirItem.setOnAction(event -> {
            String showCodeIndex = getTreeView().getSelectionModel().getSelectedItem().getValue().getPath().toString();
            String deleteObject = String.valueOf(getTreeItem().getValue());
            boolean answer = Dialog.confirm("Löschung","Wollen Sie den Ordner "+ deleteObject + " wirklich löschen?");
            if (answer) {
                TreeItem<PathItem> selectedNode = getTreeItem();
                if (selectedNode !=null){
                    TreeItem<PathItem> parentNode = selectedNode.getParent();
                    if (parentNode == null){
                        parentNode.getChildren().remove(selectedNode);
                    }
                }
            }
            proxy.deleteDirectory(showCodeIndex);
        });
        //editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
          //  @Override
            //public void handle(ActionEvent event) {
              // String oldObject = getTreeItem().getValue();
                //textField2 = new TextField(getString());
//
  //          }
    //    });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null){
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
        if (getItem() == null) {
            editingPath = null;
        } else {
            editingPath =getItem().getPath();
        }
    }

    @Override
    public void commitEdit(PathItem pathItem) {
        // rename the file or directory
        if (editingPath != null) {
            try {
                Files.move(editingPath, pathItem.getPath());
            } catch (IOException ex) {
                cancelEdit();
                messageProp.setValue(String.format("Renaming %s filed", editingPath.getFileName()));
            }
        }
        super.commitEdit(pathItem);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        //setText((String) getItem());
        setText(getString());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(PathItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());

                }
                setText(null);
                setGraphic(textField);

            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if (!getTreeItem().isLeaf()/*&&getTreeItem().getParent()!= null
                        */){
                    setContextMenu(DirMenu);
                }
                else{
                    setContextMenu(FileMenu);
                }
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased(t -> {
            if (t.getCode() == KeyCode.ENTER) {
                Path path = Paths.get(getItem().getPath().getParent().toAbsolutePath().toString(), textField.getText());
                commitEdit(new PathItem(path));
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

    }

  /*  public String getName(){
        String name = controller.getNewSnippetName();

    }
*/

    private String getString() {
        return getItem().toString();
    }
}
