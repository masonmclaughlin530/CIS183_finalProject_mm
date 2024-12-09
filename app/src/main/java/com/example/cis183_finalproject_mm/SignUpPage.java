package com.example.cis183_finalproject_mm;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SignUpPage extends AppCompatActivity
{
    EditText et_j_uname;
    EditText et_j_password;

    Button btn_j_back;
    Button btn_j_addUser;

    TextView tv_j_errorMsg;

    DatabaseHelper db;

    Intent intent_j_main;

    Boolean checkUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);


        et_j_uname = findViewById(R.id.et_v_signUp_username);
        et_j_password = findViewById(R.id.et_v_signUp_password);

        btn_j_back = findViewById(R.id.btn_v_signUp_back);
        btn_j_addUser = findViewById(R.id.btn_v_signUp_addUser);

        tv_j_errorMsg = findViewById(R.id.tv_v_signUp_errMsg);
        tv_j_errorMsg.setVisibility(View.INVISIBLE);

        intent_j_main = new Intent(SignUpPage.this, MainActivity.class);

        db = new DatabaseHelper(this);

        backBtnClickListener();
        addUserBtnClickListener();
        onTextlistener();

    }

    private void backBtnClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_main);
            }
        });
    }

    private void addUserBtnClickListener()
    {
        btn_j_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!et_j_uname.getText().toString().isEmpty() && !et_j_password.getText().toString().isEmpty())
                {
                    if(usernameCheck(et_j_uname.getText().toString()))
                    {
                        if(et_j_password.getText().toString().length() < 15)
                        {
                            tv_j_errorMsg.setVisibility(View.INVISIBLE);
                            User user = new User();

                            user.setUsername(et_j_uname.getText().toString());
                            user.setPassword(et_j_password.getText().toString());

                            db.addUser(user);

                            Log.d("Testing", "onClick: works");
                            startActivity(intent_j_main);
                        }
                        else
                        {
                            tv_j_errorMsg.setVisibility(View.VISIBLE);
                            tv_j_errorMsg.setText("ERROR: Password Is Too Long");
                        }
                    }
                    else
                    {
                        tv_j_errorMsg.setVisibility(View.VISIBLE);
                        tv_j_errorMsg.setText("ERROR: Username Already Exists");
                    }
                }
                else
                {
                    tv_j_errorMsg.setVisibility(View.VISIBLE);
                    tv_j_errorMsg.setText("ERROR: Please Fill Out All Fields");
                }
            }
        });
    }

    private void onTextlistener()
    {
        et_j_uname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                checkUsername = usernameCheck(et_j_uname.getText().toString());

                if(checkUsername)
                {
                    tv_j_errorMsg.setVisibility(View.INVISIBLE);
                    btn_j_addUser.setEnabled(true);
                }
                else
                {
                    tv_j_errorMsg.setVisibility(View.VISIBLE);
                    tv_j_errorMsg.setText("Error: Username Already In Use");
                    btn_j_addUser.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private Boolean usernameCheck(String username)
    {
        ArrayList<User> listOfUsernames = db.getAllUsersInDB();

        for (int i = 0; i < listOfUsernames.size(); i++)
        {
            if(listOfUsernames.get(i).getUsername().equals(username))
            {
                return false;
            }
        }

        return true;
    }
}