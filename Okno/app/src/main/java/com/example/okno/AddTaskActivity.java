package com.example.okno;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextTaskName, editTextTaskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        Button buttonSaveTask = findViewById(R.id.buttonSaveTask);

        buttonSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextTaskName.getText().toString();
                String opis = editTextTaskDescription.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nazwa", name);
                resultIntent.putExtra("opis", opis);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
