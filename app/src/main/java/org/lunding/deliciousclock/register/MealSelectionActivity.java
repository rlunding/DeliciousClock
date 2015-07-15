package org.lunding.deliciousclock.register;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.lunding.deliciousclock.Utilities;
import org.lunding.deliciousclock.data.AppConstants;
import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.R;
import org.lunding.deliciousclock.data.SQLiteHandler;

import java.util.ArrayList;


public class MealSelectionActivity extends AppCompatActivity {

    private static final String TAG = MealSelectionActivity.class.getSimpleName();

    private Button continueButton;
    private Meal meal;
    //private SignupObject signupObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " initializing...");
        super.onCreate(savedInstanceState);
        //signupObject = (SignupObject) getIntent().getSerializableExtra(SignupObject.SIGNOP_OBJECT_TAG);
        setContentView(R.layout.activity_meal_selection);

        continueButton = (Button) findViewById(R.id.meal_selection_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to next screen");
                Intent intent = new Intent(getApplicationContext(), TimeSelectionActivity.class);
                //signupObject.setMeal(meal);
                //intent.putExtra(SignupObject.SIGNOP_OBJECT_TAG, signupObject);
                startActivity(intent);
            }
        });

        SQLiteHandler database = new SQLiteHandler(this);
        ArrayList<Meal> arrayOfMeals = database.getMeals();
        MealAdapter adapter = new MealAdapter(this, arrayOfMeals);
        ListView listView = (ListView) findViewById(R.id.meal_selection_listView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                meal = (Meal) parent.getItemAtPosition(position);
                Log.d(TAG, "Saving pref meal: " + meal);
                Utilities.saveMeal(getApplicationContext(), meal);
                continueButton.setEnabled(true);
            }
        });

        Log.d(TAG, TAG + " initialized");
    }
}
