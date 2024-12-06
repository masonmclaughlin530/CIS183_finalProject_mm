package com.example.cis183_finalproject_mm;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class MainActivity extends AppCompatActivity
{
    //et
    EditText username;
    EditText password;

    //tv
    TextView errorMsg;

    //btn
    Button signUp;
    Button login;

    //Intent
    Intent intent_j_signUpPage;
    Intent intent_j_welcome;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.et_v_main_username);
        password = findViewById(R.id.et_v_main_password);

        errorMsg = findViewById(R.id.tv_v_main_errorMsg);
        errorMsg.setVisibility(View.INVISIBLE);

        signUp   = findViewById(R.id.btn_v_main_signup);
        login    = findViewById(R.id.btn_v_main_login);

        intent_j_signUpPage = new Intent(MainActivity.this, SignUpPage.class);
        intent_j_welcome    = new Intent(MainActivity.this, UserPage.class);

        db = new DatabaseHelper(this);

        db.initAllTables();

        loginButtonListener();
        signUpButtonListener();

    }

    private void loginButtonListener()
    {
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
                {
                    if(validUsernameAndPassword(username.getText().toString(), password.getText().toString()))
                    {
                        errorMsg.setVisibility(View.INVISIBLE);
                        startActivity(intent_j_welcome);
                    }
                    else
                    {
                        errorMsg.setVisibility(View.VISIBLE);
                        errorMsg.setText("Error: Please enter valid username or password");
                    }
                }
                else
                {
                    errorMsg.setVisibility(View.VISIBLE);
                    errorMsg.setText("Error: Please fill out all fields");
                }
            }
        });
    }


    private boolean validUsernameAndPassword(String u, String p)
    {
        ArrayList<User> listOfUsers = db.getAllUsersInDB();

        for(int i = 0; i < listOfUsers.size(); i++)
        {
            if(listOfUsers.get(i).getUsername().equals(u) && listOfUsers.get(i).getPassword().equals(p))
            {
                return true;
            }
        }

        return false;
    }

    private void signUpButtonListener()
    {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_signUpPage);
            }
        });

    }



}