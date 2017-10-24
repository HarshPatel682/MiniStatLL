package com.example.harshpatel.ministatll;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
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


    public boolean isConnected = false;
    private BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();
    String deviceAddress;
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    private BluetoothGattCharacteristic characteristic;
    //private BluetoothLeService mBluetoothLeService;



    Spinner spinner; //drop down list
    ArrayAdapter<CharSequence> adapter;//returns the value from out spinner
    TextView selectedExperiment; //will store the textview of the selected experiment

    public Button selectedExperimentButton; //this button will send the user to the specified experiment page

    public void init() {
        selectedExperimentButton = (Button) findViewById(R.id.selectedExperimentButton);
        /*when ever the continue button is pressed, this will use the value from the
        * spinner to go to the specified experiment page
        */
        selectedExperimentButton.setOnClickListener(new View.OnClickListener() {
            Intent openSelectedExperimentPage;
            //when the button is clicked it will see what experiment name is stored in "selectedExperiment"
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
                startActivity(openSelectedExperimentPage); //this will send the user to the one of the experiment pages
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); //sets up the above code onCreate

        /*Initializing the spinner and adapter */
        spinner = (Spinner)findViewById(R.id.dropDownForExperiments);
        adapter = ArrayAdapter.createFromResource(this,R.array.experiments,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //this will set the "selectedExperiment" to the experiment selected from the dropdown list
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedExperiment = (TextView) view; //set the value received from the spinner(drop down)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //does not have a null/nothing option on the drop down
            }
        });
    }

}
