package org.lunding.deliciousclock.register;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

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
    private Time time;
    private SignupObject signupObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " initializing...");
        super.onCreate(savedInstanceState);
        signupObject = (SignupObject) getIntent().getSerializableExtra(SignupObject.SIGNOP_OBJECT_TAG);
        setContentView(R.layout.activity_time_selection);
        time = new Time();

        continueButton = (Button) findViewById(R.id.time_selection_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Saving pref meal: " + time);
                Utilities.saveTime(getApplicationContext(), time);
                Log.d(TAG, "Continue to next screen");
                Intent intent = new Intent(getApplicationContext(), AddressSelectionActivity.class);
                signupObject.setTime(time);
                intent.putExtra(SignupObject.SIGNOP_OBJECT_TAG, signupObject);
                startActivity(intent);
            }
        });

        timePickerButton = (Button) findViewById(R.id.time_selection_time_button);
        setTimeButtonLabel();
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(mActivity,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (hourOfDay > 5 && hourOfDay < 11){
                                    time.setHourOfDay(hourOfDay);
                                    time.setMinute(minute);
                                    setTimeButtonLabel();
                                } else {
                                    Toast.makeText(mActivity, "We deliver from 06.00 to 11.00", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, hour, minute, true);
                timePicker.setTitle("Select time");
                timePicker.show();
            }
        });

        Log.d(TAG, TAG + " initialized");
    }

    private void setTimeButtonLabel(){
        String minute = time.getMinute() < 10 ? "0"+time.getMinute() : time.getMinute()+"";
        timePickerButton.setText(time.getHourOfDay() + ":" + minute);
    }
}
