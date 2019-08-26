package com.tdf.tdfapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tdf.tdfapplication.adapter.SectionAdapter;

public class SectionActivity extends AppCompatActivity {

    String[] sectionList = {"Personal Details",
            "Basic Household Information",
            "Family Details",
            "Asset Ownership",
            "Water and Sanitation Facilities",
            "Landholding and Irrigation Facilities",
            "Cost Benefit Analysis from Crop Cultivation",
            "Animal Husbandry Components",
            "Other Income-Generating Activities",
            "Household Consumption",
            "Agriculture Fuel and Light Expenditure",
            "Food Availibility Analysis",
            "Migration",
            "Wage Employment",
            "TDF Programme Awareness",
            "Intervention -Horticulture",
            "Intervention -Water Resource Development",
            "Intervention - Other Infrastructure",
            "Intervention - Direct",
            "Intervention - Soil Conservation",
            "Intervention - Other",
            "Environmental Benefits",
            "Applied Compost Details",
            "Community Institutions",
            "Credit Penetration & it's impact",
            "Role of Programme Implementing Agency (PIA)",
            "Other Information",
            "Constraints while availing TDF benefit",
            "Logout"
            };

    Class<?>[] classes = {
           PersonalDetailsActivity.class,
           BasicHouseholdInformationActivity.class,
           FamilyDetailsBeneficiary.class,
            AssetOwnershipActivity.class,
            WaterAndSanitationFacilityActivity.class,
            IrrigationFacilityActivity.class,
            AnimalHusbandryComponentsActivity.class,
            FuelLightExpenditureActivity.class,
            WageEmployementActivity.class,
            DirectInterventionActivity.class,
            SoilConservationIntervention.class,
            OtherTDFInfoActivity.class,
            EnvironmentalBenefitsActivity.class,
            TDFConstraintsActivity.class,
           MainActivity.class
    };

    String personKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        SectionAdapter sectionAdapter = new SectionAdapter(this,sectionList);
        ListView listView =findViewById(R.id.section_list);
        listView.setAdapter(sectionAdapter);
        Intent intent = getIntent();
        personKey = intent.getStringExtra("PERSON_KEY");
        Log.i("INFO",personKey);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SectionActivity.this,classes[position]);
                Log.i("PERSON_KEY",personKey);
                intent.putExtra("PERSON_KEY",personKey);
                startActivity(intent);
            }
        });
    }
}
