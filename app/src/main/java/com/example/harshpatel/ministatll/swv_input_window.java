package com.example.harshpatel.ministatll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class swv_input_window extends AppCompatActivity {

    /*Initializing all of the inputs that will be given by the user (buttons and edit texts)*/
    public Button swv_continue_button;

    public EditText swv_start_voltage_input;
    public EditText swv_end_voltage_input;
    public EditText swv_scan_rate_input;
    public EditText swv_voltage_step_input;
    public EditText swv_pulse_amplitude_input;
    public EditText swv_pulse_frequency_input;

    public String swv_all_inputted_values_toast;

    //setting all of the inputs to their corresponding id (found in the xml file for each)
    public void init() {
        swv_start_voltage_input = (EditText) findViewById(R.id.swv_start_voltage_input);
        swv_end_voltage_input  = (EditText) findViewById(R.id.swv_end_voltage_input);
        swv_scan_rate_input = (EditText) findViewById(R.id.swv_scan_rate_input);
        swv_voltage_step_input  = (EditText) findViewById(R.id.swv_voltage_step_input);
        swv_pulse_amplitude_input  = (EditText) findViewById(R.id.swv_pulse_amplitude_input);
        swv_pulse_frequency_input = (EditText) findViewById(R.id.swv_pulse_frequency_input);

        swv_continue_button = (Button) findViewById(R.id.swv_continue_button);
        swv_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this just appends all of the (edit text) inputs in to a string
                swv_all_inputted_values_toast = swv_start_voltage_input.getText().toString() + "," +
                        swv_end_voltage_input.getText().toString() + "," +
                        swv_scan_rate_input.getText().toString() + "," +
                        swv_voltage_step_input.getText().toString() + "," +
                        swv_pulse_amplitude_input.getText().toString() + "," +
                        swv_pulse_frequency_input.getText().toString();
                Toast.makeText(getBaseContext(), swv_all_inputted_values_toast, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swv_input_window);
        init();
    }
}
