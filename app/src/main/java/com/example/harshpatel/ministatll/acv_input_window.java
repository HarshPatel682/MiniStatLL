package com.example.harshpatel.ministatll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class acv_input_window extends AppCompatActivity {


    /*
    * Initializing all of the inputs that will be given by the user (buttons and edit texts)
    * */
    public Button acv_continue_button;

    public EditText acv_start_voltage_input;
    public EditText acv_end_voltage_input;
    public EditText acv_amplitude_input;
    public EditText acv_pulse_frequency_input;
    public EditText acv_scan_rate_input;

    public String acv_all_inputted_values_toast;


    //setting all of the inputs to their corresponding id (found in the xml file for each)
    public void init() {
        acv_start_voltage_input = (EditText) findViewById(R.id.acv_start_voltage_input);
        acv_end_voltage_input  = (EditText) findViewById(R.id.acv_end_voltage_input);
        acv_scan_rate_input = (EditText) findViewById(R.id.acv_scan_rate_input);
        acv_amplitude_input  = (EditText) findViewById(R.id.acv_amplitude_input);
        acv_pulse_frequency_input = (EditText) findViewById(R.id.acv_pulse_frequency_input);

        //button listener
        acv_continue_button = (Button) findViewById(R.id.acv_continue_button);
        acv_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this just appends all of the (edit text) inputs in to a string
                acv_all_inputted_values_toast = acv_start_voltage_input.getText().toString() + "," +
                        acv_end_voltage_input.getText().toString() + "," +
                        acv_amplitude_input.getText().toString() + "," +
                        acv_pulse_frequency_input.getText().toString() + "," +
                        acv_scan_rate_input.getText().toString();
                //was for testing purposes, just showing a toast with the inputted values
                Toast.makeText(getBaseContext(), acv_all_inputted_values_toast, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acv_input_window);
        init(); //calling the function above
    }
}
