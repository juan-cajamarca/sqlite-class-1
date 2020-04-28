package com.example.sqliteclass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Visitor[] visitors = new Visitor[] {
                new Visitor("Juan Cajamarca", 1234632817, 1107, "Family", new Date()),
                new Visitor("Jhonattan Pérez", 127462813, 1243, "Friend", new Date()),
                new Visitor("Fernando Vargas", 36271726, 109, "Family", new Date())
        };

        VisitorAdapter adapter = new VisitorAdapter(this, R.layout.listview_item, visitors);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Configuring intent
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);

                // Passing data
                intent.putExtra("from", "edit");
                intent.putExtra("visitorId", visitors[position].getIdentification());

                // Starting activity
                startActivity(intent);
            }
        });
    }
}
