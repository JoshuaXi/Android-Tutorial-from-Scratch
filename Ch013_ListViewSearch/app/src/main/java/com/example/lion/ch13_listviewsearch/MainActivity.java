package com.example.lion.ch13_listviewsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lstSearch;
    private EditText edtSearch;
    private ArrayAdapter<String> adapter;

    String data[] = {
            "Harry", "Ron", "Hermione", "Dumbledore", "Moddy",
            "Voldemork", "Draco Malfoy", "Snape", "Hagrid",
            "Sirius Black", "Dobby", "Lupin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        lstSearch = (ListView) findViewById(R.id.lstSearch);
        edtSearch = (EditText) findViewById(R.id.editSearch);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, data);
        lstSearch.setAdapter(adapter);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
