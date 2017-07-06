package com.example.lion.ch037_segmentedbuttongroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import co.ceryle.segmentedbutton.SegmentedButton;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SegmentedButtonGroup sbg =  (SegmentedButtonGroup) findViewById(R.id.segmentedButtonGroup);
        sbg.setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
            @Override
            public void onClickedButtonPosition(int position) {
                if(position == 0)
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                else if(position == 1)
                    Toast.makeText(MainActivity.this, "Register", Toast.LENGTH_SHORT).show();
                else if(position == 2)
                    Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
