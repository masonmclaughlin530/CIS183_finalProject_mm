package com.example.cis183_finalproject_mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CardSearch extends AppCompatActivity
{
    Spinner sp_user;
    Spinner sp_sport;
    Spinner sp_brand;
    Spinner sp_type;
    Spinner sp_search;

    EditText et_fname;
    EditText et_lname;

    ListView lv_cards;

    Button btn_back;
    Button btn_search;

    CardCustomAdapter adapter;

    Intent intent_mainActivity;

    DatabaseHelper db;

    ArrayAdapter<String> userAdapter;
    ArrayAdapter<String> brandAdapter;
    ArrayAdapter<String> typeAdapter;
    ArrayAdapter<String> sportAdapter;
    ArrayAdapter<String> sortAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_card_search);

        sp_brand = findViewById(R.id.sp_v_search_Brands);
        sp_sport = findViewById(R.id.sp_v_search_sports);
        sp_type = findViewById(R.id.sp_v_search_Types);
        sp_user = findViewById(R.id.sp_v_search_users);
        //sp_search = findViewById(R.id.sp_SearchSelect);

        et_fname = findViewById(R.id.et_v_search_fname);
        et_lname = findViewById(R.id.et_v_search_lname);

        lv_cards = findViewById(R.id.lv_v_search_sortedList);

        btn_search = findViewById(R.id.btn_v_search_search);
        btn_back = findViewById(R.id.btn_v_search_back);

        intent_mainActivity = new Intent(CardSearch.this, UserPage.class);

        db = new DatabaseHelper(this);

        ArrayList<String> usernames = db.getAllUsernames();
        ArrayList<String> brands = db.getAllBrands();
        ArrayList<String> sports = db.getAllSports();
        ArrayList<String> types = db.getAllTypes();

        usernames.add(0, "No User Selected");
        brands.add(0, "No Brands Selected");
        sports.add(0, "No Sports Selected");
        types.add(0, "No Types Selected");

        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, usernames);
        brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, brands);
        sportAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sports);
        typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);

//        String[] sort = {"Name", "Brand", "Sport", "Type", "User"};
//
//        sortAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sort);
//        sp_search.setAdapter(sortAdapter);



        sp_user.setAdapter(userAdapter);
        sp_brand.setAdapter(brandAdapter);
        sp_sport.setAdapter(sportAdapter);
        sp_type.setAdapter(typeAdapter);

        backBtnClickListener();
        fillListView();
        searchBtnClickListener();

    }

    private void backBtnClickListener()
    {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_mainActivity);
            }
        });
    }

    private void fillListView()
    {
        ArrayList<Card> listOfCards = db.getAllCardsInDb();

        adapter = new CardCustomAdapter(this, listOfCards);
        lv_cards.setAdapter(adapter);
    }

    private void searchBtnClickListener()
    {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                search();
//                String searchType = sp_search.getSelectedItem().toString();
//                if(searchType.equals("Name"))
//                {
//
//                }
//                else if(searchType.equals("Brand"))
//                {
//
//                }
//                else if(searchType.equals("Sport"))
//                {
//
//                }
//                else if(searchType.equals("Type"))
//                {
//
//                }
//                else if(searchType.equals("User"))
//                {
//
//                }
            }
        });
    }

    private void search()
    {

        //could not get this working switching to another way
        String user = sp_user.getSelectedItem().toString();
        String fname = et_fname.getText().toString();
        String lname = et_lname.getText().toString();
        String brand = sp_brand.getSelectedItem().toString();
        String sport = sp_sport.getSelectedItem().toString();
        String type = sp_type.getSelectedItem().toString();


        //User searchuser = db.getUser(user);

        ArrayList<Card> filteredCards = db.filterCards(user, fname, lname, brand, sport, type);

        CardCustomAdapter cc = new CardCustomAdapter(this, filteredCards);

        lv_cards.setAdapter(cc);

    }

}