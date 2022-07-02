package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Current_Job extends AppCompatActivity {

    private Button cancelCurrentJob;
    private Button saveCurrentJob;
    private EditText titleEntryField;
    private EditText companyEntryField;
    private EditText locationEntryField;
    private EditText costOfLivingEntryField;
    private EditText annualSalaryEntryField;
    private EditText annualBonusEntryField;
    private EditText retirementBenefitsEntryField;
    private EditText relocationStipendEntryField;
    private EditText trainingFundEntryField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__current__job);

        titleEntryField = findViewById(R.id.titleInputTextId);

        cancelCurrentJob = (Button)findViewById(R.id.cancelCurrentJobButtonId);
        cancelCurrentJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelCurrentJobEntry();
                Toast.makeText(Update_Current_Job.this,"Cancel Action", Toast.LENGTH_SHORT).show();
            }
        });

        saveCurrentJob = (Button)findViewById(R.id.saveCurrentJobButtonId);
        saveCurrentJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Creating a job with the entered details
                try { // Try catch for any input exceptions


                    JobDetails jobOfferDetails = new JobDetails(
                            titleEntryField.getText().toString(),
                            companyEntryField.getText().toString(),
                            locationEntryField.getText().toString(),
                            Integer.parseInt(costOfLivingEntryField.getText().toString()),
                            Integer.parseInt(annualSalaryEntryField.getText().toString()),
                            Integer.parseInt(annualBonusEntryField.getText().toString()),
                            Integer.parseInt(retirementBenefitsEntryField.getText().toString()),
                            Integer.parseInt(relocationStipendEntryField.getText().toString()),
                            Integer.parseInt(trainingFundEntryField.getText().toString()));

                    //saveCurrentJobEntry();
                    Toast.makeText(Update_Current_Job.this,
                            "New Current Job Saved", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                    Toast.makeText(Update_Current_Job.this,
                            "Exception caught and data not saved.",
                            Toast.LENGTH_SHORT).show();

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Update_Current_Job.this);

            }
        });
    }

    public void cancelCurrentJobEntry() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void saveCurrentJobEntry() {


    }




}