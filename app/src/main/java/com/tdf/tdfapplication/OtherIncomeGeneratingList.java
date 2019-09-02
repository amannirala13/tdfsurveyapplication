package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OtherIncomeGeneratingList extends AppCompatActivity {

    CheckBox[] checkBoxes;
    LinearLayout linearLayout;
    String[] incomeGeneratingActivities;
    Button button;
    boolean flag = false;
    private TextView textViewDurarionOfCredit;
    private EditText editTextDurationOfCredit,editTextOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_income_generating_list);

        initialize();

        checkBoxes[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBoxes[6].isChecked()) {
                    editTextDurationOfCredit.setVisibility(View.VISIBLE);
                    textViewDurarionOfCredit.setVisibility(View.VISIBLE);
                } else {
                    editTextDurationOfCredit.setVisibility(View.GONE);
                    textViewDurarionOfCredit.setVisibility(View.GONE);
                }
            }
        });

        checkBoxes[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBoxes[9].isChecked()){
                    editTextOther.setVisibility(View.VISIBLE);
                } else {
                    editTextOther.setVisibility(View.GONE);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int count = 0,countAsset = 0; count<10;count++){
                    if(checkBoxes[count].isChecked()){
                        flag = true;
                        incomeGeneratingActivities[countAsset] = checkBoxes[count].getText().toString();
                        countAsset = countAsset + 1;
                    }
                }

                if(flag) {
                    Intent intent = new Intent(OtherIncomeGeneratingList.this, OtherIncomeGeneratingActivity.class);
                    intent.putExtra("OTHER_INCOME_GENERATING_LIST", incomeGeneratingActivities);
                    startActivity(intent);
                }
            }
        });
    }

    private void initialize(){
        checkBoxes = new CheckBox[10];
        linearLayout = findViewById(R.id.linearLayout);
        incomeGeneratingActivities = new String[checkBoxes.length];
        button = findViewById(R.id.household_consumption_info_submit);
        editTextDurationOfCredit = findViewById(R.id.duration_of_credit_time);
        editTextOther = findViewById(R.id.other_list);
        textViewDurarionOfCredit = findViewById(R.id.duration_of_credit_time_label);

        for (int count = 1, count2 = 0; count <= 12; count++) {
            if(count != 8 && count != 9) {
                checkBoxes[count2] = (CheckBox) linearLayout.getChildAt(count);
                Log.i("id", checkBoxes[count2].getText().toString() + count);
                count2 = count2 + 1;
            }
        }
    }
}
