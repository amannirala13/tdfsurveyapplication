package com.tdf.tdfapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

public class ProgrammeImplementingAgencyActivity extends AppCompatActivity {

    private Map<Spinner, String> spinnerPIAAnswers;
    private EditText editTextPIAName, editTextPIAActivities, editTextAdditionalRemarks;
    private EditText editTextGovtProgrammeAwareness, editTextPIAOtherActivities;
    private Map<RadioGroup, String> radioGroupPIA;
    private String[] spinnerRating, spinnerDuration;
    private LinearLayout linearLayout;
    private Button button;
    private String piaName, piaActivities, piaAdditionalRemarks;
    private String govtAwarenessProgrammes, piaOtherActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme_implementing_agency);

        initialize();

        for (final Spinner spinner : spinnerPIAAnswers.keySet()) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerPIAAnswers.put(spinner, Integer.toString(i + 1));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    spinnerPIAAnswers.put(spinner, "1");
                }
            });
        }

        for (RadioGroup radioGroup : radioGroupPIA.keySet()) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    if (radioButton.getText().toString().equals("Yes")) {
                        radioGroupPIA.put(radioGroup, "1");
                    } else {
                        radioGroupPIA.put(radioGroup, "2");
                    }

                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextPIAName.getText().toString().equals("")) {
                    piaName = "666";
                } else {
                    piaName = editTextPIAName.getText().toString();
                }

                if (editTextPIAActivities.getText().toString().equals("")) {
                    piaActivities = "666";
                } else {
                    piaActivities = editTextPIAActivities.getText().toString();
                }

                if (editTextGovtProgrammeAwareness.getText().toString().equals("")) {
                    govtAwarenessProgrammes = "666";
                } else {
                    govtAwarenessProgrammes = editTextGovtProgrammeAwareness.getText().toString();
                }

                if (editTextPIAOtherActivities.getText().toString().equals("")) {
                    piaOtherActivities = "666";
                } else {
                    piaOtherActivities = editTextPIAOtherActivities.getText().toString();
                }

                if (editTextAdditionalRemarks.getText().toString().equals("")) {
                    piaAdditionalRemarks = "666";
                } else {
                    piaAdditionalRemarks = editTextAdditionalRemarks.getText().toString();
                }


                for (String string : spinnerPIAAnswers.values()) {
                    Log.i("INFO", string);
                }

                for(String string: radioGroupPIA.values()){
                    Log.i("INFO",string);
                }

                Log.i("INFO", piaName + piaActivities + piaOtherActivities + piaAdditionalRemarks + govtAwarenessProgrammes);
                finish();
            }
        });
    }

    private void initialize() {
        linearLayout = findViewById(R.id.linearLayout);
        button = findViewById(R.id.pia_info_submit);

        radioGroupPIA = new HashMap<>();
        radioGroupPIA.put((RadioGroup) findViewById(R.id.answer_2), "0");
        radioGroupPIA.put((RadioGroup) findViewById(R.id.answer_19), "0");
        radioGroupPIA.put((RadioGroup) findViewById(R.id.answer_21), "0");

        editTextPIAName = findViewById(R.id.answer_1);
        editTextPIAActivities = findViewById(R.id.answer_3);
        editTextGovtProgrammeAwareness = findViewById(R.id.answer_18);
        editTextPIAOtherActivities = findViewById(R.id.answer_20);
        editTextAdditionalRemarks = findViewById(R.id.answer_22);


        spinnerRating = new String[]{"Strongly Agree", "Agree", "Disagree", "Strongly Disagree"};
        spinnerDuration = new String[]{"Weekly", "In 15 days", "Monthy", "Other"};
        spinnerPIAAnswers = new HashMap<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerRating);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerDuration);

        for (int count = 8; count < linearLayout.getChildCount() - 11; count += 2) {
            Spinner spinner = (Spinner) linearLayout.getChildAt(count);

            if (!(count == 8 || count == 40)) {
                spinner.setAdapter(adapter);
            } else {
                spinner.setAdapter(adapter1);
            }

            spinnerPIAAnswers.put((Spinner) linearLayout.getChildAt(count), "1");
        }

        for (int count = 10; count < linearLayout.getChildCount() - 13; count += 2) {
            Log.i("INFO", linearLayout.getChildAt(count).toString());

        }
    }

}
