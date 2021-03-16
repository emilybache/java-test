package codingdojo;

import java.time.LocalDate;

public class TenPercentDiscount implements Discount {
    private final String name;
    private Stock stock;
    private LocalDate beginsOn;
    private LocalDate endsOn;

    public TenPercentDiscount(String name, Stock stock, LocalDate beginsOn, LocalDate endsOn) {
        super();
        this.name = name;
        this.stock = stock;
        this.beginsOn = beginsOn;
        this.endsOn = endsOn;
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

    @Override
    public void updateValidPeriod(LocalDate from, LocalDate to) {
        this.beginsOn = from;
        this.endsOn = to;
    }
}
