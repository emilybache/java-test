package codingdojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketParserTest {
    @Test
    void parseSampleBasket() throws JsonProcessingException {
        String inputJson = "{" +
                "  \"basket\": [" +
                "    {" +
                "      \"name\": \"soup\",\n" +
                "      \"quantity\": 3\n" +
                "    }," +
                "    {" +
                "      \"name\": \"bread\",\n" +
                "      \"quantity\": 2\n" +
                "    }" +
                "  ]," +
                "  \"purchaseDate\": 0" +
                "}";
        DatedBasket basket = new ObjectMapper().readValue(inputJson, DatedBasket.class);
        assertEquals(basket.purchaseDate, 0);
        assertEquals(basket.basket.size(), 2);
        assertEquals(basket.basket.get(0).name, "soup");
        assertEquals(basket.basket.get(0).quantity, 3);
    }

}
