package com.example.spajz;

public class Food {
    private String name;
    private int count;

    public Food(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}