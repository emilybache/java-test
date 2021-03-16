package codingdojo;

import java.time.LocalDate;

interface Discount {
    long apply(DatedBasket datedBasket);

    void updateValidPeriod(LocalDate from, LocalDate to);
}
