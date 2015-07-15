package org.lunding.deliciousclock.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.lunding.deliciousclock.R;

import java.util.ArrayList;

/**
 * Created by Lunding on 15/07/15.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    //Constants
    public static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "deliciousclock.db";

    //TABLES
    private static final String TABLE_MEAL = "meal";
    private static final String TABLE_ADDRESS = "address";

    //COLUMNS
    private static final String KEY_MEAL_ID = "id";
    private static final String KEY_MEAL_IMAGE = "image";
    private static final String KEY_MEAL_TITLE = "title";
    private static final String KEY_MEAL_DESCRIPTION = "description";

    private static final String KEY_ADDRESS_ID = "id";
    private static final String KEY_ADDRESS_ADDRESS = "address";
    private static final String KEY_ADDRESS_CITY = "city";
    private static final String KEY_ADDRESS_STATE = "state";
    private static final String KEY_ADDRESS_ZIPCODE = "zipcode";

    public SQLiteHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        arrayOfMeals.add(new Meal(1, R.drawable.bacon_and_eggs, R.drawable.bacon_and_eggs_card, R.drawable.home_bacon, "Bacon and eggs", "Includes two sweet delicious fried pieces of pork," +
                "scramled eggs, and a piece of toast."));
        arrayOfMeals.add(new Meal(2, R.drawable.burrito, R.drawable.burrito_card, R.drawable.home_burrito, "Breakfast Burrito", "Includes two delicious breakfast burritos. Pick this one" +
                "if you're allergic to everything."));
        arrayOfMeals.add(new Meal(3, R.drawable.vegetarian, R.drawable.vegetarian_card, R.drawable.home_vegetarian, "Vegetarian", "Includes some vegetarian lasagna thingy. You should pick" +
                "this one if you're against killing animals for food!"));
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d(TAG, "Initializing creation of database");
        String CREATE_MEAL_TABLE = "CREATE TABLE " + TABLE_MEAL + "("
                + KEY_MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_MEAL_IMAGE + " INTEGER,"
                + KEY_MEAL_TITLE + " TEXT UNIQUE,"
                + KEY_MEAL_DESCRIPTION + " TEXT"
                 + ");";
        db.execSQL(CREATE_MEAL_TABLE);

        String CREATE_ADDRESS_TABLE = "CREATE TABLE " + TABLE_ADDRESS + "("
                + KEY_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ADDRESS_ADDRESS + " TEXT,"
                + KEY_ADDRESS_CITY + " TEXT,"
                + KEY_ADDRESS_STATE + " TEXT,"
                + KEY_ADDRESS_ZIPCODE + " TEXT" + ");";
        db.execSQL(CREATE_ADDRESS_TABLE);

        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);

        //Create tables again
        onCreate(db);
    }


    ArrayList<Meal> arrayOfMeals = new ArrayList<>();

    public ArrayList<Meal> getMeals(){
        return arrayOfMeals;
    }

    public Meal getMeal(int id){
        for (Meal m: arrayOfMeals){
            if (m.getId() == id){
                return m;
            }
        }
        return null;
    }




/*

    public long addMeal(Meal meal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEAL_IMAGE, meal.getImage());
        values.put(KEY_MEAL_TITLE, meal.getTitle());
        values.put(KEY_MEAL_DESCRIPTION, meal.getDescription());

        long db_id = db.insert(TABLE_MEAL, null, values);
        db.close();
        Log.d(TAG, "New meal inserted into sqlite: " + meal);
        return db_id;
    }

    public Meal getMeal(String id){
        Meal meal = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MEAL + " WHERE " + KEY_MEAL_ID + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{id});
        if (cursor.moveToFirst()){
            meal = new Meal(
                    cursor.getInt(cursor.getColumnIndex(KEY_MEAL_ID)),
                    cursor.getInt(cursor.getColumnIndex(KEY_MEAL_IMAGE)),
                    cursor.getString(cursor.getColumnIndex(KEY_MEAL_TITLE)),
                    cursor.getString(cursor.getColumnIndex(KEY_MEAL_DESCRIPTION))
            );
        }
        cursor.close();
        return meal;
    }

    public ArrayList<Meal> getMeals(){
        ArrayList<Meal> meals = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MEAL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(KEY_MEAL_ID));
            int image = cursor.getInt(cursor.getColumnIndex(KEY_MEAL_IMAGE));
            String title = cursor.getString(cursor.getColumnIndex(KEY_MEAL_TITLE));
            String desc = cursor.getString(cursor.getColumnIndex(KEY_MEAL_DESCRIPTION));
            meals.add(new Meal(id, image, title, desc));
        }
        cursor.close();
        return meals;
    }*/
}
