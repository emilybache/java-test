package codingdojo;

import java.time.LocalDate;

public class TenPercentDiscount implements Discount {
    private final String name;
    private final LocalDate beginsOn;
    private final LocalDate endsOn;

    public TenPercentDiscount(String name, LocalDate beginsOn, LocalDate endsOn) {
        super();
        this.name = name;
        this.beginsOn = beginsOn;
        this.endsOn = endsOn;
    }

    @Override
    public long apply(DatedBasket datedBasket) {

        long discount = 0;
        for (ItemQuantity item :
                datedBasket.basket) {
            if (item.name.equals(this.name)) {
                discount += 1;
            }
        }
        return discount;
    }
}
