package healthhub.ViewControllers;

import healthhub.HealthHubController;
import healthhub.Views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import staff.InstructorModel;


import javax.swing.JOptionPane;
import java.io.IOException;

public class InstructorSignUpController {
    @FXML
    private TextField Name, age, Email, Password, organziation, phoneNumber;


    /**
     * Goes to the back to the previous defined page SignUpOptionsPageView.fxml
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: for View.gotoView()
     */
    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException {
        View.goToView("LoginView.fxml", event);
    }


    /**
     * Gathers the information entered into the textboxes and
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: For the FXMLLoader .load() function
     */
    @FXML
    public void onSignUpButtonPushed(ActionEvent event) throws IOException {
        // check that our inputs were properly entered
        String name = this.Name.getText();
        String ageString = this.age.getText();
        String email = this.Email.getText();
        String organization = this.organziation.getText();
        String passWord = this.Password.getText();
        String phoneNumber = this.phoneNumber.getText();
        int age = 0;

        try {
            age = Integer.parseInt(ageString);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "age could not be read");
        }
        //regex looks for any number of white space
        if (!(name.length() > 0) || name.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A Name is required");
        }

        else if (!(age > 0) || !(age < 150)) {
            JOptionPane.showMessageDialog(null, "Right now only ages 1 - 149 are accepted");
        }

        //regex looks for empty spaces entered
        else if (!(email.length() > 0) || email.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A Email is required");
        }

        //regex looks for empty spaces entered
        else if (!(organization.length() > 0) || organization.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A userName is required");
        }

        //regex looks for empty spaces entered
        else if (!(passWord.length() > 0) || passWord.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A Password is required");
        }
        //regex looks for empty spaces entered
        else if (!(phoneNumber.length() > 0) || phoneNumber.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A phone number is required");
        }
        else if((!HealthHubController.organizationExists())){
            JOptionPane.showMessageDialog(null, "A valid organization must be given");
        }


////        //For testing the outputs manually
//        System.out.println("Start Manual output Test for Instructor Sign Up input");
//        System.out.println("name: " + name);
//        System.out.println("age: " + age);
//        System.out.println("phoneNumber: " + phoneNumber);
//        System.out.println("organziation: " + organization);
//        System.out.println("email: " + email);
//        System.out.println("passWord: " + passWord);
//        System.out.println("finsished manual input testing");


        int instructorId = HealthHubController.getUniqueID();

        InstructorModel instructor = new InstructorModel(name,age,email,phoneNumber,0,0,organization,
                instructorId,null,null,passWord,null,null);

        int databaseAdditionSuccessCode = HealthHubController.addInstructor(instructor);

        switch(databaseAdditionSuccessCode){
            case 403:
                JOptionPane.showMessageDialog(null, "ERROR: Email " + email + " has already been used");
                break;
            case 500:
                JOptionPane.showMessageDialog(null, "ERROR: Server Error");
                break;
            case 200:
                //TODO: Uncomment after merge, ensure file names/controller names are correct
//                InstructorMainViewController viewController = loader.getController();
//                viewController.setupScene(instructorController.instructorID);
//                View.goToView("InstructorView.fxml", event);
        }

    }
}
