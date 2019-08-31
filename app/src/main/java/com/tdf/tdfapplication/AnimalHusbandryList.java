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

import java.util.Arrays;

public class AnimalHusbandryList extends AppCompatActivity {

    private CheckBox[] checkBoxesAnimalHusbandry;
    private LinearLayout linearLayout;
    private EditText editTextOtherAnimalHusbandry;
    private String otherAnimalHusbandry = "";
    private String[] animalHusbandry;
    private Button button;
    private boolean flagForOther = true;
    private Bundle bundle;
    private int count = 0, countAsset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_husbandry_list);

        initialize();

        checkBoxesAnimalHusbandry[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxesAnimalHusbandry[7].isChecked()) {
                    editTextOtherAnimalHusbandry.setVisibility(View.VISIBLE);
                    flagForOther = true;
                } else {
                    editTextOtherAnimalHusbandry.setVisibility(View.GONE);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = false;

                for (count = 0, countAsset = 0; count < 7; count++) {
                    if (checkBoxesAnimalHusbandry[count].isChecked()) {
                        flag = true;
                        animalHusbandry[countAsset] = checkBoxesAnimalHusbandry[count].getText().toString();
                        countAsset = countAsset + 1;
                    }
                }

                if (flagForOther) {
                    otherAnimalHusbandry = editTextOtherAnimalHusbandry.getText().toString();
                    bundle.putString("OTHER_ANIMAL_HUSBANDRY", otherAnimalHusbandry);
                }
                if (flag) {
                    Intent intent = new Intent(AnimalHusbandryList.this, AnimalHusbandryComponentsActivity.class);
                    bundle.putStringArray("ANIMAL_HUSBANDRY_LIST", Arrays.copyOfRange(animalHusbandry, 0, countAsset + 1));
                    intent.putExtra("ANIMAL_HUSBANDRY_BUNDLE", bundle);
                    startActivity(intent);
                }
            }
        });

    }

    private void initialize() {
        linearLayout = findViewById(R.id.linearLayout);
        checkBoxesAnimalHusbandry = new CheckBox[8];
        editTextOtherAnimalHusbandry = findViewById(R.id.animal_husbandry_other_list);
        animalHusbandry = new String[checkBoxesAnimalHusbandry.length];
        button = findViewById(R.id.animal_husbandry_info_submit);
        bundle = new Bundle();

        for (int count = 1; count <= 8; count++) {
            checkBoxesAnimalHusbandry[count - 1] = (CheckBox) linearLayout.getChildAt(count);
            Log.i("id", checkBoxesAnimalHusbandry[count - 1].getText().toString() + count);
        }
    }
}
