package com.scravlon.myinventory;

/**
 * Created by Scravlon on 2/24/2018.
 */

public class Item {

    String name;
    int amount;


    public Item(String name, int amount){
        this.name = name;
        this.amount = amount;
    }


     public void updateName(String name){
        this.name = name;
     }

    public void updateAmount(int amount){
        this.amount = this.amount + amount;
    }



}
