package com.figueroaluis.dormdash;

import org.json.JSONObject;

public class Waiter extends Thread {
    JSONObject result;
    Waiter(JSONObject result){
        this.result = result;
    }
    public void run(){
        System.out.println("INNER INTERFACE" + result);
    }
}
