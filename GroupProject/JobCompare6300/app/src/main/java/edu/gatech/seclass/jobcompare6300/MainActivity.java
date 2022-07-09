package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    //public SQLiteDatabase appDB;
    private Button enterJobOfferBtn;
    private Button updateCurrentJobBtn;
    private Button adjustSettingsBtn;
    private Button compareJobsBtn;
    private boolean enableJobComparison;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //appDB = openOrCreateDatabase("JobData.db",SQLiteDatabase.CREATE_IF_NECESSARY ,null);

        enterJobOfferBtn = (Button)findViewById(R.id.JobOfferButtonId);
        enterJobOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJobEntryActivity();
            }
        });

        updateCurrentJobBtn = (Button)findViewById(R.id.currentJobButtonId);
        updateCurrentJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateCurrentJobActivity();
            }
        });


        adjustSettingsBtn = (Button)findViewById(R.id.settingsButtonId);
        adjustSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdjustSettingsActivity();
            }
        });

        compareJobsBtn = (Button)findViewById(R.id.compareJobsButtonId);

        enableJobComparison = dataBaseHelper.checkForEnoughJobsToCompare();

        if (enableJobComparison == false) {
            compareJobsBtn.setVisibility(View.INVISIBLE);
        }


        compareJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllJobsDisplayActivity();
            }
        });
    }

    public void openJobEntryActivity() {
        Intent intent = new Intent(this,Job_Entry.class);
        startActivity(intent);
    }

    public void openAllJobsDisplayActivity() {
        Intent intent = new Intent(this,All_Jobs_Display.class);
        startActivity(intent);
    }

    public void openAdjustSettingsActivity() {
        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);

        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_settings);

    }

    public void openUpdateCurrentJobActivity() {
        Intent intent = new Intent(this,Update_Current_Job.class);
        startActivity(intent);

        setContentView(R.layout.activity_update__current__job);
    }




}