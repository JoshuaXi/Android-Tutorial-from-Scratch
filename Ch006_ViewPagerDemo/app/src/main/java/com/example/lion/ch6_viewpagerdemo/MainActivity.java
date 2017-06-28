package com.example.lion.ch6_viewpagerdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //TODO

    ViewPager viewPager;
    ViewPagerAdapter adapter;

    private String[] images = {
            "http://androidwalls.net/wp-content/uploads/2016/04/Blue%20Diamond%20Rhombus%20Pattern%20Android%20Wallpaper.jpg",
            "http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg",
            "https://www.celebritysizes.com/wp-content/uploads/2016/03/Scarlett-Johansson.jpg",
            "http://media-cache-ec0.pinimg.com/736x/30/f1/5e/30f15ece1213522b73469ce3bded3031.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(MainActivity.this, images);
        viewPager.setAdapter(adapter);
    }
}
