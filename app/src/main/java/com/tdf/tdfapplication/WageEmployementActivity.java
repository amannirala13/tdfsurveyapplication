package com.tdf.tdfapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WageEmployementActivity extends AppCompatActivity {

    private EditText editTextWageNumberOfDaysBeforeTDF, editTextWageValueBeforeTDF;
    private EditText editTextWageNumberOfDaysAfterTDF, editTextValueAfterTDF;
    private EditText editTextComments;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wage_employement);
        initialize();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberOfDaysBeforeTDF = editTextWageNumberOfDaysBeforeTDF.getText().toString();
                String valueBeforeTDF = editTextWageValueBeforeTDF.getText().toString();
                String numberOfDaysAfterTDF = editTextWageNumberOfDaysAfterTDF.getText().toString();
                String valueAfterTDF = editTextValueAfterTDF.getText().toString();
                String comments = editTextComments.getText().toString();
                Log.i("INFO:", PersonalDetailsActivity.PERSON_KEY + "," + numberOfDaysBeforeTDF
                        + "," + valueBeforeTDF + "," + numberOfDaysAfterTDF + "," + valueAfterTDF + "," + comments);
            }
        });
    }

    private void initialize() {
        editTextWageNumberOfDaysBeforeTDF = findViewById(R.id.wage_number_of_days);
        editTextWageNumberOfDaysAfterTDF = findViewById(R.id.number_of_days_after);
        editTextWageValueBeforeTDF = findViewById(R.id.wage_value);
        editTextValueAfterTDF = findViewById(R.id.wage_value_after);
        editTextComments = findViewById(R.id.wage_comments);

        submit = findViewById(R.id.wage_info_submit);
    }
}
