package codingdojo;

import java.time.LocalDate;

public class HalfPriceAdditionalDiscount extends Discount {
    public HalfPriceAdditionalDiscount(Stock stock, LocalDate validFrom, LocalDate validUntil, String mainItem, String additionalItem) {
        super(stock, validFrom, validUntil);

    }

    @Override
    public long apply(DatedBasket datedBasket) {
        if (!isBasketDateInValidPeriod(datedBasket.purchaseDate)) {
            return 0;
        }
        return 0;
    }
}
