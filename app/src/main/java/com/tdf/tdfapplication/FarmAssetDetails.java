package com.tdf.tdfapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FarmAssetDetails extends AppCompatActivity {

    private String[] textLabels;
    private TextView textViewfarmAssetLabel;
    private Button button;
    private EditText editTextIJBeforeTDF, editTextIJAfterTDF, editTextNoBeforeTDF, editTextNoAfterTDF;
    private String[] values;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_asset_details);
        initialize();
        Intent intent = getIntent();
        textLabels = intent.getStringArrayExtra("FARM_ASSET_LIST");
        textViewfarmAssetLabel.setText(textLabels[0]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < textLabels.length -1 && textLabels[count+1]!=null) {
                    values[0] = editTextIJBeforeTDF.getText().toString();
                    values[1] = editTextNoBeforeTDF.getText().toString();
                    values[2] = editTextIJAfterTDF.getText().toString();
                    values[3] = editTextNoAfterTDF.getText().toString();

                    textViewfarmAssetLabel.setText(textLabels[count + 1]);
                    count = count + 1;
                    editTextIJAfterTDF.setText("");
                    editTextNoAfterTDF.setText("");
                    editTextIJBeforeTDF.setText("");
                    editTextNoBeforeTDF.setText("");

                    StringBuilder logMessage = new StringBuilder();
                    for (String value : values) {
                        logMessage.append(value);
                        logMessage.append(" ");
                    }
                    Log.i("INFO", logMessage.toString());
                } else {
                    finish();
                }
            }
        });

    }

    private void initialize() {
        values = new String[4];

        textViewfarmAssetLabel = findViewById(R.id.item_label);
        button = findViewById(R.id.wage_info_submit);
        editTextIJAfterTDF = findViewById(R.id.number_of_days_after);
        editTextNoAfterTDF = findViewById(R.id.wage_value_after);
        editTextIJBeforeTDF = findViewById(R.id.wage_number_of_days);
        editTextNoBeforeTDF = findViewById(R.id.wage_value);

    }

    //view initialization
//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Calling Google Sheets API ...");
//
//        //initializing credentials and service object.
//        mCredential = GoogleAccountCredential.usingOAuth2(
//                getApplicationContext(), Arrays.asList(SCOPES))
//                .setBackOff(new ExponentialBackOff());
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

