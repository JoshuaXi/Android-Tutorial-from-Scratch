package com.example.lion.ch5_loadimageurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    //TODO
    ImageView imageView;

    String url = "http://pad3.whstatic.com/images/c/c1/Get-the-URL-for-Pictures-Step-1-Version-4.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        imageView = (ImageView) findViewById(R.id.imageView);
        loadImageFromUrl(url);
    }

    private void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)//optional
        .error(R.mipmap.ic_launcher) //if error
        .into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}
