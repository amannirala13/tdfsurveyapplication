package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class OtherIncomeGeneratingList extends AppCompatActivity {

    private CheckBox[] checkBoxes;
    private LinearLayout linearLayout;
    private String[] incomeGeneratingActivities;
    private Button button;
    private Bundle bundle;
    private boolean flag = false;
    private boolean flagForOther = false;
    private boolean flagForDuration = false;
    private int count = 0, countAsset = 0;

    private TextView textViewDurarionOfCredit;
    private EditText editTextDurationOfCredit, editTextOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_income_generating_list);

        initialize();

        checkBoxes[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxes[6].isChecked()) {
                    flagForDuration = true;
                    editTextDurationOfCredit.setVisibility(View.VISIBLE);
                    textViewDurarionOfCredit.setVisibility(View.VISIBLE);
                } else {
                    flagForDuration = false;
                    editTextDurationOfCredit.setVisibility(View.GONE);
                    textViewDurarionOfCredit.setVisibility(View.GONE);
                }
            }
        });

        checkBoxes[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxes[9].isChecked()) {
                    editTextOther.setVisibility(View.VISIBLE);
                    flagForOther = true;
                } else {
                    flagForOther = false;
                    editTextOther.setVisibility(View.GONE);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (count = 0, countAsset = 0; count < 10; count++) {
                    if (checkBoxes[count].isChecked()) {
                        flag = true;
                        incomeGeneratingActivities[countAsset] = checkBoxes[count].getText().toString();
                        countAsset = countAsset + 1;
                    }
                }

                if (flagForDuration) {
                    bundle.putString("DURATION_OF_CREDIT", editTextDurationOfCredit.getText().toString());
                }

                if (flagForOther) {
                    String otherIncomeList = editTextOther.getText().toString();
                    Log.i("INFO", otherIncomeList);
                    bundle.putStringArray("OTHER_INCOME_LIST", otherIncomeList.split(","));
                }

                if (flag) {
                    Intent intent = new Intent(OtherIncomeGeneratingList.this, OtherIncomeGeneratingActivity.class);
                    bundle.putStringArray("OTHER_INCOME_GENERATOR_LIST", Arrays.copyOfRange(incomeGeneratingActivities, 0, countAsset + 1));
                    intent.putExtra("OTHER_INCOME_GENERATOR_BUNDLE", bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void initialize() {
        checkBoxes = new CheckBox[10];
        linearLayout = findViewById(R.id.linearLayout);
        incomeGeneratingActivities = new String[checkBoxes.length];
        button = findViewById(R.id.household_consumption_info_submit);
        editTextDurationOfCredit = findViewById(R.id.duration_of_credit_time);
        editTextOther = findViewById(R.id.other_list);
        textViewDurarionOfCredit = findViewById(R.id.duration_of_credit_time_label);

        bundle = new Bundle();

        for (int count = 1, count2 = 0; count <= 12; count++) {
            if (count != 8 && count != 9) {
                checkBoxes[count2] = (CheckBox) linearLayout.getChildAt(count);
                Log.i("id", checkBoxes[count2].getText().toString() + count);
                count2 = count2 + 1;
            }
        }
    }
}
