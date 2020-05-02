package com.example.sqliteclass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    VisitorController controllerVisitor ;
    long visitorId;
    private String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        controllerVisitor = new VisitorController(getApplicationContext());
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
        from = (String) bundle.get("from");
        visitorId = Long.parseLong(bundle.get("visitorId").toString().trim());

        // Setting spinner options
        List visitorTypes = new ArrayList<String>();
        visitorTypes.add("Familia");
        visitorTypes.add("Amigo");
        visitorTypes.add("Domiciliario");

        // Setting array adapter to spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, visitorTypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        // Setting current date if a visitor is going to be created
        if (from.equalsIgnoreCase("edit")) {

            if (controllerVisitor.buscarVisitor(String.valueOf(visitorId))){
                Log.i("app","existe");
                Visitor visitor = controllerVisitor.findVisitor(visitorId);
            if (visitor!= null){
                checkinDateTV.setText(visitor.getCheckinDate().toString());
                visitorNameET.setText(visitor.getName());
                visitorIdET.setText(String.valueOf(visitor.getIdentification()));
                apartmentNoET.setText(String.valueOf(visitor.getApartmentId()));
                spinner.setSelection((visitor.getVisitorType().trim().equalsIgnoreCase("familia")) ? 0 :((visitor.getVisitorType().equalsIgnoreCase("amigo")) ? 1 : 2));
                Log.i("prueba",String.valueOf(visitor.getVisitorType()));
                Log.i("dato",String.valueOf((visitor.getVisitorType().trim().equalsIgnoreCase("familia")) ? 1 :((visitor.getVisitorType().equalsIgnoreCase("amigo")) ? 2 : 3)));
            }}

        } else {
            checkinDateTV.setText(new Date().toString());
        }

        // Setting on click method context
        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);


    }

    // Buttons actions
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:

                Visitor visitor = new Visitor(visitorNameET.getText().toString(),Long.parseLong(visitorIdET.getText().toString())
                ,Integer.parseInt(apartmentNoET.getText().toString()),spinner.getSelectedItem().toString(),new Date());
               if (from.equalsIgnoreCase("edit")){
                   controllerVisitor.actualizarVisitante(visitor);
                   Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                   startActivity(intent);
                   finish();
               } else {
                   controllerVisitor.agregarVisitante(visitor);
                   Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                   startActivity(intent);
                   finish();
               }
                break;
            case R.id.delete_btn:
                if (from.equalsIgnoreCase("edit")){
                    controllerVisitor.deleteVisitor(visitorId);
                    Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "No ha guardado el visitante", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

}
