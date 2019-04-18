package com.scravlon.myinventory;


import org.junit.Test;

import java.util.ArrayList;

import dalvik.annotation.TestTarget;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Scravlon on 2/25/2018.
 */

public class MainActivityTest {
    @Test
    public void firstTest(){
        MainActivity ma = new MainActivity();
        ma.updateInven();
        ma.inventoryList.add(new Inventory("Storeroom", new ArrayList<Item>()));
        ma.inventoryList.add(new Inventory("Wardrobe", new ArrayList<Item>()));
        ma.saveInventory();
        ma.inventoryList = new ArrayList<Inventory>();
        ma.loadData();
        assertEquals(2, ma.inventoryList.size());


    }
}
