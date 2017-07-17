package com.example.harshpatel.ministatll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView selectedExperiment; //will store the textview of the selected experiment

    public Button selectedExperimentButton;

    public void init() {
        selectedExperimentButton = (Button) findViewById(R.id.selectedExperimentButton);
        selectedExperimentButton.setOnClickListener(new View.OnClickListener() {
            Intent openSelectedExperimentPage;
            @Override
            public void onClick(View v) {
                switch ((String)selectedExperiment.getText()) {
                    case ("Cyclic Voltammetry (CV)"):
                        openSelectedExperimentPage = new Intent(MainActivity.this, cv_input_window.class);
                        break;
                    case ("Square Wave Voltammetry (SWV)"):
                        openSelectedExperimentPage = new Intent(MainActivity.this, swv_input_window.class);
                        break;
                    case ("Differential Pulse Voltammetry (DPV)"):
                        openSelectedExperimentPage = new Intent(MainActivity.this, dpv_input_window.class);
                        break;
                    case ("Alternating Current Voltammetry (ACV)"):
                        openSelectedExperimentPage = new Intent(MainActivity.this, acv_input_window.class);
                        break;
                    case ("Chronoamperometry (Amp)"):
                        openSelectedExperimentPage = new Intent(MainActivity.this, chronoamperometry_input_window.class);
                        break;
                    case ("Pulse Voltammetry (PV)"):
                        openSelectedExperimentPage = new Intent(MainActivity.this, pv_input_window.class);
                        break;
                }
                startActivity(openSelectedExperimentPage);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        spinner = (Spinner)findViewById(R.id.dropDownForExperiments);
        adapter = ArrayAdapter.createFromResource(this,R.array.experiments,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedExperiment = (TextView) view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
