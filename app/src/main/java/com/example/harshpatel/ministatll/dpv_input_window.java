package com.example.harshpatel.ministatll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dpv_input_window extends AppCompatActivity {

    public Button dpv_continue_button;

    public EditText dpv_start_voltage_input;
    public EditText dpv_end_voltage_input;
    public EditText dpv_scan_rate_input;
    public EditText dpv_voltage_step_input;
    public EditText dpv_pulse_amplitude_input;
    public EditText dpv_pulse_frequency_input;

    public String dpv_all_inputted_values_toast;

    public void init() {
        dpv_start_voltage_input = (EditText) findViewById(R.id.dpv_start_voltage_input);
        dpv_end_voltage_input  = (EditText) findViewById(R.id.dpv_end_voltage_input);
        dpv_scan_rate_input = (EditText) findViewById(R.id.dpv_scan_rate_input);
        dpv_voltage_step_input  = (EditText) findViewById(R.id.dpv_voltage_step_input);
        dpv_pulse_amplitude_input  = (EditText) findViewById(R.id.dpv_pulse_amplitude_input);
        dpv_pulse_frequency_input = (EditText) findViewById(R.id.dpv_pulse_frequency_input);

        dpv_continue_button = (Button) findViewById(R.id.dpv_continue_button);
        dpv_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpv_all_inputted_values_toast = dpv_start_voltage_input.getText().toString() + "," +
                        dpv_end_voltage_input.getText().toString() + "," +
                        dpv_scan_rate_input.getText().toString() + "," +
                        dpv_voltage_step_input.getText().toString() + "," +
                        dpv_pulse_amplitude_input.getText().toString() + "," +
                        dpv_pulse_frequency_input.getText().toString();
                Toast.makeText(getBaseContext(), dpv_all_inputted_values_toast, Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpv_input_window);
        init();
    }
}
