package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tdf.tdfapplication.utils.DatabaseManager;

import static com.tdf.tdfapplication.PersonalDetailsActivity.PERSON_KEY;

public class FamilyDetailsActivity extends AppCompatActivity {

    private String name, age, gender, education;
    private String occupationBeforeTDF, incomeBeforeTDF, occupationAfterTDF, incomeAfterTDF;
    private int count = 0;
    private int numberOfMembers;

    private EditText editTextName, editTextAge, editTextEducation, editTextOccupationBeforeTDF;
    private EditText editTextIncomeBeforeTDF, editTextOccupationAfterTDF, editTextIncomeAfterTDF;
    private RadioGroup genderGroup;
    private RadioButton radioButton;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_details);
        initialize();
        Intent intent = getIntent();
        numberOfMembers = Integer.parseInt(intent.getStringExtra("NUMBER_OF_MEMBERS"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextName.getText().toString();
                age = editTextAge.getText().toString();
                radioButton = findViewById(genderGroup.getCheckedRadioButtonId());
                if (radioButton.getText() == "Male") {
                    gender = "1";
                } else {
                    gender = "2";
                }
                education = editTextEducation.getText().toString();
                occupationAfterTDF = editTextOccupationAfterTDF.getText().toString();
                occupationBeforeTDF = editTextOccupationBeforeTDF.getText().toString();
                incomeBeforeTDF = editTextIncomeBeforeTDF.getText().toString();
                incomeAfterTDF = editTextIncomeAfterTDF.getText().toString();

                Log.i("INFO", name + " " + age + " " + gender + " " + education + " " + occupationBeforeTDF + " " + occupationAfterTDF + " " + incomeAfterTDF + " " + incomeAfterTDF);
                loadIntoSQLiteDatabase();
                if (count < numberOfMembers - 1) {
                    editTextAge.setText("");
                    editTextEducation.setText("");
                    editTextIncomeAfterTDF.setText("");
                    editTextIncomeBeforeTDF.setText("");
                    editTextOccupationBeforeTDF.setText("");
                    editTextOccupationAfterTDF.setText("");
                    editTextName.setText("");

                    count = count + 1;
                } else {
                    finish();
                }
            }
        });

    }

    private void initialize() {
        editTextName = findViewById(R.id.family_member_name);
        editTextAge = findViewById(R.id.family_member_age);
        editTextEducation = findViewById(R.id.family_member_education);
        editTextIncomeBeforeTDF = findViewById(R.id.wage_value);
        editTextOccupationBeforeTDF = findViewById(R.id.wage_number_of_days);
        editTextIncomeAfterTDF = findViewById(R.id.wage_value_after);
        editTextOccupationAfterTDF = findViewById(R.id.number_of_days_after);
        genderGroup = findViewById(R.id.gender);
        radioButton = findViewById(R.id.gender_male);
        button = findViewById(R.id.wage_info_submit);
    }

    private void loadIntoSQLiteDatabase(){

        DatabaseManager databaseManager = new DatabaseManager(this,"respondent_family_details");
        databaseManager.setCreateTable("Member_ID","ID","Member_Name","Member_Age","Member_Gender","Member_Edu","Member_Occu_Before","Member_Income_Before","Member_Occu_After","Member_Income_After");//Sheet col: Z-CT
        final DatabaseManager open = databaseManager.open();
        databaseManager.insert(PERSON_KEY+count,PERSON_KEY,name,age,gender,education,occupationBeforeTDF,incomeBeforeTDF,occupationAfterTDF,incomeAfterTDF);
        Toast.makeText(this,"INSERTED",Toast.LENGTH_LONG).show();
        Log.i("INSERTED:",databaseManager.showDetails());

    }
}
