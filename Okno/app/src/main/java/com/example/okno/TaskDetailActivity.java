package com.example.okno;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private EditText editTextTaskDetailName, editTextTaskDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        editTextTaskDetailName = findViewById(R.id.editTextTaskDetailName);
        editTextTaskDetailDescription = findViewById(R.id.editTextTaskDetailDescription);
        Button buttonUpdateTask = findViewById(R.id.buttonUpdateTask);

        Intent intent = getIntent();
        int taskPosition = intent.getIntExtra("pozycja", -1);
        String taskName = intent.getStringExtra("nazwa");
        String taskDescription = intent.getStringExtra("opis");

        editTextTaskDetailName.setText(taskName);
        editTextTaskDetailDescription.setText(taskDescription);

        buttonUpdateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedName = editTextTaskDetailName.getText().toString();
                String updatedDescription = editTextTaskDetailDescription.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("pozycja", taskPosition);
                resultIntent.putExtra("nazwa", updatedName);
                resultIntent.putExtra("opis", updatedDescription);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
