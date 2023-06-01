package sg.edu.rp.c346.id21018545.wk7simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTask;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTask;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);
        spinner = findViewById(R.id.spinnerItems);
        btnDelete = findViewById(R.id.buttonDelete);


        alTasks = new ArrayList<>();
        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(aaTask);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etElement.getText().toString();
                alTasks.add(newTask);
                aaTask.notifyDataSetChanged();
                etElement.setText(null);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTasks.size() == 0) {
                    Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int index = Integer.parseInt(etElement.getText().toString());
                    if (alTasks.size() <= index) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        alTasks.remove(index);
                        aaTask.notifyDataSetChanged();
                        etElement.setText(null);
                    }
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                aaTask.notifyDataSetChanged();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                switch(position){
                    case 0:
                        etElement.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etElement.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }
}