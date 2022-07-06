package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Job_Compare extends AppCompatActivity {

    private Button goBackBtn;
    private Button compareAgainBtn;
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

        goBackBtn = (Button)findViewById(R.id.returnToMainButton);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });
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


}