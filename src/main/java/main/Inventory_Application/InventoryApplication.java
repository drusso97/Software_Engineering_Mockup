package main.Inventory_Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;
import model.Database;
import model.Ticket;

import java.io.IOException;

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

        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 995, 360);
        stage.setTitle("ClientFlow v1.0");
        stage.setScene(scene);
        stage.show();
    }

    /** This method adds test data. */
    private static void addTestData(){

        Ticket cruze = new Ticket(1, "Cruze", 24000, 12, 10, 20);
        Database.createTicket(cruze);

        Ticket civic = new Ticket(2, "Civic", 34000, 15, 10, 20);
        Database.createTicket(civic);

        Ticket jeep = new Ticket(3, "Jeep", 41000, 7, 3, 20);
        Database.createTicket(jeep);

        Ticket tesla = new Ticket(4, "Tesla", 75000, 5, 2, 17);
        Database.createTicket(tesla);

        Ticket f150 = new Ticket(5, "F150", 40000, 20, 10, 50);
        Database.createTicket(f150);

        Ticket trailblazer = new Ticket(6, "Trail Blazer", 40000, 7, 5, 20);
        Database.createTicket(trailblazer);

        Customer customer1 = new Customer(1, "John Doe", "123-456-7891", "jdoe@gmail.com");
        Database.createCustomer(customer1);
    }

    /** This is the main method. This is the first method that gets called when you run your java program. */
    public static void main(String[] args) {

        addTestData();

        launch();
    }
}