package org.lunding.deliciousclock.register;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import org.lunding.deliciousclock.R;
import org.lunding.deliciousclock.Utilities;
import org.lunding.deliciousclock.data.Address;

import java.util.HashMap;
import java.util.Map;


public class AddressSelectionActivity extends AppCompatActivity {

    private static final String TAG = AddressSelectionActivity.class.getSimpleName();

    private EditText addressField;
    private EditText cityField;
    private AutoCompleteTextView stateField;
    private EditText zipcodeField;
    private Button continueButton;
    private Address address;
    private Activity mActivity = this;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " initializing...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_selection);

        continueButton = (Button) findViewById(R.id.address_selection_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to next screen");
                Intent intent = new Intent(getApplicationContext(), OverviewSelectionActivity.class);
                startActivity(intent);
            }
        });

        addressField = (EditText) findViewById(R.id.address_selection_address);
        cityField = (EditText) findViewById(R.id.address_selection_city);
        stateField = (AutoCompleteTextView) findViewById(R.id.address_selection_state);
        zipcodeField = (EditText) findViewById(R.id.address_selection_zipcode);

        String[] statesArray = getResources().getStringArray(R.array.states_array);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statesArray);
        stateField.setAdapter(stateAdapter);

        address = Utilities.getAddress(this);
        if (address != null){
            addressField.setText(address.getAddress());
            cityField.setText(address.getCity());
            stateField.setText(address.getState());
            zipcodeField.setText(String.valueOf(address.getZipCode()));
        }


        addressField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateInput();
                }
            }
        });

        cityField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    validateInput();
                }
            }
        });

        stateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!states.containsKey(getState())) {
                        Toast.makeText(mActivity, "That is not a state", Toast.LENGTH_SHORT).show();
                    }
                    validateInput();
                }
            }
        });

        zipcodeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String zipCode = getZipCode();
                    if (zipCode.length() != 5) {
                        Toast.makeText(mActivity, "Invalid zipcode", Toast.LENGTH_SHORT).show();
                    }
                    validateInput();
                }
            }
        });

        if (googleMap == null){
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (googleMap == null){
                Toast.makeText(this, "Sorry! Unable to create maps",
                        Toast.LENGTH_LONG).show();
            }
        }
        if (googleMap != null){
            googleMap.setMyLocationEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        validateInput();

        Log.d(TAG, TAG + " initialized");
    }

    private String getAddress(){
        return addressField.getText().toString();
    }

    private String getCity(){
        return cityField.getText().toString();
    }

    private String getState(){
        return stateField.getText().toString();
    }

    private String getZipCode(){
        return zipcodeField.getText().toString();
    }

    private void validateInput(){
        if (!getAddress().isEmpty() && !getCity().isEmpty() && !getState().isEmpty() && !getZipCode().isEmpty()){
            this.address = new Address(getAddress(), getCity(), getState(), getZipCode());
            Log.d(TAG, "Saving pref meal: " + address);
            Utilities.saveAddress(getApplicationContext(), address);
            continueButton.setEnabled(true);
        } else {
            continueButton.setEnabled(false);
        }
    }

    private static Map<String, String> states = new HashMap<>();
    static {
        states.put("Alabama","AL");
        states.put("Alaska","AK");
        states.put("Alberta","AB");
        states.put("American Samoa","AS");
        states.put("Arizona","AZ");
        states.put("Arkansas","AR");
        states.put("Armed Forces (AE)","AE");
        states.put("Armed Forces Americas","AA");
        states.put("Armed Forces Pacific","AP");
        states.put("British Columbia","BC");
        states.put("California","CA");
        states.put("Colorado","CO");
        states.put("Connecticut","CT");
        states.put("Delaware","DE");
        states.put("District Of Columbia","DC");
        states.put("Florida","FL");
        states.put("Georgia","GA");
        states.put("Guam","GU");
        states.put("Hawaii","HI");
        states.put("Idaho","ID");
        states.put("Illinois","IL");
        states.put("Indiana","IN");
        states.put("Iowa","IA");
        states.put("Kansas","KS");
        states.put("Kentucky","KY");
        states.put("Louisiana","LA");
        states.put("Maine","ME");
        states.put("Manitoba","MB");
        states.put("Maryland","MD");
        states.put("Massachusetts","MA");
        states.put("Michigan","MI");
        states.put("Minnesota","MN");
        states.put("Mississippi","MS");
        states.put("Missouri","MO");
        states.put("Montana","MT");
        states.put("Nebraska","NE");
        states.put("Nevada","NV");
        states.put("New Brunswick","NB");
        states.put("New Hampshire","NH");
        states.put("New Jersey","NJ");
        states.put("New Mexico","NM");
        states.put("New York","NY");
        states.put("Newfoundland","NF");
        states.put("North Carolina","NC");
        states.put("North Dakota","ND");
        states.put("Northwest Territories","NT");
        states.put("Nova Scotia","NS");
        states.put("Nunavut","NU");
        states.put("Ohio","OH");
        states.put("Oklahoma","OK");
        states.put("Ontario","ON");
        states.put("Oregon","OR");
        states.put("Pennsylvania","PA");
        states.put("Prince Edward Island","PE");
        states.put("Puerto Rico","PR");
        states.put("Quebec","PQ");
        states.put("Rhode Island","RI");
        states.put("Saskatchewan","SK");
        states.put("South Carolina","SC");
        states.put("South Dakota","SD");
        states.put("Tennessee","TN");
        states.put("Texas","TX");
        states.put("Utah","UT");
        states.put("Vermont","VT");
        states.put("Virgin Islands","VI");
        states.put("Virginia","VA");
        states.put("Washington","WA");
        states.put("West Virginia","WV");
        states.put("Wisconsin","WI");
        states.put("Wyoming","WY");
        states.put("Yukon Territory","YT");
    }
}
