package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Button cancelBtn;
    private Button saveSettingsBtn;

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
        Toast.makeText(Settings.this,
                "Job Weight Settings Save Action (TBC)",Toast.LENGTH_SHORT).show();
    }
}