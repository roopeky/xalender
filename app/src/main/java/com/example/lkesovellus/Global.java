package com.example.lkesovellus;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka Global
 * Singleton käyttää Drug luokan olioita
 * @author Emil Suuronen
 */

public class Global {

    private ArrayList<Drug> drugs;

    private static Global ourInstance;

    /**
     * @return palauttaa Singletonin instanssin
     */

    public static Global getInstance() {
        if (ourInstance==null) {
            ourInstance = new Global();
        }
        return ourInstance;
    }


    private Global() {                  // Global luokan konstruktori, kutsuttaessa luo uuden ArrayListin
        this.drugs = new ArrayList();   //"this" viittaa olioon joka suorittaa koodia
    }

    /**
     * @return palauttaa olion Global luoman listan drugs
     */

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    /**
     * @return palauttaa
     */
}
