package com.example.cis183_finalproject_mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddSportPage extends AppCompatActivity
{
    EditText et_j_sport;

    Button btn_j_back;
    Button btn_j_addSport;

    TextView tv_j_errorMsg;

    DatabaseHelper db;

    Intent intent_j_addCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_sport_page);

        et_j_sport = findViewById(R.id.et_j_addSport_addSport);

        btn_j_addSport = findViewById(R.id.btn_v_addSport_addSport);
        btn_j_back = findViewById(R.id.btn_v_addSport_back);

        tv_j_errorMsg = findViewById(R.id.tv_v_addSport_errorMsg);

        tv_j_errorMsg.setVisibility(View.INVISIBLE);

        db = new DatabaseHelper(this);

        intent_j_addCard = new Intent(AddSportPage.this, AddCardPage.class);

        backBtnClickListener();
        addSportBtnClickListener();

    }


    private void backBtnClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_addCard);
            }
        });
    }

    private void addSportBtnClickListener()
    {
        btn_j_addSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!et_j_sport.getText().toString().isEmpty())
                {
                    String sport = et_j_sport.getText().toString();
                    sport = sport.toLowerCase();
                    String upper = sport.substring(0,1).toUpperCase() + sport.substring(1);
                    if(sportCheck(upper)) {
                        tv_j_errorMsg.setVisibility(View.INVISIBLE);
                        Sport newSport = new Sport();

                        newSport.setSport(et_j_sport.getText().toString());

                        db.addSport(newSport);

                        startActivity(intent_j_addCard);
                    }
                    else
                    {
                        tv_j_errorMsg.setVisibility(view.VISIBLE);
                        tv_j_errorMsg.setText("THIS SPORT ALREADY EXISTS");
                    }
                }
                else
                {
                    tv_j_errorMsg.setVisibility(view.VISIBLE);
                    tv_j_errorMsg.setText("ERROR: PLEASE FILL OUT ALL FIELDS");
                }
            }
        });
    }

    private boolean sportCheck(String sport)
    {
        ArrayList<String> listOfSports = db.getAllSports();

        for(int i = 0; i < listOfSports.size();i++)
        {
            if(listOfSports.get(i).equals(sport))
            {
                return false;
            }
        }


        return true;
    }
}