package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Database;
import model.Ticket;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifyTicketController implements Initializable {

    public TextField productIdLbl;
    public TextField nameTF;
    public TextField invTF;
    public TextField priceTF;
    public TextField maxTF;
    public TextField minTF;
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
     * @param actionEvent Cancel button is clicked
     * */
    @FXML
    void onActionDisplayMainMenu(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Saves the new product and returns to main menu.
     * @param actionEvent Save button is clicked
     * */
    @FXML
    void onSave(ActionEvent actionEvent) throws IOException {

        try
        {
            int ticketID = Integer.parseInt(idTF.getText());
            LocalDate creationDate = LocalDate.now();
            int customerID = (int) custTF.getValue();
            String status = (String) ticketStatusCB.getValue();
            LocalDate deadline = deadlineDP.getValue();
            String description = descTF.getText();
            String actionTaken = actionTakenTF.getText();

            Ticket newTicket = new Ticket(ticketID, customerID, creationDate, status, deadline, description, actionTaken);

            int index = ticketID - 1;

            Database.updateTicket(index, newTicket);

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

    /** Sends the selected ticket from the main manu to the modify ticket form.
     * @param ticket The selected ticket
     * */
    public void sendProduct(Ticket ticket) {

        productIdLbl.setText(String.valueOf(ticket.getTicketID()));
        custTF.setValue(String.valueOf(ticket.getCustomerID()));
        creationDatePicker.setValue(ticket.getCreationDate());
        ticketStatusCB.setValue(ticket.getStatus());
        deadlineDP.setValue(ticket.getDeadline());
        descTF.setText(ticket.getDescription());
        actionTakenTF.setText(ticket.getActionTaken());

    }

    /** Initialize and add data to tables.
     * @param url URL
     * @param resourceBundle ResourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
