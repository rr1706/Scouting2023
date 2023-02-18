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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
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

    int navT1 = 0;
    int navT2 = 0;
    int navT3 = 0;
    int scoreT1 = 0;
    int scoreT2 = 0;
    int scoreT3 = 0;
    int loadT1 = 0;
    int loadT2 = 0;
    int loadT3 = 0;


    SeekBar defSeekTeam1,defSeekTeam2,defSeekTeam3;
    CheckBox defChkTeam1,defChkTeam2,defChkTeam3;



    Spinner alliance_sel;
    View line1,line2,line3,line4,line5,line6,line7,line8,line9,line10,line11,line12,line13;

    EditText team1,team2,team3,scouter,match,commentT1,commentT2,commentT3;
    TextView inputTeams,loadTxtT1,loadTxtT2,loadTxtT3,scoreTxtT1,scoreTxtT2,scoreTxtT3,navTxtT1,navTxtT2,navTxtT3,team2Txt,team1Txt,team3Txt;

    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    Button quickLoadMinusT1,quickLoadPlusT1,quickLoadMinusT2,quickLoadPlusT2,quickLoadMinusT3,quickLoadPlusT3,quickScoreMinusT1,quickScorePlusT1,quickScoreMinusT2,quickScorePlusT2,quickScoreMinusT3,quickScorePlusT3,navDefMinusT1,navDefPlusT1,navDefMinusT2,navDefPlusT2,navDefMinusT3,navDefPlusT3,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Spinner
        alliance_sel = findViewById(R.id.alliance_input);

        //Recycler
        recyclerView = findViewById(R.id.recyclerView);

        //Lines
        line1= findViewById(R.id.line1);
        line2= findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4= findViewById(R.id.line4);
        line5= findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        line8 = findViewById(R.id.line8);
        line9 = findViewById(R.id.line9);
        line10 = findViewById(R.id.line10);
        line11 = findViewById(R.id.line11);
        line12 = findViewById(R.id.line12);
        line13 = findViewById(R.id.line13);
        View[] lines = {line1,line2,line3,line4,line5,line6,line7,line8,line9,line10,line11,line12,line13};

        //CheckBox
        defChkTeam1 = findViewById(R.id.team1DefCK);
        defChkTeam2 = findViewById(R.id.team2DefCK);
        defChkTeam3 = findViewById(R.id.team3DefCK);

        //Seekbar
        defSeekTeam1 = findViewById(R.id.team1DefSEEK);
        defSeekTeam2 = findViewById(R.id.team2DefSEEK);
        defSeekTeam3 = findViewById(R.id.team3DefSEEK);

        //TextView
        inputTeams = findViewById(R.id.inputTeamsTxt);
        loadTxtT1 = findViewById(R.id.textTeam1Load);
        loadTxtT2 = findViewById(R.id.textTeam2Load);
        loadTxtT3 = findViewById(R.id.textTeam3Load);
        scoreTxtT1 = findViewById(R.id.textTeam1Score);
        scoreTxtT2 = findViewById(R.id.textTeam2Score);
        scoreTxtT3 = findViewById(R.id.textTeam3Score);
        navTxtT1 = findViewById(R.id.textTeam1Nav);
        navTxtT2 = findViewById(R.id.textTeam2Nav);
        navTxtT3 = findViewById(R.id.textTeam3Nav);
        team1Txt = findViewById(R.id.team1EditTxt);
        team2Txt = findViewById(R.id.team2EditTxt);
        team3Txt = findViewById(R.id.team3EditTxt);

        //Buttons
        submit = findViewById(R.id.submit);
        quickLoadMinusT1  = findViewById(R.id.minusTeam1Load);
        quickLoadPlusT1 = findViewById(R.id.plusTeam1Load);
        quickLoadMinusT2 = findViewById(R.id.minusTeam2Load);
        quickLoadPlusT2 = findViewById(R.id.plusTeam2Load);
        quickLoadMinusT3 = findViewById(R.id.minusTeam3Load);
        quickLoadPlusT3 = findViewById(R.id.plusTeam3Load);
        quickScoreMinusT1 = findViewById(R.id.minusTeam1Score);
        quickScorePlusT1 = findViewById(R.id.plusTeam1Score);
        quickScoreMinusT2 = findViewById(R.id.minusTeam2Score);
        quickScorePlusT2 = findViewById(R.id.plusTeam2Score);
        quickScoreMinusT3 = findViewById(R.id.minusTeam3Score);
        quickScorePlusT3 = findViewById(R.id.plusTeam3Score);
        navDefMinusT1 = findViewById(R.id.minusTeam1Nav);
        navDefPlusT1 = findViewById(R.id.plusTeam1Nav);
        navDefMinusT2 = findViewById(R.id.minusTeam2Nav);
        navDefPlusT2 = findViewById(R.id.plusTeam2Nav);
        navDefMinusT3 = findViewById(R.id.minusTeam3Nav);
        navDefPlusT3 = findViewById(R.id.plusTeam3Nav);



        //EditText
        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        team3 = findViewById(R.id.team3);
        scouter = findViewById(R.id.name_input);
        match = findViewById(R.id.round_input);
        commentT1 = findViewById(R.id.team1Notes);
        commentT2 = findViewById(R.id.team2Notes);
        commentT3 = findViewById(R.id.team3Notes);



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

        defChkTeam1.setOnClickListener(v->{
            if(defChkTeam1.isChecked()) {
                defSeekTeam1.setVisibility(View.VISIBLE);
            } else {
                defSeekTeam1.setVisibility(View.INVISIBLE);
            }
        });
        defChkTeam2.setOnClickListener(v->{
            if(defChkTeam2.isChecked()) {
                defSeekTeam2.setVisibility(View.VISIBLE);
            } else {
                defSeekTeam2.setVisibility(View.INVISIBLE);
            }
        });
        defChkTeam3.setOnClickListener(v->{
            if(defChkTeam3.isChecked()) {
                defSeekTeam3.setVisibility(View.VISIBLE);
            } else {
                defSeekTeam3.setVisibility(View.INVISIBLE);
            }
        });

        navDefPlusT1.setOnClickListener(v-> modScore(navTxtT1,false));
        navDefPlusT2.setOnClickListener(v-> modScore(navTxtT2,false));
        navDefPlusT3.setOnClickListener(v-> modScore(navTxtT3,false));
        navDefMinusT1.setOnClickListener(v-> modScore(navTxtT1,true));
        navDefMinusT2.setOnClickListener(v-> modScore(navTxtT2,true));
        navDefMinusT3.setOnClickListener(v-> modScore(navTxtT3,true));

        quickScorePlusT1.setOnClickListener(v-> modScore(scoreTxtT1,false));
        quickScorePlusT2.setOnClickListener(v-> modScore(scoreTxtT2,false));
        quickScorePlusT3.setOnClickListener(v-> modScore(scoreTxtT3,false));
        quickScoreMinusT1.setOnClickListener(v-> modScore(scoreTxtT1,true));
        quickScoreMinusT2.setOnClickListener(v-> modScore(scoreTxtT2,true));
        quickScoreMinusT3.setOnClickListener(v-> modScore(scoreTxtT3,true));

        quickLoadPlusT1.setOnClickListener(v-> modScore(loadTxtT1,false));
        quickLoadPlusT2.setOnClickListener(v-> modScore(loadTxtT2,false));
        quickLoadPlusT3.setOnClickListener(v-> modScore(loadTxtT3,false));
        quickLoadMinusT1.setOnClickListener(v-> modScore(loadTxtT1,true));
        quickLoadMinusT2.setOnClickListener(v-> modScore(loadTxtT2,true));
        quickLoadMinusT3.setOnClickListener(v-> modScore(loadTxtT3,true));



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
        commentT1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        commentT2.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        commentT3.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        team1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                rankUpdate();
                hideKeyboard(v);
            }
        });
        team2.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {

                rankUpdate();
                hideKeyboard(v);
            }
        });
        team3.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {

                rankUpdate();
                hideKeyboard(v);
            }
        });

        alliance_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                if (alliance_sel.getSelectedItem().toString().equals("BLUE")) {
                    for (View line : lines) {
                        line.setBackgroundColor(Color.BLUE);
                    }
                    inputTeams.setTextColor(getResources().getColor(R.color.NewBlue));
                    team1Txt.setTextColor(getResources().getColor(R.color.NewBlue));
                    team2Txt.setTextColor(getResources().getColor(R.color.NewBlue));
                    team3Txt.setTextColor(getResources().getColor(R.color.NewBlue));


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
                    inputTeams.setTextColor(getResources().getColor(R.color.NewRed));
                    team1Txt.setTextColor(getResources().getColor(R.color.NewRed));
                    team2Txt.setTextColor(getResources().getColor(R.color.NewRed));
                    team3Txt.setTextColor(getResources().getColor(R.color.NewRed));
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

    private void modScore(TextView team,boolean minus) {

        int Score = Integer.parseInt(team.getText().toString());
        if(minus && Score > -99){
            Score -= 1;
        } else if(!minus && Score < 99) {
            Score += 1;
        }
        team.setText(String.valueOf(Score));
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
    public void rankUpdate() {
        if(!team1.getText().toString().equals("") && !team2.getText().toString().equals("") && !team3.getText().toString().equals("")) {
            stringArrayList.set(0,team1.getText().toString());
            stringArrayList.set(1,team2.getText().toString());
            stringArrayList.set(2,team3.getText().toString());

            commentT1.setHint(team1.getText().toString()+" Notes:");
            commentT2.setHint(team2.getText().toString()+" Notes:");
            commentT3.setHint(team3.getText().toString()+" Notes:");

            team1Txt.setText(team1.getText().toString());
            team2Txt.setText(team2.getText().toString());
            team3Txt.setText(team3.getText().toString());

            mAdapter = new RecyclerViewAdapter(stringArrayList);
            ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(mAdapter);
        }
    }
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