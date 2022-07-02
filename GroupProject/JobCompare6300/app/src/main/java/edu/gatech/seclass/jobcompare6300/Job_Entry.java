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

                JobDetails jobOfferDetails; // Makes it before the try catch
                // Creating a job with the entered details
                try { // Try catch for any input exceptions

                    jobOfferDetails = new JobDetails(
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


                    //saveToTheSQLiteDB();
                    Toast.makeText(Job_Entry.this,
                            "Job Saved", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                    Toast.makeText(Job_Entry.this,
                            "Exception caught and data not saved.",
                            Toast.LENGTH_SHORT).show();
                    jobOfferDetails = new JobDetails("err","err","err",
                            -1,-1,-1,-1,
                            -1,-1,false);

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Job_Entry.this);
                boolean dbSuccess = dataBaseHelper.addOne(jobOfferDetails);

                Toast.makeText(Job_Entry.this,
                        "Successfully added to db ?" + dbSuccess,Toast.LENGTH_SHORT).show();

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
    }

    public void openMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    public void saveToTheSQLiteDB() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//
//    } Not used, database helper does it




}