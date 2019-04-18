package com.scravlon.myinventory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {
    Inventory inventory;
    int location;
    ArrayList<Inventory> inventoryList;
    ListView list_item;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, addItem.class);
                startActivityForResult(intent, 2);
            }
        });
        Intent intent = getIntent();
        location = intent.getIntExtra("position", -1);
        loadData();
        inventory = inventoryList.get(location);
        //Log.i("No of items", String.valueOf(inventoryList.get(location).getalist().size()));
        //Log.i("Name of inventory", String.valueOf(inventoryList.get(location).getName()));
//        Log.i("Name of item", String.valueOf(inventoryList.get(location).getalist().get(0).name));
        list_item = (ListView) findViewById(R.id.list_item);
        updateListView();
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InventoryActivity.this, position + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        list_item.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InventoryActivity.this, position + " is removed.", Toast.LENGTH_SHORT).show();
                inventory.getalist().remove(position);
                inventoryList.set(position, inventory);
                saveInventory();
                updateListView();
                return false;
            }
        });
    }

    public void updateListView(){
        ItemAdapter itm = new ItemAdapter(this, inventory.alist);
        list_item.setAdapter(itm);
    }

    public void saveInventory(){
        SharedPreferences sharedPreferences = getSharedPreferences("inventoryPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(inventoryList);
        editor.putString("task list", json);
        editor.apply();
    }

    public boolean loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("inventoryPref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Inventory>>() {}.getType();
        inventoryList = gson.fromJson(json,type);
        if (inventoryList == null){
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //String result = data.getStringExtra("result");
                inventory.getalist().add(new Item(data.getStringExtra("item_name"),data.getIntExtra("item_amount",1)));

                saveInventory();
                //Log.i("No of items", String.valueOf(inventoryList.get(location).getalist().size()));
               // Log.i("Name of inventory", String.valueOf(inventoryList.get(location).getName()));
                //Log.i("Name of item", String.valueOf(inventoryList.get(location).getalist().get(1).name));

                //updateGrid();
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }


    }
}
