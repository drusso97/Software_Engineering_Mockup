package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * This class stores parts and products in observable lists and defines the functions to manage them.
 * */
public class Inventory {
    private static ObservableList<Customer> allParts = FXCollections.observableArrayList();
    private static ObservableList<Ticket> allProducts = FXCollections.observableArrayList();

    /**
     * Adds parts to the allParts observable list.
     * @param newPart New part object
     * */
    public static void addPart(Customer newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds prducts to the allProducts observable list.
     * @param newProduct New part object
     * */
    public static void addProduct(Ticket newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Lookup parts by their ID.
     * @param partId The part's id
     * @return Returns whatever parts are found and null if none are found
     * */
    public static Customer lookupPart(int partId) {

        for(Customer part : Inventory.getAllParts())
            while(part.getId() == partId) {
                return part;
            }
        Alert noPartFound = new Alert(Alert.AlertType.ERROR);
        noPartFound.setTitle("Error");
        noPartFound.setHeaderText("No part found");
        noPartFound.show();
        return null;
    }

    /**
     * Lookup products by their ID.
     * @param productId The product's id
     * @return Returns whatever parts are found and null if none are found
     * */
    public static Ticket lookupProduct(int productId) {

        for(Ticket product : Inventory.getAllProducts())
            while(product.getId() == productId) {
                return product;
            }
        Alert noProductFound = new Alert(Alert.AlertType.ERROR);
        noProductFound.setTitle("Error");
        noProductFound.setHeaderText("No product found");
        noProductFound.show();
        return null;
    }

    /**
     * Lookup parts by their name.
     * @param partName The search string
     * @return Returns whatever parts are found
     * */
    public static ObservableList<Customer> lookupPart(String partName) {

        ObservableList<Customer> PartName = FXCollections.observableArrayList();

        for(Customer part : allParts)
            if(part.getName().contains(partName)) {
                PartName.add(part);
            }
        return PartName;
    }

    /**
     * Lookup products by their name.
     * @param productName The search string
     * @return Returns whatever products are found
     * */
    public static ObservableList<Ticket> lookupProduct(String productName) {

        ObservableList<Ticket> ProductName = FXCollections.observableArrayList();

        for(Ticket product : allProducts)
            if(product.getName().contains(productName))
                ProductName.add(product);

        return ProductName;

    }

    /**
     * Applies changes to parts.
     * @param index The part's index location in the observable list
     * @param selectedPart The new part object
     * */
    public static void updatePart(int index, Customer selectedPart) {

        allParts.set(index, selectedPart);

    }

    /**
     * Applies changes to products.
     * @param index The product's index location in the observable list
     * @param newProduct The new product object
     * */
    public static void updateProduct(int index, Ticket newProduct) {

        allProducts.set(index, newProduct);

    }

    /**
     * Deletes a part from the observable list.
     * @param selectedPart The part to be deleted
     * */
    public boolean deletePart(Customer selectedPart) {

        if(allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Deletes a product from the observable list.
     * @param selectedProduct The product to be deleted
     * */
    public static boolean deleteProduct(Ticket selectedProduct) {

        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all parts in the observable list.
     * @return allParts observable list
     * */
    public static ObservableList<Customer> getAllParts() {
        return allParts;
    }

    /**
     * Returns all products in the observable list.
     * @return allProducts observable list
     * */
    public static ObservableList<Ticket> getAllProducts() {
        return allProducts;
    }
}
