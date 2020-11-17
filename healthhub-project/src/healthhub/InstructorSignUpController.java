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

public class InstructorSignUpController {
    @FXML
    private TextField Name, BirthDate, Email, UserName, Password;

    /**
     * Goes to the back to the previous defined page SignUpOptionsPageView.fxml
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: for View.gotoView()
     */
    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException{
        View.goToView("LoginView.fxml",event);
    }


    /**
     * Gathers the information entered into the textboxes and
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: For the FXMLLoader .load() function
     */
    @FXML
    public void onSignUpButtonPushed(ActionEvent event) throws IOException {
        System.out.println("signUpButton Pushed");

        // check that our inputs were properly entered
        String name = this.Name.getText();
        String birthDate = this.BirthDate.getText();
        String email = this.Email.getText();
        String userName = this.UserName.getText();
        String passWord = this.Password.getText();

        //regex looks for any number of white space
        if(!(name.length() > 0) || name.matches("^ *$")){
            JOptionPane.showMessageDialog(null, "A Name is required");
        }

        //regex will reject anything that doesn't match dd-mm-yyyy format
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
            - figure out addInstructor:
               I shouldn't make a call to the staff package but there is not other way to add a instructor to the
               database right now
         */
        //add instructor()
//        HealthHubController.addInstructor();

        /*
        ToDo:
             change name for loader to the Instructors view
         */
        //go to instructor view
        View.goToView("InstructorView.fxml", event);
    }
}
