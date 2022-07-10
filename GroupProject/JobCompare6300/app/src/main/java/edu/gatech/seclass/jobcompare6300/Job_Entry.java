package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Job_Entry extends AppCompatActivity {

    //public SQLiteDatabase appDB;
    private Button saveBtn;
    private Button cancelBtn;
    private EditText titleEntryField;
    private EditText companyEntryField;
    private EditText locationEntryField;
    private EditText costOfLivingEntryField;
    private EditText annualSalaryEntryField;
    private EditText annualBonusEntryField;
    private EditText retirementBenefitsEntryField;
    private EditText relocationStipendEntryField;
    private EditText trainingFundEntryField;
    private Switch switchForCurrentJobIndicator;
    private Button enterAnotherOneBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__entry);

        //appDB = openOrCreateDatabase("JobData.db",SQLiteDatabase.CREATE_IF_NECESSARY ,null);

        // Assigning the data in the text fields to the private variables
        titleEntryField = findViewById(R.id.TitleEntryField);
        companyEntryField = findViewById(R.id.CompanyEntryField);
        locationEntryField = findViewById(R.id.LocationEntryField);
        costOfLivingEntryField = findViewById(R.id.CostOfLivingEntryField);
        annualSalaryEntryField = findViewById(R.id.SalaryEntryField);
        annualBonusEntryField = findViewById(R.id.BonusEntryField);
        retirementBenefitsEntryField = findViewById(R.id.RetirementBenefitsEntryField);
        relocationStipendEntryField = findViewById(R.id.RelocationStipendEntryField);
        trainingFundEntryField = findViewById(R.id.TrainingFundEntryField);
        switchForCurrentJobIndicator = findViewById(R.id.currentJobSwitch);



        saveBtn = findViewById(R.id.saveJobEntryBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Job_Entry.this, "I do something", Toast.LENGTH_LONG).show();

                JobRankDetails jobOfferDetails; // Makes it before the try catch
                // Creating a job with the entered details
                DataBaseHelper dataBaseHelper = new DataBaseHelper(Job_Entry.this);

                try { // Try catch for any input exceptions

                    if (!isValidLocationInput(locationEntryField.getText().toString())) {
                        locationEntryField.setError("Invalid location");
                        throw new IllegalArgumentException("Invalid location entry");
                    }

                    if (!isValidColIndexInput(costOfLivingEntryField.getText().toString())) {
                        costOfLivingEntryField.setError("Invalid Cost of Living Index");
                        throw new IllegalArgumentException("Invalid Cost of Living Index");
                    }

                    if (!isValidRetirementBenefit(retirementBenefitsEntryField.getText().toString())) {
                        retirementBenefitsEntryField.setError("Invalid Retirement Benefits");
                        throw new IllegalArgumentException("Invalid Retirement Benefits");
                    }

                    if (!isValidTrainingFund(trainingFundEntryField.getText().toString())) {
                        trainingFundEntryField.setError("Invalid Training Fund Amount");
                        throw new IllegalArgumentException("Invalid Training Fund Amount");
                    }

                    jobOfferDetails = new JobRankDetails(
                            titleEntryField.getText().toString(),
                            companyEntryField.getText().toString(),
                            locationEntryField.getText().toString(),
                            Integer.parseInt(costOfLivingEntryField.getText().toString()),
                            Integer.parseInt(annualSalaryEntryField.getText().toString()),
                            Integer.parseInt(annualBonusEntryField.getText().toString()),
                            Integer.parseInt(retirementBenefitsEntryField.getText().toString()),
                            Integer.parseInt(relocationStipendEntryField.getText().toString()),
                            Integer.parseInt(trainingFundEntryField.getText().toString()),
                            switchForCurrentJobIndicator.isChecked());

                    if (switchForCurrentJobIndicator.isChecked()){
                        dataBaseHelper.removeCurrentJobStatusInDB();

                        Toast.makeText(Job_Entry.this,
                                "New Current Job Saved", Toast.LENGTH_SHORT).show();

                    }

                    boolean dbSuccess = dataBaseHelper.addOne(jobOfferDetails);

                    Toast.makeText(Job_Entry.this,
                            "Successfully added to db ? " + dbSuccess,Toast.LENGTH_SHORT).show();


                    //saveToTheSQLiteDB();
                    Toast.makeText(Job_Entry.this,
                            "Job Saved", Toast.LENGTH_SHORT).show();

                    openMainMenu();

                } catch (IllegalArgumentException e) {
                    Toast.makeText(Job_Entry.this,
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                    Toast.makeText(Job_Entry.this,
                            "Exception caught and data not saved.",
                            Toast.LENGTH_SHORT).show();

                    Toast.makeText(Job_Entry.this,
                            "No successful add due to prior exception.",Toast.LENGTH_SHORT).show();

                }



                // Note if toast responses are not working just clear user data and try again.
                // Helpful link: https://www.youtube.com/watch?v=ZK3_ib-g_no&ab_channel=CodingPursuits


            }
        });

        cancelBtn = findViewById(R.id.cancelJobEntryBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMainMenu();
                Toast.makeText(Job_Entry.this,"Cancel Action", Toast.LENGTH_SHORT).show();
            }


        });

        enterAnotherOneBtn = findViewById(R.id.enterAnotherBtn);
        enterAnotherOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearJobTextFields();

            }
        });
    }

    public void openMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean isValidLocationInput(String location) {
        final Pattern pattern = Pattern.compile("^[A-Za-z, \\-]+$");
        return pattern.matcher(location).matches();
    }

    public boolean isValidColIndexInput (String costOfLiving) {
        int colIndex = Integer.parseInt(costOfLiving);
        return (colIndex >= 1) && (colIndex <= 500);
    }

    public boolean isValidRetirementBenefit (String retirementBenefit) {
        int benefitPercentage = Integer.parseInt(retirementBenefit);
        return (benefitPercentage >= 0) && (benefitPercentage <= 100);
    }

    public boolean isValidTrainingFund (String trainingFund) {
        int fundAmount = Integer.parseInt(trainingFund);
        return (fundAmount >= 0) && (fundAmount <= 18000);
    }

    public void clearJobTextFields() {

        //Clears all text fields
        titleEntryField.setText("");
        companyEntryField.setText("");
        locationEntryField.setText("");
        costOfLivingEntryField.setText("");
        annualSalaryEntryField.setText("");
        annualBonusEntryField.setText("");
        retirementBenefitsEntryField.setText("");
        relocationStipendEntryField.setText("");
        trainingFundEntryField.setText("");

        //Undo switch even if it was true
        switchForCurrentJobIndicator.setChecked(false);


    }

//    public void saveToTheSQLiteDB() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//
//    } Not used, database helper does it




}