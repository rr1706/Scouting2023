package com.frc1706.alliancescout2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Spinner alliance_sel;
    View line1,line2,line3,line4,line5,line6;

    EditText team1,team2,team3,scouter,match;
    TextView inputTeams;

    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        alliance_sel = findViewById(R.id.alliance_input);
        recyclerView = findViewById(R.id.recyclerView);
        line1= findViewById(R.id.line1);
        line2= findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4= findViewById(R.id.line4);
        line5= findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        View[] lines = {line1,line2,line3,line4,line5,line6};
        inputTeams = findViewById(R.id.inputTeamsTxt);

        submit = findViewById(R.id.submit);
        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        team3 = findViewById(R.id.team3);
        scouter = findViewById(R.id.name_input);
        match = findViewById(R.id.round_input);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                0);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        populateRecyclerView();

        getTeams();

        if(!getTeams().equals("")) {
            team1.setVisibility(View.GONE);
            team2.setVisibility(View.GONE);
            team3.setVisibility(View.GONE);
            line4.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);
            line6.setVisibility(View.GONE);
            inputTeams.setVisibility(View.GONE);
        }



        match.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        scouter.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        team1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                rankUpD();
                hideKeyboard(v);
            }
        });
        team2.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {

                rankUpD();
                hideKeyboard(v);
            }
        });
        team3.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {

                rankUpD();
                hideKeyboard(v);
            }
        });

        alliance_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                if (alliance_sel.getSelectedItem().toString().equals("BLUE")) {
                    for (View line : lines) {
                        line.setBackgroundColor(Color.BLUE);
                    }
                    for(int i=0;i<3;i++) {
                        CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                        relativeLayout.setBackgroundColor(Color.BLUE);
                        TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                        textView.setTextColor(Color.WHITE);
                    }


                } else if (alliance_sel.getSelectedItem().toString().equals("RED")) {
                    for (View line : lines) {
                        line.setBackgroundColor(Color.RED);
                    }
                    for(int i=0;i<3;i++) {
                        CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                        relativeLayout.setBackgroundColor(Color.RED);
                        TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                        textView.setTextColor(Color.WHITE);
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        submit.setOnClickListener(v->{
            String[] strings = {"","",""};
            for(int i=0;i<3;i++) {
                CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                String info = (String) textView.getText();
                strings[i] = info;
            }
            Log.e("A", Arrays.toString(strings));

        });

    }
    private String getTeams() {

        StringBuilder text = new StringBuilder();
        try {
            File documents = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file;
            file = new File(documents + "/ScoutingTeams.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("log", text.toString());
        return text.toString();
    }
    public void onBackPressed() {}

    private File getDataDirectory() {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File myDir = new File(directory + "/ScoutingData");
        myDir.mkdirs();
        return myDir;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void rankUpD() {
        if(!team1.getText().toString().equals("") && !team2.getText().toString().equals("") && !team3.getText().toString().equals("")) {
            stringArrayList.set(0,team1.getText().toString());
            stringArrayList.set(1,team2.getText().toString());
            stringArrayList.set(2,team3.getText().toString());

            mAdapter = new RecyclerViewAdapter(stringArrayList);
            ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(mAdapter);
        }
    }

    //@Override
    //public boolean dispatchTouchEvent(MotionEvent event) {
    //    if (event.getAction() == MotionEvent.ACTION_DOWN) {
    //        View v = getCurrentFocus();
    //        if ( v instanceof EditText) {
    //            Rect outRect = new Rect();
    //            v.getGlobalVisibleRect(outRect);
    //            if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
    //                if(!team1.getText().toString().equals("") && !team2.getText().toString().equals("") && !team3.getText().toString().equals("")) {
    //                    if(team1.hasFocus()){
    //                        stringArrayList.set(0,team1.getText().toString());
    //                        stringArrayList.set(1,team2.getText().toString());
    //                        stringArrayList.set(2,team3.getText().toString());
    //                    }
    //                    if(team2.hasFocus()){
    //                        stringArrayList.set(0,team1.getText().toString());
    //                        stringArrayList.set(1,team2.getText().toString());
    //                        stringArrayList.set(2,team3.getText().toString());
    //                    }
    //                    if(team3.hasFocus()){
    //                        stringArrayList.set(0,team1.getText().toString());
    //                        stringArrayList.set(1,team2.getText().toString());
    //                        stringArrayList.set(2,team3.getText().toString());
    //                    }
    //                    mAdapter = new RecyclerViewAdapter(stringArrayList);
//
    //                    ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapter);
    //                    ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
    //                    touchHelper.attachToRecyclerView(recyclerView);
//
    //                    recyclerView.setAdapter(mAdapter);
    //                }
//
//
    //                v.clearFocus();
    //                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    //                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    //            }
    //        }
    //    }
    //    return super.dispatchTouchEvent( event );
    //}

    private void populateRecyclerView() {

        stringArrayList.add("TEAM #1");
        stringArrayList.add("TEAM #2");
        stringArrayList.add("TEAM #3");

        mAdapter = new RecyclerViewAdapter(stringArrayList);

        ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);
    }
}