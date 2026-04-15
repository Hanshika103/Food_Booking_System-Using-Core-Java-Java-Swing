package model; // Package name (Model layer - holds data classes)

// This class represents a food item in menu
public class MenuItem {

    // ==============================
    // FIELDS (DATA MEMBERS)
    // ==============================

    private int itemId; // Unique ID of menu item (from database)
    private String itemName; // Name of food item
    private String category; // Category (veg, non-veg, drinks, etc.)
    private double price; // Price of item
    private String availabilityStatus; // Available / Not Available

    // ==============================
    // CONSTRUCTOR 1 (FULL DATA)
    // ==============================
    public MenuItem(int itemId, String itemName, String category, double price, String availabilityStatus) {

        this.itemId = itemId; // assign item ID
        this.itemName = itemName; // assign name
        this.category = category; // assign category
        this.price = price; // assign price
        this.availabilityStatus = availabilityStatus; // assign status
    }

    // ==============================
    // CONSTRUCTOR 2 (WITHOUT ID)
    // Used when inserting new item into DB
    // ==============================
    public MenuItem(String itemName, String category, double price, String availabilityStatus) {

        this.itemName = itemName; // name
        this.category = category; // category
        this.price = price; // price
        this.availabilityStatus = availabilityStatus; // status
    }

    // ==============================
    // GETTERS (READ DATA)
    // ==============================

    public int getItemId() {
        return itemId; // return item ID
    }

    public String getItemName() {
        return itemName; // return name
    }

    public String getCategory() {
        return category; // return category
    }

    public double getPrice() {
        return price; // return price
    }

    public String getAvailabilityStatus() {
        return availabilityStatus; // return availability status
    }

    // ==============================
    // EXTRA FIELD (BOOLEAN TYPE)
    // ==============================

    private boolean available; // true/false availability

    // Constructor using boolean availability
    public MenuItem(int itemId, String itemName, String category, double price, boolean available) {

        this.itemId = itemId; // assign ID
        this.itemName = itemName; // assign name
        this.category = category; // assign category
        this.price = price; // assign price
        this.available = available; // assign boolean availability
    }

    // Getter for boolean availability
    public boolean isAvailable() {
        return available; // return true or false
    }
}
