package com.example.lkesovellus.classes;

import java.util.ArrayList;

/**
 * Luokka Global
 * Singleton käyttää Drug luokan olioita
 * @author Emil Suuronen
 */

public class DrugData {

    private ArrayList<Drug> drugs;

    private static DrugData instance;

    /**
     * Mikäli metodia kutsuttaessa instance ei vielä viittaa mihinkään Global-olioon
     * Luodaan uusi Global-olio ja määritetään se instance olion arvoksi
     * @return palauttaa Singletonin instanssin instance
     */

    public static DrugData getInstance() {
        if (instance == null) {
            instance = new DrugData();
        }
        return instance;
    }

    private DrugData() {                  // Global luokan konstruktori, kutsuttaessa luo uuden ArrayListin
        this.drugs = new ArrayList();
    }

    /**
     * @return palauttaa olion Global luoman listan drugs
     */

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }
}
