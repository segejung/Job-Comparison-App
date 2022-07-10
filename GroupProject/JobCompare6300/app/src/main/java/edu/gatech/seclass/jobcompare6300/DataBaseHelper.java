package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
    public static final String JOB_ID = "JOB_ID";
    public static final String JOB_CALC_WEIGHTS_TABLE = "JOB_CALC_WEIGHTS_TABLE";
    public static final Integer dbVersion = 2;
    public static final String COLUMN_STATUS = "COLUMN_STATUS";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "jobData.db", null, dbVersion);
    }

    // First Time the database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the Table
        String createDBTableStatement =
                "CREATE TABLE " + JOB_OFFER_TABLE + " (" + JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT, " +
                        COLUMN_COMPANY + " TEXT, " + COLUMN_LOCATION + " TEXT, " +
                        COLUMN_COST_OF_LIVING + " INTEGER, " + COLUMN_SALARY + " INTEGER, " + COLUMN_BONUS + " INTEGER, " +
                        COLUMN_RETIREMENT_BENEFITS + " INTEGER, " + COLUMN_RELOCATION_AMOUNT + " INTEGER, " +
                        COLUMN_TRAINING_FUND + " INTEGER, " + COLUMN_IS_CURRENT_JOB + " BOOLEAN, " + COLUMN_JOB_SCORE + " INTEGER)";


        String createDBWeightTableStatement = "CREATE TABLE " + JOB_WEIGHT_TABLE + " ("+ COLUMN_STATUS + " TEXT, " + COLUMN_AYS +
                " FLOAT, " + COLUMN_AYB + " FLOAT, "
                + COLUMN_RBP + " FLOAT, " + COLUMN_RS +
                " FLOAT, " +
                COLUMN_TDF + " FLOAT)";



        String createRankTableStatement =
                "CREATE TABLE " + JOB_CALC_WEIGHTS_TABLE + " (" + JOB_ID + " INTEGER PRIMARY KEY, " + COLUMN_AYS_VAL +
                        " INTEGER, " + COLUMN_AYB_VAL + " INTEGER, " + COLUMN_RB_VAL +
                        " INTEGER, " + COLUMN_RA_VAL + " INTEGER, " + COLUMN_TDF_VAL + " INTEGER)";
//
        db.execSQL(createDBTableStatement);
        db.execSQL(createDBWeightTableStatement);
        db.execSQL(createRankTableStatement);

        this.setWeightsDefault(db);
//
    }

    // Version Update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryStrMainTable = "DROP TABLE IF EXISTS " + JOB_OFFER_TABLE;
        String queryStrWeightTable = "DROP TABLE IF EXISTS " + JOB_WEIGHT_TABLE;
        String queryStrRankTable = "DROP TABLE IF EXISTS " + JOB_CALC_WEIGHTS_TABLE;
        db.execSQL(queryStrMainTable);
        db.execSQL(queryStrWeightTable);
        db.execSQL(queryStrRankTable);
        onCreate(db);

    }

    public boolean checkForEnoughJobsToCompare() {
        boolean goodForComparison;
        SQLiteDatabase appDB = this.getReadableDatabase();

        long numberOfRows = DatabaseUtils.queryNumEntries(appDB,JOB_OFFER_TABLE);

        if (numberOfRows >= 2) {
            goodForComparison = true;
        } else {
            goodForComparison = false;
        }

        return goodForComparison;


    }


