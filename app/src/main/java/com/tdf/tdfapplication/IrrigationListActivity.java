package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class IrrigationListActivity extends AppCompatActivity {

    private CheckBox[] checkBoxes;
    private LinearLayout linearLayout;
    private Button button;
    private String[] landHoldingList;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation_list);

        initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int count = 0, count2 = 0; count<8; count++){
                    if(checkBoxes[count].isChecked()){
                        flag = true;
                        landHoldingList[count2] = checkBoxes[count].getText().toString();
                        count2 = count2 + 1;
                    }
                }

                if(flag) {
                    Intent intent = new Intent(IrrigationListActivity.this, IrrigationFacilityActivity.class);
                    intent.putExtra("LAND_HOLDING_LIST", landHoldingList);
                    startActivity(intent);
                }
            }
        });
    }

    private void initialize(){
        checkBoxes = new CheckBox[8];
        landHoldingList = new String[checkBoxes.length];
        linearLayout = findViewById(R.id.linearLayout);
        button = findViewById(R.id.irrigation_list_info_submit);

        for (int count = 1; count <= 8; count++) {
            checkBoxes[count-1] = (CheckBox)linearLayout.getChildAt(count);
            Log.i("id", checkBoxes[count - 1].getText().toString() + count);
        }


    }
}
