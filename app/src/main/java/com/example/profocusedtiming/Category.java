package com.example.profocusedtiming;

public class Category {

    public Category() {
    }

    public Category(String name, int goal, int numberOfItems) {
        Name = name;
        Goal = goal;
        NumberOfItems = numberOfItems;
    }
    public Category(String name, int minGoal,int maxGoal, int numberOfItems) {
        Name = name;
        MaxGoal = maxGoal;
        MinGoal = minGoal;
        NumberOfItems = numberOfItems;
    }

    private String Name;
    private int Goal, NumberOfItems;




    private int MaxGoal;
    private int MinGoal;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getGoal() {
        return Goal;
    }

    public void setGoal(int goal) {
        Goal = goal;
    }

    public int getNumberOfItems() {
        return NumberOfItems;
    }

    //new code
    public int getMaxGoal() {
        return MaxGoal;
    }

    public void setMaxGoal(int maxGoal) {
        MaxGoal = maxGoal;
    }

    public int getMinGoal() {
        return MinGoal;
    }

    public void setMinGoal(int minGoal) {
        MinGoal = minGoal;
    }
    //end of new code

    public void setNumberOfItems(int numberOfItems) {
        NumberOfItems = numberOfItems;
    }
}
