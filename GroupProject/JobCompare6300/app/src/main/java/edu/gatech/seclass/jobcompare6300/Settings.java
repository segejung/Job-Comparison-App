package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    public void returnToMainMenu() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}