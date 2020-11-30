package API;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class APIManager {

  //public ArrayList<ExerciseItem> SearchForExerciseItems(String searchString) { }


  public ArrayList<FoodItem> searchForFoodItem(String searchString) throws UnirestException {
    // Getting all food search hits as a json array
    JSONArray foodArray = getFoodJsonFromAPI(searchString).getJSONArray("branded");

    return topThreeResults(foodArray);
  }

  /**
   * Gets JSON of all exercises from WGER API
   *
   * @return a jsonobject with all exercises
   */
  private JSONObject getExerciseJsonFromAPI() throws UnirestException {
    //Get Http response from api host
    HttpResponse<String> response =
            Unirest.get("https://wger.de/api/v2/exercise/?format=json").asString();
    JSONObject jsonResponse = new JSONObject(response.getBody());
    return jsonResponse;
  }

  /**
   * Looks for items from json that contain the string the user is searching for
   *
   * @param searchString exercise user is looking for
   * @return a arraylist containing all the exercises found in the json that match what the user is looking for
   */
  public ArrayList<ExerciseItem> findExerciseSearchMatches(String searchString) throws UnirestException {
    if (searchString.isEmpty()) {
      return null;
    }

    //Holds exercise items
    ArrayList<ExerciseItem> searchResults = new ArrayList<>();

    //Exercise Json from API results
    JSONArray results = getExerciseJsonFromAPI().getJSONArray("results");

    for (int i = 0; i < results.length(); i++) {
      //Gets item at current index
      JSONObject item = results.getJSONObject(i);

      if (item.getString("name_original").toLowerCase().contains(searchString) ||
              item.getString("name_original").toUpperCase().contains(searchString) ||
              item.getString("name_original").contains(searchString)) {
        ExerciseItem exerciseItem = new ExerciseItem(item.getString("name_original"));
        searchResults.add(exerciseItem);
      }
    }
    return searchResults;
  }

  /**
   * grabs json of search results for search string in NutritionIX API
   *
   * @param searchString the string used to search the API with
   * @return returns a json object of the return json from teh API
   * @throws UnirestException exception
   */
  private JSONObject getFoodJsonFromAPI(String searchString) throws UnirestException {
    // Search Params
    String search = searchString;
    search = search.replace(" ", "%20");

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
