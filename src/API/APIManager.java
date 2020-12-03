package API;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class APIManager {

  public ArrayList<FoodItem> searchForFoodItem(String searchString) throws UnirestException {
    // Getting all food search hits as a json array
    JSONArray foodArray = getJsonFromAPI(searchString).getJSONArray("branded");

    return topThreeResults(foodArray);
  }

  /**
   * grabs json of search results for search string in NutritionIX API
   *
   * @param searchString the string used to search the API with
   * @return returns a json object of the return json from teh API
   * @throws UnirestException exception
   */
  private JSONObject getJsonFromAPI(String searchString) throws UnirestException {
    // Search Params
    String search = searchString;
    search = search.replace(" ", "%20");
    String params = "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat";

    // Get Http response from api host
    HttpResponse<String> response =
        Unirest.get("https://trackapi.nutritionix.com/v2/search/instant?query=" + search)
            .header("x-app-id", "c8b5e85e")
            .header("x-app-key", "c41950348293545fed04adf9a45020b9")
            .asString();
    JSONObject jsonResponse = new JSONObject(response.getBody());

    return jsonResponse;
  }

  /**
   * Used to convert the JSONArray of hits returned by the api into a ArrayList of food items of the
   * top three hits
   *
   * @param jsonArray a JSONArray of hits returned by array
   * @return
   */
  private ArrayList<FoodItem> topThreeResults(JSONArray jsonArray) {
    ArrayList<FoodItem> results = new ArrayList<>();
    results.add(jsonFieldsToFoodItem(jsonArray.getJSONObject(2)));
    results.add(jsonFieldsToFoodItem(jsonArray.getJSONObject(1)));
    results.add(jsonFieldsToFoodItem(jsonArray.getJSONObject(0)));
    return results;
  }

  /**
   * Converts a JSONObject holding the fields of a food item to a FoodItem
   *
   * @param json JSONObject holding food item fields
   * @return the converted food item
   */
  private FoodItem jsonFieldsToFoodItem(JSONObject json) {
    return new FoodItem(
        json.getString("food_name"),
            json.getInt("serving_qty"),
        json.getInt("nf_calories"));
  }
}
