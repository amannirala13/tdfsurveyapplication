package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class FarmActivity extends AppCompatActivity {

    CheckBox[] checkBoxes;
    LinearLayout linearLayout;
    String[] farmAssets;
    Button button;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        initialize();

        for (int count = 1; count <= 15; count++) {
            checkBoxes[count-1] = (CheckBox)linearLayout.getChildAt(count);
            Log.i("id", checkBoxes[count - 1].getText().toString() + count);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int count = 0,countAsset = 0; count<15;count++){
                    if(checkBoxes[count].isChecked()){
                        flag = true;
                        farmAssets[countAsset] = checkBoxes[count].getText().toString();
                        countAsset = countAsset + 1;
                    }
                }

                if(flag) {
                    Intent intent = new Intent(FarmActivity.this, FarmAssetDetails.class);
                    intent.putExtra("FARM_ASSET_LIST", farmAssets);
                    startActivity(intent);
                }
            }
        });
    }

    private void initialize() {
        checkBoxes = new CheckBox[15];
        linearLayout = findViewById(R.id.linearLayout);
        farmAssets = new String[checkBoxes.length];
        button = findViewById(R.id.farm_asset_submit);
    }
}
