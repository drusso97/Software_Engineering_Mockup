package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * This class stores parts and products in observable lists and defines the functions to manage them.
 * */
public class Database {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Ticket> allTickets = FXCollections.observableArrayList();

    /**
     * Adds parts to the allParts observable list.
     * @param newCustomer New part object
     * */
    public static void createCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

    /**
     * Adds tickets to the allProducts observable list.
     * @param createTicket New part object
     * */
    public static void createTicket(Ticket createTicket) {
        allTickets.add(createTicket);
    }

    /**
     * Lookup parts by their ID.
     * @param customerID The part's id
     * @return Returns whatever parts are found and null if none are found
     * */
    public static Customer searchCustomer(int customerID) {

        for(Customer customer : Database.getAllCustomers())
            while(customer.getId() == customerID) {
                return customer;
            }
        Alert customerNotFound = new Alert(Alert.AlertType.ERROR);
        customerNotFound.setTitle("Error");
        customerNotFound.setHeaderText("No customer found");
        customerNotFound.show();
        return null;
    }

    /**
     * Lookup products by their ID.
     * @param ticketID The product's id
     * @return Returns whatever parts are found and null if none are found
     * */
    public static Ticket searchTicket(int ticketID) {

        for(Ticket ticket : Database.getAllTickets())
            while(ticket.getTicketID() == ticketID) {
                return ticket;
            }
        Alert ticketNotFound = new Alert(Alert.AlertType.ERROR);
        ticketNotFound.setTitle("Error");
        ticketNotFound.setHeaderText("No ticket found");
        ticketNotFound.show();
        return null;
    }

    /**
     * Lookup parts by their name.
     * @param customerName The search string
     * @return Returns whatever parts are found
     * */
    public static ObservableList<Customer> searchCustomer(String customerName) {

        ObservableList<Customer> CustomerName = FXCollections.observableArrayList();

        for(Customer customer : allCustomers)
            if(customer.getName().contains(customerName)) {
                CustomerName.add(customer);
            }
        return CustomerName;
    }

    /**
     * Applies changes to products.
     * @param index The product's index location in the observable list
     * @param newTicket The new product object
     * */
    public static void updateTicket(int index, Ticket newTicket) {

        allTickets.set(index, newTicket);

    }

    /**
     * Returns all parts in the observable list.
     * @return allParts observable list
     * */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    /**
     * Returns all products in the observable list.
     * @return allProducts observable list
     * */
    public static ObservableList<Ticket> getAllTickets() {
        return allTickets;
    }
}
