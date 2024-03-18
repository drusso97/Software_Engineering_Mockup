package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Database;
import model.Customer;
import model.Ticket;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;


public class CreateTicketController implements Initializable {

    public TextField idTF;
    public DatePicker creationDatePicker;
    public ComboBox ticketStatusCB;
    public DatePicker deadlineDP;
    public ComboBox custTF;
    public TextField descTF;
    public TextField actionTakenTF;
    Stage stage;
    Parent scene;


    /** Returns to program's main menu.
     * @param event Cancel button is clicked
     * */
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Saves changes to the product and returns to main menu.
     * @param actionEvent Save button is clicked
     * */
    public void onSave(ActionEvent actionEvent) throws IOException {

        try
        {
            int ticketID = Database.getAllTickets().size() + 1; // id starts at one and increments by one when a new product is added

            // This for loop ensures that ids are never repeated by checking if the id is always in the list
            for (Ticket ticket : Database.getAllTickets()) {
                if (ticket.getTicketID() == ticketID)
                    ticketID++;
            }

            LocalDate creationDate = LocalDate.now();
            int customerID = (int) custTF.getValue();
            String status = (String) ticketStatusCB.getValue();
            LocalDate deadline = deadlineDP.getValue();
            String description = descTF.getText();
            String actionTaken = actionTakenTF.getText();

            Ticket newTicket = new Ticket(ticketID, customerID, creationDate, status, deadline, description, actionTaken);

            Database.createTicket(newTicket);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incorrect Type");
            alert.setContentText("One or more fields has an incorrect value type or is blank!");
            alert.showAndWait();
        }
    }

    /** Initialize and add data to tables.
     * @param url URL
     * @param resourceBundle ResourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
