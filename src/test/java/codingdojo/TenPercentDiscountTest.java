package codingdojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TenPercentDiscountTest {

    public static final LocalDate TODAY = LocalDate.now();

    @Test
    public void noDiscountWhenWrongKindOfItem() {
        Discount d = new TenPercentDiscount("apples", TODAY.minusDays(1), TODAY.plusDays(7));
        assertEquals(0, d.apply(new DatedBasket()));
    }

    @Test
    public void discountAppliesToAllItems() {
        Discount d = new TenPercentDiscount("apples", TODAY.minusDays(1), TODAY.plusDays(7));
        DatedBasket basket = new DatedBasket();
        basket.basket.add(new ItemQuantity("apples", 5));
        assertEquals(5, d.apply(basket));
    }
}
