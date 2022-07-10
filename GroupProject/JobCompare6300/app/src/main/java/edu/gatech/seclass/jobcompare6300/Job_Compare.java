package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Job_Compare extends AppCompatActivity {

    private Button goBackBtn;
    private Button compareAgainBtn;
    private Button returnToJobEntryBtn;
    public SQLiteDatabase appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__compare);

        compareAgainBtn = (Button)findViewById(R.id.compareAgainButton);
        compareAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareJobsAgain();
            }
        });

        returnToJobEntryBtn = findViewById(R.id.returnToJobEntryBtn);
        returnToJobEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheJobEntryPage();
            }
        });

        goBackBtn = (Button)findViewById(R.id.returnToMainButton);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        TextView job1TitleTextView = findViewById(R.id.job1titleColId);
        TextView job1CompanyTextView = findViewById(R.id.job1companyColId);
        TextView job1LocationTextView = findViewById(R.id.job1locationColId);
        TextView job1SalaryTextView = findViewById(R.id.job1aysColId);
        TextView job1BonusTextView = findViewById(R.id.job1aybColId);
        TextView job1BenefitsTextView = findViewById(R.id.job1benefitsColId);
        TextView job1StipendTextView = findViewById(R.id.job1stipendColId);
        TextView job1TrainingTextView = findViewById(R.id.job1tdfColId);

        TextView job2TitleTextView = findViewById(R.id.job2titleColId);
        TextView job2CompanyTextView = findViewById(R.id.job2companyColId);
        TextView job2LocationTextView = findViewById(R.id.job2locationColId);
        TextView job2SalaryTextView = findViewById(R.id.job2aysColId);
        TextView job2BonusTextView = findViewById(R.id.job2aybColId);
        TextView job2BenefitsTextView = findViewById(R.id.job2benefitsColId);
        TextView job2StipendTextView = findViewById(R.id.job2stipendColId);
        TextView job2TrainingTextView = findViewById(R.id.job2tdfColId);

        Bundle bundle = getIntent().getExtras();

        job1TitleTextView.setText(bundle.getString("job1Title"));
        job1CompanyTextView.setText(bundle.getString("job1Company"));
        job1LocationTextView.setText(bundle.getString("job1Location"));
        job1SalaryTextView.setText(bundle.getString("job1adjustedSalary"));
        job1BonusTextView.setText(bundle.getString("job1adjustedBonus"));
        job1BenefitsTextView.setText(bundle.getString("job1Benefits"));
        job1StipendTextView.setText(bundle.getString("job1Stipend"));
        job1TrainingTextView.setText(bundle.getString("job1trainingFund"));

        job2TitleTextView.setText(bundle.getString("job2Title"));
        job2CompanyTextView.setText(bundle.getString("job2Company"));
        job2LocationTextView.setText(bundle.getString("job2Location"));
        job2SalaryTextView.setText(bundle.getString("job2adjustedSalary"));
        job2BonusTextView.setText(bundle.getString("job2adjustedBonus"));
        job2BenefitsTextView.setText(bundle.getString("job2Benefits"));
        job2StipendTextView.setText(bundle.getString("job2Stipend"));
        job2TrainingTextView.setText(bundle.getString("job2trainingFund"));

    }

    public void openMainMenu() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(Job_Compare.this,"Returning to Main Menu",
                Toast.LENGTH_SHORT).show();
    }

    public void compareJobsAgain() {
        Intent intent = new Intent(this,All_Jobs_Display.class);
        startActivity(intent);
        Toast.makeText(Job_Compare.this,"Redoing Comparison",
                Toast.LENGTH_SHORT).show();

    }

    public void openTheJobEntryPage() {
        Intent intent = new Intent(this,Job_Entry.class);
        startActivity(intent);
        Toast.makeText(Job_Compare.this,"Returning to Job Entry",
                Toast.LENGTH_SHORT).show();
    }


}