package com.tdf.tdfapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TDFConstraintsActivity extends AppCompatActivity {
CheckBox chk[] = new CheckBox[20];
LinearLayout parent;
Button btn;
EditText other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdfconstraints);
       //-----------Initialization-------
        parent = findViewById(R.id.linearLayoutchk);
        for(int i = 1; i<=parent.getChildCount();i++){
            View tempCheckBox =(parent.getChildAt(i));
            if(tempCheckBox instanceof CheckBox ){
                chk[i-1] =(CheckBox) tempCheckBox;
                Log.i("id",Integer.toString(chk[i-1].getId()));
                Toast.makeText(this,"id: "+(i-1)+" "+chk[i-1].getText().toString(),Toast.LENGTH_LONG).show();
            }
        other = findViewById(R.id.other_constraint);
        btn = findViewById(R.id.other_asset_submit);

        //------------Operations

        other.setVisibility(View.GONE);

       /* chk[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chk[19].isChecked()){
                    other.setVisibility(View.VISIBLE);
                }
                else {
                    other.setVisibility(View.GONE);
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0;i<chk.length-1;i++){
                    if(chk[i].isChecked()){
                        Log.i("id",Integer.toString(chk[i-1].getId()));
                    }
                    if (chk[19].isChecked()){
                        Log.i("Other ",other.getText().toString());
                    }
                }
            }
        });*/
        }


    }
}
