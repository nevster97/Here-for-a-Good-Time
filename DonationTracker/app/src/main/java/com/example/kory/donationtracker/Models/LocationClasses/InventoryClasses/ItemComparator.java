// ignore this file for now

package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
    public int compareByName(String name1, String name2) {
        if (name1.equals(name2)) {
            return 0;
        }
        if (name1 == null && name2 == null) {
            return 0;
        }
        if (name1 == null) {
            return -1;
        } else if (name2 == null) {
            return 1;
        }
        return name1.compareToIgnoreCase(name2);
    }

    public int compareByPrice(double price1, double price2) {
        if (price1 < price2) { return -1; }
        else if (price1 == price2) { return 0; }
        else { return 1;}
    }

    @Override
    public int compare(Item o1, Item o2) {
        return 0;
    }
}
