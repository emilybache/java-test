package codingdojo;

public class StockItem {
    private final String name;
    private final long price;

    public StockItem(String name, long priceInCents) {
        this.name = name;
        this.price = priceInCents;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
