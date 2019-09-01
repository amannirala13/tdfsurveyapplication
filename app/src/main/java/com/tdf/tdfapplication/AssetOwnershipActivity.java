package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AssetOwnershipActivity extends AppCompatActivity {

    TextView textViewFarm, textViewOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_ownership);

        textViewFarm = findViewById(R.id.farm_asset_label);
        textViewOther = findViewById(R.id.other_asset_label);

        textViewFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetOwnershipActivity.this, FarmActivity.class);
                startActivity(intent);
            }
        });

        textViewOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssetOwnershipActivity.this, OtherActivityList.class);
                startActivity(intent);
            }
        });
    }
}
