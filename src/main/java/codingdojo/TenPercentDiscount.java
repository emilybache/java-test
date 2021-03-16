package codingdojo;

import java.time.LocalDate;

public class TenPercentDiscount implements Discount {
    private final String name;
    private Stock stock;
    private final LocalDate beginsOn;
    private final LocalDate endsOn;

    public TenPercentDiscount(String name, Stock stock, LocalDate beginsOn, LocalDate endsOn) {
        super();
        this.name = name;
        this.stock = stock;
        this.beginsOn = beginsOn;
        this.endsOn = endsOn;
    }

    @Override
    public long apply(DatedBasket datedBasket) {

        long discount = 0;
        for (ItemQuantity item :
                datedBasket.basket) {
            if (item.name.equals(this.name)) {
                discount += stock.priceFor(name)*0.1*item.quantity;
            }
        }
        return discount;
    }
}
