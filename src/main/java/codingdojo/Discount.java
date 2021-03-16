package codingdojo;

import java.time.LocalDate;

abstract class Discount {
    protected Stock stock;
    protected LocalDate beginsOn;
    protected LocalDate endsOn;

    public Discount(Stock stock, LocalDate from, LocalDate to) {
        this.stock = stock;
        beginsOn = from;
        endsOn = to;
    }

    public abstract long apply(DatedBasket datedBasket);

    public void updateValidPeriod(LocalDate from, LocalDate to) {
        this.beginsOn = from;
        this.endsOn = to;
    }


    protected boolean isBasketDateInValidPeriod(int basketPurchaseDate) {
        LocalDate basketDate = LocalDate.now().plusDays(basketPurchaseDate);
        return !basketDate.isBefore(beginsOn) && !basketDate.isAfter(endsOn);
    }
}
