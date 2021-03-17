package codingdojo;

import java.time.LocalDate;
import java.util.List;

public class HalfPriceAdditionalItemDiscount extends Discount {
    private String mainItem;
    private String additionalItem;

    public HalfPriceAdditionalItemDiscount(Stock stock, LocalDate validFrom, LocalDate validUntil, String mainItem, String additionalItem) {
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
        long numberOfTimesPossibleToApplyDiscount = basketQuantity(datedBasket.basket, this.mainItem) / 2;
        long actualNumberOfTimesToApplyDiscount = Math.min(numberOfTimesPossibleToApplyDiscount, basketQuantity(datedBasket.basket, additionalItem));
        discount = Math.round(stock.priceFor(additionalItem)*0.5*actualNumberOfTimesToApplyDiscount);

        return discount;
    }

    private long basketQuantity(List<ItemQuantity> basket, String itemName) {
        for (ItemQuantity item :
                basket) {
            if (item.name.equals(itemName)) {
                return item.quantity;
            }
        }
        return 0;
    }

}
