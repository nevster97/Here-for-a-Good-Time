package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.ItemComparator;

import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    Location location;
    int totalItems;
    double totalValue;
    List<Item> inventory;

    public Inventory(Location location) {
        this(location, 0, 0.0, new ArrayList());
    }

    public Inventory(Location location, int totalItems, double totalValue, ArrayList<Item> inventory) {
        this.location = location;
        this.totalItems = totalItems;
        this.totalValue = totalValue;
        this.inventory = inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
        totalValue += item.getValue();
        totalItems++;
        // LocationFacade.getInstance().refresh();
        LocationFacade.getInstance().update(location);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
        totalValue -= item.getValue();
        totalItems--;
        // LocationFacade.getInstance().refresh();
    }

    public List<Item> getInventory() {
        return inventory;
    }
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public int getTotalItems() {
        return totalItems;
    }
    
    public double getTotalValue() {
        return totalValue;
    }

//    public void sortByName() {
//        _inventory = _inventory.sort(ItemComparator)
//    }
}
