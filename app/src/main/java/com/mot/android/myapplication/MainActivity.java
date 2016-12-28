package com.mot.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mot.android.service.PointsServiceImpl;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private final static PointsServiceImpl pointsService = PointsServiceImpl.getInstance();

    String userId="Veer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderUsers();

        userId= "Veer";

        refreshUserDetails();

    }

    public void refreshUserDetails(){

        renderGreetings();
        renderPoints();
    }

    private void renderGreetings() {

        TextView pointsTextView = (TextView) findViewById(R.id.welcomeTextView);

        pointsTextView.setText(" Hello "+  userId  +" !!! ");

    }

    public void renderUsers(){
        Spinner spinner = (Spinner) findViewById(R.id.user_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.user_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /** Called when the user clicks the Send button */
    public void addPoint(View view) {
        // Do something in response to button

        System.out.println("**== addPoint invoked");

        pointsService.incrementPoints(this,userId);

        renderPoints();


    }

    public void renderPoints(){

        TextView pointsTextView = (TextView) findViewById(R.id.pointsTextView);

        Integer points = pointsService.getPoints(this,userId);

        System.out.println("**== Points for Veer : " + points);

        pointsTextView.setText(points.toString());
    }

    public void deletePoint(View view) {

        System.out.println("**== deletePoint invoked");

        pointsService.decrementPoints(this,userId);

        renderPoints();


    }

    public void resetPoint(View view) {

        pointsService.resetPoints(this,userId);

        renderPoints();


    }

    /** Called when the user clicks the Send button */
    public void testScreen(View view) {
        // Do something in response to button

        System.out.println("**== testScreen Called");

        Intent intent = new Intent(this, TestMainActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        System.out.println(" **-- Item selected : " + parent.getItemAtPosition(position));
        userId = (String)parent.getItemAtPosition(position);
        refreshUserDetails();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
