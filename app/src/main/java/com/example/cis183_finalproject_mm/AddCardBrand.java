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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddCardBrand extends AppCompatActivity
{
    EditText et_j_brand;

    Button btn_j_back;
    Button btn_j_addBrand;

    TextView tv_j_errorMsg;

    DatabaseHelper db;

    Intent intent_j_addCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_card_brand);

        et_j_brand = findViewById(R.id.et_v_addBrand_cardType);

        btn_j_back = findViewById(R.id.btn_v_addBrand_back);
        btn_j_addBrand = findViewById(R.id.btn_v_addBrand_addBrand);

        tv_j_errorMsg = findViewById(R.id.tv_v_addBrand_errorMsg);

        tv_j_errorMsg.setVisibility(View.INVISIBLE);

        db = new DatabaseHelper(this);

        intent_j_addCard = new Intent(AddCardBrand.this, AddCardPage.class);


        backBtnClickListener();
        addBrandClickListener();
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

    private void addBrandClickListener()
    {
        btn_j_addBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!et_j_brand.getText().toString().isEmpty())
                {
                    String brand = et_j_brand.getText().toString();
                    brand = brand.substring(0,1).toUpperCase() + brand.substring(1).toLowerCase();
                    if(brandCheck(brand)) {


                        tv_j_errorMsg.setVisibility(View.INVISIBLE);
                        Brand newBrand = new Brand();

                        newBrand.setBrand(et_j_brand.getText().toString());

                        db.addBrand(newBrand);

                        startActivity(intent_j_addCard);
                    }
                    else {
                        tv_j_errorMsg.setVisibility(View.VISIBLE);
                        tv_j_errorMsg.setText("This Brand ALready Exists");
                    }
                }
                else
                {
                    tv_j_errorMsg.setVisibility(View.VISIBLE);
                    tv_j_errorMsg.setText("ERROR: PLEASE FILL OUT ALL FIELDS");
                }
            }
        });
    }

    private boolean brandCheck(String brand)
    {
        ArrayList<String> listOfBrands = db.getAllBrands();

        for (int i = 0; i < listOfBrands.size();i++)
        {
            if(listOfBrands.get(i).equals(brand))
            {
                return false;
            }
        }
        return true;
    }
}