package com.example.sqliteclass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    EditText visitorNameET;
    EditText visitorIdET;
    EditText apartmentNoET;
    Spinner spinner;
    TextView checkinDateTV;
    Button saveBtn;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // Getting elements
        visitorNameET = (EditText) findViewById(R.id.visitor_name_et);
        visitorIdET = (EditText) findViewById(R.id.visitor_id_et);
        apartmentNoET = (EditText) findViewById(R.id.apartment_id_et);
        spinner = (Spinner) findViewById(R.id.visitor_type_sp);
        checkinDateTV = (TextView) findViewById(R.id.checking_date_tv);
        saveBtn = (Button) findViewById(R.id.save_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);

        // Getting data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String from = (String) bundle.get("from");
        long visitorId = (long) bundle.get("visitorId");

        // Setting current date if a visitor is going to be created
        if (from == "edit") {
            // checkinDateTV.setText(El que viene de la BD); Si viene "edit" en el from, es porque se va a editar, si no, se va a crear ;)
        } else {
            checkinDateTV.setText(new Date().toString());
        }

        // Setting on click method context
        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        // Setting spinner options
        List visitorTypes = new ArrayList<String>();
        visitorTypes.add("Familia");
        visitorTypes.add("Amigo");
        visitorTypes.add("Domiciliario");

        // Setting array adapter to spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, visitorTypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    // Buttons actions
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:
                break;
            case R.id.delete_btn:
                break;
            default:
                break;
        }
    }
}
