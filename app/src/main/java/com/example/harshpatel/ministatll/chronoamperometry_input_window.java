package com.example.harshpatel.ministatll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class chronoamperometry_input_window extends AppCompatActivity {

    /*
    * Initializing all of the inputs that will be given by the user (buttons and edit texts)
    * */
    public Button chronoamperometry_continue_button;

    public EditText chronoamperometry_voltage_input;
    public EditText chronoamperometry_time_length_input;

    public String chronoamperometry_all_inputted_values_toast;

    //setting all of the inputs to their corresponding id (found in the xml file for each)
    public void init() {
        chronoamperometry_voltage_input = (EditText) findViewById(R.id.chronoamperometry_voltage_input);
        chronoamperometry_time_length_input = (EditText) findViewById(R.id.chronoamperometry_time_length_input);

        //initialize the button and create a listener for it
        chronoamperometry_continue_button = (Button) findViewById(R.id.chronoamperometry_continue_button);
        chronoamperometry_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //appending all of the edit texts to a string to show as a toast
                chronoamperometry_all_inputted_values_toast = chronoamperometry_voltage_input.getText().toString() + "," +
                        chronoamperometry_time_length_input.getText().toString();
                Toast.makeText(getBaseContext(), chronoamperometry_all_inputted_values_toast, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronoamperometry_input_window);
        init();//calls the function above
    }
}
