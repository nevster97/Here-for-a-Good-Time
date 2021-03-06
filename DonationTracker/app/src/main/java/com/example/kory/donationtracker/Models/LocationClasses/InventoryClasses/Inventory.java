package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

import com.example.kory.donationtracker.Models.LocationClasses.Location;

import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Inventory class
 */
public class Inventory {
    private final Location location;
    private int totalItems;
    private double totalValue;
    private final List<Item> inventory;

    /**
     * constructs an inventory with no inventory
     * @param location the owner of the inventory
     */
    public Inventory(Location location) {
        this(location, new ArrayList<Item>());
    }

    /**
     * constructs an inventory with an inventory
     * @param location the owner of the inventory
     * @param inventory the inventory
     */
    private Inventory(Location location, List<Item> inventory) {
        this.location = location;
        this.totalItems = 0;
        this.totalValue = 0.0;
        this.inventory = inventory;
    }

    /**
     * adds an item to an inventory
     * @param item the item to add
     */
    public void addItem(Item item) {
        inventory.add(item);
        totalValue += item.getValue();
        totalItems++;
        LocationFacade locF = LocationFacade.getInstance();
        locF.update();
        // LocationFacade.getInstance().update(location);

    }

    /**
     * removes an item from an inventory
     * @param item the item to remove
     */
    public void removeItem(Item item) {
        inventory.remove(item);
        totalValue -= item.getValue();
        totalItems--;
    }

    /**
     * gets the inventory
     * @return the inventory
     */
    public List<Item> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

//    /**
//     * sets the inventory
//     * @param inventory the new inventory
//     */
//    public void setInventory(List<Item> inventory) {
//        this.inventory = inventory;
//    }

    /**
     * gets the total number of items
     * @return the total number of items
     */
    public int getTotalItems() {
        return totalItems;
    }

// --Commented out by Inspection START (11/12/18, 2:27 PM):
//    /**
//     * gets the total value of an inventory
//     * @return the total value of items
//     */
//    public double getTotalValue() {
//        return totalValue;
//    }
// --Commented out by Inspection STOP (11/12/18, 2:27 PM)
}
