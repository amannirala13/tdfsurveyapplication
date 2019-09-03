package com.tdf.tdfapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class CommunityInstitutionsActivity extends AppCompatActivity {


    private Map<RadioGroup, String> radioGroupCommunityAnswers;
    private EditText[] editTextCommunityAnswers;
    private TextView[] textViewCommunitySubQuestions;
    private String[] answerForEditTexts;
    private LinearLayout linearLayout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_institutions);

        initialize();


        for (RadioGroup radioGroup : radioGroupCommunityAnswers.keySet()) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    boolean flag = false;
                    if(radioGroup.getId() == R.id.answer_11){
                        flag = true;
                    }
                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    if (radioButton.getText().toString().equals("Yes")) {
                        radioGroupCommunityAnswers.put(radioGroup, "1");
                        Log.i("INFO", radioGroupCommunityAnswers.get(radioGroup));

                        if(flag){
                            for (int count = 0; count < textViewCommunitySubQuestions.length; count++) {
                                textViewCommunitySubQuestions[count].setVisibility(View.VISIBLE);
                                editTextCommunityAnswers[count].setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        for (int count = 0; count < textViewCommunitySubQuestions.length; count++) {
                            textViewCommunitySubQuestions[count].setVisibility(View.GONE);
                            editTextCommunityAnswers[count].setVisibility(View.GONE);
                        }
                    }
                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int count = 0; count < editTextCommunityAnswers.length; count++) {
                    if (!(editTextCommunityAnswers[count].getText().toString().equals(""))) {
                        answerForEditTexts[count] = editTextCommunityAnswers[count].getText().toString();
                    }
                }

                for (int i = 0; i < editTextCommunityAnswers.length; i++) {
                    Log.i("INFO", answerForEditTexts[i]);
                }
            }
        });


    }

    private void initialize() {
        radioGroupCommunityAnswers = new HashMap<RadioGroup, String>();

        linearLayout = findViewById(R.id.linearLayout);
        editTextCommunityAnswers = new EditText[9];
        answerForEditTexts = new String[editTextCommunityAnswers.length];
        button = findViewById(R.id.community_institutions_info_submit);
        textViewCommunitySubQuestions = new TextView[9];

        for (int count = 2, index = 0; count <= 23; count += 2, index++) {
            Log.i("INFO", linearLayout.getChildAt(count).toString());
            radioGroupCommunityAnswers.put((RadioGroup) linearLayout.getChildAt(count), "0");
        }

        for (int count = 24, index = 0; count < linearLayout.getChildCount(); count += 2, index++) {
            Log.i("INFO", linearLayout.getChildAt(count).toString());
            editTextCommunityAnswers[index] = (EditText) linearLayout.getChildAt(count);
            answerForEditTexts[index] = "666";
            editTextCommunityAnswers[index].setVisibility(View.GONE);
        }

        for (int count = 23, index = 0; count < linearLayout.getChildCount() - 1; count += 2, index++) {
            textViewCommunitySubQuestions[index] = (TextView) linearLayout.getChildAt(count);
            textViewCommunitySubQuestions[index].setVisibility(View.GONE);
        }
    }

}
