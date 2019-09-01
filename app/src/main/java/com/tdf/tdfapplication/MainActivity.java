package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tdf.tdfapplication.utils.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Spinner spinner;
    private String code;
    private String name;
    private String villageCode;
    private EditText editTextCode;
    private EditText editTextName;
    private String[] villageList;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                villageCode = Integer.toString(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                villageCode = "1";
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTextCode.getText().toString();
                name = editTextName.getText().toString();
                Log.i("INFO", code + " " + name + " " + villageCode +
                        villageList[Integer.parseInt(villageCode) - 1]
                                .toUpperCase()
                                .substring(0, 2) +
                        name.toUpperCase().
                                substring(0, 2));

                databaseManager.setCreateTable("surveyor_id","surveyor_name","village");
                databaseManager.open();
                databaseManager.insert(code,name,villageCode);
                Intent intent = new Intent(MainActivity.this, SectionActivity.class);

                intent.putExtra("PERSON_KEY", villageList[Integer.parseInt(villageCode) - 1]
                        .toUpperCase()
                        .substring(0, 2) +
                        name.toUpperCase().substring(0, 2));
                startActivity(intent);

            }
        });
    }

    private void initialize() {
        spinner = findViewById(R.id.village_list);
        editTextCode = findViewById(R.id.investigator_code);
        editTextName = findViewById(R.id.investigator_name);
        button = findViewById(R.id.surveyor_submit);
        villageList = getResources().getStringArray(R.array.village_list);
        databaseManager = new DatabaseManager(this,"surveyor");

        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.village_list, android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);

    }
}

