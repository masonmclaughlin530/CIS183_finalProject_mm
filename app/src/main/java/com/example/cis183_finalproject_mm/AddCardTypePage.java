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

public class AddCardTypePage extends AppCompatActivity
{
    EditText et_j_addType;

    Button btn_j_back;
    Button btn_j_addType;

    TextView tv_j_errorMsg;

    DatabaseHelper db;

    Intent intent_j_addCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_card_type_page);

        et_j_addType = findViewById(R.id.et_v_cardType_enterType);

        btn_j_addType = findViewById(R.id.btn_v_cardType_addType);
        btn_j_back = findViewById(R.id.btn_v_CardType_back);

        tv_j_errorMsg = findViewById(R.id.tv_v_cardType_errorMsg);

        tv_j_errorMsg.setVisibility(View.INVISIBLE);

        db = new DatabaseHelper(this);

        intent_j_addCard = new Intent(AddCardTypePage.this, AddCardPage.class);

        backBtnClickListener();
        addTypeClickListener();

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

    private void addTypeClickListener()
    {
        btn_j_addType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!et_j_addType.getText().toString().isEmpty())
                {
                    String type = et_j_addType.getText().toString();
                    type = type.toLowerCase();
                    String upper = type.substring(0,1).toUpperCase() + type.substring(1);
                    if (typeCheck(upper))
                    {
                        tv_j_errorMsg.setVisibility(View.INVISIBLE);
                        Type newType = new Type();

                        newType.setType(upper);

                        db.addType(newType);

                        startActivity(intent_j_addCard);
                    }
                    else
                    {
                        tv_j_errorMsg.setVisibility(View.VISIBLE);
                        tv_j_errorMsg.setText("THIS TYPE ALREADY EXISTS");
                    }



                }
                else
                {
                    tv_j_errorMsg.setText("ERROR: PLEASE FILL OUT ALL FIELDS");
                    tv_j_errorMsg.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private Boolean typeCheck(String type)
    {

        ArrayList<String> listOfTypes = db.getAllTypes();

        for(int i = 0; i < listOfTypes.size(); i++)
        {
            if(listOfTypes.get(i).equals(type))
            {
                return false;
            }
        }

        return true;

    }
}