package main.Inventory_Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;
import model.Database;
import model.Ticket;

import java.io.IOException;
import java.time.LocalDate;

/*
    JavaDoc located at Car_Inventory_Application/JavaDoc
 */

/** This class creates an inventory management application.
 * FUTURE ENHANCEMENT: I could store all the used IDs in an Observable List so that IDs are not reused and other IDs do not need to be changed when a part/product is deleted.
 * This would probably speed up the program too if it had hundreds or thousands of objects to sort through.
 * */
public class InventoryApplication extends Application {

    /** This is the start method. */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 995, 360);
        stage.setTitle("ClientFlow v1.0");
        stage.setScene(scene);
        stage.show();
    }

    /** This method adds test data. */
    private static void addTestData(){

        Ticket ticket1 = new Ticket(1, 1 ,  LocalDate.now(), "Unresolved", LocalDate.of(2024, 3,25), "Can't access account", "Sent customer instructions to reset password");
        Database.createTicket(ticket1);

        Ticket ticket2 = new Ticket(2, 1 ,  LocalDate.now(), "Resolved", LocalDate.of(2024, 3,24), "Billing dispute", "Issued Refund");
        Database.createTicket(ticket2);

        Ticket ticket3 = new Ticket(3, 1 ,  LocalDate.now(), "Urgent", LocalDate.of(2024, 3,20), "Account Hacked", "Locked Account, pending review");
        Database.createTicket(ticket3);

        Customer customer1 = new Customer(1, "John Doe", "123-456-7891", "jdoe@gmail.com");
        Database.createCustomer(customer1);

        Customer customer2 = new Customer(2, "George Washington", "455-211-7896", "firstpres@gmail.com");
        Database.createCustomer(customer2);

        Customer customer3 = new Customer(3, "John Williams", "233-546-7891", "jwilliams@gmail.com");
        Database.createCustomer(customer3);
    }

    /** This is the main method. This is the first method that gets called when you run your java program. */
    public static void main(String[] args) {

        addTestData();

        launch();
    }
}