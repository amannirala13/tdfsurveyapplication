package com.tdf.tdfapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IrrigationFacilityActivity extends AppCompatActivity {

    private EditText editTextLandValueBeforeTDF, editTextLandValueAfterTDF;
    private EditText editTextLandCurrenttValue;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation_facility);

        initialize();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String landValueBeforeTDF = editTextLandValueBeforeTDF.getText().toString();
                String landValueAfterTDF = editTextLandValueAfterTDF.getText().toString();
                String landCurrentValue = editTextLandCurrenttValue.getText().toString();

                Log.i("INFO", PersonalDetailsActivity.PERSON_KEY + ","
                        + landValueAfterTDF + ","
                        + landValueBeforeTDF + ","
                        + landCurrentValue);

                finish();
            }
        });
    }

    private void initialize() {
        editTextLandValueBeforeTDF = findViewById(R.id.land_value_unit);
        editTextLandValueAfterTDF = findViewById(R.id.land_value_unit_after);
        editTextLandCurrenttValue = findViewById(R.id.land_value_price);

        submit = findViewById(R.id.irrigation_info_submit);
    }
}
