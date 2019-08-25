package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        editTextAfterTDFMajor = findViewById(R.id.family_member_occupation_after);
        editTextAfterTDFMinor = findViewById(R.id.family_member_income_after);
        editTextBeforeTDFMajor = findViewById(R.id.family_member_occupation);
        editTextBeforeTDFMinor = findViewById(R.id.family_member_income);
        editTextNumberOfMembers = findViewById(R.id.family_member_count);
        button = findViewById(R.id.family_info_submit);
    }
}
