package com.example.lkesovellus;

/**
 * Luokka Drug joka mallintaa lisättävien lääkkeiden ominaisuuksia
 * Sisältää metodit joiden avulla voidaan kutsua luokan muuttujia
 * @author Emil Suuronen
 */

public class Drug {

    private String name;    //Merkkijono joka toimii lääkkeen nimenä
    private Double price;   //Double arvo joka toimii lääkkeen hintana
    private int amount;     //Integer joka kertoo lääkkeiden määrän

    /**
     * Luokan Drug konstruktori jonka avulla Drug tyyppisiä olioita luodaan
     * @param name Määritettää Drug-olion nimen, oltava merkkijono
     * @param price Määrittää Drug-olion hinnan, oltava positiivinen
     * @param amount Määrittää Drug-olion määrän, oltava positiivinen
     */
    public Drug (String name, Double price, int amount){
        this.name = name;           // this-sana viittaa koodia suorittavaan olioon
        this.price = price;
        this.amount = amount;
    }
    /**
     * Metodi jonka avulla voidaan hakea olion nimi
     * @return palauttaa kyseisen koodia suorittavan olion nimen
     */
    public String getDrugName() {
        return this.name;
    }
    /**
     * Metodi jonka avulla voidaan hakea olion hinta
     * @return palauttaa kyseisen koodia suorittavan olion hinnan
     */
    public double getDrugPrice() {
        return this.price;
    }
    /**
     * Metodi jonka avulla voidaan hakea olion määrä
     * @return palauttaa kyseisen koodia suorittavan olion määrän
     */
    public int getDrugAmount() {
        return this.amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodia kutsumalla palautetaan Drug-olion nimi
     * Ylikirjoittaa yliluokan Object.class toString() metodin, jotta kutsuttaessa palautetaan nimi
     * @return Palauttaa koodia käyttävän olion nimen
     */
    @Override
    public String toString(){
        return this.name;
    }

}
