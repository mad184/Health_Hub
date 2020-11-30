package healthhub.ViewControllers;

import database.EmptyQueryException;
import healthhub.HealthHubController;
import healthhub.Views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import staff.Models.InstructorModel;
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
    public void onSignUpButtonPushed(ActionEvent event) throws IOException, EmptyQueryException {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "age could not be read");
            View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
        }
        //regex looks for a string space string, meaning users must enter a first and last name
        if (!(name.length() > 0) || !name.matches("^([a-z]|[A-Z])+\\s([a-z]|[A-Z])+$")) {
            JOptionPane.showMessageDialog(null, "A Name is required");
            View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
        } else if (!(age > 0) || !(age < 150)) {
            JOptionPane.showMessageDialog(null, "Right now only ages 1 - 149 are accepted");
            View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
        }

        //regex looks for a email in the format of anything@anything.ca or anything@anything.com
        else if (!(email.length() > 0) || !email.matches("^.*[@]{1}.*(.ca|.com)$")) {
            JOptionPane.showMessageDialog(null, "A Email is required");
            View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
        }

        //needs to not be empty
        else if (!(organization.length() > 0)) {
            JOptionPane.showMessageDialog(null, "A userName is required");
            View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
        }

        // min length of 6, Regex looks for any spaces in the password that is one string, no spaces w/ special characters,characters,numbers
        else if (!(passWord.length() > 5) || passWord.matches("^ *$") || !passWord.matches("^(\\w|\\D|\\d|\\W)*$")) {
            JOptionPane.showMessageDialog(null, "A Password is required");
            View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
        }
        //regex looks anyting of the format #-###-###-####
        else if (!(phoneNumber.length() > 0) || !phoneNumber.matches("^[0-9][-]{1}[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}$")) {
            JOptionPane.showMessageDialog(null, "A phone number is required");
            View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
        } else {
            ////        //For testing the outputs manually
            //        System.out.println("Start Manual output Test for Instructor Sign Up input");
            //        System.out.println("name: " + name);
            //        System.out.println("age: " + age);
            //        System.out.println("phoneNumber: " + phoneNumber);
            //        System.out.println("organziation: " + organization);
            //        System.out.println("email: " + email);
            //        System.out.println("passWord: " + passWord);
            //        System.out.println("finsished manual input testing");

            int uniqueId = HealthHubController.getUniqueID();
            InstructorModel newInstructor =
                    new InstructorModel(
                            name,
                            age,
                            email,
                            phoneNumber,
                            0,
                            0,
                            "none",
                            uniqueId,
                            null,
                            "test-user",
                            "healthhub1",
                            "test-user",
                            "Test-General-Database");

            int successCode = HealthHubController.addInstructor(uniqueId, newInstructor.toJson());

            if (successCode == 403) {
                JOptionPane.showMessageDialog(null, "ERROR: Email " + email + " has already been used");
                View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
            } else if (successCode == 500) {
                JOptionPane.showMessageDialog(null, "ERROR: Server Error");
                View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
            } else if (successCode == 200) {
                View.goToViewWithUniqueID(
                        "../../Staff/InstructorViews/instructorMainView.fxml", event, uniqueId, "Instructor");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Sorry a unknown error occured");
                View.goToView("../../Staff/InstructorViews/instructorMainView.fxml", event);
            }
        }
    }
}
