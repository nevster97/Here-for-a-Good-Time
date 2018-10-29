package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.ItemComparator;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    Location _location;
    int _totalItems;
    double _totalValue;
    List<Item> _inventory;

    public Inventory(Location location) {
        this(location, 0, 0.0, new ArrayList());
    }

    public Inventory(Location location, int totalItems, double totalValue, ArrayList<Item> inventory) {
        _location = location;
        _totalItems = totalItems;
        _totalValue = totalValue;
        _inventory = inventory;
    }

    public void addItem(Item item) {
        _inventory.add(item);
        _totalValue += item.getValue();
        _totalItems++;
    }

    public void removeItem(Item item) {
        _inventory.remove(item);
        _totalValue -= item.getValue();
        _totalItems--;
    }

    public List<Item> getInventory() {
        return _inventory;
    }

    public int getTotalItems() { return _totalItems; }
    public double getTotalValue() { return _totalValue; }

//    public void sortByName() {
//        _inventory = _inventory.sort(ItemComparator)
//    }
}