//    public boolean deleteDatabaseEntry(JobRankDetails jobObj)
//    {
//        //TODO: Find Customer Model in DB and then delete it
//        SQLiteDatabase db = this.getWritableDatabase();
//        String queryStr = "DELETE FROM " +JOB_OFFER_TABLE + "WHERE " + JOB_ID + " = "
//
//
//    }


    public double computeJobScore(JobRankDetails jobToCompute) {
        double[] jobWeights = new double[5];
       jobWeights = this.getJobWeights();
        double salaryWeight = jobWeights[0];
        double bonusWeight = jobWeights[1];
        double retirementWeight = jobWeights[2];
        double relocationWeight= jobWeights[3];
        double trainingWeight = jobWeights[4];


        double sumWeights = salaryWeight + bonusWeight + retirementWeight + relocationWeight + trainingWeight;

        double jobScoreCalculated = 0;

            double AYSval = ((double) jobToCompute.getSalary()) / (((double) jobToCompute.getCostOfLiving()) / 100.0);
            double AYBval = ((double) jobToCompute.getBonus()) / (((double) jobToCompute.getCostOfLiving()) / 100.0);
            double RBPval = (double) jobToCompute.getRetirementBenefits();
            double RSval = (double) jobToCompute.getRelocationStipend();
            double TDFval = (double) jobToCompute.getTrainingAndDevelopmentFund();

            jobScoreCalculated = AYSval * ((double) salaryWeight / sumWeights)
                    + AYBval * ((double) bonusWeight / sumWeights)
                    + (RBPval * AYSval / 100.0) * ((double) retirementWeight / sumWeights)
                    + RSval * ((double) relocationWeight / sumWeights)
                    + TDFval * ((double) trainingWeight / sumWeights);



        return jobScoreCalculated;
    }

    // overload the method to decouple computation from db for unit testing
    public double computeJobScore(JobRankDetails jobToCompute, double[] jobWeights) {
        double salaryWeight = jobWeights[0];
        double bonusWeight = jobWeights[1];
        double retirementWeight = jobWeights[2];
        double relocationWeight= jobWeights[3];
        double trainingWeight = jobWeights[4];


        double sumWeights = salaryWeight + bonusWeight + retirementWeight + relocationWeight + trainingWeight;

        double jobScoreCalculated = 0;

        double AYSval = ((double) jobToCompute.getSalary()) / (((double) jobToCompute.getCostOfLiving()) / 100.0);
        double AYBval = ((double) jobToCompute.getBonus()) / (((double) jobToCompute.getCostOfLiving()) / 100.0);
        double RBPval = (double) jobToCompute.getRetirementBenefits();
        double RSval = (double) jobToCompute.getRelocationStipend();
        double TDFval = (double) jobToCompute.getTrainingAndDevelopmentFund();

        jobScoreCalculated = AYSval * ((double) salaryWeight / sumWeights)
                + AYBval * ((double) bonusWeight / sumWeights)
                + (RBPval * AYSval / 100.0) * ((double) retirementWeight / sumWeights)
                + RSval * ((double) relocationWeight / sumWeights)
                + TDFval * ((double) trainingWeight / sumWeights);
        return jobScoreCalculated;
    }

    //Adding items to the database
    public boolean addOne(JobRankDetails jobDetails) {

        //Perform job calculation first so database can be disconnected from before it is written to
        Double currentJobScore = this.computeJobScore(jobDetails);
        // This is necessary due to the just in time calculations

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
        cv.put(COLUMN_IS_CURRENT_JOB,jobDetails.getIsCurrentJob());
        cv.put(COLUMN_JOB_SCORE,currentJobScore);

        long insert = appDB.insert(JOB_OFFER_TABLE, null , cv);

        if (insert == -1) { // Kicks back negative if it is a bad insert
            return false;
        } else {
            return true;
        }

    }


    public void setWeightsDefault(SQLiteDatabase appDB)
    {

        ContentValues defaults_cv = new ContentValues();
        defaults_cv.put(COLUMN_STATUS,"CURRENT");
        defaults_cv.put(COLUMN_AYS,1.0);
        defaults_cv.put(COLUMN_AYB,1.0);
        defaults_cv.put(COLUMN_RBP,1.0);
        defaults_cv.put(COLUMN_RS,1.0);
        defaults_cv.put(COLUMN_TDF,1.0);


        long insert = appDB.insert(JOB_WEIGHT_TABLE, null , defaults_cv);


//        Toast.makeText(Settings.class,"Default Settings applied: " + dbSuccess,Toast.LENGTH_SHORT);
    }

    public boolean removeCurrentJobStatusInDB() {
        SQLiteDatabase appDB = this.getWritableDatabase();
        ContentValues jobStatus_cv = new ContentValues();
        jobStatus_cv.put(COLUMN_IS_CURRENT_JOB, 0);
        long insert = appDB.update(JOB_OFFER_TABLE,jobStatus_cv,"COLUMN_IS_CURRENT_JOB=?",new String[]{"1"});

        if (insert == -1) { // Kicks back negative if it is a bad insert
            return false;
        } else {
            return true;
        }


    }

    public boolean changeWeightsInDB(Float[] settingsWeights) {

        float AYS = settingsWeights[0];
        float AYB = settingsWeights[1];
        float RBP = settingsWeights[2];
        float RS = settingsWeights[3];
        float TDF = settingsWeights[4];

        SQLiteDatabase appDB = this.getWritableDatabase();


        ContentValues weights_cv = new ContentValues();
        weights_cv.put(COLUMN_AYS,AYS);
        weights_cv.put(COLUMN_AYB,AYB);
        weights_cv.put(COLUMN_RBP,RBP);
        weights_cv.put(COLUMN_RS,RS);
        weights_cv.put(COLUMN_TDF,TDF);

        long insert = appDB.update(JOB_WEIGHT_TABLE,weights_cv,"COLUMN_STATUS=?",new String[]{"CURRENT"});



        //long insert = appDB.insert(JOB_WEIGHT_TABLE, null , weights_cv);

        if (insert == -1) { // Kicks back negative if it is a bad insert
            return false;
        } else {
            return true;
        }
    }




    public double[] getJobWeights() {

        double[] weights = new double[5];

        String queryRequestStr = "SELECT * FROM " + JOB_WEIGHT_TABLE;
        SQLiteDatabase appDB = this.getReadableDatabase();

        Cursor weightCursor = appDB.rawQuery(queryRequestStr,null);


        if(weightCursor.moveToFirst()) {

            do {
                Float AYS_weight = weightCursor.getFloat(1);
                Float AYB_weight = weightCursor.getFloat(2);
                Float RB_weight = weightCursor.getFloat(3);
                Float RA_weight = weightCursor.getFloat(4);
                Float TDF_weight = weightCursor.getFloat(5);

                weights[0] = AYS_weight;
                weights[1] = AYB_weight;
                weights[2] = RB_weight;
                weights[3] = RA_weight;
                weights[4] = TDF_weight;
            } while (weightCursor.moveToNext());

        } else {

        }
        weightCursor.close();
        appDB.close();

        return weights;
    }

    public boolean checkForACurrentJobInTheDB()
    {
        SQLiteDatabase appDB = this.getReadableDatabase();
        boolean isThereACurrentJob;

        String currentJobCheckQuery = "SELECT * FROM " + JOB_OFFER_TABLE + " WHERE " + COLUMN_IS_CURRENT_JOB + " =?";
        String[] args = {"1"};
        Cursor cursor = appDB.rawQuery(currentJobCheckQuery,args);

        if (cursor != null) {
            isThereACurrentJob = true;
        } else {
            isThereACurrentJob = false;
        }

        return isThereACurrentJob;
    }

    public JobRankDetails getCurrentJobInTheDB() {
        SQLiteDatabase appDB = this.getReadableDatabase();
        JobRankDetails yourCurrentJob;

        String currentJobCheckQuery = "SELECT * FROM " + JOB_OFFER_TABLE + " WHERE " + COLUMN_IS_CURRENT_JOB + " =?";
        String[] args = {"1"};
        Cursor cursor = appDB.rawQuery(currentJobCheckQuery, args);

        if (cursor.moveToFirst()) {

            do {

                Integer jobID = cursor.getInt(0);
                String jobTitle = cursor.getString(1);
                String jobCompanyName = cursor.getString(2);
                String jobLocation = cursor.getString(3);
                Integer jobCostOfLiving = cursor.getInt(4);
                Integer jobAnnualSalary = cursor.getInt(5);
                Integer jobAnnualBonus = cursor.getInt(6);
                Integer jobRetirementBenefits = cursor.getInt(7);
                Integer jobRelocationStipend = cursor.getInt(8);
                Integer jobTrainingAndDevFund = cursor.getInt(9);
                boolean currentJobIndicator = cursor.getInt(10) == 1 ? true : false;
                Double jobScore = cursor.getDouble(11);

                yourCurrentJob = new JobRankDetails(jobTitle, jobCompanyName, jobLocation,
                        jobCostOfLiving, jobAnnualSalary, jobAnnualBonus, jobRetirementBenefits,
                        jobRelocationStipend, jobTrainingAndDevFund, currentJobIndicator);

            } while (cursor.moveToNext());

        } else {

            yourCurrentJob = new JobRankDetails(); // Empty constructor (but shouldn't happen)

        }
        cursor.close();
        appDB.close();

        return yourCurrentJob;
    }

    public void updateJobScores() {
        SQLiteDatabase appDB = this.getReadableDatabase();

        JobRankDetails jobToBeUpdated;

        HashMap<Integer,JobRankDetails> jobsToBeUpdatedWithIDs = new HashMap<Integer, JobRankDetails>();

        String jobScoreQuery = "SELECT * FROM " + JOB_OFFER_TABLE;

        Cursor cursor = appDB.rawQuery(jobScoreQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Integer jobID = cursor.getInt(0);
                String jobTitle = cursor.getString(1);
                String jobCompanyName = cursor.getString(2);
                String jobLocation = cursor.getString(3);
                Integer jobCostOfLiving = cursor.getInt(4);
                Integer jobAnnualSalary = cursor.getInt(5);
                Integer jobAnnualBonus = cursor.getInt(6);
                Integer jobRetirementBenefits = cursor.getInt(7);
                Integer jobRelocationStipend = cursor.getInt(8);
                Integer jobTrainingAndDevFund = cursor.getInt(9);
                boolean currentJobIndicator = cursor.getInt(10) == 1 ? true : false;
                Double jobScore = cursor.getDouble(11);

                jobToBeUpdated = new JobRankDetails(jobTitle, jobCompanyName, jobLocation,
                        jobCostOfLiving, jobAnnualSalary, jobAnnualBonus, jobRetirementBenefits,
                        jobRelocationStipend, jobTrainingAndDevFund, currentJobIndicator);
                jobToBeUpdated.setJobScore(jobScore);
                jobsToBeUpdatedWithIDs.put(jobID, jobToBeUpdated);

            } while (cursor.moveToNext());

        }else {
            // Empty

        }

        cursor.close();
        appDB.close();


        JobRankDetails jobToBeCalculated;
        Double updatedJobScore;

        // Calculation Loop

        for (Integer key : jobsToBeUpdatedWithIDs.keySet()) {
            jobToBeCalculated = jobsToBeUpdatedWithIDs.get(key);
            updatedJobScore = this.computeJobScore(jobToBeCalculated);
            //DB will be open and then closed for the computing the new job scores
            jobToBeCalculated.setJobScore(updatedJobScore);
            jobsToBeUpdatedWithIDs.put(key,jobToBeCalculated);
        }

        SQLiteDatabase appDBWrite = this.getWritableDatabase();

        JobRankDetails updatedJob;


        // Update loop
        String whereArgs;

        for (Integer key : jobsToBeUpdatedWithIDs.keySet()) {

            ContentValues updateScores_cv = new ContentValues();
            updatedJob = jobsToBeUpdatedWithIDs.get(key);

            updateScores_cv.put(COLUMN_JOB_SCORE,updatedJob.getJobScore());
            whereArgs = String.valueOf(key);

            appDBWrite.update(JOB_OFFER_TABLE,updateScores_cv,"JOB_ID=?",new String[]{whereArgs});


        }



    }


    /* returns a new JobDetails object with its details from the matching job ID */
    public JobDetails getJobDetailsWithId(int jobId) {
        JobDetails matchedJob = null;

        String queryRequestStr = "SELECT * FROM " + JOB_OFFER_TABLE
                + " WHERE " + JOB_ID + " = " + String.valueOf(jobId);
        SQLiteDatabase appDB = this.getReadableDatabase();

        Cursor cursor = appDB.rawQuery(queryRequestStr, null);

        if (cursor.moveToFirst()) {
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

            matchedJob = new JobDetails(jobTitle,jobCompanyName,jobLocation,
                    jobCostOfLiving,jobAnnualSalary,jobAnnualBonus,jobRetirementBenefits,
                    jobRelocationStipend,jobTrainingAndDevFund,currentJobIndicator);
        }

        cursor.close();
        appDB.close();

        return matchedJob;
    }

    public List<JobRankDetails> getOffers() {
        List<JobRankDetails> returnedJobOffers = new ArrayList<>();

        // This code will pull data from the database

        String queryRequestStr = "SELECT * FROM " + JOB_OFFER_TABLE;
        SQLiteDatabase appDB = this.getReadableDatabase(); // We just want to read the database here.

        Cursor cursor = appDB.rawQuery(queryRequestStr,null);
        double calculatedJobScore = 0.0;
        DecimalFormat df = new DecimalFormat("#.##");


        if(cursor.moveToFirst()) {
            // We want to iterate through the list here and create a JobRankDetails obj for each row
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
                calculatedJobScore = cursor.getDouble(11);

                // Omit job score (this should be hidden)

                JobRankDetails listedJob = new JobRankDetails(jobTitle,jobCompanyName,jobLocation,
                        jobCostOfLiving,jobAnnualSalary,jobAnnualBonus,jobRetirementBenefits,
                        jobRelocationStipend,jobTrainingAndDevFund,currentJobIndicator);
//                calculatedJobScore = this.computeJobScore(listedJob);

                listedJob.setJobScore(Double.valueOf(df.format(calculatedJobScore)));
                returnedJobOffers.add(listedJob);

            } while (cursor.moveToNext());
        } else {

            // Empty list
        }

        //close db connection
        cursor.close();
        appDB.close();

        //Sorting action for the list of offers
        Collections.sort(returnedJobOffers, new Comparator<JobRankDetails>() {
            public int compare(JobRankDetails j1, JobRankDetails j2) {
                return Double.compare(j1.getJobScore(),j2.getJobScore());
            }
        }.reversed()); // This was clever lol, don't know how I missed that, guess I was tired. - Nelson R.

        return returnedJobOffers;
    }

    public List<JobRankDetails> getJobDetails() {
        List<JobRankDetails> returnedJobObjects = new ArrayList<>();

        String queryRequestStr = "SELECT * FROM " + JOB_OFFER_TABLE;
        SQLiteDatabase appDB = this.getReadableDatabase(); // We just want to read the database here.

        Cursor cursor = appDB.rawQuery(queryRequestStr,null);


        if(cursor.moveToFirst()) {
            // We want to iterate through the list here and create a JobDetails obj for each row
            do {
                Integer jobID = cursor.getInt(0);
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

                JobRankDetails jobObj = new JobRankDetails(jobTitle,jobCompanyName,jobLocation,
                        jobCostOfLiving,jobAnnualSalary,jobAnnualBonus,jobRetirementBenefits,
                        jobRelocationStipend,jobTrainingAndDevFund,currentJobIndicator);

                returnedJobObjects.add(jobObj);

            } while (cursor.moveToNext());
        } else {

            // Empty list
        }

        //close db connection
        cursor.close();
        appDB.close();

        return returnedJobObjects;
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
