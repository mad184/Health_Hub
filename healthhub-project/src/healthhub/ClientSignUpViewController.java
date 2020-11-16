package healthhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.io.IOException;

public class ClientSignUpViewController {

    @FXML
    private TextField Name, BirthDate, Email, UserName, Password;

    public HealthHubController healthHubController = new HealthHubController(null);


    /**
     * This method take the text box entry from the ClientSignUpView, verifys it and then sends it to the healthHub
     * Controller to be added to the database, Then switches the view to the clinets view
     *
     * @param event: The ActionEvent associated with the button clicked action
     * @throws IOException: For the FXMLLoader .load() function
     */
    @FXML
    public void onSignUpButtonPushed(ActionEvent event) throws IOException {
        System.out.println("signUpButton Pushed");
        String name = this.Name.getText();
        String birthDate = this.BirthDate.getText();
        String email = this.Email.getText();
        String userName = this.UserName.getText();
        String passWord = this.Password.getText();

        //regex looks for any number of white space
        if(!(name.length() > 0) || name.matches("^ *$")){
            JOptionPane.showMessageDialog(null, "A Name is required");
        }

        //regex matches dd-mm-yyy format
        else if(!(birthDate.length() > 0) || !birthDate.matches("^([0]?[1-9]|[1|2][0-9]|[3][0|1])[-]([0]?[1-9]" +
                "|[1][0-2])[-]([0-9]{4}|[0-9]{2})$")){
            JOptionPane.showMessageDialog(null, "BirthDate is required to be an integer " +
                    "sequence in  dd-mm-yyyy format");
        }

        //regex looks for empty spaces entered
        else if(!(email.length() > 0) || email.matches("^ *$")){
            JOptionPane.showMessageDialog(null, "A Email is required");
        }

        //regex looks for empty spaces entered
        else if(!(userName.length() > 0) || userName.matches("^ *$")){
            JOptionPane.showMessageDialog(null, "A userName is required");
        }

        //regex looks for empty spaces entered
        else if(!(passWord.length() > 0) || passWord.matches("^ *$")){
            JOptionPane.showMessageDialog(null, "A Password is required");
        }

        /*
        ToDO:
            - figure out addClient:
               I shouldn't make a call to the staff package but there is not other way to add a manager to the
               database right now
         */
        //add the client
        healthHubController.addClient();

        //go to client view
        /*
        ToDO:
            change .fxml file to the proper name for the client view
         */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
}
