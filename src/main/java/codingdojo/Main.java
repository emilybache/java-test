package codingdojo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static final NumberFormat FORMAT_AS_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US);

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please provide one argument: the name of the json file containing basket contents and current date");
        }
        String basketFilename = args[0];

        List<StockItem> currentStock = getCurrentStock();
        List<Discount> currentDiscounts = getCurrentDiscounts(currentStock);
        HenrysGrocery henrysGrocery = new HenrysGrocery(currentStock, currentDiscounts);
        long totalPrice = henrysGrocery.calculatePrice(new FileReader(basketFilename));
        System.out.println(FORMAT_AS_DOLLARS.format(totalPrice / 100.0));
    }

    private static List<Discount> getCurrentDiscounts(List<StockItem> stock) {
        List<Discount> discounts = new ArrayList<>();
        LocalDate TODAY = LocalDate.now();
        LocalDate NEXT_MONTH = TODAY.plusMonths(1);
        discounts.add(new TenPercentDiscount("apples", new Stock(stock), TODAY.plusDays(3), NEXT_MONTH.withDayOfMonth(NEXT_MONTH.lengthOfMonth())));
        discounts.add(new HalfPriceAdditionalItemDiscount(new Stock(stock), TODAY.minusDays(1), TODAY.plusDays(7), "soup", "bread"));
        return discounts;
    }

    private static List<StockItem> getCurrentStock() {
        List<StockItem> stock = new ArrayList<>();
        stock.add(new StockItem("soup", 65));
        stock.add(new StockItem("bread", 80));
        stock.add(new StockItem("milk", 130));
        stock.add(new StockItem("apples", 10));
        return stock;
    }
}
