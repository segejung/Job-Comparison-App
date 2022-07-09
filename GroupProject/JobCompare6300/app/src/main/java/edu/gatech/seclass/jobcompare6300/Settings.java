package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Button cancelBtn;
    private Button saveSettingsBtn;
    private SeekBar salarySeek;
    private SeekBar bonusSeek;
    private SeekBar retirementSeek;
    private SeekBar relocationSeek;
    private SeekBar trainingSeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        cancelBtn = (Button)findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();
            }
        });

        saveSettingsBtn = (Button)findViewById(R.id.saveSettingsButton);
        saveSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettingsForUser();
            }
        });
    }

    public void returnToMainMenu() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(Settings.this,"Returning to main menu",
                Toast.LENGTH_SHORT).show();
    }

    public void saveSettingsForUser() {
        Intent intent = new Intent(this,Settings.class);
        DataBaseHelper appDBHelper = new DataBaseHelper(Settings.this);
        Float[] jobWeights = new Float[5];

        salarySeek = findViewById(R.id.seekBarSalary);
        jobWeights[0] = (float)salarySeek.getProgress();
        bonusSeek = findViewById(R.id.seekBarBonus);
        jobWeights[1] = (float)bonusSeek.getProgress();
        retirementSeek = findViewById(R.id.seekBarRetirement);
        jobWeights[2] = (float)retirementSeek.getProgress();
        relocationSeek = findViewById(R.id.seekBarRelocation);
        jobWeights[3] = (float)relocationSeek.getProgress();
        trainingSeek = findViewById(R.id.seekBarRelocation);
        jobWeights[4] = (float)trainingSeek.getProgress();

        boolean dbSuccess = appDBHelper.changeWeightsInDB(jobWeights);




        Toast.makeText(Settings.this,
                "New Job Weight Settings Saved Status: "+ dbSuccess,Toast.LENGTH_SHORT).show();

        appDBHelper.updateJobScores();
    }
}