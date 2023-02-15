package com.frc1706.alliancescout2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Spinner alliance_sel;
    View line1,line2,line3;
    ConstraintLayout inputTeams;
    EditText team1,team2,team3,scouter,match,round;


    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        alliance_sel = findViewById(R.id.alliance_input);
        recyclerView = findViewById(R.id.recyclerView);
        line1= findViewById(R.id.line1);
        line2= findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        submit = findViewById(R.id.submit);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);



        populateRecyclerView();


        alliance_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                if (alliance_sel.getSelectedItem().toString().equals("BLUE")) {
                    line1.setBackgroundColor(Color.BLUE);
                    line2.setBackgroundColor(Color.BLUE);
                    line3.setBackgroundColor(Color.BLUE);
                    for(int i=0;i<3;i++) {
                        CardView relativeLayout = (CardView) recyclerView.getChildAt(i);
                        relativeLayout.setBackgroundColor(Color.BLUE);
                        TextView textView = (TextView) relativeLayout.findViewById(R.id.txtTitle);
                        textView.setTextColor(Color.WHITE);
                    }


                } else if (alliance_sel.getSelectedItem().toString().equals("RED")) {
                    line1.setBackgroundColor(Color.RED);
                    line2.setBackgroundColor(Color.RED);
                    line3.setBackgroundColor(Color.RED);
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
    private void populateRecyclerView() {

        stringArrayList.add("TEAM #1");
        stringArrayList.add("TEAM #2");
        stringArrayList.add("TEAM #3");

        mAdapter = new RecyclerViewAdapter(stringArrayList);

        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);
    }
}