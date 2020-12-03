package staff.InstructorViews;

import Client.Client;
import com.google.gson.Gson;
import database.EmptyQueryException;
import healthhub.Views.View;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import staff.Controllers.InstructorController;
import staff.StaffToDB;
import staff.UserID;

import java.io.IOException;
import java.util.*;

public class InstructorClientViewController {

    public InstructorController controller = new InstructorController(null);
    public StaffToDB db = null;
    @FXML ComboBox<String> clientSelectComboBox = new ComboBox<>();
    @FXML TextField inviteEmailField = new TextField();
    @FXML Label currentCalories = null;
    @FXML Label goalCalories = null;
    ObservableList<String> clientList = null;
    ArrayList<UserID> clients = null;
    JSONObject selectedClient = null;

    public void setupScene(InstructorController controller) {
        this.controller = controller;

        clients = (ArrayList<UserID>) controller.getClients();
        ArrayList<String> clientNames = new ArrayList<>();
        if (!(clients == null)) {
          for (UserID client : clients) {
            clientNames.add(client.getName());
          }
        }
        // Show the client names in the combo box
        clientList = FXCollections.observableList(clientNames);
        this.clientSelectComboBox.setValue("");
        this.clientSelectComboBox.setItems(clientList);
    }

    /**
     * Opens a new window that displays the relevant information about a client.
     *
     * @param event: Event which pressed the button.
     */
    public void onViewClientButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
        System.out.println("You have selected a Client");
        String clientName = clientSelectComboBox.getValue();
        if (!(clientName == null)) {
            for (UserID client : clients) {
                if (client.getName().equals(clientName)) {
                    selectedClient = controller.getClientInfo(client);
                    break;
                }
            }
            Gson gson = new Gson();
            String currentCal = gson.fromJson(String.valueOf(selectedClient.get("calories")), String.class);
            String goalCal = gson.fromJson(String.valueOf(selectedClient.get("goalCals")), String.class);
            currentCalories.setText(currentCal);
            goalCalories.setText(goalCal);
        }
    }

    /**
     * Sends an invite to the Client email in the textField box.
     *
     * @param event: Event which pressed the button.
     */
    public void onInviteButtonPressed(ActionEvent event) {
        System.out.println("You have invited a client");
    }

    /**
     * Sends the Instructor back to their home screen.
     *
     * @param event: Button press.
     * @throws IOException
     * @throws EmptyQueryException
     */
    public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorMainView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        InstructorMainViewController MainViewController = loader.getController();
        MainViewController.setupScene(controller.getId());

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}
