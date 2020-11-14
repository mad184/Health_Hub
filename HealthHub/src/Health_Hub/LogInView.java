package Health_Hub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogInView extends Application {

  public static void main(String[] args){
      launch(args);
  }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login View");
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }
}