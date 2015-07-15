package org.lunding.deliciousclock;

import android.content.Context;
import android.content.SharedPreferences;

import org.lunding.deliciousclock.data.Address;
import org.lunding.deliciousclock.data.AppConstants;
import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.data.SQLiteHandler;
import org.lunding.deliciousclock.data.Time;

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
}
