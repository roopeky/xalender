package com.example.lkesovellus;

import java.util.ArrayList;

public class Drug extends MainActivity{

    private String name;
    private String price;
    private int amount;

    public Drug (String name, String price, int amount){
        name = this.name;
        price = this.price;
        amount = this.amount;
    }

    public String getDrugName() {
        return this.name;
    }

    public String getDrugPrice() {
        return this.price;
    }

    public int getDrugAmount() {
        return this.amount;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
