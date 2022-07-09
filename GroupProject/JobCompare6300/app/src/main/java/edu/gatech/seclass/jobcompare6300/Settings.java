package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Button cancelBtn;
    private Button saveSettingsBtn;
    private SeekBar salarySeek;
    private SeekBar bonusSeek;
    private SeekBar retirementSeek;
    private SeekBar relocationSeek;
    private SeekBar trainingSeek;
    private TextView salaryWeightValue;
    private TextView bonusWeightValue;
    private TextView retirementWeightValue;
    private TextView relocationWeightValue;
    private TextView trainingWeightValue;

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

        DataBaseHelper appDBHelper = new DataBaseHelper(Settings.this);
        double[] jobWeights = appDBHelper.getJobWeights();
        setSeekersAndValues();

        salarySeek.setProgress((int) Math.round(jobWeights[0]));
        salaryWeightValue.setText(String.valueOf(Math.round(jobWeights[0])));

        bonusSeek.setProgress((int) Math.round(jobWeights[1]));
        bonusWeightValue.setText(String.valueOf(Math.round(jobWeights[1])));

        retirementSeek.setProgress((int) Math.round(jobWeights[2]));
        retirementWeightValue.setText(String.valueOf(Math.round(jobWeights[2])));

        relocationSeek.setProgress((int) Math.round(jobWeights[3]));
        relocationWeightValue.setText(String.valueOf(Math.round(jobWeights[3])));

        trainingSeek.setProgress((int) Math.round(jobWeights[4]));
        trainingWeightValue.setText(String.valueOf(Math.round(jobWeights[4])));

    }

    public void setSeekersAndValues() {
        salarySeek = findViewById(R.id.seekBarSalary);
        salaryWeightValue = findViewById(R.id.salaryWeightValue);

        bonusSeek = findViewById(R.id.seekBarBonus);
        bonusWeightValue = findViewById(R.id.bonusWeightValue);

        retirementSeek = findViewById(R.id.seekBarRetirement);
        retirementWeightValue = findViewById(R.id.retirementWeightValue3);

        relocationSeek = findViewById(R.id.seekBarRelocation);
        relocationWeightValue = findViewById(R.id.relocationWeightValue4);

        trainingSeek = findViewById(R.id.seekBarTraining);
        trainingWeightValue = findViewById(R.id.trainingWeightValue5);
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

        jobWeights[0] = (float)salarySeek.getProgress();
        jobWeights[1] = (float)bonusSeek.getProgress();
        jobWeights[2] = (float)retirementSeek.getProgress();
        jobWeights[3] = (float)relocationSeek.getProgress();
        jobWeights[4] = (float)trainingSeek.getProgress();


        salaryWeightValue.setText(String.valueOf(Math.round(jobWeights[0])));
        bonusWeightValue.setText(String.valueOf(Math.round(jobWeights[1])));
        retirementWeightValue.setText(String.valueOf(Math.round(jobWeights[2])));
        relocationWeightValue.setText(String.valueOf(Math.round(jobWeights[3])));
        trainingWeightValue.setText(String.valueOf(Math.round(jobWeights[4])));


        boolean dbSuccess = appDBHelper.changeWeightsInDB(jobWeights);



        Toast.makeText(Settings.this,
                "New Job Weight Settings Saved Status: "+ dbSuccess,Toast.LENGTH_SHORT).show();

        appDBHelper.updateJobScores();
    }
}