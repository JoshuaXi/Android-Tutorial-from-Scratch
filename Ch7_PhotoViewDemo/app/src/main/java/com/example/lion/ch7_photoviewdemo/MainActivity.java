package com.example.lion.ch7_photoviewdemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity {

    //TODO

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();

    }
}
