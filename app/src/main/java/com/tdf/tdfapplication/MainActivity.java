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

import com.google.android.gms.common.ConnectionResult;
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
import com.tdf.tdfapplication.Data.DBManager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions;

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
    private DBManager dbManager;

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
                dbManager.open();
                dbManager.setCREATE_TABLE("person_id","name","village");
                dbManager.insert(code,name,villageCode);
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
        dbManager = new DBManager(this,"surveyer_details");
        villageList = getResources().getStringArray(R.array.village_list);

        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.village_list, android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);

    }
}

//    private int getResultsFromApi() {
//        //check if google play services are available
//        if (!isGooglePlayServicesAvailable()) {
//            //if not then install
//            acquireGooglePlayServices();
//        } else if (mCredential.getSelectedAccountName() == null) {
//            //choose user google account for accessing sheet
//            chooseAccount();
//        } else if (!isDeviceOnline()) {
//            //if no network, display no network message
//            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
//        } else {
//            //start background task for retrieving data from Google Sheets
//            new MainActivity.MakeRequestTask(mCredential).execute();
//        }
//
//        return 0;
//    }
//
//    //choose account for accessing Google Sheets
//    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
//    private void chooseAccount() {
//        if (EasyPermissions.hasPermissions(
//                this, Manifest.permission.GET_ACCOUNTS)) {
//            //if permission exists, get user account name in private mode
//            String accountName = getPreferences(Context.MODE_PRIVATE)
//                    .getString(PREF_ACCOUNT_NAME, null);
//            if (accountName != null) {
//                //set credential user name to account name if account name isn't null
//                mCredential.setSelectedAccountName(accountName);
//                //call this again to connect to API
//                getResultsFromApi();
//            } else {
//                // Start a dialog from which the user can choose an account
//                startActivityForResult(
//                        mCredential.newChooseAccountIntent(),
//                        REQUEST_ACCOUNT_PICKER);
//            }
//        } else {
//            // Request the GET_ACCOUNTS permission via a user dialog
//            EasyPermissions.requestPermissions(
//                    this,
//                    "This app needs to access your Google account (via Contacts).",
//                    REQUEST_PERMISSION_GET_ACCOUNTS,
//                    Manifest.permission.GET_ACCOUNTS);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(
//            int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case REQUEST_GOOGLE_PLAY_SERVICES:
//                if (resultCode != RESULT_OK) {
//                    Toast.makeText(this,
//                            "This app requires Google Play Services. Please install " +
//                                    "Google Play Services on your device and relaunch this app.",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    getResultsFromApi();
//                }
//                break;
//            case REQUEST_ACCOUNT_PICKER:
//                if (resultCode == RESULT_OK && data != null &&
//                        data.getExtras() != null) {
//                    String accountName =
//                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
//                    if (accountName != null) {
//                        SharedPreferences settings =
//                                getPreferences(Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putString(PREF_ACCOUNT_NAME, accountName);
//                        editor.apply();
//                        mCredential.setSelectedAccountName(accountName);
//                        getResultsFromApi();
//                    }
//                }
//                break;
//            case REQUEST_AUTHORIZATION:
//                if (resultCode == RESULT_OK) {
//                    getResultsFromApi();
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        EasyPermissions.onRequestPermissionsResult(
//                requestCode, permissions, grantResults, this);
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> list) {
//        //do nothing
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> list) {
//        //do nothing
//    }
//
//    private boolean isDeviceOnline() {
//        ConnectivityManager connMgr =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        return (networkInfo != null && networkInfo.isConnected());
//    }
//
//    //check if Play Services are available
//    private boolean isGooglePlayServicesAvailable() {
//        GoogleApiAvailability apiAvailability =
//                GoogleApiAvailability.getInstance();
//        final int connectionStatusCode =
//                apiAvailability.isGooglePlayServicesAvailable(this);
//        return connectionStatusCode == ConnectionResult.SUCCESS;
//    }
//
//    //install Google Play Services for device
//    private void acquireGooglePlayServices() {
//        GoogleApiAvailability apiAvailability =
//                GoogleApiAvailability.getInstance();
//        final int connectionStatusCode =
//                apiAvailability.isGooglePlayServicesAvailable(this);
//        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
//            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
//        }
//    }
//
//    //show error message if Play services couldn't be found
//    void showGooglePlayServicesAvailabilityErrorDialog(
//            final int connectionStatusCode) {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        Dialog dialog = apiAvailability.getErrorDialog(
//                MainActivity.this,
//                connectionStatusCode,
//                REQUEST_GOOGLE_PLAY_SERVICES);
//        dialog.show();
//    }
//
//    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
//        private com.google.api.services.sheets.v4.Sheets mService = null;
//        private Exception mLastError = null;
//
//        MakeRequestTask(GoogleAccountCredential credential) {
//            HttpTransport transport = AndroidHttp.newCompatibleTransport();
//            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
//                    transport, jsonFactory, credential)
//                    .setApplicationName("Google Sheets API Android Quickstart")
//                    .build();
//        }
//
//        @Override
//        protected List<String> doInBackground(Void... params) {
//            try {
//                return setDataToGoogleSheetsAPI();
//            } catch (Exception e) {
//                mLastError = e;
//                cancel(true);
//                return null;
//            }
//        }
//
//        //Set the form data of PersonalDetails to Google Sheets and display all row values as Toast Message
//        private List<String> setDataToGoogleSheetsAPI() throws IOException {
//            //set spreadsheet id to our sheet
//            String spreadsheetId = "1QOvOFD7yd1uCTboFKNFnjak8fTCoxt8FcEFIe18Jz0A";
//            //set the table range
//            String range = "Sheet1!A2:C";
//
//            //set current date;
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//            Date date = new Date();
//            System.out.println(dateFormat.format(date));
//
//            //create the Object to be appened to the row
//            List<List<Object>> respondentRowValues = Arrays.asList(
//                    Arrays.asList((Object) code,
//                            (Object) name,
//                            (Object) villageCode));
//            //append the object
//            ValueRange respondentRecord = new ValueRange().setValues(respondentRowValues);
//            AppendValuesResponse result =
//                    this.mService.spreadsheets().values().append(spreadsheetId, range, respondentRecord)
//                            .setValueInputOption("RAW")
//                            .execute();
//            System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
//
//            //retrieve row values from sheets and add it to the list of objects
//            List<String> results = new ArrayList<String>();
//            ValueRange response = this.mService.spreadsheets().values()
//                    .get(spreadsheetId, range)
//                    .execute();
//            List<List<Object>> values = response.getValues();
//            if (values != null) {
//                results.add("\n\n");
//                for (List row : values) {
//                    results.add("Surveyor name: " + row.get(0) + "Surveyor code: " + row.get(1) + " Village code:" + row.get(2) + "\n\n");
//                }
//            }
//            return results;
//        }
//
//
//        @Override
//        protected void onPreExecute() {
//            mProgress.show();
//        }
//
//        @Override
//        protected void onPostExecute(List<String> output) {
//            mProgress.hide();
//            Intent intent = new Intent(MainActivity.this, SectionActivity.class);
//            startActivity(intent);
//        }
//
//        //if request was cancelled or error occured during data retrieval from sheet
//        @Override
//        protected void onCancelled() {
//            mProgress.hide();
//            if (mLastError != null) {
//                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
//                    showGooglePlayServicesAvailabilityErrorDialog(
//                            ((GooglePlayServicesAvailabilityIOException) mLastError)
//                                    .getConnectionStatusCode());
//                } else if (mLastError instanceof UserRecoverableAuthIOException) {
//                    startActivityForResult(
//                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
//                            REQUEST_AUTHORIZATION);
//                } else {
//                    Log.e("ERROR_MESSAGE", "The following error occurred:\n"
//                            + mLastError.getMessage());
//                }
//            } else {
//                Log.e("REQUEST_CANCELLED", "Request cancelled.");
//            }
//        }
//    }

