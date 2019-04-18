package com.scravlon.myinventory;

import java.util.ArrayList;

/**
 * Created by Scravlon on 2/24/2018.
 * Inventory class, that has all the items;
 */

public class Inventory {

    String name;
    ArrayList<Item> alist;

    public Inventory(String name){
        this.name = name;
        alist = new ArrayList<Item>();
    }

    public Inventory(String name, ArrayList<Item> alist){
        this.name = name;
        this.alist = alist;
    }

    /** Add the item into the inventory
     *
     * @param item The Item to be added
     * @param amount amount of item to be added
     */
    public void addItem(Item item, int amount){
        if (alist.contains(item)){
            alist.get(alist.indexOf(item)).updateAmount(amount);
        } else{
            alist.add(item);
        }
    }

    /** Remove the item from the inventory
     *
     * @param loc The location of the item in the List view
     */
    public void removeItem(int loc){
        alist.remove(loc);
    }

    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }

    public ArrayList<Item> getalist(){
        return alist;
    }
}
