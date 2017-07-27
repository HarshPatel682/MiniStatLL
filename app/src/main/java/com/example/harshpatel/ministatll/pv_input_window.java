package com.example.harshpatel.ministatll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class pv_input_window extends AppCompatActivity {

    /*Initializing all of the inputs that will be given by the user (buttons and edit texts)*/
    public Button pv_continue_button;

    public EditText pv_frequency_input;
    public EditText pv_pulse_width_input;
    public EditText pv_pulse_amplitude_input;
    public EditText pv_pulse_per_cycle_input;

    public String pv_all_inputted_values_toast;

    //setting all of the inputs to their corresponding id (found in the xml file for each)
    public void init() {

        pv_frequency_input = (EditText) findViewById(R.id.pv_frequency_input);
        pv_pulse_width_input = (EditText) findViewById(R.id.pv_pulse_width_input);
        pv_pulse_amplitude_input = (EditText) findViewById(R.id.pv_pulse_amplitude_input);
        pv_pulse_per_cycle_input = (EditText) findViewById(R.id.pv_pulse_per_cycle_input);

        //creating a listener for the button
        pv_continue_button = (Button) findViewById(R.id.pv_continue_button);
        pv_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pv_all_inputted_values_toast = pv_frequency_input.getText().toString() + "," + pv_pulse_width_input.getText().toString() + "," +
                      pv_pulse_amplitude_input.getText().toString() + "," + pv_pulse_per_cycle_input.getText().toString();
                Toast.makeText(getBaseContext(), pv_all_inputted_values_toast, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv_input_window);
        init();

    }
}
