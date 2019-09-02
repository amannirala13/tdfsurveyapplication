package com.tdf.tdfapplication;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/*import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
*/
import com.tdf.tdfapplication.utils.DatabaseManager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/*
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions;
*/
public class MainActivity extends AppCompatActivity{

//    private GoogleAccountCredential mCredential;
//
//    //constant initialization
//    static final int REQUEST_ACCOUNT_PICKER = 1000;
//    static final int REQUEST_AUTHORIZATION = 1001;
//    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
//    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
//
//
//    //constant declaration
//    private static final String PREF_ACCOUNT_NAME = "accountName";
//
//    private static final String[] SCOPES = {SheetsScopes.SPREADSHEETS};


    private Button button;
    private Spinner spinner;
    private String code;
    private String name;
    private ProgressDialog mProgress;
    private String villageCode;
    private EditText editTextCode;
    private EditText editTextName;
    private String[] villageList;
    private DatabaseManager dbManager;

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
//                loadToSQLiteDatabase();
                Log.i("INFO", code + " " + name + " " + villageCode + villageList[Integer.parseInt(villageCode) - 1].toUpperCase().substring(0, 2) + name.toUpperCase().substring(0, 2));

                Intent intent = new Intent(MainActivity.this, SectionActivity.class);

                intent.putExtra("PERSON_KEY", villageList[Integer.parseInt(villageCode) - 1].toUpperCase().substring(0, 2) + name.toUpperCase().substring(0, 2));
                startActivity(intent);

            }
        });
    }

    private void initialize() {

        //view initialization
//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Calling Google Sheets API ...");
//
//        //initializing credentials and service object.
//        mCredential = GoogleAccountCredential.usingOAuth2(
//                getApplicationContext(), Arrays.asList(SCOPES))
//                .setBackOff(new ExponentialBackOff());

        spinner = findViewById(R.id.village_list);
        editTextCode = findViewById(R.id.investigator_code);
        editTextName = findViewById(R.id.investigator_name);
        button = findViewById(R.id.surveyor_submit);
        dbManager = new DatabaseManager(this,"surveyer_details");
        villageList = getResources().getStringArray(R.array.village_list);

        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.village_list, android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);

    }

//    public void loadToSQLiteDatabase(){
//        dbManager.setCreateTable("surveyor_id","name","village_code");
//        dbManager.open();
//        dbManager.insert(code,name,villageCode);
//
//    }
}
