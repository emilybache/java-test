package codingdojo;

public class ItemQuantity {
    public String name;
    public long quantity;

    // used by jackson
    public ItemQuantity() {
    }

    public ItemQuantity(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
