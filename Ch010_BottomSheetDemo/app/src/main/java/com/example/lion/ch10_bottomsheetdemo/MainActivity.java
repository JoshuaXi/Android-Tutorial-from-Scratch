package com.example.lion.ch10_bottomsheetdemo;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetBehavior mBottomSheetBehavior;
    boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_1:
                if(isClick==false)
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                else
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                isClick = !isClick;
                break;
            default:
                break;
        }
    }
}
