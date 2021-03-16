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


    protected boolean isBasketDateIsInValidPeriod(int basketPurchaseDate) {
        boolean basketDateIsInValidPeriod = true;
        LocalDate basketDate = LocalDate.now().plusDays(basketPurchaseDate);
        if (basketDate.isBefore(beginsOn) || basketDate.isAfter(endsOn)) {
            basketDateIsInValidPeriod = false;
        }
        return basketDateIsInValidPeriod;
    }
}
