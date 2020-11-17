package healthhub;

import com.jcraft.jsch.IO;
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

public class OrganizationSignUpController {

    @FXML
    private TextField organizationName, ownerName, BirthDate, Email, UserName, Password;


    public HealthHubController healthHubController = new HealthHubController(null);



    @FXML
    public void goBackButtonPushed(ActionEvent event) throws IOException{
        View.goToView("SignUpOptionsPageView.fxml",event);
    }

    /**
     * When the Create Organization button is pressed,
     *  - if the organization has already been created display a message and go back to the LoginView.fxml
     *  - otherwise gather/check all the information from the textbox input, create the organization and go to the
     *    owners view
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onCreateOrganizationButtonPushed(ActionEvent event) throws IOException {
        if (HealthHubAccessSingleton.isOrganizationCreated()) {
            JOptionPane.showMessageDialog(null, "Organization has already been created");
            View.goToView("LoginView.fxml", event);
        }
        else{
            // check that our inputs were properly entered
            String organizationName = this.organizationName.getText();
            String ownerName = this.ownerName.getText();
            String birthDate = this.BirthDate.getText();
            String email = this.Email.getText();
            String userName = this.UserName.getText();
            String passWord = this.Password.getText();

            //regex looks for any number of white space
            if(!(ownerName.length() > 0) || ownerName.matches("^ *$")){
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
             - figure out addManager:
               I shouldn't make a call to the staff package but there is not other way to add a manager to the
               database right now
             */
//              Owner owner = new Owner();
//            healthHubController.createOrganization(organizationName, owner);
//            healthHubController.addManager();


            /*
            ToDO:
             - go to The Owners View, not sure of the fileName yet
             */
            View.goToView("OwnerView.fxml", event);
        }
    }
}
