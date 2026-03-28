package model;

public class MenuItem {

    private int itemId;
    private String itemName;
    private String category;
    private double price;
    private String availabilityStatus;

    public MenuItem(int itemId, String itemName, String category, double price, String availabilityStatus) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
    }

    public MenuItem(String itemName, String category, double price, String availabilityStatus) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }
  private boolean available;

public MenuItem(int itemId, String itemName, String category, double price, boolean available) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.category = category;
    this.price = price;
    this.available = available;
}

public boolean isAvailable() {
    return available;
}
}
