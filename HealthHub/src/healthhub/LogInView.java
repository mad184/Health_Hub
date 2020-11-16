package healthhub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogInView extends Application {

  public static void main(String[] args){
      launch(args);
  }

    /**
     * This function starts up the logInView when the main method is called, opens the initial login view
     *
     * @param stage: fixed
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Welcome to Health Hub");
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }
}