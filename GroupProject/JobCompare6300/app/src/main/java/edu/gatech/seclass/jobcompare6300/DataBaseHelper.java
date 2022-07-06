package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

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
    public static final String JOB_WEIGHT_TABLE = "JOB_WEIGHT_TABLE";
    public static final String COLUMN_AYS = "COLUMN_AYS";
    public static final String COLUMN_AYB = "COLUMN_AYB";
    public static final String COLUMN_RBP = "COLUMN_RBP";
    public static final String COLUMN_RS = "COLUMN_RS";
    public static final String COLUMN_TDF = "COLUMN_TDF";
    public static final String COLUMN_AYS_VAL = "COLUMN_AYS_VAL";
    public static final String COLUMN_AYB_VAL = "COLUMN_AYB_VAL";
    public static final String COLUMN_RB_VAL = "COLUMN_RB_VAL";
    public static final String COLUMN_RA_VAL = "COLUMN_RA_VAL";
    public static final String COLUMN_TDF_VAL = "COLUMN_TDF_VAL";

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


        // TODO: Create a SQL Table Statement for the weights
        String createDBWeightTableStatement = "CREATE TABLE " + JOB_WEIGHT_TABLE + " (" + COLUMN_AYS +
                " FLOAT, " + COLUMN_AYB + " FLOAT, " + COLUMN_RBP + " FLOAT, " + COLUMN_RS + " FLOAT, " +
                COLUMN_TDF + " FLOAT)";

        String createRankTableStatement =
                "CREATE TABLE JOB_CALC_WEIGHTS_TABLE (JOB_ID INTEGER FOREIGN KEY, " + COLUMN_AYS_VAL +
                        " INTEGER, " + COLUMN_AYB_VAL + " INTEGER, " + COLUMN_RB_VAL +
                        " INTEGER, " + COLUMN_RA_VAL + " INTEGER, " + COLUMN_TDF_VAL + " INTEGER)";
//
        db.execSQL(createDBTableStatement);
        db.execSQL(createDBWeightTableStatement);
        db.execSQL(createRankTableStatement);
//
    }

    // Version Update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // TODO: Create a method that performs the

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

        if (insert == -1) { // Kicks back negative if it is a bad insert
            return false;
        } else {
            return true;
        }

    }

    //TODO: Complete internal updating the weights via the database.

    public boolean changeWeights(int AYS, int AYB, int RB, int RA, int TDF) {

        SQLiteDatabase appDB = this.getWritableDatabase();


        ContentValues weights_cv = new ContentValues();
        weights_cv.put(COLUMN_AYS,AYS);
        weights_cv.put(COLUMN_AYB,AYB);
        weights_cv.put(COLUMN_RETIREMENT_BENEFITS,RB);
        weights_cv.put(COLUMN_RELOCATION_AMOUNT,RA);
        weights_cv.put(COLUMN_TDF,TDF);

        long insert = appDB.insert(JOB_WEIGHT_TABLE, null , weights_cv);

        if (insert == -1) { // Kicks back negative if it is a bad insert
            return false;
        } else {
            return true;
        }
    }


    // TODO: Complete the job weight retrieval function from the SQL table

    public double[] getJobWeights() {

        double[] weights = new double[5];

        String queryRequestStr = "SELECT * FROM " + JOB_WEIGHT_TABLE;
        SQLiteDatabase appDB = this.getReadableDatabase();

        Cursor weightCursor = appDB.rawQuery(queryRequestStr,null);


        if(weightCursor.moveToFirst()) {

            do {
                Integer AYS_weight = weightCursor.getInt(1);
                Integer AYB_weight = weightCursor.getInt(2);
                Integer RB_weight = weightCursor.getInt(3);
                Integer RA_weight = weightCursor.getInt(4);
                Integer TDF_weight = weightCursor.getInt(5);
            } while (weightCursor.moveToNext());

        } else {

        }
        weightCursor.close();
        appDB.close();

        return weights;
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
                returnedJobOffers.add(listedJob);

            } while (cursor.moveToNext());
        } else {

            // Empty list
        }

        //close db connection
        cursor.close();
        appDB.close();

        return returnedJobOffers;
    }

    public List<Pair> getOffersWithIDs() {
        List<Pair> returnedJobOffersAndIDs = new ArrayList<>();

        // This code will pull data from the database

        String queryRequestStr = "SELECT * FROM " + JOB_OFFER_TABLE;
        SQLiteDatabase appDB = this.getReadableDatabase(); // We just want to read the database here.

        Cursor cursor = appDB.rawQuery(queryRequestStr,null);

        // TODO: Change this so it sorts by Job Score first and then does this iteration, it may be worth placing this in the a sort function to do prior to listing

        if(cursor.moveToFirst()) {
            // We want to iterate through the list here and create a JobDetails obj for each row
            do {
                Integer jobID = cursor.getInt(0);
                String jobTitle = cursor.getString(1);
//                String jobCompanyName = cursor.getString(2);
//                String jobLocation = cursor.getString(3);
//                Integer jobCostOfLiving = cursor.getInt(4);
//                Integer jobAnnualSalary = cursor.getInt(5);
//                Integer jobAnnualBonus = cursor.getInt(6);
//                Integer jobRetirementBenefits = cursor.getInt(7);
//                Integer jobRelocationStipend = cursor.getInt(8);
//                Integer jobTrainingAndDevFund = cursor.getInt(9);
//                boolean currentJobIndicator = cursor.getInt(10) == 1 ? true: false;

                // Omit job score (this should be hidden)

                Pair<Integer,String> jobPair = new Pair<Integer, String>(jobID,jobTitle);
                returnedJobOffersAndIDs.add(jobPair);

            } while (cursor.moveToNext());
        } else {

            // Empty list
        }

        //close db connection
        cursor.close();
        appDB.close();

        return returnedJobOffersAndIDs;
    }
}
