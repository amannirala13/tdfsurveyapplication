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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class PersonalDetailsActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextNumber;
    private Spinner spinnerYear;
    private RadioGroup radioGroupGender;
    private RadioButton radioButton;
    private CheckBox beneficiary;
    private String name, contact, year, gender, isBeneficiary;
    private Button button;
    public static String PERSON_KEY = "ABCD";
    String village_code;
    private ArrayList<String> yearsOfJoiningList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        initialize();

        Intent intent = getIntent();
        if (PERSON_KEY.equals(intent.getStringExtra("PERSON_KEY"))) {
            int sequence = Integer.parseInt(PERSON_KEY.substring(4, PERSON_KEY.length()));
            sequence = sequence + 1;
            PERSON_KEY = PERSON_KEY + sequence;
        } else {
            PERSON_KEY = intent.getStringExtra("PERSON_KEY") + "1";
        }
        village_code = intent.getStringExtra("Village_code");

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = yearsOfJoiningList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                year = yearsOfJoiningList.get(0);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextName.getText().toString();
                contact = editTextNumber.getText().toString();

                if (beneficiary.isChecked()) {
                    isBeneficiary = "1";
                }

                radioButton = findViewById(radioGroupGender.getCheckedRadioButtonId());
                if (radioButton.getText() == "Male") {
                    gender = "1";
                } else {
                    gender = "2";
                }
                //person_id  primary key,  name , gender , village , year date, contact_no )
                String values = " '" + PERSON_KEY + "','" + name + "','" + isBeneficiary +"','"+ gender+"','"+village_code+"','" +year+"','" + contact + "'" ;
                Log.i("INFO", year + " " + PERSON_KEY + " " + name + " " + isBeneficiary + " " + contact + " " + gender);

                Intent intent = new Intent(PersonalDetailsActivity.this, SectionActivity.class);
                finish();
            }
        });
    }

    private void initialize() {
        editTextName = findViewById(R.id.respondent_name_edit_text);
        editTextNumber = findViewById(R.id.respondent_contact_number_edit_text);
        spinnerYear = findViewById(R.id.spinner);
        beneficiary = findViewById(R.id.beneficiary);
        radioGroupGender = findViewById(R.id.gender);
        radioButton = findViewById(R.id.gender_male);

        button = findViewById(R.id.submit);

        yearsOfJoiningList = new ArrayList<>();
        spinnerYear = findViewById(R.id.spinner);
        gender = "1";
        isBeneficiary = "0";

        //setting year of joining values to year of joining item
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2000; i <= thisYear; i++) {
            yearsOfJoiningList.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearsOfJoiningList);
        spinnerYear.setAdapter(adapter);

    }
}
