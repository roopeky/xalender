package com.example.lkesovellus;

import java.util.ArrayList;
import java.util.List;

public class Global {

    public ArrayList<Drug> drugs;

    private static final Global ourInstance = new Global();

    public static Global getInstance() {
        return ourInstance;
    }

    private Global() {
        this.drugs = new ArrayList();
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }




    @Override
    public String toString(){
        return Global.getInstance().getDrugs().get(0).toString();
    }
}
