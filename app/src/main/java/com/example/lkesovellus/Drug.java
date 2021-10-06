package com.example.lkesovellus;

import android.util.Log;

import java.util.ArrayList;

public class Drug {

    private String name;
    private int price;
    private int amount;

    public Drug (String name, int price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getDrugName() {
        return this.name;
    }

    public int getDrugPrice() {
        return this.price;
    }

    public int getDrugAmount() {
        return this.amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
