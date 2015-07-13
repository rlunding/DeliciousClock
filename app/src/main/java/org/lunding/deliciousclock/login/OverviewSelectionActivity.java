package org.lunding.deliciousclock.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.lunding.deliciousclock.R;
import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.data.Time;

import java.util.ArrayList;


public class OverviewSelectionActivity extends AppCompatActivity {

    private static final String TAG = OverviewSelectionActivity.class.getSimpleName();

    private Button continueButton;
    private SignupObject signupObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " initializing...");
        super.onCreate(savedInstanceState);
        signupObject = (SignupObject) getIntent().getSerializableExtra(SignupObject.SIGNOP_OBJECT_TAG);
        setContentView(R.layout.activity_overview_selection);

        continueButton = (Button) findViewById(R.id.overview_selection_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to next screen");
                Intent intent = new Intent(getApplicationContext(), TimeSelectionActivity.class);
                intent.putExtra(SignupObject.SIGNOP_OBJECT_TAG, signupObject);
                startActivity(intent);
            }
        });

        int image = signupObject.getMeal().getImage();
        String meal = signupObject.getMeal().getTitle();
        Time time = signupObject.getTime();
        String delivery = time.getHourOfDay() + ":" + (time.getMinute() < 10 ? "0"+time.getMinute() : time.getMinute()+"") + " am";
        String alarm = String.valueOf(time.getOffset());
        String address = signupObject.getAddress().getAddress();

        ((ImageView) findViewById(R.id.overview_selection_image)).setImageResource(image);
        ((TextView) findViewById(R.id.overview_selection_meal)).setText(meal);
        ((TextView) findViewById(R.id.overview_selection_delivery)).setText(delivery);
        ((TextView) findViewById(R.id.overview_selection_alarm)).setText(alarm);
        ((TextView) findViewById(R.id.overview_selection_address)).setText(address);

        Log.d(TAG, TAG + " initialized");
    }
}
