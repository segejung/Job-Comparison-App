package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends  SQLiteOpenHelper{

    public static final String JOB_OFFER_TABLE = "JOB_OFFER_TABLE";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_COMPANY = "COMPANY";
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_COST_OF_LIVING = "COST_OF_LIVING";
    public static final String COLUMN_SALARY = "SALARY";
    public static final String COLUMN_BONUS = "BONUS";
    public static final String COLUMN_RETIREMENT_BENEFITS = "RETIREMENT_BENEFITS";
    public static final String COLUMN_RELOCATION_AMOUNT = "RELOCATION_AMOUNT";
    public static final String COLUMN_TRAINING_FUND = "TRAINING_FUND";

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "jobData.db", null, 1);
    }

    // First Time the database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the Table
        String createDBTableStatement =
                "CREATE TABLE " + JOB_OFFER_TABLE + " (" + COLUMN_TITLE + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_COMPANY + " TEXT, " + COLUMN_LOCATION + " TEXT, " +
                        COLUMN_COST_OF_LIVING + " INTEGER, " + COLUMN_SALARY + " INTEGER, " + COLUMN_BONUS + " INTEGER, " +
                        COLUMN_RETIREMENT_BENEFITS + " INTEGER, " + COLUMN_RELOCATION_AMOUNT + " INTEGER, " +
                        COLUMN_TRAINING_FUND + " INTEGER)";

        db.execSQL(createDBTableStatement);

    }

    // Version Update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
