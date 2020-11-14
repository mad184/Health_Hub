package apiTests;

import API.APIManager;
import API.FoodItem;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class APITest {

    APIManager APISearcher = new APIManager();

    @Test
    void foodSearch() throws UnirestException {
        ArrayList<FoodItem> results = APISearcher.searchForFoodItem("Cheddar Cheese");
    }
}
