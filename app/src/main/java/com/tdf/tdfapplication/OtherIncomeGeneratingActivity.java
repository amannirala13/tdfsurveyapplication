package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tdf.tdfapplication.model.RespondentIncomeGeneration;

public class OtherIncomeGeneratingActivity extends AppCompatActivity {

    private EditText editTextEarningsBeforeTDF, editTextEarningsAfterTDF;
    private EditText editTextPaidOutCostsBeforeTDF, editTextPaidOutCostsAfterTDF;
    private TextView textViewItemLabel;
    private TextView textViewPaidOutCostsBeforeTDF, textViewPaidOutCostsAfterTDF;
    private Button button;
    private RespondentIncomeGeneration respondentIncomeGenerationBeforeTDF,
            respondentIncomeGenerationAfterTDF;
    private String[] incomeGeneratorItems, otherIncomeGenerators;
    private int count = 0;
    private int countForOther = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_income_generating);

        initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(editTextEarningsBeforeTDF.getText().toString().equals(""))) {
                    respondentIncomeGenerationBeforeTDF
                            .setEarnings(editTextEarningsBeforeTDF
                                    .getText()
                                    .toString());
                }

                if (!(editTextPaidOutCostsBeforeTDF.getText().toString().equals(""))) {
                    respondentIncomeGenerationBeforeTDF
                            .setPaidOutCosts(editTextPaidOutCostsBeforeTDF
                                    .getText()
                                    .toString());
                }

                if (!(editTextEarningsAfterTDF.getText().toString().equals(""))) {
                    respondentIncomeGenerationAfterTDF
                            .setEarnings(editTextEarningsAfterTDF
                                    .getText()
                                    .toString());
                }

                if (!(editTextPaidOutCostsAfterTDF.getText().toString().equals(""))) {
                    respondentIncomeGenerationAfterTDF
                            .setPaidOutCosts(editTextPaidOutCostsAfterTDF
                                    .getText()
                                    .toString());
                }

                Log.i("INFO",
                        respondentIncomeGenerationBeforeTDF.getEarnings() +
                                respondentIncomeGenerationBeforeTDF.getPaidOutCosts());
                Log.i("INFO",
                        respondentIncomeGenerationAfterTDF.getEarnings() +
                                respondentIncomeGenerationAfterTDF.getPaidOutCosts());

                if (count < incomeGeneratorItems.length - 2
                        && !(incomeGeneratorItems[count + 1].equals("Other"))) {
                    count = count + 1;
                    textViewItemLabel.setText(incomeGeneratorItems[count]);
                    editTextEarningsBeforeTDF.setText("");
                    editTextEarningsAfterTDF.setText("");
                    editTextPaidOutCostsAfterTDF.setText("");
                    editTextPaidOutCostsBeforeTDF.setText("");

                    if (incomeGeneratorItems[count].equals("Agricultural Label")
                            || incomeGeneratorItems[count].equals("Non-Agricultural Label")) {
                        textViewPaidOutCostsAfterTDF.setVisibility(View.GONE);
                        textViewPaidOutCostsBeforeTDF.setVisibility(View.GONE);
                        editTextPaidOutCostsBeforeTDF.setVisibility(View.GONE);
                        editTextPaidOutCostsAfterTDF.setVisibility(View.GONE);
                    } else {
                        textViewPaidOutCostsAfterTDF.setVisibility(View.VISIBLE);
                        textViewPaidOutCostsBeforeTDF.setVisibility(View.VISIBLE);
                        editTextPaidOutCostsBeforeTDF.setVisibility(View.VISIBLE);
                        editTextPaidOutCostsAfterTDF.setVisibility(View.VISIBLE);
                    }

                } else if ((!otherIncomeGenerators[0].equals(""))
                        && (countForOther < otherIncomeGenerators.length)) {
                    textViewPaidOutCostsAfterTDF.setVisibility(View.VISIBLE);
                    textViewPaidOutCostsBeforeTDF.setVisibility(View.VISIBLE);
                    editTextPaidOutCostsBeforeTDF.setVisibility(View.VISIBLE);
                    editTextPaidOutCostsAfterTDF.setVisibility(View.VISIBLE);

                    editTextEarningsBeforeTDF.setText("");
                    editTextEarningsAfterTDF.setText("");
                    editTextPaidOutCostsAfterTDF.setText("");
                    editTextPaidOutCostsBeforeTDF.setText("");

                    textViewItemLabel.setText(otherIncomeGenerators[countForOther]);
                    countForOther = countForOther + 1;
                } else {
                    finish();
                }
            }
        });
    }

    private void initialize() {
        editTextEarningsAfterTDF = findViewById(R.id.earning_from_activities_after);
        editTextEarningsBeforeTDF = findViewById(R.id.earning_from_activities);
        editTextPaidOutCostsAfterTDF = findViewById(R.id.paid_out_costs_after);
        editTextPaidOutCostsBeforeTDF = findViewById(R.id.paid_out_costs);
        textViewItemLabel = findViewById(R.id.item_label);
        textViewPaidOutCostsAfterTDF = findViewById(R.id.paid_out_costs_label_after);
        textViewPaidOutCostsBeforeTDF = findViewById(R.id.paid_out_costs_label);

        button = findViewById(R.id.other_income_generating_activities_info_submit);

        respondentIncomeGenerationAfterTDF = new RespondentIncomeGeneration();
        respondentIncomeGenerationBeforeTDF = new RespondentIncomeGeneration();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("OTHER_INCOME_GENERATOR_BUNDLE");
        incomeGeneratorItems = bundle.getStringArray("OTHER_INCOME_GENERATOR_LIST");

        if (bundle.containsKey("OTHER_INCOME_LIST")) {
            otherIncomeGenerators = bundle.getStringArray("OTHER_INCOME_LIST");
            if (!(incomeGeneratorItems[0].equals("Other"))) {
                textViewItemLabel.setText(incomeGeneratorItems[0]);
            } else {
                textViewItemLabel.setText(otherIncomeGenerators[countForOther]);
                countForOther = countForOther + 1;
            }
        } else {
            textViewItemLabel.setText(incomeGeneratorItems[0]);
        }
        if (incomeGeneratorItems[0].equals("Agricultural Label")
                || incomeGeneratorItems[0].equals("Non-Agricultural Label")) {
            textViewPaidOutCostsAfterTDF.setVisibility(View.GONE);
            textViewPaidOutCostsBeforeTDF.setVisibility(View.GONE);
            editTextPaidOutCostsBeforeTDF.setVisibility(View.GONE);
            editTextPaidOutCostsAfterTDF.setVisibility(View.GONE);
        } else {
            textViewPaidOutCostsAfterTDF.setVisibility(View.VISIBLE);
            textViewPaidOutCostsBeforeTDF.setVisibility(View.VISIBLE);
            editTextPaidOutCostsBeforeTDF.setVisibility(View.VISIBLE);
            editTextPaidOutCostsAfterTDF.setVisibility(View.VISIBLE);
        }
    }
}
