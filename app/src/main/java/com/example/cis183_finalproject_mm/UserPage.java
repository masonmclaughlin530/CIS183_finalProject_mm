package com.example.cis183_finalproject_mm;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class UserPage extends AppCompatActivity
{
    TextView tv_j_username;
    TextView tv_j_logOut;
    TextView tv_j_cardSelect;
    TextView changeUserName;


    ListView lv_cards;

    Button btn_j_searchCards;
    Button btn_j_addCards;

    Intent intent_mainActivity;
    Intent intent_cardSearch;
    Intent intent_addCard;
    Intent intent_changeUsername;

    DatabaseHelper db;

    CardCustomAdapter adapter;

    ArrayList<Card> cards;

    //all variables below are for dialog
    Dialog popup;
    EditText popup_search;
    ListView popup_listView;
    ArrayAdapter<Card> popup_Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_page);

        tv_j_logOut = findViewById(R.id.tv_v_userPage_logout);
        tv_j_username = findViewById(R.id.tv_v_user_username);
        //tv_j_cardSelect = findViewById(R.id.tv_v_userPage_customSpinner);

        changeUserName = findViewById(R.id.changeUserName);

        lv_cards = findViewById(R.id.lv_v_user_listView);

        btn_j_addCards = findViewById(R.id.btn_v_user_addCard);
        btn_j_searchCards = findViewById(R.id.btn_v_user_searchPage);

        intent_mainActivity = new Intent(UserPage.this, MainActivity.class);
        intent_addCard = new Intent(UserPage.this, AddCardPage.class);
        intent_cardSearch = new Intent(UserPage.this, CardSearch.class);
        intent_changeUsername = new Intent(UserPage.this, UsernameChange.class);

        db = new DatabaseHelper(this);

        cards = db.getAllCardsInDb();




        addCardBtnClickListener();
        searchBtnClickListener();
        logOutTextClickListener();
        changeUsernameClickListener();

        fillListView();

        //Log.d("Test", SessionData.getLoggedInUser().getUsername());


    }

    private void changeUsernameClickListener()
    {
        changeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_changeUsername);
            }
        });
    }

    private void clickListenerTextView()
    {
        tv_j_cardSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                popup = new Dialog(UserPage.this);

                popup.setContentView(R.layout.spinner_data);

                popup.getWindow().setLayout(650,800);

                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                popup.show();


                popup_search = popup.findViewById(R.id.et_search);
                popup_listView = popup.findViewById(R.id.lv_cards);
                //popup_Adapter = new CardCustomAdapter(this, cards);
            }
        });
    }

    private void addCardBtnClickListener()
    {
        btn_j_addCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_addCard);
            }
        });
    }

    private void searchBtnClickListener()
    {
        btn_j_searchCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_cardSearch);
            }
        });
    }

    private void logOutTextClickListener()
    {
        tv_j_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SessionData.setLoggedInUser(null);
                startActivity(intent_mainActivity);
            }
        });
    }

    private void fillListView()
    {
        ArrayList<Card> listOfCards = db.getAllCardsInDbFromAUser(SessionData.getLoggedInUser().getUserId());

        adapter = new CardCustomAdapter(this,listOfCards);

        lv_cards.setAdapter(adapter);

        lvOnClickListener(listOfCards);


    }

    private void lvOnClickListener(ArrayList<Card> lc)
    {

        lv_cards.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Card deleteCard = lc.get(position);

                db.deleteCardFromDb(deleteCard.getCardId());

                refreshLv();

                return true;
            }
        });


        lv_cards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

            }
        });


    }

    private void refreshLv()
    {
        ArrayList<Card> updatedList = db.getAllCardsInDbFromAUser(SessionData.loggedInUser.getUserId());

        CardCustomAdapter newAdapter = new CardCustomAdapter(this, updatedList);

        lv_cards.setAdapter(newAdapter);
    }
}