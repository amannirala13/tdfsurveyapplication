package com.tdf.tdfapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class WaterAndSanitationFacilityActivity extends AppCompatActivity {

    private Spinner drinkingWaterResourceBeforeTDF, drinkingWaterResourceAfterTDF;
    private Spinner waterResourceDistanceBeforeTDF, waterResourceDistanceAfterTDF;
    private Spinner waterFetcherBeforeTDF, waterFetcherAfterTDF;
    private EditText editTextDurationOfUsageBeforeTDF, editTextDurationOfUsageAfterTDF;
    private EditText editTextOtherDistanceBeforeTDF, editTextOtherDistanceAfterTDF;
    private TextView textViewWaterResourceBeforeTDF, textViewWaterResourceAfterTDF;
    private TextView textViewOtherDistanceBeforeTDF, textViewOtherDistanceAfterTDF;
    private TextView textViewWaterFetcherBeforeTDF, textViewWaterFetcherAfterTDF;
    private TextView textViewOtherWaterResourceBeforeTDF, textViewOtherWaterResourceAfterTDF;
    private EditText editTextOtherWaterResourceBeforeTDF, editTextOtherWaterResourceAfterTDF;
    private TextView textViewDurationOfUsageBeforeTDF, textViewDurationOfUsageAfterTDF;
    private RadioGroup radioGroupToiletFacilityBeforeTDF, radioGroupToiletFacilityAfterTDF;
    private Button button;
    private String[] waterResourceList, waterResourceDistanceList;
    private String[] waterFetcherList;

    private String waterResourceValueBeforeTDF, waterDistanceBeforeTDF;
    private String waterResourceAvailibilityBeforeTDF, waterResourceAvailibityAfterTDF;
    private String waterResourceValueAfterTDF, waterDistanceAfterTDF;
    private String fetcherBeforeTDF, fetcherAfterTDF;
    private String toiletFacilityBeforeTDF, toiletFacilityAfterTDF;
    private String durationBeforeTDF, durationAfterTDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_and_sanitation_facility);

        initialize();

        drinkingWaterResourceBeforeTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    waterResourceValueBeforeTDF = Integer.toString(i);
                    waterResourceAvailibilityBeforeTDF = "1";
                    textViewWaterResourceBeforeTDF.setVisibility(View.VISIBLE);
                    waterResourceDistanceBeforeTDF.setVisibility(View.VISIBLE);

                    textViewWaterFetcherBeforeTDF.setVisibility(View.VISIBLE);
                    waterFetcherBeforeTDF.setVisibility(View.VISIBLE);

                    if (i == waterResourceList.length - 1) {
                        textViewOtherWaterResourceBeforeTDF.setVisibility(View.VISIBLE);
                        editTextOtherWaterResourceBeforeTDF.setVisibility(View.VISIBLE);
                    } else {
                        textViewOtherWaterResourceBeforeTDF.setVisibility(View.GONE);
                        editTextOtherWaterResourceBeforeTDF.setVisibility(View.GONE);
                    }
                } else {
                    waterResourceValueBeforeTDF = "666";
                    waterResourceAvailibilityBeforeTDF = "0";
                    textViewWaterResourceBeforeTDF.setVisibility(View.GONE);
                    waterResourceDistanceBeforeTDF.setVisibility(View.GONE);

                    textViewWaterFetcherBeforeTDF.setVisibility(View.GONE);
                    waterFetcherBeforeTDF.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                waterResourceValueBeforeTDF = "666";
                waterResourceAvailibilityBeforeTDF = "0";
            }
        });

        drinkingWaterResourceAfterTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    waterResourceAvailibityAfterTDF = "1";
                    waterResourceValueAfterTDF = Integer.toString(i);

                    textViewWaterResourceAfterTDF.setVisibility(View.VISIBLE);
                    waterResourceDistanceAfterTDF.setVisibility(View.VISIBLE);

                    textViewWaterFetcherAfterTDF.setVisibility(View.VISIBLE);
                    waterFetcherAfterTDF.setVisibility(View.VISIBLE);
                    if (i == waterResourceList.length - 1) {
                        textViewOtherWaterResourceAfterTDF.setVisibility(View.VISIBLE);
                        editTextOtherWaterResourceAfterTDF.setVisibility(View.VISIBLE);
                    } else {
                        textViewOtherWaterResourceAfterTDF.setVisibility(View.GONE);
                        editTextOtherWaterResourceAfterTDF.setVisibility(View.GONE);
                    }
                } else {
                    waterResourceAvailibityAfterTDF = "0";
                    waterResourceValueAfterTDF = "666";
                    textViewWaterResourceAfterTDF.setVisibility(View.GONE);
                    waterResourceDistanceAfterTDF.setVisibility(View.GONE);

                    textViewWaterFetcherAfterTDF.setVisibility(View.GONE);
                    waterFetcherAfterTDF.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                waterResourceAvailibityAfterTDF = "0";
                waterResourceValueAfterTDF = "666";
            }
        });

        waterResourceDistanceBeforeTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                waterDistanceBeforeTDF = Integer.toString(i + 1);
                if (i == waterResourceDistanceList.length - 1) {
                    textViewOtherDistanceBeforeTDF.setVisibility(View.VISIBLE);
                    editTextOtherDistanceBeforeTDF.setVisibility(View.VISIBLE);
                } else {
                    textViewOtherDistanceBeforeTDF.setVisibility(View.GONE);
                    editTextOtherDistanceBeforeTDF.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                waterDistanceBeforeTDF = "666";
            }
        });

        waterResourceDistanceAfterTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                waterDistanceAfterTDF = Integer.toString(i + 1);
                if (i == waterResourceDistanceList.length - 1) {
                    textViewOtherDistanceAfterTDF.setVisibility(View.VISIBLE);
                    editTextOtherDistanceAfterTDF.setVisibility(View.VISIBLE);
                } else {
                    textViewOtherDistanceAfterTDF.setVisibility(View.GONE);
                    editTextOtherDistanceAfterTDF.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                waterDistanceAfterTDF = "666";
            }
        });

        radioGroupToiletFacilityBeforeTDF.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.toilet_yes) {
                    toiletFacilityBeforeTDF = "1";
                    textViewDurationOfUsageBeforeTDF.setVisibility(View.VISIBLE);
                    editTextDurationOfUsageBeforeTDF.setVisibility(View.VISIBLE);
                } else if (id == R.id.toilet_no) {
                    toiletFacilityBeforeTDF = "0";
                    textViewDurationOfUsageBeforeTDF.setVisibility(View.GONE);
                    editTextDurationOfUsageBeforeTDF.setVisibility(View.GONE);
                }
            }
        });

        radioGroupToiletFacilityAfterTDF.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.toilet_yes_after) {
                    toiletFacilityAfterTDF = "1";
                    textViewDurationOfUsageAfterTDF.setVisibility(View.VISIBLE);
                    editTextDurationOfUsageAfterTDF.setVisibility(View.VISIBLE);
                } else if (id == R.id.toilet_no_after) {
                    toiletFacilityAfterTDF = "0";
                    textViewDurationOfUsageAfterTDF.setVisibility(View.GONE);
                    editTextDurationOfUsageAfterTDF.setVisibility(View.GONE);
                }
            }
        });

        waterFetcherBeforeTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fetcherBeforeTDF = Integer.toString(i+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                fetcherBeforeTDF = "666";
            }
        });

        waterFetcherAfterTDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fetcherAfterTDF = Integer.toString(i+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                fetcherAfterTDF = "666";
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                durationBeforeTDF = editTextDurationOfUsageBeforeTDF.getText().toString();
                durationAfterTDF = editTextDurationOfUsageAfterTDF.getText().toString();

                Log.i("INFO",fetcherBeforeTDF +
                        fetcherAfterTDF +
                        toiletFacilityAfterTDF +
                        toiletFacilityBeforeTDF +
                        durationBeforeTDF +
                        durationAfterTDF +
                        waterResourceAvailibilityBeforeTDF +
                        waterResourceAvailibityAfterTDF +
                        waterDistanceAfterTDF +
                        waterDistanceBeforeTDF +
                        waterResourceValueBeforeTDF +
                        waterResourceValueAfterTDF);

                finish();
            }
        });

    }

    private void initialize() {
        waterResourceValueBeforeTDF = "666";
        waterDistanceBeforeTDF = "666";
        waterResourceAvailibilityBeforeTDF = "666";
        waterResourceAvailibityAfterTDF = "666";
        waterResourceValueAfterTDF = "666";
        waterDistanceAfterTDF = "666";
        fetcherBeforeTDF = "666";
        fetcherAfterTDF = "666";
        toiletFacilityBeforeTDF = "0";
        toiletFacilityAfterTDF = "0";
        durationBeforeTDF = "666";
        durationAfterTDF= "666";

        drinkingWaterResourceAfterTDF = findViewById(R.id.water_resource_value_after);
        drinkingWaterResourceBeforeTDF = findViewById(R.id.water_resource_value);
        waterResourceDistanceBeforeTDF = findViewById(R.id.water_distance);
        waterResourceDistanceAfterTDF = findViewById(R.id.water_distance_after);
        editTextOtherDistanceBeforeTDF = findViewById(R.id.other_distance);
        editTextOtherDistanceAfterTDF = findViewById(R.id.other_distance_after);
        textViewWaterResourceBeforeTDF = findViewById(R.id.water_source_distance);
        textViewWaterResourceAfterTDF = findViewById(R.id.water_source_distance_after);
        textViewOtherDistanceBeforeTDF = findViewById(R.id.other_distance_label);
        textViewOtherDistanceAfterTDF = findViewById(R.id.other_distance_label_after);
        textViewWaterFetcherBeforeTDF = findViewById(R.id.fetch_water_member_label);
        textViewWaterFetcherAfterTDF = findViewById(R.id.fetch_water_member_label_after);
        waterFetcherAfterTDF = findViewById(R.id.fetch_water_member_after);
        waterFetcherBeforeTDF = findViewById(R.id.fetch_water_member);
        radioGroupToiletFacilityBeforeTDF = findViewById(R.id.toilet_flag);
        radioGroupToiletFacilityAfterTDF = findViewById(R.id.toilet_flag_after);
        textViewDurationOfUsageBeforeTDF = findViewById(R.id.toilet_usage_label);
        textViewDurationOfUsageAfterTDF = findViewById(R.id.toilet_usage_label_after);
        editTextDurationOfUsageBeforeTDF = findViewById(R.id.toilet_usage);
        editTextDurationOfUsageAfterTDF = findViewById(R.id.toilet_usage_after);
        button = findViewById(R.id.sanitation_info_submit);
        textViewOtherWaterResourceBeforeTDF = findViewById(R.id.other_resource_label);
        textViewOtherWaterResourceAfterTDF = findViewById(R.id.other_resource_label_after);
        editTextOtherWaterResourceAfterTDF = findViewById(R.id.other_resource_after);
        editTextOtherWaterResourceBeforeTDF = findViewById(R.id.other_resource);

        waterResourceList = getResources().getStringArray(R.array.water_resource);
        waterResourceDistanceList = getResources().getStringArray(R.array.water_resource_distance);
        waterFetcherList = new String[]{"Male", "Female", "Both"};



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, waterResourceList);

        drinkingWaterResourceBeforeTDF.setAdapter(arrayAdapter);
        drinkingWaterResourceAfterTDF.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, waterResourceDistanceList);

        waterResourceDistanceBeforeTDF.setAdapter(arrayAdapter1);
        waterResourceDistanceAfterTDF.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, waterFetcherList);
        waterFetcherBeforeTDF.setAdapter(arrayAdapter2);
        waterFetcherAfterTDF.setAdapter(arrayAdapter2);
    }
}
