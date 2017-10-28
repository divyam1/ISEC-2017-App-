package dexter.com.isec17.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import dexter.com.isec17.model.PlannerItem;

/**
 * Created by dexter on 21/1/17.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "plannerManager";

    // Contacts table name
    private static final String TABLE_PLANNER = "planner";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TYPE = "type";
    private static final String KEY_TIME = "start_time";
    private static final String KEY_DATE = "start_date";
    private static final String KEY_PLACE = "place";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLANNER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT , "
                + KEY_TYPE + " TEXT , "
                + KEY_PLACE + " TEXT , "
                + KEY_DATE + " DATE , "
                + KEY_TIME + " TIME " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANNER);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Plan
    public long addPlan(PlannerItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, item.getTitle()); // Planner Title
        values.put(KEY_TYPE, item.getType()); // Planner type
        values.put(KEY_PLACE,item.getPlace()); // Planner place
        values.put(KEY_TIME,item.getStartTime()); // Planner time
        values.put(KEY_DATE,item.getDate()); // Planner Date


        // Inserting Row
        long id = db.insert(TABLE_PLANNER, null, values);
        db.close(); // Closing database connection
        return id;
    }

    // Getting single plan
    PlannerItem getPlan(String title) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLANNER, new String[] { KEY_ID,
                        KEY_TITLE, KEY_TYPE,KEY_PLACE,KEY_TIME }, KEY_TITLE + "=?",
                new String[] { String.valueOf(title) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        PlannerItem item = new PlannerItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        // return item
        return item;
    }

    // Getting All Plans
    public List<PlannerItem> getAllPlans() {
        List<PlannerItem> planList = new ArrayList<PlannerItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLANNER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PlannerItem item = new PlannerItem();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setTitle(cursor.getString(1));
                item.setType(cursor.getString(2));
                item.setPlace(cursor.getString(3));
                item.setDate(cursor.getString(4));
                item.setStartTime(cursor.getString(5));

                // Adding plan to list
                planList.add(item);
                System.out.println("PLAN : " + item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return planList;
    }


    // Deleting single PLan
    public void deletePlan(PlannerItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLANNER, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        db.close();
    }

}