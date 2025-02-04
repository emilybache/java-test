package codingdojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HenrysGrocery {

    private Stock stock;
    private List<Discount> discounts;

    public HenrysGrocery() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public HenrysGrocery(List<StockItem> stock, List<Discount> discounts) {

        this.stock = new Stock(stock);
        this.discounts = discounts;
    }

    public long calculatePrice(Reader dataReader) throws IOException {
        DatedBasket basket = new ObjectMapper().readValue(dataReader, DatedBasket.class);

        return calculatePrice(basket);
    }

    public long calculatePrice(DatedBasket datedBasket) {
        long price = datedBasket.basket
                .stream()
                .mapToLong(item -> item.quantity * stock.priceFor(item.name))
                .sum();
        long discount = discounts
                .stream()
                .mapToLong(d -> d.apply(datedBasket))
                .sum();
        return price - discount;
    }

}
