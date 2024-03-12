package controller;

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
import model.Inventory;
import model.Customer;
import model.Ticket;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partCostCol;
    public TableView productsTable;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productInvCol;
    public TableColumn productCostCol;
    public TextField productSearchField;
    public TextField partSearchField;
    Stage stage;
    Parent scene;

    /** This method closes the program. */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /** Loads the add part form.
     * @param event Add button clicked
     * */
    @FXML
    void onActionCreateCustomer(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CreateCustomerForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Loads the modify part form for the selected part.
     * @param event Modify button clicked
     * */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();

            ModifyCustomerController MPFController = loader.getController();
            MPFController.sendCustomer((Customer) partsTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: No part selected!");
            alert.setContentText("Please select a product to modify");
            alert.show();
        }
    }

    /** Deletes the selected part.
     * @param event Delete button clicked
     * */
    @FXML
    void onActionDeletePart(ActionEvent event) throws IOException {

        Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        deleteConfirmation.setTitle("Confirm Delete Part");
        deleteConfirmation.setHeaderText("Delete");
        deleteConfirmation.setContentText("Are you sure you want to delete this part?");

        Optional<ButtonType> result = deleteConfirmation.showAndWait();

        if (result.get() == ButtonType.OK)
        {
            try {
                Customer deletedPart = (Customer) partsTable.getSelectionModel().getSelectedItem();

                int id = deletedPart.getId();

                partsTable.getItems().removeAll(partsTable.getSelectionModel().getSelectedItem());

                for (Customer part : Inventory.getAllParts())
                    if (part.getId() > id)
                        part.setId(part.getId() - 1);
            }
            catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: No part selected!");
                alert.setContentText("Please select a part to delete");
                alert.show();
            }
        }
    }

    /** Loads the add product form.
     * @param event Add button clicked
     * */
    @FXML
    void onActionCreateTicket(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CreateTicketForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Loads the modify product form for the selected product.
     * @param event Modify button clicked
     * */
    @FXML
    void onActionModifyTicket(ActionEvent event) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();

            ModifyTicketController MPFController = loader.getController();
            MPFController.sendProduct((Ticket) productsTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: No product selected!");
            alert.setContentText("Please select a product to modify");
            alert.show();
        }

    }


    /** Deletes the selected product.
     * @param event Delete button clicked
     * */
    @FXML
    void onActionDeleteProduct(ActionEvent event) throws IOException {

        Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        deleteConfirmation.setTitle("Confirm Delete Product");
        deleteConfirmation.setHeaderText("Delete");
        deleteConfirmation.setContentText("Are you sure you want to delete this product?");

        Optional<ButtonType> result = deleteConfirmation.showAndWait();

        if (result.get() == ButtonType.OK)
        {
            try {
                Ticket selectedProduct = (Ticket) productsTable.getSelectionModel().getSelectedItem();

                int id = selectedProduct.getId();

                if (selectedProduct.getAllAssociatedParts().size() > 0) {
                    Alert deleteError = new Alert(Alert.AlertType.ERROR);
                    deleteError.setTitle("Cannot delete product!");
                    deleteError.setContentText("Product has associated parts and cannot be deleted until associated parts are removed!");
                    deleteError.showAndWait();
                    return;
                } else
                    productsTable.getItems().removeAll(productsTable.getSelectionModel().getSelectedItem());

                // makes sure that Ids are never duplicated by decrementing each higher product id by one when a part is deleted. This is necessary due to the way ids are generated
                for (Ticket product : Inventory.getAllProducts())
                    if (product.getId() > id)
                        product.setId(product.getId() - 1);
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: No product selected!");
                alert.setContentText("Please select a product to delete");
                alert.show();
            }
        }
    }

    /** Searches for products by a given integer or string.
     * @param event Text is entered in search bar
     * */
    public void onProductSearch(KeyEvent event) {

        String searchString = productSearchField.getText();
        ObservableList<Ticket> searchResults = Inventory.lookupProduct(searchString);

        try
        {
            while (searchResults.isEmpty()) {
                int searchId = Integer.parseInt(searchString);
                searchResults.add(Inventory.lookupProduct(searchId));
            }
            productsTable.setItems(searchResults);
        }
        catch (NumberFormatException exception) {
            Alert noProductsFound = new Alert(Alert.AlertType.ERROR);
            noProductsFound.setTitle("Error");
            noProductsFound.setContentText("No products found!");
            noProductsFound.showAndWait();
        }

    }

    /** Searches for parts by a given integer or string.
     * @param event Text is entered in search bar
     * */
    public void onPartSearch(KeyEvent event) {

        String searchString = partSearchField.getText();
        ObservableList<Customer> searchResults = Inventory.lookupPart(searchString);

        try
        {
            while (searchResults.isEmpty()) {
                int searchId = Integer.parseInt(searchString);
                searchResults.add(Inventory.lookupPart(searchId));
            }
            partsTable.setItems(searchResults);
        }
        catch (NumberFormatException exception) {
            Alert noPartsFound = new Alert(Alert.AlertType.ERROR);
            noPartsFound.setTitle("Error");
            noPartsFound.setContentText("No parts found!");
            noPartsFound.showAndWait();
        }

    }

    /** Initialize and add data to tables.
     * @param url URL
     * @param resourceBundle ResourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productsTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}