package codingdojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HenrysGroceryTest {

    @Test
    void firstSample() throws IOException {
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
        long price = HenrysGrocery.calculatePrice(new StringReader(inputJson));
        assertEquals(315, price);
    }
}
