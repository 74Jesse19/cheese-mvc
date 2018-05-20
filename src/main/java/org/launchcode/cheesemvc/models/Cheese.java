package org.launchcode.cheesemvc.models;

import java.util.HashMap;

public class Cheese {
    private static HashMap<String,String> cheeses = new HashMap<>();


    public Cheese(HashMap<String,String> cheeses){
        this.cheeses = cheeses;
    }

    public static HashMap getCheeses()
    {
        return cheeses;
    }

    public static void setCheeses(String aKey, String aValue)
    {
        cheeses.put(aKey, aValue);
    }

}

