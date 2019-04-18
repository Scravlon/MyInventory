package com.scravlon.myinventory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Inventory> inventoryList;
    ArrayList<String> adapterString;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this, addLocation.class);

              startActivityForResult(intent, 1);


            }
        });

        loadData();
//
//        inventoryList.remove(0);
//        saveInventory();
//        updateInven();
//        loadData();
//        Log.d("NUMBER OF ITEEM", String.valueOf(inventoryList.size()));

        GridView location_grid = (GridView) findViewById(R.id.grid_Location);

        if(loadData()) {
           adapterString = new ArrayList<String>();
            for (int i = 0; i <inventoryList.size(); i++){
                adapterString.add(inventoryList.get(i).getName());
           }

           location_grid.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, adapterString)
           {
               public View getView(int position, View convertView, ViewGroup parent){
// Return the GridView current item as a View
                   View view = super.getView(position,convertView,parent);

                   // Convert the view as a TextView widget
                   TextView tv = (TextView) view;

                   //tv.setTextColor(Color.DKGRAY);

                   // Set the layout parameters for TextView widget
                   RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(
                           ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                   );
                   tv.setLayoutParams(lp);

                   // Get the TextView LayoutParams
                   RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();

                   // Set the width of TextView widget (item of GridView)

                   // Set the TextView layout parameters
                   tv.setLayoutParams(params);

                   // Display TextView text in center position
                   tv.setGravity(Gravity.CENTER);

                   // Set the TextView text font family and text size
                   tv.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                   tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

                   // Set the TextView text (GridView item text)
                   tv.setText(adapterString.get(position));

                   // Set the TextView background color
                   tv.setBackgroundColor(Color.parseColor("#FF82DE81"));

                   // Return the TextView widget as GridView item
                   return tv;
               }
           });

            location_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, position + " is clicked!", Toast.LENGTH_SHORT).show();
                    Intent inte = new Intent(MainActivity.this,InventoryActivity.class);
                    inte.putExtra("position",position);
                    startActivity(inte);
                }
            });

            location_grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, position + " is remomved", Toast.LENGTH_SHORT).show();
                    inventoryList.remove(position);
                    saveInventory();
                    updateGrid();
                    return false;
                }
            });
        }
    }

    public void updateGrid(){
        GridView location_grid = (GridView) findViewById(R.id.grid_Location);
        if(loadData()) {
            final ArrayList<String> adapterString = new ArrayList<String>();
            for (int i = 0; i <inventoryList.size(); i++){
                adapterString.add(inventoryList.get(i).getName());
            }

            location_grid.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, adapterString)
            {
                public View getView(int position, View convertView, ViewGroup parent){
// Return the GridView current item as a View
                    View view = super.getView(position,convertView,parent);

                    // Convert the view as a TextView widget
                    TextView tv = (TextView) view;

                    //tv.setTextColor(Color.DKGRAY);

                    // Set the layout parameters for TextView widget
                    RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                    );
                    tv.setLayoutParams(lp);

                    // Get the TextView LayoutParams
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();

                    // Set the width of TextView widget (item of GridView)

                    // Set the TextView layout parameters
                    tv.setLayoutParams(params);

                    // Display TextView text in center position
                    tv.setGravity(Gravity.CENTER);

                    // Set the TextView text font family and text size
                    tv.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

                    // Set the TextView text (GridView item text)
                    tv.setText(adapterString.get(position));

                    // Set the TextView background color
                    tv.setBackgroundColor(Color.parseColor("#FF82DE81"));

                    // Return the TextView widget as GridView item
                    return tv;
                }
            });

            location_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, position + " is clicked!", Toast.LENGTH_SHORT).show();
                    Intent inte = new Intent(MainActivity.this,InventoryActivity.class);
                    inte.putExtra("position",position);
                    startActivity(inte);
                }
            });
        }
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
            inventoryList = new ArrayList<Inventory>();
            saveInventory();
            return false;
        }
        return true;
    }

    public void updateInven(){
        inventoryList = new ArrayList<Inventory>();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Clean the inventory
     */
    public void cleanInventory(){
        loadData();
        while(0 < inventoryList.size()){
            inventoryList.remove(0);
        }
        saveInventory();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                inventoryList.add(new Inventory(result));
                saveInventory();
                updateGrid();
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }
    }
}
