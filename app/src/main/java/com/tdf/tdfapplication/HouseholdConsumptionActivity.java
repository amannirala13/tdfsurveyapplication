package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tdf.tdfapplication.model.RespondentHouseholdConsumption;

public class HouseholdConsumptionActivity extends AppCompatActivity {


    private TextView textViewItemLabel;
    private Button button;
    private EditText editTextFrequencyBeforeTDF, editTextFrequencyAfterTDF;
    private EditText editTextPriceBeforeTDF, editTextPriceAfterTDF;
    private Spinner spinnerSourceBeforeTDF, spinnerSourceAfterTDF;
    private String[] sources;
    private String[] householdItems;
    private int count = 0;
    private RespondentHouseholdConsumption respondentHouseholdConsumptionBeforeTDF,
            respondentHouseholdConsumptionAfterTDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_consumption);

        initialize();

        spinnerSourceAfterTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    respondentHouseholdConsumptionAfterTDF
                            .setSource(Integer.toString(i + 1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                respondentHouseholdConsumptionAfterTDF
                        .setSource("666");
            }
        });

        spinnerSourceBeforeTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    respondentHouseholdConsumptionBeforeTDF
                            .setSource(Integer.toString(i + 1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                respondentHouseholdConsumptionBeforeTDF
                        .setSource("666");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < householdItems.length - 1 && householdItems[count + 1] != null) {

                    textViewItemLabel.setText(householdItems[count + 1]);
                    count = count + 1;

                    if (!(editTextFrequencyBeforeTDF.getText().toString().equals(""))) {
                        respondentHouseholdConsumptionBeforeTDF
                                .setFrequency(editTextFrequencyBeforeTDF.getText().toString());
                    }
                    if (!(editTextPriceBeforeTDF.getText().toString().equals(""))) {
                        respondentHouseholdConsumptionBeforeTDF
                                .setPrice(editTextPriceBeforeTDF.getText().toString());
                    }

                    if (!(editTextFrequencyAfterTDF.getText().toString().equals(""))) {
                        respondentHouseholdConsumptionAfterTDF
                                .setFrequency(editTextFrequencyAfterTDF.getText().toString());
                    }
                    if (!(editTextPriceAfterTDF.getText().toString().equals(""))) {
                        respondentHouseholdConsumptionAfterTDF
                                .setPrice(editTextPriceAfterTDF.getText().toString());
                    }

                    Log.i("INFO", respondentHouseholdConsumptionBeforeTDF.getPrice() +
                            respondentHouseholdConsumptionBeforeTDF.getSource() +
                            respondentHouseholdConsumptionBeforeTDF.getFrequency());

                    Log.i("INFO", respondentHouseholdConsumptionAfterTDF.getPrice() +
                            respondentHouseholdConsumptionAfterTDF.getSource() +
                            respondentHouseholdConsumptionAfterTDF.getFrequency());

                    editTextFrequencyBeforeTDF.setText("");
                    editTextPriceBeforeTDF.setText("");
                    editTextFrequencyAfterTDF.setText("");
                    editTextPriceAfterTDF.setText("");

                    spinnerSourceAfterTDF.setSelection(0);
                    spinnerSourceBeforeTDF.setSelection(0);

                } else {
                    finish();
                }
            }
        });
    }

    private void initialize() {
        textViewItemLabel = findViewById(R.id.item_label);
        button = findViewById(R.id.household_consumption_submit);
        editTextFrequencyAfterTDF = findViewById(R.id.household_frequency_after);
        editTextFrequencyBeforeTDF = findViewById(R.id.household_frequency);
        editTextPriceAfterTDF = findViewById(R.id.household_price_after);
        editTextPriceBeforeTDF = findViewById(R.id.household_price);

        spinnerSourceAfterTDF = findViewById(R.id.household_source_after);
        spinnerSourceBeforeTDF = findViewById(R.id.household_source);

        sources = new String[]{"None", "Market", "Own Farm"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                sources);

        spinnerSourceBeforeTDF.setAdapter(adapter);
        spinnerSourceAfterTDF.setAdapter(adapter);

        respondentHouseholdConsumptionAfterTDF = new RespondentHouseholdConsumption();
        respondentHouseholdConsumptionBeforeTDF = new RespondentHouseholdConsumption();

        Intent intent = getIntent();
        householdItems = intent.getStringArrayExtra("HOUSEHOLD_CONSUMPTION_LIST");
        textViewItemLabel.setText(householdItems[0]);
    }
}
