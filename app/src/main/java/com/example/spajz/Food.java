/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

/**
 * Food class, food item for JSON file
 */
public class Food {
    /**
     * Attributes of the class
     */
    private String name;
    private int count;

    /**
     * Easy constructor for the Food
     * @param name String name of the food
     * @param count int count of the food
     */
    public Food(String name, int count) {
        this.name = name;
        this.count = count;
    }

    /**
     * Count getter
     * @return int
     */
    public int getCount() {
        return count;
    }

    /**
     * Count setter
     * @param count int count which will be set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Name getter
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name String name which will be set
     */
    public void setName(String name) {
        this.name = name;
    }
}
