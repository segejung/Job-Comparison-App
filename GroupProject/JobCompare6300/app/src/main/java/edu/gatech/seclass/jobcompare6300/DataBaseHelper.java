package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.INTEGER;

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
    public static final String COLUMN_IS_CURRENT_JOB = "COLUMN_IS_CURRENT_JOB";
    public static final String COLUMN_JOB_SCORE = "COLUMN_JOB_SCORE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "jobData.db", null, 1);
    }

    // First Time the database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the Table
        String createDBTableStatement =
                "CREATE TABLE " + JOB_OFFER_TABLE + " (JOB_ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT, " +
                        COLUMN_COMPANY + " TEXT, " + COLUMN_LOCATION + " TEXT, " +
                        COLUMN_COST_OF_LIVING + " INTEGER, " + COLUMN_SALARY + " INTEGER, " + COLUMN_BONUS + " INTEGER, " +
                        COLUMN_RETIREMENT_BENEFITS + " INTEGER, " + COLUMN_RELOCATION_AMOUNT + " INTEGER, " +
                        COLUMN_TRAINING_FUND + " INTEGER, " + COLUMN_IS_CURRENT_JOB + " BOOLEAN, " + COLUMN_JOB_SCORE + " INTEGER)";

//        String createRankTableStatement =
//                " CREATE TABLE " + JOB

        db.execSQL(createDBTableStatement);

    }

    // Version Update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Adding items to the database
    public boolean addOne(JobDetails jobDetails) {

        //Used to write to the database
        SQLiteDatabase appDB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,jobDetails.getTitle());
        cv.put(COLUMN_COMPANY,jobDetails.getCompany());
        cv.put(COLUMN_LOCATION,jobDetails.getLocation());
        cv.put(COLUMN_COST_OF_LIVING,jobDetails.getCostOfLiving());
        cv.put(COLUMN_SALARY,jobDetails.getSalary());
        cv.put(COLUMN_BONUS,jobDetails.getBonus());
        cv.put(COLUMN_RETIREMENT_BENEFITS,jobDetails.getRetirementBenefits());
        cv.put(COLUMN_RELOCATION_AMOUNT,jobDetails.getRelocationStipend());
        cv.put(COLUMN_TRAINING_FUND,jobDetails.getTrainingAndDevelopmentFund());
        cv.put(COLUMN_IS_CURRENT_JOB,jobDetails.isCurrentJob());
        cv.put(COLUMN_JOB_SCORE,0);

        long insert = appDB.insert(JOB_OFFER_TABLE, null , cv);

        if (insert == -1) { // Kicks bag negative if it is a bad insert
            return false;
        } else {
            return true;
        }



    }

    public List<JobDetails> getOffers() {
        List<JobDetails> returnedJobOffers = new ArrayList<>();

        // This code will pull data from the database

        String queryRequestStr = "SELECT * FROM " + JOB_OFFER_TABLE;
        SQLiteDatabase appDB = this.getReadableDatabase(); // We just want to read the database here.

        Cursor cursor = appDB.rawQuery(queryRequestStr,null);

        // TODO: Change this so it sorts by Job Score first and then does this iteration, it may be worth placing this in the a sort function to do prior to listing

        if(cursor.moveToFirst()) {
            // We want to iterate through the list here and create a JobDetails obj for each row
            do {
                // Omit primary key id
                String jobTitle = cursor.getString(1);
                String jobCompanyName = cursor.getString(2);
                String jobLocation = cursor.getString(3);
                Integer jobCostOfLiving = cursor.getInt(4);
                Integer jobAnnualSalary = cursor.getInt(5);
                Integer jobAnnualBonus = cursor.getInt(6);
                Integer jobRetirementBenefits = cursor.getInt(7);
                Integer jobRelocationStipend = cursor.getInt(8);
                Integer jobTrainingAndDevFund = cursor.getInt(9);
                boolean currentJobIndicator = cursor.getInt(10) == 1 ? true: false;

                // Omit job score (this should be hidden)

                JobDetails listedJob = new JobDetails(jobTitle,jobCompanyName,jobLocation,
                        jobCostOfLiving,jobAnnualSalary,jobAnnualBonus,jobRetirementBenefits,
                        jobRelocationStipend,jobTrainingAndDevFund,currentJobIndicator);

            } while (cursor.moveToNext());
        } else {

            // Empty list
        }

        //close db connection
        cursor.close();
        appDB.close();

        return returnedJobOffers;
    }
}
