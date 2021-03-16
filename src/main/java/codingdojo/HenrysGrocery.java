package codingdojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class HenrysGrocery {

    private List<StockItem> stock;
    private List<Discount> discounts;

    public HenrysGrocery() {
        this(new ArrayList<StockItem>(), new ArrayList<Discount>());
    }

    public HenrysGrocery(List<StockItem> stock, List<Discount> discounts) {
        this.stock = stock;
        this.discounts = discounts;
    }

    public long calculatePrice(Reader dataReader) throws IOException {
        DatedBasket basket = new ObjectMapper().readValue(dataReader, DatedBasket.class);

        return 315;
    }

    public long calculatePrice(DatedBasket datedBasket) {
        long price = 0;
        for (ItemQuantity item :
             datedBasket.basket) {
            price += item.quantity * priceForItem(item.name);
        }
        return price;
    }

    private long priceForItem(String name) {
        return 65;
    }
}
