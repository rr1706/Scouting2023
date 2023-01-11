package com.rr1706.scouting2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
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
    ImageView btnPlus,btnMinus,data_submitted,rrlogo,cone0, cube0, cone1, cone2, cube1, cone3, cone4, cube2, cone5, cone6, cube3, cone7, cone8, cube4, cone9, cone10, cube5, cone11,both0,both1,both2,both3,both4,both5,both6,both7,both8;
    EditText name_input,round_input,team_input,notes;
    Spinner AutoEngage,EndgameEngage;
    Button PregameBtn,AutoChange,pregame_close,noShow,Red_Alliance,Blue_Alliance,sameScouter,submit,Gray_Box;
    CheckBox teamAutofill;
    TextView allianceText,missedScore;
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

    ConstraintLayout Background,Pregame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        robotError = findViewById(R.id.robotError);
        submit = findViewById(R.id.submit);
        missedScore = findViewById(R.id.missedScore);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
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

        AutoChange.setOnClickListener(v->{
            if(mode.equals("auto")) {
                mode = "teleop";
                AutoChange.setText("TELEOP");
            } else {
                mode = "auto";
                AutoChange.setText("AUTO");
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
        btnPlus.setOnClickListener(v -> {
            if (missedIntake < 99) {
                missedIntake++;
            }
            missedScore.setText(Integer.toString(missedIntake));
        });
        btnMinus.setOnClickListener(v -> {
            if (missedIntake > 0) {
                missedIntake--;
            }
            missedScore.setText(Integer.toString(missedIntake));
        });

        Thread myThread = new Thread(myRunnable);
        myThread.start();


        both0.setOnClickListener(v->{
            arrayUpdate(2,0,R.id.both0);
        });
        both1.setOnClickListener(v->{
            arrayUpdate(2,1,R.id.both1);
        });
        both2.setOnClickListener(v->{
            arrayUpdate(2,2,R.id.both2);
        });
        both3.setOnClickListener(v->{
            arrayUpdate(2,3,R.id.both3);
        });
        both4.setOnClickListener(v->{
            arrayUpdate(2,4,R.id.both4);
        });
        both5.setOnClickListener(v->{
            arrayUpdate(2,5,R.id.both5);
        });
        both6.setOnClickListener(v->{
            arrayUpdate(2,6,R.id.both6);
        });
        both7.setOnClickListener(v->{
            arrayUpdate(2,7,R.id.both7);
        });
        both8.setOnClickListener(v->{
            arrayUpdate(2,8,R.id.both8);
        });
        cube0.setOnClickListener(v->{
            arrayUpdate(0,1,R.id.cube0);
        });
        cube1.setOnClickListener(v->{
            arrayUpdate(0,4,R.id.cube1);
        });
        cube2.setOnClickListener(v->{
            arrayUpdate(0,7,R.id.cube2);
        });
        cube3.setOnClickListener(v->{
            arrayUpdate(1,1,R.id.cube3);
        });
        cube4.setOnClickListener(v->{
            arrayUpdate(1,4,R.id.cube4);
        });
        cube5.setOnClickListener(v->{
            arrayUpdate(1,7,R.id.cube5);
        });
        cone0.setOnClickListener(v->{
            arrayUpdate(0,0,R.id.cone0);
        });
        cone1.setOnClickListener(v->{
            arrayUpdate(0,2,R.id.cone1);
        });
        cone2.setOnClickListener(v->{
            arrayUpdate(0,3,R.id.cone2);
        });
        cone3.setOnClickListener(v->{
            arrayUpdate(0,5,R.id.cone3);
        });
        cone4.setOnClickListener(v->{
            arrayUpdate(0,6,R.id.cone4);
        });
        cone5.setOnClickListener(v->{
            arrayUpdate(0,8,R.id.cone5);
        });

        cone6.setOnClickListener(v->{
            arrayUpdate(1,0,R.id.cone6);
        });
        cone7.setOnClickListener(v->{
            arrayUpdate(1,2,R.id.cone7);
        });
        cone8.setOnClickListener(v->{
            arrayUpdate(1,3,R.id.cone8);
        });
        cone9.setOnClickListener(v->{
            arrayUpdate(1,5,R.id.cone9);
        });
        cone10.setOnClickListener(v->{
            arrayUpdate(1,6,R.id.cone10);
        });
        cone11.setOnClickListener(v->{
            arrayUpdate(1,8,R.id.cone11);
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
                    allianceText.setBackgroundColor(Color.TRANSPARENT);
                    round_input.setBackground(textBackground);
                    team_input.setBackground(textBackground);
                    name_input.setBackground(nameBackground);
                }
            }

        });


        /*
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
                submitError += " No Results,";
                AutoEngage.setBackgroundColor(Color.argb(255, 255, 255, 0));
                Pregame.setVisibility(View.INVISIBLE);
            }
            if (EndgameEngage.getSelectedItem().toString().equals("No Input")) {
                submitError += " No Climb Result,";
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
                    myOutWriter.println("Auto Top: " + autoLowerScore);
                    myOutWriter.println("Auto Middle: " + autoLowerScore);
                    myOutWriter.println("Auto Bottom: " + autoLowerScore);

                    myOutWriter.println("Missed Intakes: " + missedScore);

                    myOutWriter.println("Top Array: " + teleopUpperScore);
                    myOutWriter.println("Middle Array: " + teleopUpperScore);
                    myOutWriter.println("Bottom Array: " + teleopUpperScore);

                    myOutWriter.println("Endgame: " + EndgameEngage.getSelectedItem());
                    myOutWriter.println("Notes: " + notes.getText());

                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getApplicationContext(), "Data Submitted!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    //If anything goes wrong, it throws an error instead of crashing
                    Toast.makeText(getApplicationContext(), "Data Submission Failed! (Tell scouting)", Toast.LENGTH_SHORT).show();
                    Log.e("Exception", "File write failed: " + e);
                }
                //Reset Everything
                roundfill = Integer.parseInt(round_input.getText().toString());
                roundfill++;
                teleopLowerScore = 0;
                teleopUpperScore = 0;
                autoLowerScore = 0;
                autoUpperScore = 0;
                missedIntake = 0;
                auto_lower_text.setEnabled(true);
                auto_upper_text.setEnabled(true);
                auto_missed_minus.setEnabled(true);
                auto_missed_plus.setEnabled(true);
                auto_missed_text.setEnabled(true);
                auto_missed_minus.setAlpha((float) 1);
                auto_missed_plus.setAlpha((float) 1);
                auto_missed_text.setAlpha((float) 1);
                auto_upper_minus.setEnabled(true);
                auto_upper_plus.setEnabled(true);
                auto_lower_minus.setEnabled(true);
                auto_lower_plus.setEnabled(true);
                auto_lower_minus.setAlpha((float) 1);
                auto_lower_plus.setAlpha((float) 1);
                auto_lower_text.setAlpha((float) 1);
                auto_upper_minus.setAlpha((float) 1);
                auto_upper_plus.setAlpha((float) 1);
                auto_upper_text.setAlpha((float) 1);
                toptext.setAlpha((float) 1);
                auto_upper_minus.setEnabled(true);
                auto_upper_minus.setAlpha((float) 1);
                auto_upper_plus.setEnabled(true);
                auto_upper_plus.setAlpha((float) 1);
                attemptedAutoText.setAlpha((float) 1);
                bottomtext.setAlpha((float) 1);
                notes.setText("");
                scouterName = name_input.getText().toString();
                auto_no_autoColor.setBackgroundColor(Color.TRANSPARENT);
                name_input.setText("");
                round_input.setText(String.valueOf(roundfill));
                team_input.setText("");
                endgame_results.setSelection(0);
                climbResult.setSelection(0);
                auto_no_auto.setChecked(false);
                robotError.setChecked(false);
                teleop_upper_text.setText("0");
                teleop_lower_text.setText("0");
                auto_upper_text.setText("0");
                auto_lower_text.setText("0");
                missedShotsText.setText("0");
                defenseTimer.setText("00:00.00");
                climbTimer.setText("00:00.00");
                playedDefense.setChecked(false);
                defensetimer = 0;
                climbtimer = 0;
                startDefenseTimer.setEnabled(false);
                startDefenseTimer.setAlpha((float) 0.5);
                defended1.setEnabled(false);
                defended1.setAlpha((float) 0.5);
                defended2.setEnabled(false);
                defended2.setAlpha((float) 0.5);
                defended3.setEnabled(false);
                defended3.setAlpha((float) 0.5);
                DefenseNumber.setEnabled(false);
                DefenseNumber.setAlpha((float) 0.5);
                defenseLowerScore = 0;
                defenseUpperScore = 0;
                Defense.setVisibility(View.INVISIBLE);
                startDefenseTimer.setText("Start Timer");
                climbTimerStart.setText("Start Timer");
                autoMissedScore = 0;
                auto_missed_text.setText("0");
                climbtimerreset = 0;
                timerReset = 0;
                milisecondsclimbraw = 0;
                milisecondsclimb = 0;
                milisecondsdefendingraw = 0;
                milisecondsdefending = 0;
                secondsclimb = 0;
                secondsdefending = 0;
                minutesclimb = 0;
                minutesdefending = 0;

                defended1.setChecked(false);
                defended2.setChecked(false);
                defended3.setChecked(false);
                DefenseNumber.setText("");
                Endgame.setVisibility(View.INVISIBLE);
                Pregame.setVisibility(View.VISIBLE);
                startDefenseTimer.setEnabled(false);
                startDefenseTimer.setAlpha((float) 0.5);
                if (roundfill > 1) {
                    sameScouter.setVisibility(View.VISIBLE);
                }
                String[] tempIntArr1 = null;
                String[] splittempIntArr1 = null;
                if (getTeams() != "") {
                    tempIntArr1 = getTeams().split("\n");
                    roundfill = Integer.parseInt(round_input.getText().toString());
                    try {
                        splittempIntArr1 = tempIntArr1[roundfill - 1].split(",");

                        if (tabletnumber >= 4) {
                            defended1.setText(splittempIntArr1[0]);
                            defended2.setText(splittempIntArr1[1]);
                            defended3.setText(splittempIntArr1[2]);
                        } else if (tabletnumber <= 3) {
                            defended1.setText(splittempIntArr1[3]);
                            defended2.setText(splittempIntArr1[4]);
                            defended3.setText(splittempIntArr1[5]);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
                if (teamAutofill.isChecked() && getTeams() != "") {
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
                if (!teamAutofill.isChecked()) {
                    team_input.setText("");
                }
            }
        });
        */

















    }
    private void arrayUpdate(int row, int column, int id) {
        //Determine Cone Cube or Both
        ImageView image = (ImageView)findViewById(id);
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
                    autoTop -= 1;
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
                    autoMid -= 1;
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
                    autoLow -= 1;
                } else {
                    teleLow -= 1;
                }
            }


        }
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