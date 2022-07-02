package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class All_Jobs_Display extends AppCompatActivity {

    private Button viewAllBtn;
    private Button compareSelectedBtn;
    private Button cancelCompareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__jobs__display);

        viewAllBtn = (Button)findViewById(R.id.viewJobsBtn);
        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper databaseHelper = new DataBaseHelper(All_Jobs_Display.this);
                List<JobDetails> jobListEntries = databaseHelper.getOffers();
                Toast.makeText(All_Jobs_Display.this,"Jobs from DB are displayed",
                        Toast.LENGTH_SHORT).show();

            }
        });



        compareSelectedBtn = findViewById(R.id.compareJobsButtonId);
        cancelCompareBtn = findViewById(R.id.cancelBtnOnJobDisplay);
        cancelCompareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();
            }
        });


    }

    public void returnToMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}