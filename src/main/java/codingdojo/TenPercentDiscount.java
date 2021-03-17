package codingdojo;

import java.time.LocalDate;

public class TenPercentDiscount extends Discount {
    private final String name;

    public TenPercentDiscount(String name, Stock stock, LocalDate beginsOn, LocalDate endsOn) {
        super(stock, beginsOn, endsOn);
        this.name = name;

    }

    @Override
    public long apply(DatedBasket datedBasket) {
        if (!isBasketDateInValidPeriod(datedBasket.purchaseDate)) {
            return 0;
        }

        return datedBasket.basket
                .stream()
                .filter(item -> item.name.equals(this.name))
                .mapToLong(item -> (long) (stock.priceFor(name) * 0.1 * item.quantity))
                .sum();
    }

}
