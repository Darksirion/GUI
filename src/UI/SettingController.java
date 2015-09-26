package UI;


import Controll.Dialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Darksirion on 13.09.15.
 */
public class SettingController implements Initializable {


    @FXML
    Button btnSettingClose;

    @FXML
    Button btnSettingSave;

    @FXML
    CheckBox checkBoxSetting1;

    @FXML
    CheckBox checkBoxSetting2;

    @FXML
    CheckBox checkBoxSetting3;
    @FXML
    CheckBox checkBoxSetting4;

    @FXML
    ComboBox<String> comboBoxHotKey;

    @FXML
    ComboBox<String> comboBoxHotKeyComboA;

    @FXML
    ComboBox<String> comboBoxHotKeyComboB;

    private Stage settingStage;
    private int[] setting = new int[4];
    private boolean[] checkBox = new boolean[4];


    public void saveButtonSettingClicked() {
        //save Setting speichern und Setting Window close
        String settingCheckbox = "";
        // int[] setting = new int[4];

        if (checkBoxSetting1.isSelected()) {
            //settingCheckbox += 1;
            setting[0] = 1;
        } else if (!checkBoxSetting1.isSelected()) {
            //settingCheckbox += 0;
            setting[0] = 0;
        }
        if (checkBoxSetting2.isSelected()) {
            //settingCheckbox += 1;
            setting[1] = 1;
        } else if (!checkBoxSetting2.isSelected()) {
            //settingCheckbox += 0;
            setting[1] = 0;
        }
        if (checkBoxSetting3.isSelected()) {
            // settingCheckbox += 1;
            setting[2] = 1;
        } else if (!checkBoxSetting3.isSelected()) {
            // settingCheckbox += 0;
            setting[2] = 0;
        }
        if (checkBoxSetting4.isSelected()) {
            // settingCheckbox += 1;
            setting[3] = 1;
        } else if (!checkBoxSetting4.isSelected()) {
            // settingCheckbox += 0;
            setting[3] = 0;
        }

        System.out.println(setting[0]);
        System.out.println(setting[1]);
        System.out.println(setting[2]);
        System.out.println(setting[3]);
        System.out.println(settingCheckbox);
        settingStage.close();

    }


    public void cancelButtonSettingClicked() {
        // ConfirmBox nachfragen, ob wirklich abbrechen
        System.out.println("Cancel");

        boolean answer = Dialog.confirm("Exit", "Wollen sie das Programm wirklich beenden?");
        if (answer)
            settingStage.close();
    }

    public int[] getSetting() {
        int[] setting = checkBox();
        return setting;
    }

    public void setSetting(int[] setting) {
        this.setting = setting;
    }

    public void setStage(Stage settingStage) {
        this.settingStage = settingStage;
    }

    private int[] checkBox() {


        if (checkBoxSetting1.isSelected()) {
            //settingCheckbox += 1;
            setting[0] = 1;
        } else if (!checkBoxSetting1.isSelected()) {
            //settingCheckbox += 0;
            setting[0] = 0;
        }
        if (checkBoxSetting2.isSelected()) {
            //settingCheckbox += 1;
            setting[1] = 1;
        } else if (!checkBoxSetting2.isSelected()) {
            //settingCheckbox += 0;
            setting[1] = 0;
        }
        if (checkBoxSetting3.isSelected()) {
            // settingCheckbox += 1;
            setting[2] = 1;
        } else if (!checkBoxSetting3.isSelected()) {
            // settingCheckbox += 0;
            setting[2] = 0;
        }
        if (checkBoxSetting4.isSelected()) {
            // settingCheckbox += 1;
            setting[3] = 1;
        } else if (!checkBoxSetting4.isSelected()) {
            // settingCheckbox += 0;
            setting[3] = 0;

        }
        return setting;
    }

    public boolean[] loadSetting(int[] setting) {

        for (int i = 0; i < 4; i++) {

            if (setting[i] != 0) {
                System.out.println(setting[i]);
                checkBox[i] = true;
            } else {
                System.out.println(setting[i]);
                checkBox[i] = false;
            }
            System.out.println(checkBox[i]);


        }
        return checkBox;

    }

    public void initialize(URL url, ResourceBundle rb) {
        loadSetting(setting);
        setSetting(setting);


        checkBoxSetting1.setSelected(checkBox[0]);
        checkBoxSetting2.setSelected(checkBox[1]);
        checkBoxSetting3.setSelected(checkBox[2]);
        checkBoxSetting4.setSelected(checkBox[3]);
        // TODO

        assert comboBoxHotKey != null : "fx:id=\"comboBoxHotKey\" was not injected: check your FXML file 'setting.fxml'.";
        assert comboBoxHotKeyComboA != null : "fx:id=\"comboBoxHotKeyComboA\" was not injected: check your FXML file 'setting.fxml'.";
        assert comboBoxHotKeyComboB != null : "fx:id=\"comboBoxHotKeyCombo\" was not injected: check your FXML file 'setting.fxml'.";


        comboBoxHotKey.getItems().addAll(
                "New",
                "Close",
                "Copy",
                "Paste",
                "Delete",
                "About",
                "Help"

        );

        comboBoxHotKeyComboA.getItems().addAll(
                "Strg",
                "Alt",
                "Command"
        );


        comboBoxHotKeyComboB.getItems().addAll(
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z"
        );

        comboBoxHotKey.getSelectionModel().selectedItemProperty().addListener((selected, oldKey, newKey) -> {

            if (newKey != null) {
                switch (newKey) {
                    case "New":
                        String selection = newKey;
                        // System.out.println(selection);

                        break;
                    case "Close":
                        selection = newKey;


                        break;
                    case "Copy":
                        selection = newKey;
                        selection = newKey;

                        ;
                        System.out.println(newKey);

                        break;
                    case "Paste":
                        selection = newKey;

                        System.out.println(newKey);
                        ;

                }
            }


        });

    }
}
