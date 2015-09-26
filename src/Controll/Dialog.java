package Controll;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Darksirion on 15.09.15.
 */
public class Dialog {

    static boolean answer;

    public static void information(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static boolean confirm(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            alert.close();
            answer = true;

        }
        return answer;

    }

    public static void exception(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);

    }


    //public static void error(){
    //    String txt = textFld.getText().trim();
    //    String msg = "Text saved: ";
    //    boolean valid = true;
    //    if ((txt.isEmpty()) || (txt.length() < 5)) {
    //        valid = false;
    //        Alert alert = new Alert(AlertType.ERROR);
    //        alert.setTitle(titleTxt);
    //        String s = "Text should be at least 5 characters long. " + "Enter valid text and save. ";
    //        alert.setContentText(s);
    //        alert.showAndWait();
    //        msg = "Invalid text entered: ";
    //    }
    //
    //    actionStatus.setText(msg + txt);
    //    if (! valid) {
    //        textFld.requestFocus();
    //
    //    }
    //
    //}
}
