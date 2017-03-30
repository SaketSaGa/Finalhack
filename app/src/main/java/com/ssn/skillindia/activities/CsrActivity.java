package com.ssn.skillindia.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssn.skillindia.R;
import com.ssn.skillindia.model.csrdetails;

/**
 * Created by karthik on 30-03-2017.
 */
public class CsrActivity extends AppCompatActivity {
    Button CSRbutton;
    Spinner t_spinner;
    Spinner s_spinner;
    EditText name;
    EditText amount;
    EditText email;
    EditText objective;
    String sector,TOA;
    private DatabaseReference mDatabase;
    int lastPrimary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        t_spinner = (Spinner) findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> t_adapter = ArrayAdapter.createFromResource(this,R.array.TOA, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        t_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        t_spinner.setAdapter(t_adapter);

        s_spinner = (Spinner) findViewById(R.id.sector_spinner);
        ArrayAdapter<CharSequence> s_adapter = ArrayAdapter.createFromResource(this,R.array.sector, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        s_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        s_spinner.setAdapter(s_adapter);

        CSRbutton=(Button)findViewById(R.id.CSRSUBMIT);
        name=(EditText)findViewById(R.id.name);
        amount=(EditText)findViewById(R.id.amount);
        email=(EditText)findViewById(R.id.email);
        objective=(EditText)findViewById(R.id.objective_content);
        lastPrimary =0;
        mDatabase = FirebaseDatabase.getInstance().getReference();


        CSRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"Thanks for contirbuting...we will get back to you soon", Toast.LENGTH_SHORT);
                toast.show();
                sector= (String) s_spinner.getSelectedItem().toString();
                TOA= (String) t_spinner.getSelectedItem().toString();
                csrdetails csr= new csrdetails(name.getText().toString(),TOA.toString(),sector.toString(),email.getText().toString(),amount.getText().toString(),objective.getText().toString());
                SharedPreferences sp = getSharedPreferences("csrdetails",MODE_PRIVATE);

                lastPrimary = sp.getInt("csrid",0);
                lastPrimary++;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("csrid", lastPrimary);
                editor.apply();


                mDatabase.child("csrFB").child(String.valueOf(lastPrimary)).setValue(csr);

            }
        });
    }
















}
