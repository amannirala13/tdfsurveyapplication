package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tdf.tdfapplication.utils.DatabaseManager;

import static com.tdf.tdfapplication.PersonalDetailsActivity.PERSON_KEY;

public class BasicHouseholdInformationActivity extends AppCompatActivity {

    private String caste = "666", religion = "666", tribe = "666", totalIncome;
    private String numberOfPeople, rationCardType, electricty;

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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_household_information);
        initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfPeople = editTextNumberOfPeople.getText().toString();
                for (CheckBox checkBox : checkBoxes) {
                    if (checkBox.isChecked()) {
                        electricty = checkBox.getText().toString() + ",";
                    }
                }
                loadIntoSQLiteDatabase();
                Log.i("INFO", PERSON_KEY + " " + totalIncome + " " + caste + " " + tribe + " "
                        + religion + " " + numberOfPeople + " " + electricty + " " + rationCardType);
                Intent intent = new Intent(BasicHouseholdInformationActivity.this, BasicHouseholdInfoActivity.class);
                intent.putExtra("NUMBER_OF_MEMBERS", numberOfPeople);
                startActivity(intent);
            }
        });

        spinnerReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 4 || position == 5) {
                    religion = "777";
                } else {
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
                if (position == 4) {
                    caste = "777";
                } else {
                    caste = Integer.toString(position + 1);
                }
                if (position < 3) {
                    spinnerTribe.setVisibility(View.VISIBLE);
                    textViewTribe.setVisibility(View.VISIBLE);
                } else {
                    spinnerTribe.setVisibility(View.GONE);
                    textViewTribe.setVisibility(View.GONE);
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
                tribe = Integer.toString(position + 1);
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

        spinnerRationCardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rationCardType = Integer.toString(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initialize() {

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


        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.religion_list, android.R.layout.simple_spinner_item);
        spinnerReligion.setAdapter(arrayAdapter);

        ArrayAdapter<?> arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.caste_list, android.R.layout.simple_spinner_item);
        spinnerCaste.setAdapter(arrayAdapter1);

        ArrayAdapter<?> arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.tribe_list, android.R.layout.simple_spinner_item);
        spinnerTribe.setAdapter(arrayAdapter2);

        ArrayAdapter<?> arrayAdapter3 = ArrayAdapter.createFromResource(this, R.array.income_list, android.R.layout.simple_spinner_item);
        spinnerIncome.setAdapter(arrayAdapter3);

        ArrayAdapter<?> arrayAdapter4 = ArrayAdapter.createFromResource(this, R.array.ration_card_type_list, android.R.layout.simple_spinner_item);
        spinnerRationCardType.setAdapter(arrayAdapter4);

    }

    private void loadIntoSQLiteDatabase(){

        DatabaseManager databaseManager = new DatabaseManager(this,"respondent_basic_household_information");
        databaseManager.setCreateTable("ID","Religion",	"Caste",	"Tribe",	"Annual_Income",	"Household_Size",	"Ration_Card_Type",);//Sheet col: K-O
        databaseManager.open();
        databaseManager.insert(PERSON_KEY,religion,caste,tribe,totalIncome,numberOfPeople,rationCardType);
        Toast.makeText(this,"INSERTED",Toast.LENGTH_LONG).show();
        Log.i("INSERTED:",databaseManager.showDetails());

    }


}

