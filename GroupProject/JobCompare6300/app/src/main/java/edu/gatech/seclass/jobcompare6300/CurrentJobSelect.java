package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CurrentJobSelect extends AppCompatActivity {

    private Button returnToJobEntryPageBtn;
    private Button selectNewCurrentJobFromSpinnerBtn;
    private Spinner nonCurrentJobsSpinner;
    private ListView nonCurrentJobsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job_select);

        returnToJobEntryPageBtn = findViewById(R.id.returnToJobEntryFromCurrentSelectorBtn);
        returnToJobEntryPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheJobEntryPage();
            }
        });

        selectNewCurrentJobFromSpinnerBtn = findViewById(R.id.selectCurrentFromPriorEntriesBtn);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(CurrentJobSelect.this);
        nonCurrentJobsList = findViewById(R.id.jobSelectorForCurrentJobView);

        try {
            List<JobRankDetails> nonCurrentJobs = dataBaseHelper.getNonCurrentOffers();

            List<String> jobListHeaders = new ArrayList<String>();
            for (JobRankDetails j : nonCurrentJobs) {
                jobListHeaders.add(j.getTitle() + ", " + j.getCompany());
            }

            ArrayAdapter jobOfferListArrayAdapter = new ArrayAdapter<String>(CurrentJobSelect.this,
                    android.R.layout.simple_list_item_1, jobListHeaders);
            nonCurrentJobsList.setAdapter(jobOfferListArrayAdapter);

            nonCurrentJobsSpinner = findViewById(R.id.currentJobSelectorSpinner);


            // Creating the spinners once you try to view the jobs
            List<Pair> jobPairs1 = dataBaseHelper.getOffersWithIDs();
            createNonCurrentJobSpinnerList(jobPairs1,nonCurrentJobsSpinner);

        } catch (Exception e) {
            Toast.makeText(CurrentJobSelect.this, "Jobs from DB were not displayed, error occured.",
                    Toast.LENGTH_SHORT).show();

            Toast.makeText(CurrentJobSelect.this, "Spinners could not be made.",
                    Toast.LENGTH_SHORT).show();
        }

        selectNewCurrentJobFromSpinnerBtn = findViewById(R.id.currentJobSelectorBtn);
        selectNewCurrentJobFromSpinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setANewCurrentJob();
            }
        });

    }

    public void createNonCurrentJobSpinnerList(List<Pair> nonCurrentJobsFromDB, Spinner spinnerToChange) {
        ArrayAdapter<Pair> adapter = new ArrayAdapter<Pair>(this,android.R.layout.simple_spinner_dropdown_item, (List<Pair>) nonCurrentJobsFromDB);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToChange.setAdapter(adapter);


    }

    public void setANewCurrentJob() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(CurrentJobSelect.this);
        boolean removalSuccess = dataBaseHelper.removeCurrentJobStatusInDB();
        JobRankDetails jobSelected;

        if (removalSuccess) {


            String job1Str = nonCurrentJobsSpinner.getSelectedItem().toString();
            String job1IdStr = job1Str.split(" ", 2)[0].replaceAll("[\\D]", "");
            int job1Id = Integer.valueOf(job1IdStr);
            dataBaseHelper.setNewCurrentJob(job1Id);

            Toast.makeText(CurrentJobSelect.this,"New Current Job set",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(CurrentJobSelect.this,"None of the jobs held were current.",Toast.LENGTH_SHORT).show();
        }
    }



    public void openTheJobEntryPage() {
        Intent intent = new Intent(this,Job_Entry.class);
        startActivity(intent);
        Toast.makeText(CurrentJobSelect.this,"Returning to Job Entry",
                Toast.LENGTH_SHORT).show();
    }
}