package com.example.harshpatel.ministatll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cv_input_window extends AppCompatActivity {

    public Button cv_continue_button;

    public EditText cv_start_voltage_input;
    public EditText cv_end_voltage_input;
    public EditText cv_voltage_step_input;
    public EditText cv_scan_rate_input;

    public String cv_all_inputted_values_toast;

    public void init() {
        cv_start_voltage_input = (EditText) findViewById(R.id.cv_start_voltage_input);
        cv_end_voltage_input = (EditText) findViewById(R.id.cv_end_voltage_input);
        cv_voltage_step_input = (EditText) findViewById(R.id.cv_voltage_step_input);
        cv_scan_rate_input = (EditText) findViewById(R.id.cv_scan_rate_input);

        cv_continue_button = (Button) findViewById(R.id.cv_continue_button);
        cv_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv_all_inputted_values_toast = cv_start_voltage_input.getText().toString() + "," + cv_end_voltage_input.getText().toString()
                        + "," + cv_voltage_step_input.getText().toString() + "," +
                        cv_scan_rate_input.getText().toString();
                Toast.makeText(getBaseContext(), cv_all_inputted_values_toast, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_input_window);
        init();
    }
}
