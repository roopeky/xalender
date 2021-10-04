package com.example.lkesovellus;

import java.util.ArrayList;
import java.util.List;

public class Global {

    public List<Drug> drugs;

    private static final Global ourInstance = new Global();

    public static Global getInstance() {
        return ourInstance;
    }

    private Global() {
        this.drugs = new ArrayList();
    }

    public List<Drug> getDrugs() {
        return drugs;
    }


}
