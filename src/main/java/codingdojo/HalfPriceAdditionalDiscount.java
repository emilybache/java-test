package codingdojo;

import java.time.LocalDate;

public class HalfPriceAdditionalDiscount extends Discount {
    private String mainItem;
    private String additionalItem;

    public HalfPriceAdditionalDiscount(Stock stock, LocalDate validFrom, LocalDate validUntil, String mainItem, String additionalItem) {
        super(stock, validFrom, validUntil);

        this.mainItem = mainItem;
        this.additionalItem = additionalItem;
    }

    @Override
    public long apply(DatedBasket datedBasket) {
        if (!isBasketDateInValidPeriod(datedBasket.purchaseDate)) {
            return 0;
        }
        long discount = 0;
        for (ItemQuantity item :
                datedBasket.basket) {
            if (item.name.equals(this.mainItem) && item.quantity >= 2) {
                discount += stock.priceFor(additionalItem)*0.5;
            }
        }
        return discount;
    }
}
