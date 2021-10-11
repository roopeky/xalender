package com.example.lkesovellus;

public class Drug {

    private String name;
    private Double price;
    private int amount;

    public Drug (String name, Double price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getDrugName() {
        return this.name;
    }

    public double getDrugPrice() {
        return this.price;
    }

    public int getDrugAmount() {
        return this.amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void takeDrug() {
        this.amount--;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
