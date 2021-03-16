package codingdojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HalfPriceAdditionalItemDiscountTest {

    public static final LocalDate TODAY = LocalDate.now();
    private DatedBasket basket = new DatedBasket();
    Stock stock = new Stock(List.of(new StockItem("soup", 65), new StockItem("bread", 80)));


    @Test
    public void noDiscountOnEmptyBasket() {
        Discount d = new HalfPriceAdditionalDiscount(stock, TODAY.minusDays(1), TODAY.plusDays(7), "soup", "bread");
        assertEquals(0, d.apply(basket));
    }

    @Test
    public void discountDoesntApplyOutsideDateRange() {
        HalfPriceAdditionalDiscount d = new HalfPriceAdditionalDiscount(stock, TODAY.plusDays(1), TODAY.plusDays(7), "soup", "bread");
        basket.purchaseDate = 0;
        assertEquals(0, d.apply(basket));
    }

    @Test
    public void discountAppliesWithTwoMainAndOneAdditionalItem() {
        HalfPriceAdditionalDiscount d = new HalfPriceAdditionalDiscount(stock, TODAY.minusDays(1), TODAY.plusDays(7), "soup", "bread");
        basket.basket.add(new ItemQuantity("soup", 2));
        basket.basket.add(new ItemQuantity("bread", 1));
        assertEquals(40, d.apply(basket));
    }
}
