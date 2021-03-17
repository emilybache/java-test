package codingdojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HalfPriceAdditionalItemDiscountTest {

    public static final LocalDate TODAY = LocalDate.now();
    private DatedBasket basket = new DatedBasket();
    Stock stock = new Stock(List.of(new StockItem("soup", 65), new StockItem("bread", 80)));
    private HalfPriceAdditionalItemDiscount discount = new HalfPriceAdditionalItemDiscount(stock, TODAY.minusDays(1), TODAY.plusDays(7), "soup", "bread");;


    @Test
    public void noDiscountOnEmptyBasket() {
        assertEquals(0, discount.apply(basket));
    }

    @Test
    public void discountDoesntApplyOutsideDateRange() {
        basket.purchaseDate = 0;
        discount.updateValidPeriod(TODAY.plusDays(1), TODAY.plusDays(7));
        assertEquals(0, discount.apply(basket));
    }

    @Test
    public void discountAppliesWithTwoMainAndOneAdditionalItem() {
        basket.basket.add(new ItemQuantity("soup", 2));
        basket.basket.add(new ItemQuantity("bread", 1));
        assertEquals(40, discount.apply(basket));
    }

    @Test
    public void shouldntDiscountIfNoAdditionalItem() {
        basket.basket.add(new ItemQuantity("soup", 2));
        assertEquals(0, discount.apply(basket));
    }

    @Test
    public void applyDiscountTwiceWhenManyMainItemsAndAdditionalItems() {
        basket.basket.add(new ItemQuantity("soup", 4));
        basket.basket.add(new ItemQuantity("bread", 4));
        assertEquals(80, discount.apply(basket));
    }

    @Test
    public void doesntGiveYouMoreDiscountedItemsThanYouBought() {
        basket.basket.add(new ItemQuantity("soup", 4));
        basket.basket.add(new ItemQuantity("bread", 1));
        assertEquals(40, discount.apply(basket));
    }
}
