package com.example.sqliteclass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    VisitorController visitorController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        visitorController = new VisitorController(getApplicationContext());
        /*
        final Visitor[] visitors = new Visitor[] {
                new Visitor("Juan Cajamarca", 1234632817, 1107, "Family", new Date()),
                new Visitor("Jhonattan Pérez", 127462813, 1243, "Friend", new Date()),
                new Visitor("Fernando Vargas", 36271726, 109, "Family", new Date())
        };*/
        final Cursor  visitors = visitorController.allVisitors();
        VisitorAdapter adapter = new VisitorAdapter(this,  visitors,R.layout.listview_item);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Configuring intent
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);

                // Passing data
                intent.putExtra("from", "edit");
                intent.putExtra("visitorId", visitors.getString(visitors.getColumnIndexOrThrow(DefDB.col_identification)));

                // Starting activity
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.itmAgregar) {
            Intent intent = new Intent(getApplicationContext(),AddContactActivity.class);
            intent.putExtra("from", "new");
            intent.putExtra("visitorId", 0);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
