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
        String itemName = "apples";
        int priceInCents = 10;
        int quantity = 5;
        Discount d = createCurrentDiscountForItem(itemName, priceInCents, quantity);
        assertEquals(5, d.apply(basket));
    }

    @Test
    public void discountIsTenPercent() {
        String itemName = "milk";
        int priceInCents = 130;
        int quantity = 1;
        Discount d = createCurrentDiscountForItem(itemName, priceInCents, quantity);
        assertEquals(13, d.apply(basket));
    }

    private Discount createCurrentDiscountForItem(String itemName, int priceInCents, int quantity) {
        Stock stock = new Stock(List.of(new StockItem(itemName, priceInCents)));
        Discount discount = new TenPercentDiscount(itemName, stock, TODAY.minusDays(1), TODAY.plusDays(7));
        basket.basket.add(new ItemQuantity(itemName, quantity));
        return discount;
    }


}
