package healthhub.Views;

import Client.ClientView.ClientMainViewController;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import staff.InstructorViews.InstructorMainViewController;
import staff.OwnerViews.OwnerMainViewController;

import javax.swing.JOptionPane;
import java.io.IOException;

public class View {

    /**
     * Function switches to the gui window
     *
     * @param fxmlFileName: the .fxml file wanting to switch to
     * @param event: the ActionEvent that occured
     * @throws IOException: For the FXMLLoader .load() function
     */
    public static void goToView(String fxmlFileName, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(View.class.getResource(fxmlFileName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    public static void goToView(String fxmlFileName, ActionEvent event, Parent root) throws IOException {
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    public static void goToViewWithUniqueID(String fxmlFileName, ActionEvent event, int UniqueIDCode, String userType) throws EmptyQueryException, IOException {
        try {
            // client
            FXMLLoader loader = new FXMLLoader(View.class.getResource(fxmlFileName));
            Parent root = loader.load();

            if (userType.equals("Owner")) {
                // Gets main view controller and passes client to it
                OwnerMainViewController viewController = loader.getController();
                //            viewController.setupScene(UniqueIDCode);

            } else if (userType.equals("Instructor")) {
                // Gets main view controller and passes client to it
                InstructorMainViewController viewController = loader.getController();
                //            viewController.setupScene(UniqueIDCode);
            } else if (userType.equals("Manager")) {
                // Gets main view controller and passes client to it
                InstructorMainViewController viewController = loader.getController();
                //            viewController.setupScene(UniqueIDCode);
            }
            //assume its a client if others aren't met (least security measures required)
            else {
                // Gets main view controller and passes client to it
                ClientMainViewController viewController = loader.getController();
                viewController.setupScene(UniqueIDCode);
            }

            Scene viewScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: IOException From view.java");
            View.goToView("LoginView.fxml", event);

        } catch (EmptyQueryException e) {
            JOptionPane.showMessageDialog(null, "Error: EmptyQueryExeption From view.java");
            View.goToView("LoginView.fxml", event);
        }
    }
}
