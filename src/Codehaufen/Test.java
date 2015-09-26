package Codehaufen;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Darksirion on 10.09.15.
 */
public class Test {

    public static void main(String[] args) {
        Date datum = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String formatString = dateFormat.format(datum);
        System.out.println(formatString);
    }
}
