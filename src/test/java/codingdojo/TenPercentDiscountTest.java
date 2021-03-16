package codingdojo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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

    @ParameterizedTest
    @MethodSource("dateRangeProvider")
    public void discountDoesntApplyOutsideDateRange(int basketDay, int discountStartDay, int discountEndDay) {
        TenPercentDiscount d = createCurrentDiscountForItem("milk", 130, 1);
        d.updateValidPeriod(TODAY.plusDays(discountStartDay), TODAY.plusDays(discountEndDay));
        basket.purchaseDate = basketDay;
        assertEquals(0, d.apply(basket));
    }

    static Stream<Arguments> dateRangeProvider() {
        return Stream.of(
                arguments(0, 3, 7),
                arguments(0, -13, -7),
                arguments(-1, -13, -7),
                arguments(3, 4, 7)
        );
    }

    private TenPercentDiscount createCurrentDiscountForItem(String itemName, int priceInCents, int quantity) {
        Stock stock = new Stock(List.of(new StockItem(itemName, priceInCents)));
        TenPercentDiscount discount = new TenPercentDiscount(itemName, stock, TODAY.minusDays(1), TODAY.plusDays(7));
        basket.basket.add(new ItemQuantity(itemName, quantity));
        return discount;
    }
}
