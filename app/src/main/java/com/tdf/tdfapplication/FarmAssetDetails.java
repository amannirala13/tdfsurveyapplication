package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FarmAssetDetails extends AppCompatActivity {

    private String[] textLabels;
    private TextView textViewfarmAssetLabel;
    private Button button;
    private EditText editTextIJBeforeTDF, editTextIJAfterTDF, editTextNoBeforeTDF, editTextNoAfterTDF, editTextCurrentValue;
    private String[] values;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_asset_details);
        initialize();
        Intent intent = getIntent();
        textLabels = intent.getStringArrayExtra("FARM_ASSET_LIST");
        textViewfarmAssetLabel.setText(textLabels[0]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < textLabels.length - 1 && textLabels[count + 1] != null) {
                    values[0] = editTextIJBeforeTDF.getText().toString();
                    values[1] = editTextNoBeforeTDF.getText().toString();
                    values[2] = editTextIJAfterTDF.getText().toString();
                    values[3] = editTextNoAfterTDF.getText().toString();
                    values[4] = editTextCurrentValue.getText().toString();

                    textViewfarmAssetLabel.setText(textLabels[count + 1]);
                    count = count + 1;
                    editTextIJAfterTDF.setText("");
                    editTextNoAfterTDF.setText("");
                    editTextIJBeforeTDF.setText("");
                    editTextNoBeforeTDF.setText("");
                    editTextCurrentValue.setText("");

                    StringBuilder logMessage = new StringBuilder();
                    for (String value : values) {
                        logMessage.append(value);
                        logMessage.append(" ");
                    }
                    Log.i("INFO", logMessage.toString());
                } else {
                    finish();
                }
            }
        });

    }

    private void initialize() {
        values = new String[5];

        textViewfarmAssetLabel = findViewById(R.id.item_label);
        button = findViewById(R.id.wage_info_submit);
        editTextIJAfterTDF = findViewById(R.id.number_of_days_after);
        editTextNoAfterTDF = findViewById(R.id.wage_value_after);
        editTextIJBeforeTDF = findViewById(R.id.wage_number_of_days);
        editTextNoBeforeTDF = findViewById(R.id.wage_value);
        editTextCurrentValue = findViewById(R.id.family_member_count);

    }
}