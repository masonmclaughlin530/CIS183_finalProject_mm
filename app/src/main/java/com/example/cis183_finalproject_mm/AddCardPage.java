package com.example.cis183_finalproject_mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddCardPage extends AppCompatActivity
{
    EditText et_v_fname;
    EditText et_v_lname;
    EditText et_v_year;
    EditText et_v_cardNum;

    Spinner sp_sport;
    Spinner sp_brand;
    Spinner sp_type;

    TextView errorMsg;
    TextView tv_v_sport;
    TextView tv_v_brand;
    TextView tv_v_type;

    Button btn_v_back;
    Button btn_v_add;

    DatabaseHelper db;

    Intent intent_j_user;
    Intent intent_j_brand;
    Intent intent_j_sport;
    Intent intent_j_type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_card_page);

        et_v_fname = findViewById(R.id.et_v_addCard_firstName);
        et_v_lname = findViewById(R.id.et_v_addcard_lname);
        et_v_year = findViewById(R.id.et_v_addcard_year);
        et_v_cardNum = findViewById(R.id.et_v_addCard_cardNum);

        sp_sport = findViewById(R.id.sp_v_addCard_sports);
        sp_brand = findViewById(R.id.sp_v_addCard_brands);
        sp_type = findViewById(R.id.sp_v_addCard_cardType);

        errorMsg = findViewById(R.id.tv_v_addCard_errorMsg);
        tv_v_brand = findViewById(R.id.tv_v_addCard_addBrand);
        tv_v_sport = findViewById(R.id.tv_v_addCard_addSport);
        tv_v_type = findViewById(R.id.tv_v_addCard_addType);

        errorMsg.setVisibility(View.INVISIBLE);

        btn_v_add = findViewById(R.id.btn_v_addCard_addCard);
        btn_v_back = findViewById(R.id.btn_v_addCard_back);

        db = new DatabaseHelper(this);

        intent_j_user = new Intent(AddCardPage.this, UserPage.class);
        intent_j_sport = new Intent(AddCardPage.this, AddSportPage.class);
        intent_j_brand = new Intent(AddCardPage.this, AddCardBrand.class);
        intent_j_type = new Intent(AddCardPage.this, AddCardTypePage.class);

        //need to make db functions for these
        ArrayList<String> listOfBrands = db.getAllBrands();
        ArrayList<String> listOfTypes = db.getAllTypes();
        ArrayList<String> listOfSports = db.getAllSports();

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfBrands);
        ArrayAdapter<String> sportAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfSports);
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfTypes);

        sp_brand.setAdapter(brandAdapter);
        sp_sport.setAdapter(sportAdapter);
        sp_type.setAdapter(typesAdapter);




        backBtnClickListener();
        sportTextOnClickListener();
        brandOnTextClickListener();
        typeOnClickTextListener();
        addCardBtnClickListener();

    }

    private void addCardBtnClickListener()
    {
        btn_v_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String fname = et_v_fname.getText().toString();
                String lname = et_v_lname.getText().toString();
                int year = Integer.parseInt(et_v_year.getText().toString());
                int cardNum = Integer.parseInt(et_v_cardNum.getText().toString());
                String sport = sp_sport.getSelectedItem().toString();
                String brand = sp_brand.getSelectedItem().toString();
                String type = sp_type.getSelectedItem().toString();

                if(!fname.isEmpty() && !lname.isEmpty() && !et_v_year.getText().toString().isEmpty() && !et_v_cardNum.getText().toString().isEmpty() && !sport.isEmpty() && !brand.isEmpty() && !type.isEmpty())
                {
                    if(year >1860 || year < 2025)
                    {
                        Card card = new Card();
                        card.setCardNum(cardNum);
                        card.setType(type);
                        card.setBrand(brand);
                        card.setSport(sport);
                        card.setLname(lname);
                        card.setFname(fname);
                        card.setYear(year);
                        card.setUserId(SessionData.getLoggedInUser().getUserId());

                        db.addCard(card);

                        startActivity(intent_j_user);


                    }
                    else
                    {
                        errorMsg.setVisibility(View.VISIBLE);
                        errorMsg.setText("ERROR: PLEASE ENTER A VALID YEAR");
                    }
                }
                else
                {
                    errorMsg.setVisibility(View.VISIBLE);
                    errorMsg.setText("ERROR: PLEASE FILL OUT ALL FIELDS");
                }
            }
        });
    }

    private void backBtnClickListener()
    {
        btn_v_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_user);
            }
        });
    }

    private void sportTextOnClickListener()
    {
        tv_v_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_sport);
            }
        });
    }

    private void brandOnTextClickListener()
    {
        tv_v_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_brand);
            }
        });
    }

    private void typeOnClickTextListener()
    {
        tv_v_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_type);
            }
        });
    }


}