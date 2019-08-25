package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class BasicHouseholdInfoActivity extends AppCompatActivity {

    private Spinner spinnerAADHAR;
    private Spinner spinnerSHG;
    private Spinner spinnerRationCardType;
    private Spinner spinnerMGNERA, spinnerMaleMGNERA, spinnerFemaleMGNERA;
    private String numberOfPeopleWithMGNERA,maleMGNERA,femaleMGNERA;
    private ArrayList<String> arrayList1,arrayList2,arrayList3,arrayList4,arrayList5;

    private int numberOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_household_info);
        Intent intent = getIntent();
        numberOfPeople = Integer.parseInt(intent.getStringExtra("NUMBER_OF_MEMBERS"));
    }

    private void initialize(){
        spinnerAADHAR = findViewById(R.id.respondent_AADHAR_card);
        spinnerFemaleMGNERA = findViewById(R.id.respondent_MGNERA_card_female);
        spinnerMaleMGNERA = findViewById(R.id.respondent_MGNERA_card_male);
        spinnerMGNERA = findViewById(R.id.respondent_MGNERA_card_count);
        spinnerSHG = findViewById(R.id.respondent_SHG_member);
        spinnerRationCardType = findViewById(R.id.respondent_ration_card_type);

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        arrayList5 = new ArrayList<>();

        range(numberOfPeople,arrayList1);
        range(numberOfPeople,arrayList2);
        range(numberOfPeople,arrayList3);
        range(numberOfPeople,arrayList4);
        range(numberOfPeople,arrayList5);

//        ArrayAdapter<?> arrayAdapter5 = ArrayAdapter.createFromResource(this,android.R.layout.simple_spinner_item,arrayList1);

    }

    private void range(int num,ArrayList<String> arr){
        for(int count =0;count<num;++count){
            arr.add(Integer.toString(count+1));
        }

    }
}
