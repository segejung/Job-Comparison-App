package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Pair;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class All_Jobs_Display extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button viewAllBtn;
    private Button compareSelectedBtn;
    private Button cancelCompareBtn;
    private ListView jobListDisplay;
    private Spinner job1Spinner;
    private Spinner job2Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__jobs__display);

        jobListDisplay = findViewById(R.id.jobListEntries);

        job1Spinner = (Spinner) findViewById(R.id.job1SelectionSpinner);
        job2Spinner = (Spinner) findViewById(R.id.job2SelectionSpinner);

        viewAllBtn = (Button)findViewById(R.id.viewJobsBtn);
        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper databaseHelper = new DataBaseHelper(All_Jobs_Display.this);

                try {
                    List<JobDetails> jobListEntries = databaseHelper.getOffers();

                    ArrayAdapter jobOfferListArrayAdapter = new ArrayAdapter<JobDetails>(All_Jobs_Display.this,
                            android.R.layout.simple_list_item_1,jobListEntries);
                    jobListDisplay.setAdapter(jobOfferListArrayAdapter);

                    //Toast.makeText(All_Jobs_Display.this,jobListEntries.toString(),Toast.LENGTH_SHORT).show();

                    Toast.makeText(All_Jobs_Display.this,"Jobs from DB are displayed",
                            Toast.LENGTH_SHORT).show();

                    // Creating the spinners once you try to view the jobs
                    List<Pair> jobPairs1 = databaseHelper.getOffersWithIDs();
                    createJobSpinner1List(jobPairs1,job1Spinner);

                    List<Pair> jobPairs2 = databaseHelper.getOffersWithIDs();
                    createJobSpinner2List(jobPairs2,job2Spinner);

                    Toast.makeText(All_Jobs_Display.this,"Spinners populated, " +
                                    "please select jobs for comparison.",
                            Toast.LENGTH_SHORT).show();



                } catch (Exception e) {
                    Toast.makeText(All_Jobs_Display.this,"Jobs from DB were not displayed, error occured.",
                            Toast.LENGTH_SHORT).show();

                    Toast.makeText(All_Jobs_Display.this,"Spinners could not be made.",
                            Toast.LENGTH_SHORT).show();

                }






            }
        });



        compareSelectedBtn = findViewById(R.id.compareInitiateBtn);
        compareSelectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(All_Jobs_Display.this);
                pullItemOutOfSpinner1(dataBaseHelper.getOffersWithIDs());
                startComparison();
                Toast.makeText(All_Jobs_Display.this,"Starting comparison",
                        Toast.LENGTH_SHORT).show();
            }
        });


        cancelCompareBtn = findViewById(R.id.cancelBtnOnJobDisplay);
        cancelCompareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();
                Toast.makeText(All_Jobs_Display.this,"Returning to main menu",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void createJobSpinner1List(List<Pair> jobPairsFromDB, Spinner spinnerToChange) {
        ArrayAdapter<Pair> adapter = new ArrayAdapter<Pair>(this,android.R.layout.simple_spinner_dropdown_item, (List<Pair>) jobPairsFromDB);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToChange.setAdapter(adapter);
        spinnerToChange.setOnItemSelectedListener(this);

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(All_Jobs_Display.this);
        List<Pair>jobPairsFromDB = dataBaseHelper.getOffersWithIDs();
        Pair<Integer, JobDetails> job1PairSelection = jobPairsFromDB.get(position);
                Toast.makeText(All_Jobs_Display.this," You selected: " + job1PairSelection,Toast.LENGTH_SHORT);
            }
        public void onNothingSelected(AdapterView<?> parent) {

        }




    public void createJobSpinner2List(List<Pair> jobPairsFromDB, Spinner spinnerToChange) {
        ArrayAdapter<Pair> adapter = new ArrayAdapter<Pair>(this,android.R.layout.simple_spinner_dropdown_item, (List<Pair>) jobPairsFromDB);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToChange.setAdapter(adapter);
    }

    public void startComparison() {
        Intent intent = new Intent(this, Job_Compare.class);
        startActivity(intent);
    }

    public void returnToMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void pullItemOutOfSpinner1(List<Pair> jobPairsFromSpinner) {
        job1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Pair<Integer, JobDetails> job1PairSelection = jobPairsFromSpinner.get(position);
                Toast.makeText(All_Jobs_Display.this," You selected: " + job1PairSelection,Toast.LENGTH_SHORT);
                 //job1Selected = (Pair<Integer,JobDetails>) job1Selected;
                //job1Selected.toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}