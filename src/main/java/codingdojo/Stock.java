package codingdojo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    private final Map<String, Long> stockPrices  = new HashMap<>();

    public Stock(List<StockItem> stock) {
        for (StockItem item :
                stock) {
            this.stockPrices.put(item.getName(), item.getPrice());
        }
    }

    public long priceFor(String name) {
        return this.stockPrices.get(name);
    }
}
