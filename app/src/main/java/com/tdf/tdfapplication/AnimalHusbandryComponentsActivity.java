package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalHusbandryComponentsActivity extends AppCompatActivity {

    private String[] animalHusbandryComponents;
    private String[] otherAnimalHusbandryComponents;
    private int count = 0;
    private int countForOther = -1;
    private EditText editTextConsumptionBeforeTDF, editTextConsumptionAfterTDF;
    private EditText editTextSellBeforeTDF, editTextSellAfterTDF;
    private Button button;
    private TextView textViewAnimalHusbandryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_husbandry_components);

        initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String consumptionBeforeTDF = editTextConsumptionBeforeTDF.getText().toString();
                String consumptionAfterTDF = editTextConsumptionAfterTDF.getText().toString();
                String sellBeforeTDF = editTextSellBeforeTDF.getText().toString();
                String sellAfterTDF = editTextSellAfterTDF.getText().toString();
                Log.i("INFO", consumptionBeforeTDF + consumptionAfterTDF + sellBeforeTDF + sellAfterTDF);

                if (count < animalHusbandryComponents.length - 2) {
                    editTextConsumptionBeforeTDF.setText("");
                    editTextConsumptionAfterTDF.setText("");
                    editTextSellBeforeTDF.setText("");
                    editTextSellAfterTDF.setText("");

                    count = count + 1;
                    textViewAnimalHusbandryLabel.setText(animalHusbandryComponents[count]);
                } else if ((!otherAnimalHusbandryComponents[0].equals(""))&& (countForOther < otherAnimalHusbandryComponents.length - 1) ){
                    editTextConsumptionBeforeTDF.setText("");
                    editTextConsumptionAfterTDF.setText("");
                    editTextSellBeforeTDF.setText("");
                    editTextSellAfterTDF.setText("");
                    countForOther = countForOther + 1;
                    Toast.makeText(AnimalHusbandryComponentsActivity.this, otherAnimalHusbandryComponents[countForOther], Toast.LENGTH_SHORT).show();
                    textViewAnimalHusbandryLabel.setText(otherAnimalHusbandryComponents[countForOther]);
                } else {
                    finish();
                }
            }
        });
    }

    private void initialize() {
        textViewAnimalHusbandryLabel = findViewById(R.id.item_label);
        button = findViewById(R.id.animal_husbandry_component_info_submit);
        editTextConsumptionAfterTDF = findViewById(R.id.for_consumption_after);
        editTextConsumptionBeforeTDF = findViewById(R.id.for_consumption);
        editTextSellBeforeTDF = findViewById(R.id.for_sell);
        editTextSellAfterTDF = findViewById(R.id.for_sell_after);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ANIMAL_HUSBANDRY_BUNDLE");

        animalHusbandryComponents = bundle.getStringArray("ANIMAL_HUSBANDRY_LIST");
        Toast.makeText(this, "Wahtsup?" + Integer.toString(animalHusbandryComponents.length), Toast.LENGTH_SHORT).show();

        if (bundle.containsKey("OTHER_ANIMAL_HUSBANDRY")) {
            otherAnimalHusbandryComponents = bundle.getString("OTHER_ANIMAL_HUSBANDRY").split(",");
        }


        textViewAnimalHusbandryLabel.setText(animalHusbandryComponents[0]);
    }

}
