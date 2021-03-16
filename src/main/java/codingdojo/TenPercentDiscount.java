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
        LocalDate basketDate = LocalDate.now().plusDays(datedBasket.purchaseDate);
        if (basketDate.isBefore(beginsOn) || basketDate.isAfter(endsOn)) {
            return 0;
        }

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
