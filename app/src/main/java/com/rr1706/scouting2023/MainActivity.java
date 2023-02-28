package com.rr1706.scouting2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageView data_submitted,rrlogo,cone0, cube0, cone1, cone2, cube1, cone3, cone4, cube2, cone5, cone6, cube3, cone7, cone8, cube4, cone9, cone10, cube5, cone11,both0,both1,both2,both3,both4,both5,both6,both7,both8;
    EditText name_input,round_input,team_input,notes;
    Spinner AutoEngage,EndgameEngage;
    Button PregameBtn,AutoChange,pregame_close,noShow,Red_Alliance,Blue_Alliance,sameScouter,submit,Gray_Box;
    CheckBox teamAutofill,playedDefense;
    TextView allianceText,dummyTeam,endgameHide;
    Switch robotError;

    int teleTop;
    int teleMid;
    int teleLow;
    int autoTop;
    int autoMid;
    int autoLow;

    int roundfill = 1;
    int ds_cooldown = 0;
    int missedIntake = 0;
    String tabletName;
    int tabletnumber;
    int[][] grid = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
    String alliance = "none";
    String mode = "auto";
    String scouterName;

    ConstraintLayout Background,Pregame;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dummyTeam = findViewById(R.id.dummyTeam);
        robotError = findViewById(R.id.robotError);
        submit = findViewById(R.id.submit);
        playedDefense = findViewById(R.id.playedDefense);
        data_submitted = findViewById(R.id.data_submitted);
        teamAutofill = findViewById(R.id.autoFill);
        sameScouter = findViewById(R.id.sameScouter);
        name_input = findViewById(R.id.name_input);
        round_input = findViewById(R.id.round_input);
        team_input = findViewById(R.id.team_input);
        notes = findViewById(R.id.notes);
        AutoEngage = findViewById(R.id.autoSpinner);
        EndgameEngage = findViewById(R.id.endgameSpinner);
        Gray_Box = findViewById(R.id.Gray_Box);
        PregameBtn= findViewById(R.id.pregameBtn);
        endgameHide = findViewById(R.id.endgameTxt);
        AutoChange= findViewById(R.id.autoChange);
        Blue_Alliance = findViewById(R.id.Blue_Alliance);
        Red_Alliance = findViewById(R.id.Red_Alliance);
        pregame_close= findViewById(R.id.pregame_close);
        noShow= findViewById(R.id.no_show);
        allianceText = findViewById(R.id.alliance_text);
        Background = findViewById(R.id.Background);
        Pregame = findViewById(R.id.Pregame);
        rrlogo = findViewById(R.id.rrlogo);
        Gray_Box.setBackgroundColor(Color.argb(200, 240, 240, 240));
        round_input.setText(String.valueOf(roundfill));
        Drawable textBackground = round_input.getBackground();
        Drawable nameBackground = name_input.getBackground();
        Drawable spinnerbackground = AutoEngage.getBackground();
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
        both8 = findViewById(R.id.both8);
        ImageView[][] imArray = {{cone0, cube0, cone1, cone2, cube1, cone3, cone4, cube2, cone5},{cone6, cube3, cone7, cone8, cube4, cone9, cone10, cube5, cone11},{both0,both1,both2,both3,both4,both5,both6,both7,both8}};

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                0);

        if (!Objects.equals(getTeams(), "")) {
            teamAutofill.setChecked(true);
        }

        tabletName = Settings.Secure.getString(getContentResolver(), "bluetooth_name");

        switch (tabletName) {
            case "1706's 1st Fire":
                tabletnumber = 4;
                break;
            case "1706's 2nd Fire":
                tabletnumber = 5;
                break;
            case "1706's 3rd Fire":
                tabletnumber = 6;
                break;
            case "1706's 5th Fire":
                tabletnumber = 1;
                break;
            case "1706's 6th Fire":
                tabletnumber = 2;
                break;
            case "1706's 7th Fire":
                tabletnumber = 3;
                break;
        }

        int tabletnumbercomp = tabletnumber - 1;


        //Change From Auto To Teleop - Needs to disable the clicked ones in Auto when transitioning - Needs to reenable disabled if switch back to auto
        AutoChange.setOnClickListener(v->{
            Log.e("VALUES:||", autoTop+","+autoMid+","+autoLow+","+teleTop+","+teleMid+","+teleLow);
            if(mode.equals("auto")) {
                mode = "teleop";
                submit.setVisibility(View.VISIBLE);
                endgameHide.setVisibility(View.VISIBLE);
                EndgameEngage.setVisibility(View.VISIBLE);
                AutoChange.setText("TELEOP");
                for(int i=0; i < grid.length; i++){
                    for(int j=0; j<grid[i].length; j++) {
                        if(grid[i][j] == 1) {
                            imArray[i][j].setEnabled(false);
                        }

                    }
                }

            } else {
                mode = "auto";
                AutoChange.setText("AUTO");
                submit.setVisibility(View.INVISIBLE);

                endgameHide.setVisibility(View.INVISIBLE);
                EndgameEngage.setVisibility(View.INVISIBLE);
                for(int i=0; i < grid.length; i++){
                    for(int j=0; j<grid[i].length; j++) {
                        if(!imArray[i][j].isEnabled()) {
                            imArray[i][j].setEnabled(true);
                        }

                    }
                }
            }
        });


        notes.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });



        Blue_Alliance.setOnClickListener(v -> {
            Background.setBackgroundColor(Color.argb(127, 127, 127, 247));
            Pregame.setBackgroundColor(Color.argb(255, 127, 127, 247));
            alliance = "blue";
        });
        //This sets the alliance red and sets the background color red.
        Red_Alliance.setOnClickListener(v -> {
            Background.setBackgroundColor(Color.argb(127, 247, 127, 127));
            Pregame.setBackgroundColor(Color.argb(255, 247, 127, 127));
            alliance = "red";
        });

        pregame_close.setOnClickListener(view -> {
            String closeError = "";
            if (team_input.getText().toString().equals("")) {
                closeError += " No Team,";
                team_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (round_input.getText().toString().equals("")) {
                closeError += " No Match,";
                round_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (alliance.equals("none")) {
                closeError += " No Alliance,";
                allianceText.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (name_input.getText().toString().equals("")) {
                closeError += " No Name,";
                name_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (!closeError.equals("")) {
                closeError = closeError.substring(0, closeError.length() - 1) + ".";
            }
            if (!(closeError.equals(""))) {
                Toast.makeText(getApplicationContext(), "Submit Error:" + closeError, Toast.LENGTH_LONG).show();
                data_submitted.setVisibility(View.VISIBLE);
                data_submitted.setImageResource(R.drawable.x);
                ds_cooldown = 1500;
            } else {
                Pregame.setVisibility(View.INVISIBLE);
                Gray_Box.setVisibility(View.INVISIBLE);
                allianceText.setBackgroundColor(Color.TRANSPARENT);
                round_input.setBackground(textBackground);
                team_input.setBackground(textBackground);
                name_input.setBackground(nameBackground);
                notes.setVisibility(View.VISIBLE);
            }
        });


        PregameBtn.setOnClickListener(v->{
            if (Pregame.getVisibility() == View.INVISIBLE | Pregame.getVisibility() == View.GONE) {
                Pregame.setVisibility(View.VISIBLE);
                notes.setVisibility(View.INVISIBLE);
                Gray_Box.setVisibility(View.VISIBLE);
                Pregame.bringToFront();
            } else if (Pregame.getVisibility() == View.VISIBLE) {
                notes.setVisibility(View.VISIBLE);
                Pregame.setVisibility(View.INVISIBLE);
                Gray_Box.setVisibility(View.INVISIBLE);
            }
        });






        Runnable myRunnable = () -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {}
                //This is just the big check or X if people submit data.
                data_submitted.post(() -> {
                            //data_submitted stuff
                            if (ds_cooldown > 0) {
                                ds_cooldown--;
                            }
                            if (ds_cooldown == 0) {
                                data_submitted.setVisibility(View.GONE);
                            }
                        });
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();


        both0.setOnClickListener(v-> arrayUpdate(2,0,R.id.both0));
        both1.setOnClickListener(v-> arrayUpdate(2,1,R.id.both1));
        both2.setOnClickListener(v-> arrayUpdate(2,2,R.id.both2));
        both3.setOnClickListener(v-> arrayUpdate(2,3,R.id.both3));
        both4.setOnClickListener(v-> arrayUpdate(2,4,R.id.both4));
        both5.setOnClickListener(v-> arrayUpdate(2,5,R.id.both5));
        both6.setOnClickListener(v-> arrayUpdate(2,6,R.id.both6));
        both7.setOnClickListener(v-> arrayUpdate(2,7,R.id.both7));
        both8.setOnClickListener(v-> arrayUpdate(2,8,R.id.both8));
        cube0.setOnClickListener(v-> arrayUpdate(0,1,R.id.cube0));
        cube1.setOnClickListener(v-> arrayUpdate(0,4,R.id.cube1));
        cube2.setOnClickListener(v-> arrayUpdate(0,7,R.id.cube2));
        cube3.setOnClickListener(v-> arrayUpdate(1,1,R.id.cube3));
        cube4.setOnClickListener(v-> arrayUpdate(1,4,R.id.cube4));
        cube5.setOnClickListener(v-> arrayUpdate(1,7,R.id.cube5));
        cone0.setOnClickListener(v-> arrayUpdate(0,0,R.id.cone0));
        cone1.setOnClickListener(v-> arrayUpdate(0,2,R.id.cone1));
        cone2.setOnClickListener(v-> arrayUpdate(0,3,R.id.cone2));
        cone3.setOnClickListener(v-> arrayUpdate(0,5,R.id.cone3));
        cone4.setOnClickListener(v-> arrayUpdate(0,6,R.id.cone4));
        cone5.setOnClickListener(v-> arrayUpdate(0,8,R.id.cone5));
        cone6.setOnClickListener(v-> arrayUpdate(1,0,R.id.cone6));
        cone7.setOnClickListener(v-> arrayUpdate(1,2,R.id.cone7));
        cone8.setOnClickListener(v-> arrayUpdate(1,3,R.id.cone8));
        cone9.setOnClickListener(v-> arrayUpdate(1,5,R.id.cone9));
        cone10.setOnClickListener(v-> arrayUpdate(1,6,R.id.cone10));
        cone11.setOnClickListener(v-> arrayUpdate(1,8,R.id.cone11));

        final DialogInterface.OnClickListener NoShowDialog = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE: //If the yes button is clicked for no show, this executes
                    SimpleDateFormat time = new SimpleDateFormat("dd-HHmmss", Locale.getDefault());
                    File dir = getDataDirectory();
                    try {
                        File myFile = new File(dir, team_input.getText().toString() + "_" + round_input.getText().toString() + "_" + time.format(new Date()) + ".txt");
                        FileOutputStream fOut = new FileOutputStream(myFile, true);
                        PrintWriter myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));
                        myOutWriter.println("Scouter: " + name_input.getText().toString());
                        myOutWriter.println("Team: " + team_input.getText().toString());
                        myOutWriter.println("Timestamp: " + time.format(new Date()));
                        myOutWriter.println("Match: " + round_input.getText().toString());
                        myOutWriter.flush();
                        myOutWriter.close();
                        fOut.close();
                        Toast.makeText(getApplicationContext(), "Data Submitted!", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Data Submission Failed! (Tell scouting)", Toast.LENGTH_SHORT).show();
                        Log.e("Exception", "File write failed: " + e);
                        data_submitted.setImageResource(R.drawable.x);
                        data_submitted.setVisibility(View.VISIBLE);
                    }
                    allianceText.setBackgroundColor(Color.TRANSPARENT);
                    round_input.setBackground(textBackground);
                    team_input.setBackground(textBackground);
                    name_input.setBackground(nameBackground);
                    if (ds_cooldown == 0) {
                        data_submitted.setImageResource(R.drawable.check);
                        data_submitted.setVisibility(View.VISIBLE);
                    }
                    resetVars();
                    teamAuto();

                    if (!teamAutofill.isChecked()) {
                        team_input.setText("");
                    }
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        teamAuto();

        if (team_input.getText().toString().equals("1706")) {
            rrlogo.animate().rotation(2880f).setDuration(5000).start();
        }
        teamAutofill.setOnClickListener(v -> {
            teamAuto();

            if (team_input.getText().toString().equals("1706")) {
                    rrlogo.animate().rotation(2880f).setDuration(5000).start();
                }
            if (!teamAutofill.isChecked()) {
                team_input.setText("");
            }
        });

        sameScouter.setOnClickListener(v -> name_input.setText(scouterName));


        noShow.setOnClickListener(v -> {
            String submitError = "";
            //Special handling
            if (alliance.equals("none")) {
                submitError += " No Alliance,";
            }
            if (name_input.getText().toString().equals("")) {
                submitError += " No Name,";
            }
            if (team_input.getText().toString().equals("")) {
                submitError += " No Team#,";
            }
            if (round_input.getText().toString().equals("")) {
                submitError += " No Round#,";
            }
            if (!submitError.equals("")) {
                submitError = submitError.substring(0, submitError.length() - 1) + ".";
            }
            if (!(submitError.equals(""))) {
                Toast.makeText(getApplicationContext(), "Submit Error:" + submitError, Toast.LENGTH_LONG).show();
                data_submitted.setVisibility(View.VISIBLE);
                data_submitted.setImageResource(R.drawable.x);
                ds_cooldown = 1500;
            } else {
                builder.setMessage("Are you sure the team is a no show?")
                        .setPositiveButton("Yes", NoShowDialog)
                        .setNegativeButton("No", NoShowDialog)
                        .show();
            }
        });


        Gray_Box.setOnClickListener(View -> {
            if (Pregame.getVisibility() == android.view.View.VISIBLE) {
                String closeError = "";
                if (team_input.getText().toString().equals("")) {
                    closeError += " No Team,";
                    team_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
                }
                if (round_input.getText().toString().equals("")) {
                    closeError += " No Match,";
                    round_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
                }
                if (alliance.equals("none")) {
                    closeError += " No Alliance,";
                    allianceText.setBackgroundColor(Color.argb(255, 255, 255, 0));
                }
                if (name_input.getText().toString().equals("")) {
                    closeError += " No Name,";
                    name_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
                }
                if (!closeError.equals("")) {
                    closeError = closeError.substring(0, closeError.length() - 1) + ".";
                }

                if (!(closeError.equals(""))) {
                    Toast.makeText(getApplicationContext(), "Submit Error:" + closeError, Toast.LENGTH_LONG).show();

                    data_submitted.setVisibility(android.view.View.VISIBLE);
                    data_submitted.setImageResource(R.drawable.x);
                    ds_cooldown = 1500;
                } else {
                    Pregame.setVisibility(android.view.View.INVISIBLE);
                    Gray_Box.setVisibility(android.view.View.INVISIBLE);
                    notes.setVisibility(View.VISIBLE);
                    allianceText.setBackgroundColor(Color.TRANSPARENT);
                    round_input.setBackground(textBackground);
                    team_input.setBackground(textBackground);
                    name_input.setBackground(nameBackground);
                }
            }

        });


        name_input.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        round_input.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && !Objects.equals(getTeams(), "")) {
                String[] tempIntArr = null;
                String[] splittempIntArr = null;
                tempIntArr = getTeams().split("\n");

                if (round_input.getText().toString().equals("")) {
                    round_input.setText("1");
                } else {
                    roundfill = Integer.parseInt(round_input.getText().toString());
                }
                try {
                    splittempIntArr = tempIntArr[roundfill - 1].split(",");
                    hideKeyboard(v);
                    if (teamAutofill.isChecked()) {
                        team_input.setText(splittempIntArr[tabletnumbercomp]);
                        dummyTeam.setText(team_input.getText());

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    hideKeyboard(v);

                }

            }
        });





        team_input.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
                dummyTeam.setText(team_input.getText());

            }
            if (team_input.getText().toString().equals("1706")) {
                rrlogo.animate().rotation(2880f).setDuration(5000).start();
            }
        });


        submit.setOnClickListener(v -> {
            String submitError = "";
            SimpleDateFormat time = new SimpleDateFormat("dd-HHmmss", Locale.getDefault());
            int team;
            int round;
            //Special handling
            if (team_input.getText().toString().equals("")) {
                team = -1;
            } else {
                team = Integer.parseInt(team_input.getText().toString());
            }
            if (round_input.getText().toString().equals("")) {
                round = -1;
            } else {
                round = Integer.parseInt(round_input.getText().toString());
            }
            //These are telling the toast what to put in the error field, and it changes the color to yellow.
            if (alliance.equals("none")) {
                submitError += " No Alliance,";
                allianceText.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.VISIBLE);
            }
            if (name_input.getText().toString().equals("")) {
                submitError += " No Name,";
                name_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.VISIBLE);
            }
            if (round_input.getText().toString().equals("420")) {
                submitError += " No. Its not even funny,";
                round_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.VISIBLE);
            }
            if (AutoEngage.getSelectedItem().toString().equals("No Input")) {
                submitError += " No Auto Choice,";
                AutoEngage.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.INVISIBLE);
            }
            if (EndgameEngage.getSelectedItem().toString().equals("No Input")) {
                submitError += " No Endgame Choice,";
                EndgameEngage.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.INVISIBLE);
            }
            if (team_input.getText().toString().equals("")) {
                submitError += " No Team#,";
                team_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.VISIBLE);
            }
            if (round_input.getText().toString().equals("")) {
                submitError += " No Round#,";
                round_input.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.VISIBLE);
            }
            if (!submitError.equals("")) {
                submitError = submitError.substring(0, submitError.length() - 1) + ".";
            }
            //If any of the above are true, the thing returns a submit error.
            if (!(submitError.equals(""))) {
                Toast.makeText(getApplicationContext(), "Submit Error:" + submitError, Toast.LENGTH_LONG).show();
                //Place an X if incorrect
                data_submitted.setVisibility(View.VISIBLE);
                data_submitted.setImageResource(R.drawable.x);
                ds_cooldown = 1500;
                if (Pregame.getVisibility() != View.VISIBLE) {
                    Gray_Box.setVisibility(View.INVISIBLE);
                }

            } else {
                //This is what happens whenever all is correct
                data_submitted.setVisibility(View.VISIBLE);
                data_submitted.setImageResource(R.drawable.check);
                team_input.setBackground(textBackground);
                round_input.setBackground(textBackground);
                name_input.setBackground(nameBackground);
                allianceText.setBackgroundColor(Color.TRANSPARENT);
                AutoEngage.setBackground(spinnerbackground);
                EndgameEngage.setBackground(spinnerbackground);
                ds_cooldown = 1500; //Makes the check mark appear

                //Save data for transfer
                File dir = getDataDirectory();
                //This creates a file
                try {
                    File myFile = new File(dir, team + "_" + round + "_" + time.format(new Date()) + ".txt");
                    FileOutputStream fOut = new FileOutputStream(myFile, true);
                    PrintWriter myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));
                    //This prints all of the lines into the file for transfer.
                    myOutWriter.println("Scouter: " + name_input.getText());
                    myOutWriter.println("Team: " + team);
                    myOutWriter.println("Timestamp: " + time.format(new Date()));
                    myOutWriter.println("Match: " + round);
                    myOutWriter.println("Alliance: " + alliance);
                    myOutWriter.println("Robot Errors: " + robotError.isChecked());

                    myOutWriter.println("Auto Docked: " + AutoEngage.getSelectedItem());
                    myOutWriter.println("Auto Top: " + autoTop);
                    myOutWriter.println("Auto Middle: " + autoMid);
                    myOutWriter.println("Auto Bottom: " + autoLow);

                    myOutWriter.println("Tele Top: " + teleTop);
                    myOutWriter.println("Tele Middle: " + teleMid);
                    myOutWriter.println("Tele Bottom: " + teleLow);

                    myOutWriter.println("Played Defense: " + playedDefense.isChecked());

                    myOutWriter.println("Top Array: " + Arrays.toString(grid[0]));
                    myOutWriter.println("Middle Array: " + Arrays.toString(grid[1]));
                    myOutWriter.println("Bottom Array: " + Arrays.toString(grid[2]));

                    myOutWriter.println("Endgame: " + EndgameEngage.getSelectedItem());
                    myOutWriter.println("Notes: " + notes.getText());

                    myOutWriter.println("Defense Played: ");
                    myOutWriter.println("Defense Score: ");
                    myOutWriter.println("Quickness Load: ");
                    myOutWriter.println("Quickness Score: ");
                    myOutWriter.println("Team Order: ");
                    myOutWriter.println("Comments: ");


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
                AutoEngage.setBackgroundColor(Color.WHITE);
                EndgameEngage.setBackgroundColor(Color.WHITE);

                teamAuto();
            }
        });

        if (tabletnumber <= 3) {
            Red_Alliance.performClick();
        } else {
            Blue_Alliance.performClick();
        }



    }

    private void teamAuto() {

        if (teamAutofill.isChecked() && !Objects.equals(getTeams(), "")) {
            int tabletnumbercomp = tabletnumber - 1;
            String[] tempIntArr = null;
            String[] splittempIntArr = null;
            tempIntArr = getTeams().split("\n");
            roundfill = Integer.parseInt(round_input.getText().toString());
            try {
                splittempIntArr = tempIntArr[roundfill - 1].split(",");
                team_input.setText(splittempIntArr[tabletnumbercomp]);
                dummyTeam.setText(team_input.getText());

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetVars() {
        roundfill = Integer.parseInt(round_input.getText().toString());
        roundfill++;
        autoTop = 0;
        autoMid = 0;
        autoLow = 0;
        teleTop = 0;
        for (int[] ints : grid) {
            Arrays.fill(ints, 0);
        }
        teleMid = 0;
        teleLow = 0;
        missedIntake = 0;
        ImageView[][] imArray = {
                {cone0, cube0, cone1, cone2, cube1, cone3, cone4, cube2, cone5},
                {cone6, cube3, cone7, cone8, cube4, cone9, cone10, cube5, cone11},
                {both0,both1,both2,both3,both4,both5,both6,both7,both8}
        };


        for(int i=0; i < imArray.length; i++){
            for(int j=0; j<imArray[i].length; j++) {
                if(!imArray[i][j].isEnabled()) {
                    imArray[i][j].setEnabled(true);
                }

                List<Integer> coneCol = Arrays.asList(0,2,3,5,6,8);

                if(coneCol.contains(j) && i < 2) {
                    imArray[i][j].setImageResource(R.drawable.redcone);
                } else if (!coneCol.contains(j) && i < 2){
                    imArray[i][j].setImageResource(R.drawable.redcube);
                } else if(i==2) {
                    imArray[2][j].setImageResource(R.drawable.redboth);
                }

            }
        }
        notes.setText("");
        scouterName = name_input.getText().toString();
        name_input.setText("");
        round_input.setText(String.valueOf(roundfill));
        team_input.setText("");
        EndgameEngage.setSelection(0);
        AutoEngage.setSelection(0);
        AutoChange.setText("AUTO");
        submit.setVisibility(View.INVISIBLE);
        mode = "auto";
        robotError.setChecked(false);
        playedDefense.setChecked(false);
        Pregame.setVisibility(View.VISIBLE);
        Gray_Box.setVisibility(View.VISIBLE);
        notes.setVisibility(View.INVISIBLE);
        if (roundfill > 1) {
            sameScouter.setVisibility(View.VISIBLE);
        }
        if (!teamAutofill.isChecked()) {
            team_input.setText("");
        }
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

    private void arrayUpdate(int row, int column, int id) {
        //Determine Cone Cube or Both
        ImageView[][] imArray =  {{cone0, cube0, cone1, cone2, cube1, cone3, cone4, cube2, cone5}, {cone6, cube3, cone7, cone8, cube4, cone9, cone10, cube5, cone11}, {both0, both1, both2, both3, both4, both5, both6, both7, both8}};
        final AlertDialog.Builder popup = new AlertDialog.Builder(this);

        final DialogInterface.OnClickListener AutoEnd = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                mode = "teleop";
                submit.setVisibility(View.VISIBLE);
                AutoChange.setText("TELEOP");
                for(int i=0; i < grid.length; i++){
                    for(int j=0; j<grid[i].length; j++) {
                        if(grid[i][j] == 1) {
                            imArray[i][j].setEnabled(false);
                        }

                    }
                }
            }
        };


        if ((autoTop + autoMid + autoLow >= 4) && Objects.equals(mode, "auto")) {
            popup.setMessage("Are you supposed to be in Auto still?")
                    .setPositiveButton("Yes",AutoEnd)
                    .setNegativeButton("No",AutoEnd)
                    .show();
        }

        ImageView image = findViewById(id);
        List<Integer> coneCol = Arrays.asList(0,2,3,5,6,8);
        if(row == 0) {
            if (grid[row][column] == 0) {
                if(coneCol.contains(column)) {
                    image.setImageResource(R.drawable.greencone);
                } else {
                    image.setImageResource(R.drawable.greencube);
                }

                grid[row][column] = 1;

                if(Objects.equals(mode, "auto")) {
                    autoTop += 1;
                } else {
                    teleTop += 1;
                }

            } else if (grid[row][column] == 1) {
                if(coneCol.contains(column)) {
                    image.setImageResource(R.drawable.redcone);
                } else {
                    image.setImageResource(R.drawable.redcube);
                }

                grid[row][column] = 0;

                if(Objects.equals(mode, "auto")) {
                    if(autoTop==0) {
                        teleTop -= 1;
                    } else {
                        autoTop -= 1;
                    }
                } else {
                    teleTop -= 1;
                }

            }
        }else if(row == 1) {
            if (grid[row][column] == 0) {
                if(coneCol.contains(column)) {
                    image.setImageResource(R.drawable.greencone);
                } else {
                    image.setImageResource(R.drawable.greencube);
                }

                grid[row][column] = 1;

                if(Objects.equals(mode, "auto")) {
                    autoMid += 1;
                } else {
                    teleMid += 1;
                }

            } else if (grid[row][column] == 1) {
                if(coneCol.contains(column)) {
                    image.setImageResource(R.drawable.redcone);
                } else {
                    image.setImageResource(R.drawable.redcube);
                }

                grid[row][column] = 0;

                if(Objects.equals(mode, "auto")) {
                    if(autoMid==0) {
                        teleMid -=1;
                    } else {
                        autoMid -= 1;
                    }

                } else {
                    teleMid -= 1;
                }

            }
        } else if(row == 2) {
            if (grid[row][column] == 0) {
                image.setImageResource(R.drawable.greenboth);
                grid[row][column] = 1;
                if(Objects.equals(mode, "auto")) {
                    autoLow += 1;
                } else {
                    teleLow += 1;
                }

            } else if (grid[row][column] == 1) {
                image.setImageResource(R.drawable.redboth);
                grid[row][column] = 0;

                if(Objects.equals(mode, "auto")) {
                    if(autoLow==0) {
                        teleLow -=1;
                    } else {
                        autoLow -= 1;
                    }
                } else {
                    teleLow -= 1;
                }
            }


        }

    }

    @Override
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
}