package com.example.hemal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button

        System.out.println("**== sendMessage Called");

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }

    public void saveMessage(View view) {
        //Context context = getActivity();
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(EXTRA_MESSAGE, message);
        editor.commit();

    }

    public void loadMessage(View view) {

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //int defaultValue = getResources().getIdentifier() String(EXTRA_MESSAGE);
        String message = sharedPref.getString(EXTRA_MESSAGE,"default value");

        EditText loadText = (EditText) findViewById(R.id.load_message);
        loadText.setText(message);


    }



}
