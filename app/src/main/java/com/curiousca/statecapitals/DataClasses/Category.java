package com.curiousca.statecapitals.DataClasses;

public class Category {

    public static final int US = 1;
    public static final int AMERICAS = 2;
    public static final int AFRICA = 3;
    public static final int EUROPE = 4;
    public static final int ASIA_AUSTRALIA = 5;
    public static final int MIDDLE_EAST = 6;

    private int id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
