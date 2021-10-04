package com.example.lkesovellus;

import java.util.ArrayList;
import java.util.List;

public class Global {

    public ArrayList<Drug> drugs;

    private static Global ourInstance;

    public static Global getInstance() {
        if (ourInstance==null) {
            ourInstance = new Global();
        }
        return ourInstance;
    }

    private Global() {
        this.drugs = new ArrayList();
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public String getName(){
        return Global.getInstance().getDrugs().get(0).getDrugName();
    }


    
    @Override
    public String toString(){
        return Global.getInstance().getDrugs().get(0).toString();
    }

}
