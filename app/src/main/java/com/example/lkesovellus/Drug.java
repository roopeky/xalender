package com.example.lkesovellus;

import java.util.ArrayList;

public class Drug {

    private String name;
    private String price;
    private ArrayList<Drug> drugsListObject;

    public Drug (String name, String price){
        name = this.name;
        price = this.price;
    }

    public String getDrugName() {
        return this.name;
    }

    public String getDrugPrice() {
        return this.price;
    }

    public ArrayList<Drug> getDrugsList (){
        return drugsListObject;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
