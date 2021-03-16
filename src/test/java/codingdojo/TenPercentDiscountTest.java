package codingdojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TenPercentDiscountTest {

    public static final LocalDate TODAY = LocalDate.now();

    @Test
    public void noDiscountWhenWrongKindOfItem() {
        Discount d = new TenPercentDiscount("apples", TODAY.minusDays(1), TODAY.plusDays(7));
        assertEquals(0, d.apply(new DatedBasket()));
    }
}
