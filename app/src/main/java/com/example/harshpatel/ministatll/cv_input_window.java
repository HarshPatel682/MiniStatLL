package com.example.harshpatel.ministatll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cv_input_window extends AppCompatActivity {

    /*Initializing all of the inputs that will be given by the user (buttons and edit texts)*/
    public Button cv_continue_button;

    public EditText cv_start_voltage_input;
    public EditText cv_end_voltage_input;
    public EditText cv_voltage_step_input;
    public EditText cv_scan_rate_input;

    public String cv_all_inputted_values_toast;

    //setting all of the inputs to their corresponding id (found in the xml file for each)
    public void init() {
        cv_start_voltage_input = (EditText) findViewById(R.id.cv_start_voltage_input);
        cv_end_voltage_input = (EditText) findViewById(R.id.cv_end_voltage_input);
        cv_voltage_step_input = (EditText) findViewById(R.id.cv_voltage_step_input);
        cv_scan_rate_input = (EditText) findViewById(R.id.cv_scan_rate_input);

        //button listener
        cv_continue_button = (Button) findViewById(R.id.cv_continue_button);
        cv_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this just appends all of the (edit text) inputs in to a string
                cv_all_inputted_values_toast = cv_start_voltage_input.getText().toString() + "," + cv_end_voltage_input.getText().toString()
                        + "," + cv_voltage_step_input.getText().toString() + "," +
                        cv_scan_rate_input.getText().toString();
                //Toast.makeText(getBaseContext(), cv_all_inputted_values_toast, Toast.LENGTH_LONG).show();

                /*Just to test if the graph works...this starts the "graph_example" when the button is clicked*/
                Intent goToGraphExample = new Intent(cv_input_window.this, graph_example.class);
                startActivity(goToGraphExample);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_input_window);
        init();//calling the function above
    }
}
