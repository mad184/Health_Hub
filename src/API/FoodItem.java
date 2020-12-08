package API;

public class FoodItem {
    private String foodName;
    private double servingAmount;
    private int calories;

  public FoodItem(String foodName, double servingAmount, int calories) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingAmount = servingAmount;
    }


    //Getters
    public String getFoodName() {
        return foodName;
    }

    public double getServingAmount() {
        return servingAmount;
    }

    public int getCalories() {
        return calories;
    }

    //Setters
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setServingAmount(double servingAmount) {
        this.servingAmount = servingAmount;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return foodName + ";" + calories + ";" + servingAmount + "/";
    }
}
