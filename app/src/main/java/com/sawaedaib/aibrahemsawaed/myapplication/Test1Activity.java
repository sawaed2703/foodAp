package com.sawaedaib.aibrahemsawaed.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Test1Activity extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    //todo :  https://firebasestorage.googleapis.com/v0/b/myapp-8b063.appspot.com/o/User%2FUser%2Faib%20Sa%3A%201532782755756?alt=media&token=d4b91c52-060e-44ed-ba7b-566a3d4b867f
    //todo : "https://firebasestorage.googleapis.com/v0/b/myapp-8b063.appspot.com/o?name=User%2FUser%2FApps%20Development%3A%201532597572832&uploadType=resumable&upload_id=AEnB2Uplwf1qfza_DNMSWeFIjQOsCW6CfUW2MC2_u_1e3RTq-1poMEqazS3-9XQG7bnCCrLv5XbYE_6tyCtgkRDnWYI3nB3hkg&upload_protocol=resumable"
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Test1Activity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }



}
