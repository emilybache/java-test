package codingdojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class HenrysGrocery {

    public HenrysGrocery() {
        this(new ArrayList<StockItem>(), new ArrayList<Discount>());
    }

    public HenrysGrocery(List<StockItem> stock, List<Discount> discounts) {

    }

    public long calculatePrice(Reader dataReader) throws IOException {
        DatedBasket basket = new ObjectMapper().readValue(dataReader, DatedBasket.class);

        return 315;
    }

    public long calculatePrice(DatedBasket datedBasket) {
        return 0;
    }
}
