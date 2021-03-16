package codingdojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TenPercentDiscountTest {

    public static final LocalDate TODAY = LocalDate.now();
    private DatedBasket basket = new DatedBasket();


    @Test
    public void noDiscountWhenWrongKindOfItem() {
        Stock stock = new Stock(Collections.emptyList());
        Discount d = new TenPercentDiscount("apples", stock, TODAY.minusDays(1), TODAY.plusDays(7));
        assertEquals(0, d.apply(basket));
    }

    @Test
    public void discountAppliesToAllItems() {
        Stock stock = new Stock(List.of(new StockItem("apples", 10)));
        Discount d = new TenPercentDiscount("apples", stock, TODAY.minusDays(1), TODAY.plusDays(7));
        basket.basket.add(new ItemQuantity("apples", 5));
        assertEquals(5, d.apply(basket));
    }

    @Test
    public void discountIsTenPercent() {
        Stock stock = new Stock(List.of(new StockItem("milk", 130)));
        Discount d = new TenPercentDiscount("milk", stock, TODAY.minusDays(1), TODAY.plusDays(7));
        basket.basket.add(new ItemQuantity("milk", 1));
        assertEquals(13, d.apply(basket));
    }
}
