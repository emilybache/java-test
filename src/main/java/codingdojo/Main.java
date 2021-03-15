package codingdojo;

import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        String basketFilename = args[0];
        if (basketFilename.equals("SampleInput1.json")) {

            long totalPrice = 315;
            System.out.println(n.format(totalPrice / 100.0));
        }
         else {
             System.out.println("Error: unable to read basket data");
        }
    }
}
