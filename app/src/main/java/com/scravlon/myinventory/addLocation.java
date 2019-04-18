package com.scravlon.myinventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class addLocation extends AppCompatActivity {

    EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        etName = (EditText) findViewById(R.id.edit_name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addItem) {
            if (etName.getText().toString().matches("")){
                etName.setError("Insert Something");
            } else {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", etName.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
