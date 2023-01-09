package com.rr1706.scouting2023;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageView rrlogo,cone0, cube0, cone1, cone2, cube1, cone3, cone4, cube2, cone5, cone6, cube3, cone7, cone8, cube4, cone9, cone10, cube5, cone11,both0,both1,both2,both3,both4,both5,both6,both7,both8;

    int[][] grid = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        rrlogo = findViewById(R.id.rrlogo);

        cube0 = findViewById(R.id.cube0);
        cube1 = findViewById(R.id.cube1);
        cube2 = findViewById(R.id.cube2);
        cube3 = findViewById(R.id.cube3);
        cube4 = findViewById(R.id.cube4);
        cube5 = findViewById(R.id.cube5);
        cone0 = findViewById(R.id.cone0);
        cone1 = findViewById(R.id.cone1);
        cone2 = findViewById(R.id.cone2);
        cone3 = findViewById(R.id.cone3);
        cone4 = findViewById(R.id.cone4);
        cone5 = findViewById(R.id.cone5);
        cone6 = findViewById(R.id.cone6);
        cone7 = findViewById(R.id.cone7);
        cone8 = findViewById(R.id.cone8);
        cone9 = findViewById(R.id.cone9);
        cone10 = findViewById(R.id.cone10);
        cone11 = findViewById(R.id.cone11);
        both0 = findViewById(R.id.both0);
        both1 = findViewById(R.id.both1);
        both2 = findViewById(R.id.both2);
        both3 = findViewById(R.id.both3);
        both4 = findViewById(R.id.both4);
        both5 = findViewById(R.id.both5);
        both6 = findViewById(R.id.both6);
        both7 = findViewById(R.id.both7);

        both0.setOnClickListener(v->{
            if(grid[2][0]==0) {
                both0.setImageResource(R.drawable.greenboth);
                grid[2][0]=1;

            } else if(grid[2][0]==1) {
                both0.setImageResource(R.drawable.redboth);
                grid[2][0]=0;
            }
        });





    }

    @Override
    public void onBackPressed() {}
    private File getDataDirectory() {
        File directory = Environment.getExternalStorageDirectory();
        File myDir = new File(directory + "/ScoutingData");
        myDir.mkdirs();
        return myDir;
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}