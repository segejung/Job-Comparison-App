package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button enterJobOfferBtn;
    private Button updateCurrentJobBtn;
    private Button adjustSettingsBtn;
    private Button compareJobsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterJobOfferBtn = (Button)findViewById(R.id.JobOfferButtonId);
        enterJobOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJobEntryActivity();
            }
        });
        updateCurrentJobBtn = (Button)findViewById(R.id.currentJobButtonId);
        adjustSettingsBtn = (Button)findViewById(R.id.settingsButtonId);
        compareJobsBtn = (Button)findViewById(R.id.compareJobsButtonId);
    }

    public void openJobEntryActivity() {
        Intent intent = new Intent(this,Job_Entry.class);
        startActivity(intent);
    }




}