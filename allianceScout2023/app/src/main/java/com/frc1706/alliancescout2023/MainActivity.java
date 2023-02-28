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
import android.content.RestrictionEntry;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Environment;
import android.provider.Settings;
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
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOError;
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
    SeekBar defSeekTeam1,defSeekTeam2,driverAbility1,driverAbility2;
    CheckBox defChkTeam1,defChkTeam2,defChkTeam3;


    String tabletName;
    int tabletnumber;
    String sameScouter;
    Spinner alliance_sel;
    View line1,line2,line3,line4,line6,line7,line8,line10,line12;
    ToggleButton team1Op,team2Op,team3Op;
    EditText team1,team2,team3,scouter,match,commentT1,commentT2;
    TextView inputTeams,loadTxtT1,loadTxtT2,loadTxtT3,scoreTxtT1,scoreTxtT2,scoreTxtT3,navTxtT1,navTxtT2,navTxtT3,team2Txt,team1Txt,playDefTxt,driverTxt,worstTxt,bestTxt,team1Txt2,team2Txt2;

    Button quickLoadMinusT1,quickLoadPlusT1,quickLoadMinusT2,quickLoadPlusT2,quickScoreMinusT1,quickScorePlusT1,quickScoreMinusT2,quickScorePlusT2,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        tabletName = Settings.Secure.getString(getContentResolver(), "bluetooth_name");


        Log.e("New Name",tabletName);
        switch (tabletName) {
            case "1706's 8th Fire":
                tabletnumber = 8;
                break;
            case "1706's 9th Fire":
                tabletnumber = 9;
                break;
        }
        //Spinner
        alliance_sel = findViewById(R.id.alliance_input);

        //Toggle Button
        team1Op = findViewById(R.id.team1Op);
        team2Op  = findViewById(R.id.team2Op);
        team3Op = findViewById(R.id.team3Op);

        //Lines
        line1= findViewById(R.id.line1);
        line2= findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4= findViewById(R.id.line4);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        line8 = findViewById(R.id.line8);
        line10 = findViewById(R.id.line10);
        line12 = findViewById(R.id.line12);
        View[] lines = {line1,line2,line3,line4,line6,line7,line8,line10,line12};

        //CheckBox
        defChkTeam1 = findViewById(R.id.team1DefCK);
        defChkTeam2 = findViewById(R.id.team2DefCK);

        //Seekbar
        defSeekTeam1 = findViewById(R.id.team1DefSEEK);
        defSeekTeam2 = findViewById(R.id.team2DefSEEK);

        //TextView
        inputTeams = findViewById(R.id.inputTeamsTxt);
        loadTxtT1 = findViewById(R.id.textTeam1Load);
        loadTxtT2 = findViewById(R.id.textTeam2Load);
        scoreTxtT1 = findViewById(R.id.textTeam1Score);
        scoreTxtT2 = findViewById(R.id.textTeam2Score);
        team1Txt = findViewById(R.id.team1EditTxt);
        team2Txt = findViewById(R.id.team2EditTxt);
        playDefTxt = findViewById(R.id.playDefTxt);
        driverTxt = findViewById(R.id.driverTxt);
        worstTxt = findViewById(R.id.worstTxt);
        bestTxt = findViewById(R.id.bestTxt);
        team1Txt2 = findViewById(R.id.team1EditTxt2);
        team2Txt2 = findViewById(R.id.team2EditTxt2);

        driverAbility1 = findViewById(R.id.driverAbility1);
        driverAbility2 = findViewById(R.id.driverAbility2);


        //Buttons
        submit = findViewById(R.id.submit);
        quickLoadMinusT1  = findViewById(R.id.minusTeam1Load);
        quickLoadPlusT1 = findViewById(R.id.plusTeam1Load);
        quickLoadMinusT2 = findViewById(R.id.minusTeam2Load);
        quickLoadPlusT2 = findViewById(R.id.plusTeam2Load);
        quickScoreMinusT1 = findViewById(R.id.minusTeam1Score);
        quickScorePlusT1 = findViewById(R.id.plusTeam1Score);
        quickScoreMinusT2 = findViewById(R.id.minusTeam2Score);
        quickScorePlusT2 = findViewById(R.id.plusTeam2Score);


        //EditText
        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        scouter = findViewById(R.id.name_input);
        match = findViewById(R.id.round_input);
        commentT1 = findViewById(R.id.team1Notes);
        commentT2 = findViewById(R.id.team2Notes);

        match.setText(String.valueOf(round));

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                0);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        Log.e("NUMB", String.valueOf(tabletnumber));


        if(tabletnumber==8) {
            alliance_sel.setSelection(2);
            for (View line : lines) {
                line.setBackgroundColor(Color.RED);
            }
            textColorChange("red");
        } else if(tabletnumber==9) {
            alliance_sel.setSelection(1);
            for (View line : lines) {
                line.setBackgroundColor(Color.BLUE);
            }
            textColorChange("blue");
        }

        autoFill();

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

        quickScorePlusT1.setOnClickListener(v-> modScore(scoreTxtT1,false));
        quickScorePlusT2.setOnClickListener(v-> modScore(scoreTxtT2,false));
        quickScoreMinusT1.setOnClickListener(v-> modScore(scoreTxtT1,true));
        quickScoreMinusT2.setOnClickListener(v-> modScore(scoreTxtT2,true));

        quickLoadPlusT1.setOnClickListener(v-> modScore(loadTxtT1,false));
        quickLoadPlusT2.setOnClickListener(v-> modScore(loadTxtT2,false));
        quickLoadMinusT1.setOnClickListener(v-> modScore(loadTxtT1,true));
        quickLoadMinusT2.setOnClickListener(v-> modScore(loadTxtT2,true));




        team1Op.setOnClickListener(v -> populateTeams(team1Op));
        team2Op.setOnClickListener(v -> populateTeams(team2Op));
        team3Op.setOnClickListener(v -> populateTeams(team3Op));





        match.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);

                team1.setText("");
                team2.setText("");
                team1.setHint("Team 1");
                team2.setHint("Team 2");
                team2Txt.setText("Team 2");
                team1Txt.setText("Team 1");
                team2Txt2.setText("Team 2");
                team1Txt2.setText("Team 1");
                commentT2.setHint("Team 2 Notes");
                commentT1.setHint("Team 1 Notes");
                team1Op.setEnabled(true);
                team2Op.setEnabled(true);
                team3Op.setEnabled(true);
                team1Op.setChecked(false);
                team2Op.setChecked(false);
                team3Op.setChecked(false);
                team1Op.setTextSize((float) 18);
                team2Op.setTextSize((float) 18);
                team3Op.setTextSize((float) 18);


                autoFill();

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
        team1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        team2.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        alliance_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                if (alliance_sel.getSelectedItem().toString().equals("BLUE")) {
                    for (View line : lines) {
                        line.setBackgroundColor(Color.BLUE);
                    }

                    textColorChange("blue");


                } else if (alliance_sel.getSelectedItem().toString().equals("RED")) {
                    for (View line : lines) {
                        line.setBackgroundColor(Color.RED);
                    }

                    textColorChange("red");


                }
                autoFill();

            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        Drawable textBackground = match.getBackground();

        Drawable spinnerbackground = alliance_sel.getBackground();

        submit.setOnClickListener(v->{
            String[] strings = {"","",""};

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
                    myOutWriter.println("Played Defense: ");
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

                    myOutWriter.println("Team Order: " + driverAbility1.getProgress());
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

                    myOutWriter.println("Team Order: " + driverAbility2.getProgress());
                    //Comments of Team
                    myOutWriter.println("Comments: " + commentT2.getText().toString());

                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(), "Data Submitted!", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    //If anything goes wrong, it throws an error instead of crashing
                    Toast.makeText(getApplicationContext(), "Data Submission Failed! (TELL SCOUTING)", Toast.LENGTH_SHORT).show();
                    Log.e("Exception", "File write failed: " + e);
                }
                sameScouter = scouter.getText().toString();
                //Reset Everything
                resetVars();

                alliance_sel.setBackground(spinnerbackground);
                scouter.setText(sameScouter);
                autoFill();

            }
        });

    }
    private void populateTeams(ToggleButton button) {
        //DISABLE
        if(team1Op.isChecked() && team2Op.isChecked()) {
            team3Op.setEnabled(false);
        } else if(team1Op.isChecked() && team3Op.isChecked()) {
            team2Op.setEnabled(false);
        } else if (team2Op.isChecked() && team3Op.isChecked()) {
            team1Op.setEnabled(false);
        }

        if(team1Op.isChecked() && !team2Op.isChecked() && !team3Op.isChecked()) {
            team2Op.setEnabled(true);
            team3Op.setEnabled(true);
        } else if(!team1Op.isChecked() && team2Op.isChecked() && !team3Op.isChecked()) {
            team1Op.setEnabled(true);
            team3Op.setEnabled(true);
        } else if(!team1Op.isChecked() && !team2Op.isChecked() && team3Op.isChecked()) {
            team2Op.setEnabled(true);
            team1Op.setEnabled(true);
        }



            if(button.isChecked()) {
                button.setTextSize((float) 24.0);

                if(team1Txt.getText().toString().equals("Team 1") && !team2Txt.getText().toString().equals(button.getText().toString())) {

                    team1Txt.setText(button.getText().toString());
                    team1Txt2.setText(button.getText().toString());
                    team1.setText(button.getText().toString());
                    team1.setEnabled(false);
                    commentT1.setHint(button.getText().toString()+" Notes");

                } else if(team2Txt.getText().toString().equals("Team 2")) {
                    team2Txt.setText(button.getText().toString());
                    team2Txt2.setText(button.getText().toString());
                    team2.setText(button.getText().toString());
                    team2.setEnabled(false);
                    commentT2.setHint(button.getText().toString()+" Notes");
                }


            }else {
                button.setTextSize((float) 18.0);

                if(team1Txt.getText().toString().equals(button.getText().toString())) {
                    team1Txt.setText("Team 1");
                    team1Txt2.setText("Team 1");
                    team1.setHint("Team 1");
                    team1.setText("");
                    team1.setEnabled(true);

                    commentT1.setHint("Team 1 Notes");
                } else if(team2Txt.getText().toString().equals(button.getText().toString())) {
                    team2Txt.setText("Team 2");
                    team2Txt2.setText("Team 2");
                    team2.setHint("Team 2");
                    team2.setText("");
                    team2.setEnabled(true);
                    commentT2.setHint("Team 2 Notes");
                }
            }
    }

    private void resetVars() {
        team1.setEnabled(true);
        team2.setEnabled(true);
        scouter.setText("");
        match.setText("");
        team1.setText("");
        team2.setText("");
        defChkTeam1.setChecked(false);
        defChkTeam2.setChecked(false);
        defSeekTeam1.setProgress(0);
        defSeekTeam2.setProgress(0);
        defSeekTeam1.setVisibility(View.INVISIBLE);
        defSeekTeam2.setVisibility(View.INVISIBLE);
        team1Txt.setText("TEAM 1");
        team2Txt.setText("TEAM 2");
        scoreTxtT1.setText("0");
        scoreTxtT2.setText("0");
        loadTxtT1.setText("0");
        loadTxtT2.setText("0");
        commentT1.setText("");
        commentT2.setText("");
        commentT1.setHint("Team 1 Notes:");
        commentT2.setHint("Team 2 Notes:");
        team1Op.setChecked(false);
        team2Op.setChecked(false);
        team3Op.setChecked(false);
        team1Op.setEnabled(true);
        team2Op.setEnabled(true);
        team3Op.setEnabled(true);
        team1Op.setTextSize((float) 18);
        team2Op.setTextSize((float) 18);
        team3Op.setTextSize((float) 18);
        round += 1;
        match.setText(String.valueOf(round));
        driverAbility1.setProgress(0);
        driverAbility2.setProgress(0);

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

        return text.toString();
    }

    public void textColorChange(String color) {
        if(Objects.equals(color, "blue")) {
            inputTeams.setTextColor(getResources().getColor(R.color.NewBlue));
            team1Txt.setTextColor(getResources().getColor(R.color.NewBlue));
            team2Txt.setTextColor(getResources().getColor(R.color.NewBlue));
            alliance_sel.setBackgroundColor(getResources().getColor(R.color.NewBlue));
            playDefTxt.setTextColor(getResources().getColor(R.color.NewBlue));
            driverTxt.setTextColor(getResources().getColor(R.color.NewBlue));
            worstTxt.setTextColor(getResources().getColor(R.color.NewBlue));
            bestTxt.setTextColor(getResources().getColor(R.color.NewBlue));
            team1Txt2.setTextColor(getResources().getColor(R.color.NewBlue));
            team2Txt2.setTextColor(getResources().getColor(R.color.NewBlue));
            team1Op.setBackgroundColor(Color.BLUE);
            team2Op.setBackgroundColor(Color.BLUE);
            team3Op.setBackgroundColor(Color.BLUE);
            team1Op.setTextColor(Color.WHITE);
            team2Op.setTextColor(Color.WHITE);
            team3Op.setTextColor(Color.WHITE);

        } else if (Objects.equals(color, "red")) {
            inputTeams.setTextColor(getResources().getColor(R.color.NewRed));
            team1Txt.setTextColor(getResources().getColor(R.color.NewRed));
            team2Txt.setTextColor(getResources().getColor(R.color.NewRed));
            alliance_sel.setBackgroundColor(getResources().getColor(R.color.NewRed));
            playDefTxt.setTextColor(getResources().getColor(R.color.NewRed));
            driverTxt.setTextColor(getResources().getColor(R.color.NewRed));
            worstTxt.setTextColor(getResources().getColor(R.color.NewRed));
            bestTxt.setTextColor(getResources().getColor(R.color.NewRed));
            team1Txt2.setTextColor(getResources().getColor(R.color.NewRed));
            team2Txt2.setTextColor(getResources().getColor(R.color.NewRed));
            team1Op.setBackgroundColor(Color.RED);
            team2Op.setBackgroundColor(Color.RED);
            team3Op.setBackgroundColor(Color.RED);

            team1Op.setTextColor(Color.WHITE);
            team2Op.setTextColor(Color.WHITE);
            team3Op.setTextColor(Color.WHITE);

        }
    }

    public void onBackPressed() {}

    private void autoFill() {
        if(!getTeams().equals("")) {
            team1Op.setVisibility(View.VISIBLE);
            team2Op.setVisibility(View.VISIBLE);
            team3Op.setVisibility(View.VISIBLE);
            team1.setEnabled(false);
            team2.setEnabled(false);

            String[] tempIntArr;
            String[] splittempIntArr;
            tempIntArr = getTeams().split("\n");
            int arrayRound = Integer.parseInt(match.getText().toString()) - 1;
            splittempIntArr = tempIntArr[arrayRound].split(",");

            if (alliance_sel.getSelectedItem().toString().equals("RED")) {
                try {
                    team1Op.setText(splittempIntArr[0]);
                    team2Op.setText(splittempIntArr[1]);
                    team3Op.setText(splittempIntArr[2]);
                    team1Op.setTextOn(splittempIntArr[0]);
                    team2Op.setTextOn(splittempIntArr[1]);
                    team3Op.setTextOn(splittempIntArr[2]);
                    team1Op.setTextOff(splittempIntArr[0]);
                    team2Op.setTextOff(splittempIntArr[1]);
                    team3Op.setTextOff(splittempIntArr[2]);

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }


            } else if (alliance_sel.getSelectedItem().toString().equals("BLUE")) {
                try {
                    team1Op.setText(splittempIntArr[3]);
                    team2Op.setText(splittempIntArr[4]);
                    team3Op.setText(splittempIntArr[5]);
                    team1Op.setTextOn(splittempIntArr[3]);
                    team2Op.setTextOn(splittempIntArr[4]);
                    team3Op.setTextOn(splittempIntArr[5]);
                    team1Op.setTextOff(splittempIntArr[3]);
                    team2Op.setTextOff(splittempIntArr[4]);
                    team3Op.setTextOff(splittempIntArr[5]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }


    }

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
}