package codingdojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HalfPriceAdditionalItemDiscountTest {

    public static final LocalDate TODAY = LocalDate.now();
    private DatedBasket basket = new DatedBasket();

    @Test
    public void noDiscountOnEmptyBasket() {
        Stock stock = new Stock(Collections.emptyList());
        Discount d = new HalfPriceAdditionalDiscount(stock, TODAY.minusDays(1), TODAY.plusDays(7), "soup", "bread");
        assertEquals(0, d.apply(basket));
    }
}
