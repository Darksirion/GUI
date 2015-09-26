package Controll;


/**
 * Created by Darksirion on 17.05.15.
 */
public class Close {
    public static void closeProgramm() {


        //ConfirmBox
        boolean answer = Dialog.confirm("Exit", "Wollen sie das Programm wirklich beenden?");
        if (answer)
            System.exit(0);


    }
}
