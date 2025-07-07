package com.example.to_dolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etTask;
    private Button btnAdd;
    private ListView lvTasks;
    private ArrayList<String> tasks;
    private ArrayAdapter<String> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        lvTasks = findViewById(R.id.lvTasks);

        tasks = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        lvTasks.setAdapter(taskAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskText = etTask.getText().toString().trim();
                if (!taskText.isEmpty()) {
                    tasks.add(taskText);
                    taskAdapter.notifyDataSetChanged();
                    etTask.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Enter a task!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task removed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}