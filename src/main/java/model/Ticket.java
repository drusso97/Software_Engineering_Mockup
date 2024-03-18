package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Class defines product objects.
 * */
public class Ticket {

    private int ticketID;
    private int customerID;
    private LocalDate creationDate;
    private String status;
    private LocalDate deadline;
    private String description;
    private String actionTaken;

    public Ticket(int ticketID, int customerID, LocalDate creationDate, String status, LocalDate deadline, String description, String actionTaken) {
        this.ticketID = ticketID;
        this.customerID = customerID;
        this.creationDate = creationDate;
        this.status = status;
        this.deadline = deadline;
        this.description = description;
        this.actionTaken = actionTaken;
    }

    /**
     * @return the product's id
     */
    public int getTicketID() {
        return ticketID;
    }

    /**
     * @param ticketID the product's id
     */
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }
}
