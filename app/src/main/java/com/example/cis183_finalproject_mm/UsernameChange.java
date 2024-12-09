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

public class UsernameChange extends AppCompatActivity {
    Button back;
    Button change;

    Intent userPage;
    Intent main;

    EditText newUname;
    EditText password;

    TextView errorMsg;
    TextView deleteAcc;

    DatabaseHelper db;

    Boolean checkUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_username_change);

        back = findViewById(R.id.btn_v_userChange_back);
        change = findViewById(R.id.btn_v_userChange_change);

        newUname = findViewById(R.id.et_v_userChange_name);
        password = findViewById(R.id.et_v_userChange_password);

        errorMsg = findViewById(R.id.tv_v_userChange_errorMsg);
        deleteAcc = findViewById(R.id.deleteUser);

        userPage = new Intent(UsernameChange.this, UserPage.class);
        main = new Intent(UsernameChange.this, MainActivity.class);

        db = new DatabaseHelper(this);

        getUser();
        backBtnClickListener();
        usernameKeyEventListener();
        changeUnameBtnClickListener();
        deleteOnTextClickListener();




    }

    private void deleteOnTextClickListener()
    {
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                db.deleteUser(SessionData.getLoggedInUser().getUsername());

                SessionData.setLoggedInUser(null);

                startActivity(main);
            }
        });
    }

    private void changeUnameBtnClickListener()
    {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!newUname.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
                {
                    if(password.getText().toString().equals(SessionData.getLoggedInUser().getPassword()))
                    {
                        User user = SessionData.getLoggedInUser();

                        String username = SessionData.getLoggedInUser().getUsername();

                        user.setUsername(newUname.getText().toString());


                        db.unameUpdate(user, username);

                        startActivity(userPage);

                    }
                }
                else
                {
                    errorMsg.setVisibility(View.VISIBLE);
                    errorMsg.setText("Error: Please Fill Out All Fields");
                }
            }
        });
    }

    private void backBtnClickListener()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(userPage);
            }
        });
    }

    private void getUser()
    {
        User user = SessionData.getLoggedInUser();

        change.setHint(user.getUsername());
    }

    private void usernameKeyEventListener()
    {
        newUname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                checkUsername = validName(newUname.getText().toString());

                if(checkUsername)
                {
                    errorMsg.setVisibility(View.INVISIBLE);
                    change.setEnabled(true);
                }
                else
                {
                    errorMsg.setVisibility(View.VISIBLE);
                    errorMsg.setText("Error: Username Already In Use");
                    change.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean validName(String uname)
    {
        ArrayList<User> listOfUsers = db.getAllUsersInDB();

        for(int i = 0; i < listOfUsers.size(); i++)
        {
            if(listOfUsers.get(i).getUsername().equals(uname) && !uname.equals(SessionData.getLoggedInUser().getUsername()))
            {
                return false;
            }
        }

        return true;
    }
}