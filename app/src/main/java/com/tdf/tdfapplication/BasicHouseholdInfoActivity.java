package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class BasicHouseholdInfoActivity extends AppCompatActivity {

    private Spinner spinnerAADHAR;
    private Spinner spinnerSHG;
    private Spinner spinnerMGNERA, spinnerMaleMGNERA;
    private String countAADHAR, countMGNERA, countMaleMGNERA, countFemaleMGNERA, countSHG;
    private ArrayList<String> arrayList1;
    private Button button;
    private TextView textView;

    private int numberOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_household_info);
        Intent intent = getIntent();
        numberOfPeople = Integer.parseInt(intent.getStringExtra("NUMBER_OF_MEMBERS"));
        initialize();

        spinnerAADHAR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countAADHAR = Integer.toString(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countAADHAR = "0";
            }
        });
        spinnerMGNERA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countMGNERA = Integer.toString(position);
                if (position > 0) {
                    textView.setVisibility(View.VISIBLE);
                    spinnerMaleMGNERA.setVisibility(View.VISIBLE);
                } else {
                    spinnerMaleMGNERA.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerMaleMGNERA.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
            }
        });

        spinnerMaleMGNERA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countMaleMGNERA = Integer.toString(position);
                countFemaleMGNERA = Integer.toString(arrayList1.size() - position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countMaleMGNERA = "0";
                countFemaleMGNERA = "0";
            }
        });
        spinnerSHG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countSHG = Integer.toString(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countSHG = "0";
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", PersonalDetailsActivity.PERSON_KEY + "," + countAADHAR + "," + countMGNERA + countMaleMGNERA + countFemaleMGNERA + countSHG);
                finish();
            }
        });
    }

    private void initialize() {
        spinnerAADHAR = findViewById(R.id.respondent_AADHAR_card);
        spinnerMaleMGNERA = findViewById(R.id.respondent_MGNERA_card_male);
        spinnerMGNERA = findViewById(R.id.respondent_MGNERA_card_count);
        spinnerSHG = findViewById(R.id.respondent_SHG_member);
        button = findViewById(R.id.basic_household_info_submit);
        textView = findViewById(R.id.respondent_MGNERA_card_male_label);

        arrayList1 = new ArrayList<String>();

        range(numberOfPeople, arrayList1);

        ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAADHAR.setAdapter(arrayAdapter);
        spinnerMGNERA.setAdapter(arrayAdapter);
        spinnerSHG.setAdapter(arrayAdapter);

        spinnerMaleMGNERA.setAdapter(arrayAdapter);
        textView.setVisibility(View.GONE);
        spinnerMaleMGNERA.setVisibility(View.GONE);
    }

    private void range(int num, ArrayList<String> arr) {
        for (int count = 0; count <= num; ++count) {
            arr.add(Integer.toString(count));
        }

    }
}
