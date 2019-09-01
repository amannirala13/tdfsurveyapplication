package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IrrigationFacilityActivity extends AppCompatActivity {

    private EditText editTextLandValueBeforeTDF, editTextLandValueAfterTDF;
    private EditText editTextLandCurrenttValue;
    private Button submit;
    private String landValueBeforeTDF, landValueAfterTDF, landCurrentValue;
    private String landOwnedBeforeTDF, landOwnedAfterTDF;
    private int count = 0;
    private String[] landHoldingList;
    private TextView textViewLandHoldingLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation_facility);

        initialize();
        Intent intent = getIntent();
        landHoldingList = intent.getStringArrayExtra("LAND_HOLDING_LIST");
        textViewLandHoldingLabel.setText(landHoldingList[0]);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < landHoldingList.length -1 && landHoldingList[count] != null) {
                    textViewLandHoldingLabel.setText(landHoldingList[count]);
                    landValueBeforeTDF = editTextLandValueBeforeTDF.getText().toString();
                    landValueAfterTDF = editTextLandValueAfterTDF.getText().toString();
                    landCurrentValue = editTextLandCurrenttValue.getText().toString();
                    landOwnedAfterTDF = "1";
                    landOwnedBeforeTDF = "1";

                    if (landValueBeforeTDF.equals("")) {
                        landOwnedBeforeTDF = "0";
                        landValueBeforeTDF = "666";
                    }
                    if (landValueAfterTDF.equals("")) {
                        landOwnedAfterTDF = "0";
                        landValueAfterTDF = "666";
                    }
                    if (landCurrentValue.equals("")) {
                        landCurrentValue = "666";
                    }

                    Log.i("INFO", PersonalDetailsActivity.PERSON_KEY + ","
                            + landValueAfterTDF + landOwnedAfterTDF
                            + landValueBeforeTDF + landOwnedBeforeTDF
                            + landCurrentValue);

                    editTextLandCurrenttValue.setText("");
                    editTextLandValueAfterTDF.setText("");
                    editTextLandValueBeforeTDF.setText("");
                    count = count + 1;
                } else {
                    finish();
                }
            }
        });
    }

    private void initialize() {
        editTextLandValueBeforeTDF = findViewById(R.id.land_value_unit);
        editTextLandValueAfterTDF = findViewById(R.id.land_value_unit_after);
        editTextLandCurrenttValue = findViewById(R.id.land_value_price);
        textViewLandHoldingLabel = findViewById(R.id.land_holding_label);

        landValueAfterTDF = "666";
        landValueBeforeTDF = "666";
        landCurrentValue = "666";

        landOwnedBeforeTDF = "0";
        landOwnedAfterTDF = "0";
        submit = findViewById(R.id.irrigation_info_submit);
    }
}
