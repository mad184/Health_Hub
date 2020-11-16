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
    JSONArray foodArray = getJsonFromAPI(searchString).getJSONArray("hits");

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
    // Url of host
    String host = "https://nutritionix-api.p.rapidapi.com/";
    String charset = "UTF-8";

    // Headers for request
    String xRapidApiHost = "nutritionix-api.p.rapidapi.com";
    String xRapidApiKey = "f8a969fa5emshcd412181335267dp1da0d3jsneb9267e2126e";

    // Search Params
    String search = searchString;
    search = search.replace(" ", "%20");
    String params = "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat";

    // Get Http response from api host
    HttpResponse<String> response =
        Unirest.get("https://nutritionix-api.p.rapidapi.com/v1_1/search/" + search + params)
            .header("x-rapidapi-key", "f8a969fa5emshcd412181335267dp1da0d3jsneb9267e2126e")
            .header("x-rapidapi-host", "nutritionix-api.p.rapidapi.com")
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
    results.add(jsonFieldsToFoodItem(jsonArray.getJSONObject(2).getJSONObject("fields")));
    results.add(jsonFieldsToFoodItem(jsonArray.getJSONObject(1).getJSONObject("fields")));
    results.add(jsonFieldsToFoodItem(jsonArray.getJSONObject(0).getJSONObject("fields")));
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
        json.getString("item_name"),
        json.getDouble("nf_calories"),
        json.getInt("nf_serving_size_qty"));
  }
}
