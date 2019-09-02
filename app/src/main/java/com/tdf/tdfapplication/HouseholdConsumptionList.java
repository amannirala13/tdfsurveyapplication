package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class HouseholdConsumptionList extends AppCompatActivity {

    CheckBox[] checkBoxes;
    LinearLayout linearLayout;
    String[] consumedHouseholdItems;
    Button button;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_consumption_list);

        initialize();

        for (int count = 1; count <= 20; count++) {
            checkBoxes[count-1] = (CheckBox)linearLayout.getChildAt(count);
            Log.i("id", checkBoxes[count - 1].getText().toString() + count);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int count = 0,countAsset = 0; count<20;count++){
                    if(checkBoxes[count].isChecked()){
                        flag = true;
                        consumedHouseholdItems[countAsset] = checkBoxes[count].getText().toString();
                        countAsset = countAsset + 1;
                    }
                }

                if(flag) {
                    Intent intent = new Intent(HouseholdConsumptionList.this, HouseholdConsumptionActivity.class);
                    intent.putExtra("HOUSEHOLD_CONSUMPTION_LIST", consumedHouseholdItems);
                    startActivity(intent);
                }
            }
        });
    }

    private void initialize(){
        checkBoxes = new CheckBox[20];
        linearLayout = findViewById(R.id.linearLayout);
        consumedHouseholdItems = new String[checkBoxes.length];
        button = findViewById(R.id.household_consumption_info_submit);
    }
}
