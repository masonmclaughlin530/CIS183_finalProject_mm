package com.example.cis183_finalproject_mm;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "Card.db";
    private static final String users_table_name = "Users";
    private static final String cards_table_name = "Cards";
    private static final String brands_table_name = "Brands";
    private static final String sports_table_name = "Sports";
    private static final String types_table_name = "types";

    public DatabaseHelper (Context c) {super(c,database_name,null,0);};

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(" CREATE TABLE " + users_table_name + " (username varChar(50), userId primary key autoincrement not null, password varchar(50))");
        db.execSQL(" CREATE TABLE " + cards_table_name + " (year int, fname varchar(50), lname varchar(50), sport varchar(20), brand varchar(20), type varchar(20) ,cardNum int, cardId primary key autoincrement not null, foreign key (userId) references " + users_table_name + "(userId))");
        db.execSQL(" CREATE TABLE " + brands_table_name + " (brandId primary key autoincrement not null, foreign key (brand) references " + cards_table_name + "(brand))");
        db.execSQL(" CREATE TABLE " + sports_table_name + " (sportsId primary key autoincrement not null, foreign key (sport) references " + cards_table_name + "(sport))");
        db.execSQL(" CREATE TABLE " + types_table_name + " (typeId primary key autoincrement not null, foreign key (type) references " + cards_table_name + "(type))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL(" DROP TABLE IF EXISTS " + users_table_name + ";");
        db.execSQL(" DROP TABLE IF EXISTS " + cards_table_name + ";");
        db.execSQL(" DROP TABLE IF EXISTS " + brands_table_name + ";");
        db.execSQL(" DROP TABLE IF EXISTS " + sports_table_name + ";");
        db.execSQL(" DROP TABLE IF EXISTS " + types_table_name + ";");

        onCreate(db);
    }

    public void initAllTables()
    {
        //create dummy data
        initUsers();
        initCards();
        initBrands();
        initSports();
        initTypes();
    }

    private void initUsers()
    {

        if(countDB(users_table_name) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + users_table_name + " (username, password) VALUES ('masonm', 'password')");
            db.execSQL(" INSERT INTO " + users_table_name + " (username, password) VALUES ('bobsmith', '1234')");

            db.close();
        }
    }

    private void initCards()
    {
        if(countDB(cards_table_name) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lanme, sport, brand, type ,cardNum, userId) VALUES ('2018', 'Shohei', 'Ohtani', 'Baseball', 'Topps', 'Rookie', '49', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lanme, sport, brand, type ,cardNum, userId) VALUES ('1959', 'Mickey', 'Mantle', 'Baseball', 'Topps', 'All Star', '564', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lanme, sport, brand, type ,cardNum, userId) VALUES ('1993', 'Derek', 'Jeter', 'Baseball', 'Topps', 'Rookie', '98', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lanme, sport, brand, type ,cardNum, userId) VALUES ('2023', 'Joey', 'Porter Jr.', 'Football', 'Panini', 'Rookie', '386', 2)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lanme, sport, brand, type ,cardNum, userId) VALUES ('1958', 'Willie', 'Mays', 'Baseball', 'Topps', 'Common', '5', 2)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lanme, sport, brand, type ,cardNum, userId) VALUES ('1990', 'Ken', 'Griffey', 'Baseball', 'Topps', 'Rookie', '336', 2)");

            db.close();
        }
    }


    private void initBrands()
    {
        if(countDB(brands_table_name) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + brands_table_name + " (brand) VALUES ('Topps')");
            db.execSQL(" INSERT INTO " + brands_table_name + "(brand) VALUES ('Panini')");


            db.close();
        }
    }

    private void initSports()
    {
        if (countDB(sports_table_name) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + sports_table_name + " (sport) VALUES ('Baseball')");
            db.execSQL(" INSERT INTO " + sports_table_name + " (sport) VALUES ('Football')");

            db.close();
        }
    }

    private void initTypes()
    {
        if (countDB(types_table_name) == 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + types_table_name + " (type) VALUES ('Common')");
            db.execSQL(" INSERT INTO " + types_table_name + " (type) VALUES ('Rookie')");
            db.execSQL(" INSERT INTO " + types_table_name + " (type) VALUES ('All Star')");
            db.execSQL(" INSERT INTO " + types_table_name + " (type) VALUES ('Autograph')");

            db.close();
        }
    }




    public int countDB(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        db.close();

        return numRows;
    }
}
