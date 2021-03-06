package apiTests;

import API.APIManager;
import API.FoodItem;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class APITest {

  APIManager APISearcher = new APIManager();

  @Test
  void foodSearch() throws UnirestException {
    ArrayList<FoodItem> results = APISearcher.searchForFoodItem("Big Mac");
    System.out.println(
        "Top result: "
            + results.get(0).getFoodName()
            + " ,Calories: "
            + results.get(0).getCalories()
            + "\n"
                + "Second result: "
                + results.get(1).getFoodName()
                + " ,Calories: "
                + results.get(1).getCalories()
                + "\n"
                + "Third result: "
                + results.get(2).getFoodName()
                + " ,Calories: "
                + results.get(2).getCalories());
  }

  @Test
  void wrongFoodSearch() throws UnirestException {
    ArrayList<FoodItem> results = APISearcher.searchForFoodItem("1");
    System.out.println(
            "Top result: "
                    + results.get(0).getFoodName()
                    + " ,Calories: "
                    + results.get(0).getCalories()
                    + "\n"
                    + "Second result: "
                    + results.get(1).getFoodName()
                    + " ,Calories: "
                    + results.get(1).getCalories()
                    + "\n"
                    + "Third result: "
                    + results.get(2).getFoodName()
                    + " ,Calories: "
                    + results.get(2).getCalories());
  }

  @Test
  void spaceFoodSearch() throws UnirestException {
    ArrayList<FoodItem> results = APISearcher.searchForFoodItem(" hamburger");
    System.out.println(
            "Top result: "
                    + results.get(0).getFoodName()
                    + " ,Calories: "
                    + results.get(0).getCalories()
                    + "\n"
                    + "Second result: "
                    + results.get(1).getFoodName()
                    + " ,Calories: "
                    + results.get(1).getCalories()
                    + "\n"
                    + "Third result: "
                    + results.get(2).getFoodName()
                    + " ,Calories: "
                    + results.get(2).getCalories());
  }

  @Test
  void spaceAfterFoodSearch() throws UnirestException {
    ArrayList<FoodItem> results = APISearcher.searchForFoodItem("hamburger ");
    System.out.println(
            "Top result: "
                    + results.get(0).getFoodName()
                    + " ,Calories: "
                    + results.get(0).getCalories()
                    + "\n"
                    + "Second result: "
                    + results.get(1).getFoodName()
                    + " ,Calories: "
                    + results.get(1).getCalories()
                    + "\n"
                    + "Third result: "
                    + results.get(2).getFoodName()
                    + " ,Calories: "
                    + results.get(2).getCalories());
  }

  @Test
  void doubleSpaceFoodSearch() throws UnirestException {
    ArrayList<FoodItem> results = APISearcher.searchForFoodItem(" hamburger ");
    System.out.println(
            "Top result: "
                    + results.get(0).getFoodName()
                    + " ,Calories: "
                    + results.get(0).getCalories()
                    + "\n"
                    + "Second result: "
                    + results.get(1).getFoodName()
                    + " ,Calories: "
                    + results.get(1).getCalories()
                    + "\n"
                    + "Third result: "
                    + results.get(2).getFoodName()
                    + " ,Calories: "
                    + results.get(2).getCalories());
  }

  @Test
  void emptyExerciseSearch() throws UnirestException{
    assertNull(APISearcher.findExerciseSearchMatches(""));
  }

  @Test
  void notExerciseSearch() throws UnirestException{
    System.out.println(APISearcher.findExerciseSearchMatches("burger").toString());
  }

  @Test
  void exerciseSearch() throws UnirestException {
    System.out.println(APISearcher.findExerciseSearchMatches("press").toString());
  }

  @Test
  void spaceExerciseSearch() throws UnirestException {
    System.out.println(APISearcher.findExerciseSearchMatches(" press").toString());
  }

  @Test
  void spaceAfterExerciseSearch() throws UnirestException {
    System.out.println(APISearcher.findExerciseSearchMatches("press ").toString());
  }

  @Test
  void doubleSpaceExerciseSearch() throws UnirestException {
    System.out.println(APISearcher.findExerciseSearchMatches(" press ").toString());
  }

  @Test
  void incorrectExerciseSearch() throws UnirestException {
    System.out.println(APISearcher.findExerciseSearchMatches("1").toString());
  }

}
