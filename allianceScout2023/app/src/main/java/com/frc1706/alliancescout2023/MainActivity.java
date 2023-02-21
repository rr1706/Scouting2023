package com.frc1706.alliancescout2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    int round = 1;
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

        match.setText(String.valueOf(round));

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





                } else if (alliance_sel.getSelectedItem().toString().equals("RED")) {
                    for (View line : lines) {
                        line.setBackgroundColor(Color.RED);
                    }

                    inputTeams.setTextColor(getResources().getColor(R.color.NewRed));
                    team1Txt.setTextColor(getResources().getColor(R.color.NewRed));
                    team2Txt.setTextColor(getResources().getColor(R.color.NewRed));
                    team3Txt.setTextColor(getResources().getColor(R.color.NewRed));
                }
                ColorView();
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        Drawable textBackground = match.getBackground();

        Drawable spinnerbackground = alliance_sel.getBackground();

        submit.setOnClickListener(v->{
            String[] strings = {"","",""};
            for(int i=0;i<3;i++) {
                CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                String info = (String) textView.getText();
                strings[i] = info;
            }

            String submitError = "";
            SimpleDateFormat time = new SimpleDateFormat("dd-HHmmss", Locale.getDefault());

            int round;
            //Special handling
            if (match.getText().toString().equals("")) {
                round = -1;
            } else {
                round = Integer.parseInt(match.getText().toString());
            }
            //These are telling the toast what to put in the error field, and it changes the color to yellow.
            if (alliance_sel.getSelectedItem().equals("Alliance")) {
                submitError += " No Alliance,";
                alliance_sel.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (scouter.getText().toString().equals("")) {
                submitError += " No Name,";
                scouter.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (match.getText().toString().equals("420")) {
                submitError += " No. Its not even funny,";
                match.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (!submitError.equals("")) {
                submitError = submitError.substring(0, submitError.length() - 1) + ".";
            }
            //If any of the above are true, the thing returns a submit error.
            if (!(submitError.equals(""))) {
                Toast.makeText(getApplicationContext(), "Submit Error:" + submitError, Toast.LENGTH_LONG).show();
            } else {
                //This is what happens whenever all is correct

                match.setBackground(textBackground);
                scouter.setBackground(textBackground);
                alliance_sel.setBackground(spinnerbackground);

                //Save data for transfer
                File dir = getDataDirectory();
                //This creates a file
                try {
                    File myFile = new File(dir, team1.getText().toString() + "_" + round + "_" + time.format(new Date()) + ".txt");
                    FileOutputStream fOut = new FileOutputStream(myFile, true);
                    PrintWriter myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));
                    //This prints all of the lines into the file for transfer.
                    myOutWriter.println("Scouter: " + scouter.getText());
                    myOutWriter.println("Team: " + team1.getText().toString());
                    myOutWriter.println("Timestamp: " + time.format(new Date()));
                    myOutWriter.println("Match: " + round);
                    myOutWriter.println("Alliance: " + alliance_sel.getSelectedItem().toString());
                    myOutWriter.println("Robot Errors: ");
                    myOutWriter.println("Auto Docked: " );
                    myOutWriter.println("Auto Top: " );
                    myOutWriter.println("Auto Middle: " );
                    myOutWriter.println("Auto Bottom: " );
                    myOutWriter.println("Tele Top: " );
                    myOutWriter.println("Tele Middle: " );
                    myOutWriter.println("Tele Bottom: " );
                    myOutWriter.println("Missed Intakes: ");
                    myOutWriter.println("Top Array: ");
                    myOutWriter.println("Middle Array: " );
                    myOutWriter.println("Bottom Array: ");
                    myOutWriter.println("Endgame: " );
                    myOutWriter.println("Notes: " );

                    //Defense Played
                    myOutWriter.println("Defense Played: " + defChkTeam1.isChecked());
                    //Defense Score 1-5
                    if(defChkTeam1.isChecked()) {
                        myOutWriter.println("Defense Score: " + defSeekTeam1.getProgress());
                    } else {
                        myOutWriter.println("Defense Score: N/A");
                    }

                    // Score of Quickness Load
                    myOutWriter.println("Quickness Load: " + loadTxtT1.getText().toString());

                    //Score of Quickness Score
                    myOutWriter.println("Quickness Score: " + scoreTxtT1.getText().toString());

                    //Score of Defense Navigation
                    myOutWriter.println("Defense navigation: " + navTxtT1.getText().toString());

                    for(int i=0;i<3;i++) {
                        if(Objects.equals(strings[i], team1.getText().toString())) {
                            myOutWriter.println("Team Order: " + strings[i]);
                            break;
                        }
                    }
                    //Comments of Team
                    myOutWriter.println("Comments: " + commentT1.getText().toString());




                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();


                    myFile = new File(dir, team2.getText().toString() + "_" + round + "_" + time.format(new Date()) + ".txt");
                    fOut = new FileOutputStream(myFile, true);
                    myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));
                    //This prints all of the lines into the file for transfer.
                    myOutWriter.println("Scouter: " + scouter.getText());
                    myOutWriter.println("Team: " + team2.getText().toString());
                    myOutWriter.println("Timestamp: " + time.format(new Date()));
                    myOutWriter.println("Match: " + round);
                    myOutWriter.println("Alliance: " + alliance_sel.getSelectedItem().toString());
                    myOutWriter.println("Robot Errors: ");
                    myOutWriter.println("Auto Docked: " );
                    myOutWriter.println("Auto Top: " );
                    myOutWriter.println("Auto Middle: " );
                    myOutWriter.println("Auto Bottom: " );
                    myOutWriter.println("Tele Top: " );
                    myOutWriter.println("Tele Middle: " );
                    myOutWriter.println("Tele Bottom: " );
                    myOutWriter.println("Missed Intakes: ");
                    myOutWriter.println("Top Array: ");
                    myOutWriter.println("Middle Array: " );
                    myOutWriter.println("Bottom Array: ");
                    myOutWriter.println("Endgame: " );
                    myOutWriter.println("Notes: " );

                    //Defense Played
                    myOutWriter.println("Defense Played: " + defChkTeam2.isChecked());
                    //Defense Score 1-5
                    if(defChkTeam2.isChecked()) {
                        myOutWriter.println("Defense Score: " + defSeekTeam2.getProgress());
                    } else {
                        myOutWriter.println("Defense Score: N/A");
                    }

                    // Score of Quickness Load
                    myOutWriter.println("Quickness Load: " + loadTxtT2.getText().toString());

                    //Score of Quickness Score
                    myOutWriter.println("Quickness Score: " + scoreTxtT2.getText().toString());

                    //Score of Defense Navigation
                    myOutWriter.println("Defense navigation: " + navTxtT2.getText().toString());

                    //Order of teams

                    for(int i=0;i<3;i++) {
                        if(Objects.equals(strings[i], team2.getText().toString())) {
                            myOutWriter.println("Team Order: " + strings[i]);
                            break;
                        }
                    }



                    //Comments of Team
                    myOutWriter.println("Comments: " + commentT2.getText().toString());




                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();


                    myFile = new File(dir, team3.getText().toString() + "_" + round + "_" + time.format(new Date()) + ".txt");
                    fOut = new FileOutputStream(myFile, true);
                    myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));
                    //This prints all of the lines into the file for transfer.
                    myOutWriter.println("Scouter: " + scouter.getText());
                    myOutWriter.println("Team: " + team3.getText().toString());
                    myOutWriter.println("Timestamp: " + time.format(new Date()));
                    myOutWriter.println("Match: " + round);
                    myOutWriter.println("Alliance: " + alliance_sel.getSelectedItem().toString());
                    myOutWriter.println("Robot Errors: ");
                    myOutWriter.println("Auto Docked: " );
                    myOutWriter.println("Auto Top: " );
                    myOutWriter.println("Auto Middle: " );
                    myOutWriter.println("Auto Bottom: " );
                    myOutWriter.println("Tele Top: " );
                    myOutWriter.println("Tele Middle: " );
                    myOutWriter.println("Tele Bottom: " );
                    myOutWriter.println("Missed Intakes: ");
                    myOutWriter.println("Top Array: ");
                    myOutWriter.println("Middle Array: " );
                    myOutWriter.println("Bottom Array: ");
                    myOutWriter.println("Endgame: " );
                    myOutWriter.println("Notes: " );

                    //Defense Played
                    myOutWriter.println("Defense Played: " + defChkTeam3.isChecked());
                    //Defense Score 1-5
                    if(defChkTeam3.isChecked()) {
                        myOutWriter.println("Defense Score: " + defSeekTeam3.getProgress());
                    } else {
                        myOutWriter.println("Defense Score: N/A");
                    }

                    // Score of Quickness Load
                    myOutWriter.println("Quickness Load: " + loadTxtT3.getText().toString());

                    //Score of Quickness Score
                    myOutWriter.println("Quickness Score: " + scoreTxtT3.getText().toString());

                    //Score of Defense Navigation
                    myOutWriter.println("Defense navigation: " + navTxtT3.getText().toString());

                    for(int i=0;i<3;i++) {
                        if(Objects.equals(strings[i], team3.getText().toString())) {
                            myOutWriter.println("Team Order: " + String.valueOf(i));
                            break;
                        }
                    }
                    //Comments of Team
                    myOutWriter.println("Comments: " + commentT3.getText().toString());

                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(), "Data Submitted!", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    //If anything goes wrong, it throws an error instead of crashing
                    Toast.makeText(getApplicationContext(), "Data Submission Failed! (TELL SCOUTING)", Toast.LENGTH_SHORT).show();
                    Log.e("Exception", "File write failed: " + e);
                }
                //Reset Everything
                resetVars();
                for (View line : lines) {
                    line.setBackgroundColor(Color.WHITE);
                }
                alliance_sel.setBackground(spinnerbackground);

            }
        });
    }


    private void resetVars() {
        scouter.setText("");
        match.setText("");
        alliance_sel.setSelection(0);
        team1.setText("");
        team2.setText("");
        team3.setText("");
        defChkTeam1.setChecked(false);
        defChkTeam2.setChecked(false);
        defChkTeam3.setChecked(false);
        defSeekTeam1.setProgress(0);
        defSeekTeam2.setProgress(0);
        defSeekTeam3.setProgress(0);
        defSeekTeam1.setVisibility(View.INVISIBLE);
        defSeekTeam2.setVisibility(View.INVISIBLE);
        defSeekTeam3.setVisibility(View.INVISIBLE);
        team1Txt.setText("TEAM 1");
        team2Txt.setText("TEAM 2");
        team3Txt.setText("TEAM 3");
        scoreTxtT1.setText("0");
        scoreTxtT2.setText("0");
        scoreTxtT3.setText("0");
        loadTxtT1.setText("0");
        loadTxtT2.setText("0");
        loadTxtT3.setText("0");
        navTxtT1.setText("0");
        navTxtT2.setText("0");
        navTxtT3.setText("0");

        if(getTeams().equals("")) {
            stringArrayList.set(0,"Team #1");
            stringArrayList.set(1,"Team #2");
            stringArrayList.set(2,"Team #3");
            mAdapter = new RecyclerViewAdapter(stringArrayList);
            ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(mAdapter);
        } //NEED TO ADD AUTO FILL LOGIC

        commentT1.setText("");
        commentT2.setText("");
        commentT3.setText("");
        commentT1.setHint("Team 1 Notes:");
        commentT2.setHint("Team 2 Notes:");
        commentT3.setHint("Team 3 Notes:");
        round += 1;
        match.setText(String.valueOf(round));




    }

    private void ColorView() {
        if (alliance_sel.getSelectedItem().toString().equals("BLUE")) {
            for(int i=0;i<3;i++) {
                CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                relativeLayout.setBackgroundColor(Color.BLUE);
                TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                textView.setTextColor(Color.WHITE);
            }
        } else if (alliance_sel.getSelectedItem().toString().equals("RED")) {
            for(int i=0;i<3;i++) {
                CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                relativeLayout.setBackgroundColor(Color.RED);
                TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                textView.setTextColor(Color.WHITE);
            }
        }
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



            for(int i = 0; i<3; i++) {
                CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                textView.setText(stringArrayList.get(i));
            }

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