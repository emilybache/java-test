package codingdojo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static final NumberFormat FORMAT_AS_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US);

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please provide one argument: the name of the json file containing basket contents and current date");
        }
        String basketFilename = args[0];
        Reader dataReader = new FileReader(basketFilename);
        long totalPrice = new HenrysGrocery().calculatePrice(dataReader);
        System.out.println(FORMAT_AS_DOLLARS.format(totalPrice / 100.0));
    }
}
