package org.lunding.deliciousclock.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.R;

import java.util.ArrayList;


public class MealSelectionActivity extends AppCompatActivity {

    private static final String TAG = MealSelectionActivity.class.getSimpleName();

    private Button continueButton;
    private Meal meal;
    private SignupObject signupObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " initializing...");
        super.onCreate(savedInstanceState);
        signupObject = (SignupObject) getIntent().getSerializableExtra(SignupObject.SIGNOP_OBJECT_TAG);
        setContentView(R.layout.activity_meal_selection);

        continueButton = (Button) findViewById(R.id.meal_selection_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to next screen");
                Intent intent = new Intent(getApplicationContext(), TimeSelectionActivity.class);
                signupObject.setMeal(meal);
                intent.putExtra(SignupObject.SIGNOP_OBJECT_TAG, signupObject);
                startActivity(intent);
            }
        });


        ArrayList<Meal> arrayOfMeals = new ArrayList<>();
        arrayOfMeals.add(new Meal(R.drawable.bacon_and_eggs, "Bacon and eggs", "Includes two sweet delicious fried pieces of pork," +
                "scramled eggs, and a piece of toast."));
        arrayOfMeals.add(new Meal(R.drawable.burrito, "Breakfast Burrito", "Includes two delicious breakfast burritos. Pick this one" +
                "if you're allergic to everything."));
        arrayOfMeals.add(new Meal(R.drawable.vegetarian, "Vegetarian", "Includes some vegetarian lasagna thingy. You should pick" +
                "this one if you're against killing animals for food!"));
        MealAdapter adapter = new MealAdapter(this, arrayOfMeals);
        ListView listView = (ListView) findViewById(R.id.meal_selection_listView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                meal = (Meal) parent.getItemAtPosition(position);
                continueButton.setEnabled(true);
            }
        });

        Log.d(TAG, TAG + " initialized");
    }
}
