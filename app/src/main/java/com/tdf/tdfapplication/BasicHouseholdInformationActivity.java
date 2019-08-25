package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class BasicHouseholdInformationActivity extends AppCompatActivity {

    private String caste = "666",religion = "666",tribe = "666",totalIncome;
    private String numberOfPeople;
    private String countOfAADHAR,electricityAvailibility,countOfSHG;

    private EditText editTextNumberOfPeople;
    private CheckBox[] checkBoxes;
    private Button button;
    TextView textViewTribe;
    private Spinner spinnerReligion;
    private Spinner spinnerCaste;
    private Spinner spinnerTribe;
    private Spinner spinnerRationCardType;

    private Spinner spinnerIncome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_household_information);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfPeople = editTextNumberOfPeople.getText().toString();
                Intent intent = new Intent(BasicHouseholdInformationActivity.this,BasicHouseholdInfoActivity.class);
                intent.putExtra("NUMBER_OF_MEMBERS",numberOfPeople);
            }
        });

        spinnerReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4 || position == 5){
                    religion = "777";
                }else{
                    religion = Integer.toString(position + 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                religion = "666";
            }
        });

        spinnerCaste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4){
                    caste = "777";
                } else {
                    caste = Integer.toString(position + 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                caste = "666";
            }

        });

        spinnerTribe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tribe = Integer.toString(position +1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tribe = "666";
            }
        });

        spinnerIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                totalIncome = Integer.toString(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                totalIncome = "666";
            }
        });


    }

    private void initialize(){

        editTextNumberOfPeople = findViewById(R.id.respondent_number_of_people);

        checkBoxes = new CheckBox[3];
        checkBoxes[0] = findViewById(R.id.home);
        checkBoxes[1] = findViewById(R.id.farm);
        checkBoxes[2] = findViewById(R.id.none);

        button = findViewById(R.id.basic_household_info_submit);

        textViewTribe = findViewById(R.id.respondent_tribe_label);

        spinnerTribe = findViewById(R.id.tribe);
        spinnerCaste = findViewById(R.id.caste);
        spinnerReligion = findViewById(R.id.religion);
        spinnerIncome = findViewById(R.id.income);
        spinnerRationCardType = findViewById(R.id.respondent_ration_card_type);



        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.religion_list,android.R.layout.simple_spinner_item);
        spinnerReligion.setAdapter(arrayAdapter);

        ArrayAdapter<?> arrayAdapter1 = ArrayAdapter.createFromResource(this,R.array.caste_list,android.R.layout.simple_spinner_item);
        spinnerCaste.setAdapter(arrayAdapter1);

        ArrayAdapter<?> arrayAdapter2 = ArrayAdapter.createFromResource(this,R.array.tribe_list,android.R.layout.simple_spinner_item);
        spinnerTribe.setAdapter(arrayAdapter2);

        ArrayAdapter<?> arrayAdapter3 = ArrayAdapter.createFromResource(this,R.array.income_list,android.R.layout.simple_spinner_item);
        spinnerIncome.setAdapter(arrayAdapter3);

        ArrayAdapter<?> arrayAdapter4 = ArrayAdapter.createFromResource(this,R.array.ration_card_type_list,android.R.layout.simple_spinner_item);
        spinnerRationCardType.setAdapter(arrayAdapter4);


    }


//        button = findViewById(R.id.basic_household_info_submit);
//        textView = findViewById(R.id.respondent_tribe_label);
//        tribe= findViewById(R.id.tribe);
//        checkBoxes = new CheckBox[3];
//
//
//        other = findViewById(R.id.respondent_other_tribe_name);
////        otherTribe = findViewById(R.id.editText);
//
//        final Spinner spinner = findViewById(R.id.caste);
//        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.caste_list,android.R.layout.simple_spinner_dropdown_item);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinner.setAdapter(arrayAdapter);
//
//        final ArrayAdapter<CharSequence> tribeAdapter = ArrayAdapter.createFromResource(this,R.array.tribe_list,android.R.layout.simple_spinner_dropdown_item);
//        tribeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        tribe.setAdapter(tribeAdapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                casteName= getResources().getStringArray(R.array.caste_list)[position];
//                if(position == 2 || position == 3 ){
//                    textView.setVisibility(View.VISIBLE);
//                    tribe.setVisibility(View.VISIBLE);
//                }else{
//                    other.setVisibility(View.GONE);
//                    otherTribe.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                casteName = getResources().getStringArray(R.array.village_list)[4];
//            }
//        });
//
//        tribe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                tribeName = getResources().getStringArray(R.array.tribe_list)[position];
//                if(position == 6){
//                    other.setVisibility(View.VISIBLE);
//                    otherTribe.setVisibility(View.VISIBLE);
//                } else {
//                    other.setVisibility(View.GONE);
//                    otherTribe.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String checkBoxValue ="";
//
//                for(int i =0; i<checkBoxes.length;i++){
//                    if(checkBoxes[i].isChecked()){
//                        checkBoxValue = checkBoxValue + " " + checkBoxes[i].getText().toString();
//                    }
//                }
//
//                String value = "Caste: " + casteName + "Tribe:" + tribeName + "Other Tribe name:" + otherTribe.getText().toString()+ checkBoxes;
//                Log.i("HOUSE_HOLD_VALUES",value);
//                finish();
//            }
//        });
    }
}
