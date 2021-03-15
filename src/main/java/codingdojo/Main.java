package codingdojo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        String basketFilename = args[0];
        if (basketFilename.equals("SampleInput1.json")) {
            Reader dataReader = new FileReader(basketFilename);
            long totalPrice = HenrysGrocery.calculatePrice(dataReader);
            System.out.println(n.format(totalPrice / 100.0));
        }
         else {
             System.out.println("Error: unable to read basket data");
        }
    }
}
