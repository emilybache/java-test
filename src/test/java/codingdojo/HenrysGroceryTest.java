package codingdojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HenrysGroceryTest {

    private ArrayList<StockItem> stock;
    private ArrayList<Discount> discounts;
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate NEXT_MONTH = TODAY.plusMonths(1);

    @BeforeEach
    void setUp() {
        stock = new ArrayList<>();
        stock.add(new StockItem("soup", 65));
        stock.add(new StockItem("bread", 80));
        stock.add(new StockItem("milk", 130));
        stock.add(new StockItem("apples", 10));

        discounts = new ArrayList<>();

    }

    @Test
    void firstSample() throws IOException {
        String inputJson = "{" +
                "  \"basket\": [" +
                "    {" +
                "      \"name\": \"soup\",\n" +
                "      \"quantity\": 3\n" +
                "    }," +
                "    {" +
                "      \"name\": \"bread\",\n" +
                "      \"quantity\": 2\n" +
                "    }" +
                "  ]," +
                "  \"purchaseDate\": 0" +
                "}";
        discounts.add(new TenPercentDiscount("apples", new Stock(stock), TODAY.plusDays(3), NEXT_MONTH.withDayOfMonth(NEXT_MONTH.lengthOfMonth())));
        discounts.add(new HalfPriceAdditionalItemDiscount(new Stock(stock), TODAY.minusDays(1), TODAY.plusDays(7), "soup", "bread"));
        long price = new HenrysGrocery(stock, discounts).calculatePrice(new StringReader(inputJson));
        assertEquals(315, price);
    }

    @Test
    void calculatePriceEmptyBasket() throws IOException {
        long price = new HenrysGrocery(stock, new ArrayList<Discount>()).calculatePrice(new DatedBasket());
        assertEquals(0, price);
    }

    @Test
    void calculatePriceNoDiscounts() throws IOException {
        DatedBasket basket = new DatedBasket();
        basket.basket.add(new ItemQuantity("soup", 1));
        long price = new HenrysGrocery(stock, new ArrayList<Discount>()).calculatePrice(basket);
        assertEquals(65, price);
    }

    @Test
    void calculatePriceSeveralItemsNoDiscounts() throws IOException {
        DatedBasket basket = new DatedBasket();
        basket.basket.add(new ItemQuantity("apples", 1));
        basket.basket.add(new ItemQuantity("milk", 5));
        long price = new HenrysGrocery(stock, new ArrayList<Discount>()).calculatePrice(basket);
        assertEquals(660, price);
    }

    @Test
    void calculatePriceTenPercentDiscount() throws IOException {
        DatedBasket basket = new DatedBasket();
        basket.basket.add(new ItemQuantity("apples", 1));
        discounts.add(new TenPercentDiscount("apples", new Stock(stock), TODAY.minusDays(1), NEXT_MONTH.withDayOfMonth(NEXT_MONTH.lengthOfMonth())));
        long price = new HenrysGrocery(stock, discounts).calculatePrice(basket);
        assertEquals(9, price);
    }
}
