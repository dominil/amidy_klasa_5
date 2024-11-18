package com.example.okno;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewTasks);
        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);

        tasks = new ArrayList<>();
        adapter = new TaskAdapter(tasks, position -> {
            Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
            intent.putExtra("pozycja", position);
            intent.putExtra("nazwa", tasks.get(position).getName());
            intent.putExtra("opis", tasks.get(position).getOpis());
            startActivityForResult(intent, 2);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("nazwa");
            String opis = data.getStringExtra("opis");
            tasks.add(new Task(name, opis));
            adapter.notifyItemInserted(tasks.size() - 1);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            int position = data.getIntExtra("pozycja", -1);
            String name = data.getStringExtra("nazwa");
            String opis = data.getStringExtra("opis");

            if (position != -1) {
                tasks.get(position).setName(name);
                tasks.get(position).setOpis(opis);
                adapter.notifyItemChanged(position);
            }
        }
    }
}
