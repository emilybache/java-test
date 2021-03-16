package codingdojo;

import java.time.LocalDate;

public class TenPercentDiscount implements Discount {
    public TenPercentDiscount(String name, LocalDate beginsOn, LocalDate endsOn) {
        super();
    }

    @Override
    public long applyToBasket(DatedBasket datedBasket) {
        return 1;
    }
}
