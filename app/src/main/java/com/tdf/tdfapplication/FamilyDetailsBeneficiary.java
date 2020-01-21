package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tdf.tdfapplication.utils.DatabaseManager;

import static com.tdf.tdfapplication.PersonalDetailsActivity.PERSON_KEY;

public class FamilyDetailsBeneficiary extends AppCompatActivity {

    private EditText editTextBeforeTDFMajor,editTextBeforeTDFMinor,editTextAfterTDFMajor,editTextAfterTDFMinor,editTextNumberOfMembers;
    private String majorBeforeTDF,majorAfterTDF,minorAfterTDF,minorBeforeTDF;
    private String numberOfMembers;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_details_beneficiary);
        initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                majorAfterTDF = editTextAfterTDFMajor.getText().toString();
                minorAfterTDF = editTextAfterTDFMinor.getText().toString();
                majorBeforeTDF = editTextBeforeTDFMajor.getText().toString();
                minorBeforeTDF = editTextBeforeTDFMinor.getText().toString();

                numberOfMembers = editTextNumberOfMembers.getText().toString();

                Log.i( "INFO",majorAfterTDF + minorAfterTDF + majorBeforeTDF + minorBeforeTDF + numberOfMembers);
                Intent intent = new Intent(FamilyDetailsBeneficiary.this,FamilyDetailsActivity.class);
                intent.putExtra("NUMBER_OF_MEMBERS",numberOfMembers);
                startActivity(intent);
            }
        });
    }

    private void initialize(){
        editTextAfterTDFMajor = findViewById(R.id.number_of_days_after);
        editTextAfterTDFMinor = findViewById(R.id.wage_value_after);
        editTextBeforeTDFMajor = findViewById(R.id.wage_number_of_days);
        editTextBeforeTDFMinor = findViewById(R.id.wage_value);
        editTextNumberOfMembers = findViewById(R.id.family_member_count);
        button = findViewById(R.id.wage_info_submit);
    }

    private void loadIntoSQLiteDatabase(){

        DatabaseManager databaseManager = new DatabaseManager(this,"respondent_family_details_beneficiary");
        databaseManager.setCreateTable("ID", "Major_Occu_Before","Subsidiary_Occu_Before","Major_Occu_After","Subsidiary_Occupation_After");//Sheet col: CU-CX
        databaseManager.open();
        databaseManager.insert(PERSON_KEY,majorBeforeTDF,minorBeforeTDF,majorAfterTDF,minorAfterTDF);
        Toast.makeText(this,"INSERTED",Toast.LENGTH_LONG).show();
        Log.i("INSERTED:",databaseManager.showDetails());

    }
}
