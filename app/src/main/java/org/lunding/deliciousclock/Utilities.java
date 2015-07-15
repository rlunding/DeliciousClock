package org.lunding.deliciousclock;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import org.lunding.deliciousclock.data.Address;
import org.lunding.deliciousclock.data.AppConstants;
import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.data.SQLiteHandler;
import org.lunding.deliciousclock.data.Time;

import java.util.Calendar;

/**
 *
 * Created by Lunding on 15/07/15.
 */
public class Utilities {


    public static void saveMeal(Context context, Meal meal){
        SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(AppConstants.PREF_MEAL, meal.getId());
        editor.apply();

    }

    public static void saveTime(Context context, Time time){
        SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(AppConstants.PREF_HOUR, time.getHourOfDay());
        editor.putInt(AppConstants.PREF_MINUTES, time.getMinute());
        editor.putInt(AppConstants.PREF_OFFSET, time.getOffset());
        editor.apply();
    }

    public static void saveAddress(Context context, Address address){
        SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(AppConstants.PREF_STREET, address.getAddress());
        editor.putString(AppConstants.PREF_CITY, address.getCity());
        editor.putString(AppConstants.PREF_STATE, address.getState());
        editor.putString(AppConstants.PREF_ZIPCODE, address.getZipCode());
        editor.apply();
    }

    public static Meal getMeal(Context context){
        SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_NAME, 0);
        int id = settings.getInt(AppConstants.PREF_MEAL, 1);
        SQLiteHandler database = new SQLiteHandler(context);
        return database.getMeal(id);
    }

    public static Time getTime(Context context){
        SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_NAME, 0);
        int hour = settings.getInt(AppConstants.PREF_HOUR, 7);
        int minute = settings.getInt(AppConstants.PREF_MINUTES, 0);
        int offset = settings.getInt(AppConstants.PREF_OFFSET, 10);
        return new Time(hour, minute, offset);
    }

    public static Address getAddress(Context context){
        SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_NAME, 0);
        String street = settings.getString(AppConstants.PREF_STREET, "");
        String city = settings.getString(AppConstants.PREF_CITY, "");
        String state = settings.getString(AppConstants.PREF_STATE, "");
        String zipcode = settings.getString(AppConstants.PREF_ZIPCODE, "");
        return new Address(street, city, state, zipcode);
    }

    public static String makeTimeString(Time time){
        String hour = (time.getHourOfDay() > 9 ? "" : "0") + time.getHourOfDay();
        String minutes = (time.getMinute() > 9 ? "" : "0") + time.getMinute();
        return hour + ":" + minutes + " AM";
    }

    public static String makeOffsetStrign(Time time){
        return time.getOffset() + " MIN";
    }

    public static void showTimePicker(final Context context, final Time time, final Button timeButton){
        showTimePicker(context, time, timeButton, "");
    }

    public static void showTimePicker(final Context context, final Time time, final Button timeButton, final String padding){
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay > 5 && hourOfDay < 11) {
                            time.setHourOfDay(hourOfDay);
                            time.setMinute(minute);
                            timeButton.setText(padding + Utilities.makeTimeString(time));
                            Utilities.saveTime(context, time);
                        } else {
                            Toast.makeText(context, "We deliver from 06.00 to 11.00", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, hour, minute, true);
        timePicker.setTitle("Select time");
        timePicker.show();
    }

    public static void showNumberPicker(final Context context, final Time time, final Button offsetButton){
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Minutes before breakfast");
        dialog.setContentView(R.layout.number_picker_dialog);
        Button b1 = (Button) dialog.findViewById(R.id.button1);
        final NumberPicker np = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
        np.setMinValue(1);
        np.setMaxValue(30);
        np.setWrapSelectorWheel(true);
        np.setValue(time.getOffset());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setOffset(np.getValue());
                Utilities.saveTime(context, time);
                offsetButton.setText(Utilities.makeOffsetStrign(time));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
