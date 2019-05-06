package com.figueroaluis.dormdash;

public class MenuItem {

    String foodName;
    String foodType;

    public MenuItem(String foodName, String foodType) {
        this.foodName = foodName;
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
