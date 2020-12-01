package API;

public class ExerciseItem {
    private String exerciseName;
    private int reps;
    private int sets;
    //private int caloriesBurned;

    public ExerciseItem(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public ExerciseItem(String exerciseName, int reps, int sets) {
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    //public int getCaloriesBurned() {
    //    return caloriesBurned;
    //}
    //public void setCaloriesBurned(int caloriesBurned) {
    //    this.caloriesBurned = caloriesBurned;
    //}

    @Override
    public String toString() {
        return exerciseName + ";" + sets + ";" + reps + "/";
    }
}
