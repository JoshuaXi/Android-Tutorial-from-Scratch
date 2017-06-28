package com.example.lion.customspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TODO
    List<String> lstSource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        generateData();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(lstSource, MainActivity.this);
        spinner.setAdapter(adapter);
        spinner.setDropDownHorizontalOffset(100);
    }

    private void generateData(){
        for(int i = 0; i <10; i++){
            lstSource.add("item "+i);
        }
    }
}
