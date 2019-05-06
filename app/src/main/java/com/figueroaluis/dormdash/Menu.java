package com.figueroaluis.dormdash;

import java.util.ArrayList;

public class Menu {
    private final String MARKETPLACE = "MARKETPLACE";
    private final String GREENBEAN = "GREENBEAN";
    private final String COFFEECART = "COFEECART";
    private final String COOLER = "COOLER";
    private ArrayList<MenuItem> allMenuItems = new ArrayList<>();
    private ArrayList<MenuItem> mpMenuList;
    private ArrayList<MenuItem> gbMenuList;
    private ArrayList<MenuItem> ccMenuList;
    private ArrayList<MenuItem> cooMenuList;

    public Menu(ArrayList<MenuItem> menuItemList, String menuPlace) {
        if(menuPlace.equals(MARKETPLACE)){
            this.mpMenuList=menuItemList;
        }else if(menuPlace.equals(GREENBEAN)){
            this.gbMenuList=menuItemList;
        }else if(menuPlace.equals(COFFEECART)){
            this.ccMenuList=menuItemList;
        }else if(menuPlace.equals(COOLER)){
            this.cooMenuList=menuItemList;
        }
        this.allMenuItems.addAll(menuItemList);
    }

    public ArrayList<MenuItem> getAllMenuItems() {
        return allMenuItems;
    }

    public void setAllMenuItems(ArrayList<MenuItem> allMenuItems) {
        this.allMenuItems = allMenuItems;
    }

    public ArrayList<MenuItem> getMpMenuList() {
        return mpMenuList;
    }

    public void setMpMenuList(ArrayList<MenuItem> mpMenuList) {
        this.mpMenuList = mpMenuList;
    }

    public ArrayList<MenuItem> getGbMenuList() {
        return gbMenuList;
    }

    public void setGbMenuList(ArrayList<MenuItem> gbMenuList) {
        this.gbMenuList = gbMenuList;
    }

    public ArrayList<MenuItem> getCcMenuList() {
        return ccMenuList;
    }

    public void setCcMenuList(ArrayList<MenuItem> ccMenuList) {
        this.ccMenuList = ccMenuList;
    }

    public ArrayList<MenuItem> getCooMenuList() {
        return cooMenuList;
    }

    public void setCooMenuList(ArrayList<MenuItem> cooMenuList) {
        this.cooMenuList = cooMenuList;
    }
}
