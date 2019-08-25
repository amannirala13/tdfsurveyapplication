package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class BasicHouseholdInfoActivity extends AppCompatActivity {

    private Spinner spinnerAADHAR;
    private Spinner spinnerSHG;
    private Spinner spinnerMGNERA, spinnerMaleMGNERA, spinnerFemaleMGNERA;
    private String countAADHAR,countMGNERA,countMaleMGNERA,countFemaleMGNERA,countSHG;
    private ArrayList<String> arrayList1,arrayList2,arrayList3,arrayList4,arrayList5;
    private Button button;

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
                countMGNERA =Integer.toString(position);
                if(position>0) {
                    spinnerFemaleMGNERA.setVisibility(View.VISIBLE);
                    spinnerMaleMGNERA.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerFemaleMGNERA.setVisibility(View.GONE);
                spinnerMaleMGNERA.setVisibility(View.GONE);
            }
        });
        spinnerFemaleMGNERA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countFemaleMGNERA = Integer.toString(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countFemaleMGNERA = "0";
            }
        });
        spinnerMaleMGNERA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countMaleMGNERA = Integer.toString(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countMaleMGNERA = "0";
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
                Log.i("INFO",PersonalDetailsActivity.PERSON_KEY + "," + countAADHAR + "," + countMGNERA + countMaleMGNERA + countFemaleMGNERA + countSHG);
                finish();
            }
        });
    }

    private void initialize(){
        spinnerAADHAR = findViewById(R.id.respondent_AADHAR_card);
        spinnerFemaleMGNERA = findViewById(R.id.respondent_MGNERA_card_female);
        spinnerMaleMGNERA = findViewById(R.id.respondent_MGNERA_card_male);
        spinnerMGNERA = findViewById(R.id.respondent_MGNERA_card_count);
        spinnerSHG = findViewById(R.id.respondent_SHG_member);
        button = findViewById(R.id.basic_household_info_submit);

        arrayList1 = new ArrayList<String>();
        arrayList2 = new ArrayList<String>();
        arrayList3 = new ArrayList<String>();
        arrayList4 = new ArrayList<String>();
        arrayList5 = new ArrayList<String>();

        range(numberOfPeople,arrayList1);
        range(numberOfPeople,arrayList2);
        range(numberOfPeople,arrayList3);
        range(numberOfPeople,arrayList4);
        range(numberOfPeople,arrayList5);

        ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayList1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAADHAR.setAdapter(arrayAdapter);
        spinnerMGNERA.setAdapter(arrayAdapter);
        spinnerSHG.setAdapter(arrayAdapter);

        spinnerMaleMGNERA.setAdapter(arrayAdapter);
        spinnerFemaleMGNERA.setAdapter(arrayAdapter);
    }

    private void range(int num,ArrayList<String> arr){
        for(int count =0;count<=num;++count){
            arr.add(Integer.toString(count));
        }

    }
}
