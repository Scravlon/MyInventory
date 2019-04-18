package com.scravlon.myinventory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class addItem extends AppCompatActivity {

    ImageButton button_add;
    ImageButton button_minus;
    EditText edit_amount;
    EditText edit_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        edit_item = (EditText) findViewById(R.id.edit_item);
        button_add = (ImageButton) findViewById(R.id.button_add);
        button_minus = (ImageButton) findViewById(R.id.button_minus);
        edit_amount = (EditText) findViewById(R.id.edit_amount);
        button_minus.setEnabled(false);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_amount.setText(String.valueOf(Integer.valueOf(edit_amount.getText().toString()) + 1));
                button_minus.setEnabled(true);
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_amount.getText().toString().equals("2")){
                    button_minus.setEnabled(false);
                }
                edit_amount.setText(String.valueOf(Integer.valueOf(edit_amount.getText().toString()) - 1));

            }
        });
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
            if(edit_item.getText().toString().matches("")){
                edit_item.setError("Insert a Name");
            } else{
                Intent returnIntent = new Intent();
                returnIntent.putExtra("item_name", edit_item.getText().toString());
                returnIntent.putExtra("item_amount", Integer.valueOf(edit_amount.getText().toString()));
                setResult(Activity.RESULT_OK, returnIntent);

                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
