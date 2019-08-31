package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtherAssetActivity extends AppCompatActivity {


    private EditText editTextBeforeTDF, editTextAfterTDF, editTextCurrentValue;
    private TextView textViewLifeStock;
    private Button button;
    private String[] labels;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_asset);

        initialize();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < labels.length -1 && labels[count+1]!=null){
                    String lifeStockBeforeTDFValue = editTextBeforeTDF.getText().toString();
                    String lifeStockAfterTDFValue = editTextAfterTDF.getText().toString();
                    String lifeStockCurrentValue = editTextCurrentValue.getText().toString();

                    Log.i("INFO",lifeStockAfterTDFValue + "," + lifeStockBeforeTDFValue + "" + lifeStockCurrentValue);
                } else {
                    finish();
                }
            }
        });
    }

    private void initialize() {
        editTextBeforeTDF = findViewById(R.id.other_before_tdf_value);
        editTextAfterTDF = findViewById(R.id.other_after_tdf_value);
        editTextCurrentValue = findViewById(R.id.other_current_value_price);
        textViewLifeStock = findViewById(R.id.item_label);
        button = findViewById(R.id.other_submit);
        labels = new String[18];

        Intent intent = getIntent();
        labels = intent.getStringArrayExtra("OTHER_ASSET_LIST");
        textViewLifeStock.setText(labels[0]);
    }
}
