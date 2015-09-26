package Codehaufen;

import Controll.Dialog;
import Core.Proxy;
import UI.Controller;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Created by Darksirion on 16.09.15.
 */
public class TextFieldTreeCellImpl extends TreeCell<String> {

    private TextField textField;
    private TextField textField2 = textField;
    //  private Proxy proxy;
    private StringProperty messageProp;
    private Controller controller;
    private ContextMenu FileMenu = new ContextMenu();
    private ContextMenu DirMenu = new ContextMenu();

    public TextFieldTreeCellImpl(Proxy proxy) {
        MenuItem addFileItem = new MenuItem("Add File");
        MenuItem deleteFileItem = new MenuItem("Delete Delete");
        MenuItem addDirItem = new MenuItem("Add Directory");
        MenuItem deleteDirItem = new MenuItem("Delete Directory");
        FileMenu.getItems().addAll(deleteFileItem);
        DirMenu.getItems().addAll(addDirItem, deleteDirItem);
    /*    addFileItem.setOnAction(new EventHandler() {
            public void handle(Event t) {
                String newSnippetName = "Test";
                //controller.getNewSnippetName();

                TreeItem newProgrammer =
                        new TreeItem<String>(newSnippetName);
                getTreeItem().getChildren().add(newProgrammer);


            }
        });
*/
        addDirItem.setOnAction(new EventHandler() {
            public void handle(Event t) {
                TreeItem newProgrammer =
                        new TreeItem<String>("New Directory");
                getTreeItem().getParent().getChildren().add(newProgrammer);

                String Directory = String.valueOf(newProgrammer.getValue());
                System.out.println(Directory);
                proxy.addDirectory(Directory);
            }
        });
        deleteFileItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String showCodeIndex = getTreeItem().getParent().getParent().getValue() + "/" + getTreeItem().getParent().getValue() + "/" + getTreeItem().getValue();
                String deleteObject = getTreeItem().getValue();
                boolean answer = Dialog.confirm("Löschung", "Wollen Sie " + deleteObject + " wirklich löschen?");
                if (answer) {
                    TreeItem<String> selectedNode = getTreeItem();
                    if (selectedNode != null) {
                        TreeItem<String> parentNode = selectedNode.getParent();
                        if (parentNode != null) {
                            parentNode.getChildren().remove(selectedNode);
                        }
                    }
                    System.out.println(showCodeIndex);
                }
                proxy.deleteDirectory(deleteObject);
            }
        });
        deleteDirItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String deleteObject = getTreeItem().getValue();
                boolean answer = Dialog.confirm("Löschung", "Wollen Sie den Ordner " + deleteObject + " wirklich löschen?");
                if (answer) {
                    TreeItem<String> selectedNode = getTreeItem();
                    if (selectedNode != null) {
                        TreeItem<String> parentNode = selectedNode.getParent();
                        if (parentNode == null) {
                            parentNode.getChildren().remove(selectedNode);
                        }
                    }
                }
            }
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

        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
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
                if (/*!*/getTreeItem().isLeaf()/*&&getTreeItem().getParent()!= null
                        */) {
                    setContextMenu(FileMenu);
                } else {
                    setContextMenu(DirMenu);
                }
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    //Übergabe newName an proxy
                    commitEdit(textField.getText());
                    String oldObject = getTreeItem().getValue();
                    //System.out.println(oldObject);

                    String newObject = textField.getText();
                    System.out.println(newObject);
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });

    }

  /*  public String getName(){
        String name = controller.getNewSnippetName();

    }
*/

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
