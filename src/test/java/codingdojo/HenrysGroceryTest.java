package codingdojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HenrysGroceryTest {

    private ArrayList<StockItem> stock;

    @BeforeEach
    void setUp() {
        stock = new ArrayList<>();
        stock.add(new StockItem("soup", 65));
        stock.add(new StockItem("bread", 80));
        stock.add(new StockItem("milk", 130));
        stock.add(new StockItem("apples", 10));
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
        long price = new HenrysGrocery().calculatePrice(new StringReader(inputJson));
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
}
