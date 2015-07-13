package org.lunding.deliciousclock.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.lunding.deliciousclock.R;
import org.lunding.deliciousclock.data.Address;
import org.lunding.deliciousclock.data.Meal;

import java.util.ArrayList;


public class AddressSelectionActivity extends AppCompatActivity {

    private static final String TAG = AddressSelectionActivity.class.getSimpleName();

    private Button continueButton;
    private Address address;
    private SignupObject signupObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " initializing...");
        super.onCreate(savedInstanceState);
        signupObject = (SignupObject) getIntent().getSerializableExtra(SignupObject.SIGNOP_OBJECT_TAG);
        setContentView(R.layout.activity_address_selection);

        continueButton = (Button) findViewById(R.id.address_selection_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to next screen");
                Intent intent = new Intent(getApplicationContext(), TimeSelectionActivity.class);
                //
                intent.putExtra(SignupObject.SIGNOP_OBJECT_TAG, signupObject);
                startActivity(intent);
            }
        });

        Log.d(TAG, TAG + " initialized");
    }
}
