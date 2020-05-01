package com.example.malopus;

import java.util.Comparator;

public class CustomComparator implements Comparator<Item> {// may be it would be Model
    @Override
    public int compare(Item obj1, Item obj2) {
        return obj1.getDate().compareTo(obj2.getDate());// compare two objects
    }

}
