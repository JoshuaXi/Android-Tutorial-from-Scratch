package com.example.lion.ch031_flagsquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lion.ch031_flagsquizapp.Common.CustomAdapter;
import com.example.lion.ch031_flagsquizapp.DbHelper.DbHelper;
import com.example.lion.ch031_flagsquizapp.Model.Ranking;

import java.security.KeyStore;
import java.util.List;

public class Score extends AppCompatActivity {

    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lstView = (ListView) findViewById(R.id.lstRanking);
        DbHelper db = new DbHelper(this);
        List<Ranking> lstRanking = db.getRanking();
        if(lstRanking.size() > 0)
        {
            CustomAdapter adapter = new CustomAdapter(this, lstRanking);
            lstView.setAdapter(adapter);
        }

    }

}
