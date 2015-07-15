package org.lunding.deliciousclock.register;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import org.lunding.deliciousclock.MainActivity;
import org.lunding.deliciousclock.R;
import org.lunding.deliciousclock.Utilities;
import org.lunding.deliciousclock.data.AppConstants;
import org.lunding.deliciousclock.data.Time;

import java.util.Calendar;

/**
 * Created by Lunding on 13/07/15.
 */
public class TimeSelectionActivity extends AppCompatActivity {

    private static final String TAG = TimeSelectionActivity.class.getSimpleName();

    private Activity mActivity= this;
    private Button continueButton;
    private Button timePickerButton;
    private Button offsetPickerButton;
    private Time time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " initializing...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selection);
        time = Utilities.getTime(this);

        continueButton = (Button) findViewById(R.id.time_selection_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to next screen");
                Intent intent = new Intent(getApplicationContext(), AddressSelectionActivity.class);
                startActivity(intent);
            }
        });

        timePickerButton = (Button) findViewById(R.id.time_selection_time_button);
        timePickerButton.setText(Utilities.makeTimeString(time));
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.showTimePicker(mActivity, time, timePickerButton);
            }
        });

        offsetPickerButton = (Button) findViewById(R.id.time_selection_offset_button);
        offsetPickerButton.setText(Utilities.makeOffsetStrign(time));
        offsetPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.showNumberPicker(mActivity, time, offsetPickerButton);
            }
        });

        Log.d(TAG, TAG + " initialized");
    }

}
