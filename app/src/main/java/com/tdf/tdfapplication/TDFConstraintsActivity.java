package com.tdf.tdfapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class TDFConstraintsActivity extends AppCompatActivity {
CheckBox chk[] = new CheckBox[20];
LinearLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdfconstraints);
        parent = findViewById(R.id.linearLayoutchk);
        for(int i = 1; i<=20;i++){
            CheckBox tempCheckBox =((CheckBox)parent.getChildAt(i));
            if( tempCheckBox.getTag().equals("other_asset_item_"+i)){
                chk[i-1] = tempCheckBox;
                Log.i("id",chk[i-1].getTag().toString());
            }
        }

    }
}
