package com.example.kory.donationtracker.Models.LocationClasses;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    private ArrayList<String> invList;
    Location typeOne;
    Location typeTwo;
    Location typeThree;

    @Before
    public void setup() {
        ArrayList<String> inventoryList = new ArrayList<>();
        inventoryList.add("cup");
        inventoryList.add("shoe");
        inventoryList.add("stove");

        invList = inventoryList;

        typeOne = new Location("New Location", "101", "111", "420 High Road",
                "Drop Off", "478-550-0168", "google.com");
        typeTwo = new Location("New Location", "101", "111", "420 High Road",
                "Store", "478-550-0168", "google.com");
        typeThree = new Location("New Location", "101", "111", "420 High Road",
                "Warehouse", "478-550-0168", "google.com");
    }

    @Test
    public void testConstructorTypeOne() {
        assertEquals("New Location", typeOne.getName());
        assertEquals("101", typeOne.getLat());
        assertEquals("111", typeOne.getLon());
        assertEquals("420 High Road", typeOne.getAddress());
        assertEquals("Drop Off", typeOne.getType().getStringType());
        assertEquals("478-550-0168", typeOne.getPhone());
        assertEquals("google.com", typeOne.getWebsite());
    }

    @Test
    public void testConstructorTypeTwo() {
        assertEquals("New Location", typeTwo.getName());
        assertEquals("101", typeTwo.getLat());
        assertEquals("111", typeTwo.getLon());
        assertEquals("420 High Road", typeTwo.getAddress());
        assertEquals("Store", typeTwo.getType().getStringType());
        assertEquals("478-550-0168", typeTwo.getPhone());
        assertEquals("google.com", typeTwo.getWebsite());
    }

    @Test
    public void testConstructorTypeThree() {
        assertEquals("New Location", typeThree.getName());
        assertEquals("101", typeThree.getLat());
        assertEquals("111", typeThree.getLon());
        assertEquals("420 High Road", typeThree.getAddress());
        assertEquals("Warehouse", typeThree.getType().getStringType());
        assertEquals("478-550-0168", typeThree.getPhone());
        assertEquals("google.com", typeThree.getWebsite());
    }
}