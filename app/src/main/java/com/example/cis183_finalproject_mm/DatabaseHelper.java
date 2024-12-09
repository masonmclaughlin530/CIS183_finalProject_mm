package com.example.cis183_finalproject_mm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "Card.db";
    private static final String users_table_name = "Users";
    private static final String cards_table_name = "Cards";
    private static final String brands_table_name = "Brands";
    private static final String sports_table_name = "Sports";
    private static final String types_table_name = "types";

    public DatabaseHelper (Context c) {super(c,database_name,null,6);};

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(" CREATE TABLE " + users_table_name + " (username varChar(50), userId integer primary key autoincrement not null, password varchar(50))");
        db.execSQL(" CREATE TABLE " + cards_table_name + " (cardId integer primary key autoincrement not null, year integer, fname varchar(50), lname varchar(50), sport varchar(50), brand varchar(50), type varchar(50), userId integer, cardNum integer, foreign key (userId) references " + users_table_name + "(userId))");
        db.execSQL(" CREATE TABLE " + brands_table_name + " (brandId integer primary key autoincrement not null, brand varchar(50) ,foreign key (brand) references " + cards_table_name + "(brand))");
        db.execSQL(" CREATE TABLE " + sports_table_name + " (sportsId integer primary key autoincrement not null, sport varchar(50) ,foreign key (sport) references " + cards_table_name + "(sport))");
        db.execSQL(" CREATE TABLE " + types_table_name + " (typeId integer primary key autoincrement not null, type varchar(50) ,foreign key (type) references " + cards_table_name + "(type))");
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

            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('2018', 'Shohei', 'Ohtani', 'Baseball', 'Topps', 'Rookie', '49', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1959', 'Mickey', 'Mantle', 'Baseball', 'Topps', 'All Star', '564', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1993', 'Derek', 'Jeter', 'Baseball', 'Topps', 'Rookie', '98', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('2023', 'Joey', 'Porter Jr.', 'Football', 'Panini', 'Rookie', '386', 2)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1958', 'Willie', 'Mays', 'Baseball', 'Topps', 'Common', '5', 2)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1990', 'Ken', 'Griffey', 'Baseball', 'Topps', 'Rookie', '336', 2)");

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


    public ArrayList<User> getAllUsersInDB()
    {
        ArrayList<User> allUsers = new ArrayList<>();

        String selectString = "SELECT username, password FROM " + users_table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);

        while(cursor.moveToNext())
        {
            String uname = cursor.getString(0);
            String password = cursor.getString(1);

            //User newUser = new User(uname, password);

            User newUser = new User();

            newUser.setUsername(uname);
            newUser.setPassword(password);
            allUsers.add(newUser);
        }

        //must close database
        db.close();

        return allUsers;
    }

    public ArrayList<Card> getAllCardsInDb()
    {
        ArrayList<Card> allCards = new ArrayList<>();

        //cardId integer, year integer, fname varchar(50), lname varchar(50), sport varchar(50), brand varchar(50), type varchar(50), userId integer, cardNum integer,
        String selectString = " SELECT * FROM " + cards_table_name + " ORDER BY lname";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);
        while(cursor.moveToNext())
        {
            int cardId = cursor.getInt(0);
            int year = cursor.getInt(1);
            String fname = cursor.getString(2);
            String lname = cursor.getString(3);
            String sport = cursor.getString(4);
            String brand = cursor.getString(5);
            String type = cursor.getString(6);
            int userId = cursor.getInt(7);
            int cardNum = cursor.getInt(8);

            Card card = new Card();

            card.setCardId(cardId);
            card.setYear(year);
            card.setFname(fname);
            card.setLname(lname);
            card.setSport(sport);
            card.setBrand(brand);
            card.setType(type);
            card.setUserId(userId);
            card.setCardNum(cardNum);

            allCards.add(card);

        }
        db.close();

        return allCards;
    }

    public ArrayList<Card> getAllCardsInDbFromAUser(int u)
    {
        ArrayList<Card> allCards = new ArrayList<>();

        //cardId integer, year integer, fname varchar(50), lname varchar(50), sport varchar(50), brand varchar(50), type varchar(50), userId integer, cardNum integer,
        String selectString = "SELECT * FROM " + cards_table_name + " WHERE userId = '" + u + "';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);
        while(cursor.moveToNext())
        {
            int cardId = cursor.getInt(0);
            int year = cursor.getInt(1);
            String fname = cursor.getString(2);
            String lname = cursor.getString(3);
            String sport = cursor.getString(4);
            String brand = cursor.getString(5);
            String type = cursor.getString(6);
            int userId = cursor.getInt(7);
            int cardNum = cursor.getInt(8);

            Card card = new Card();

            card.setCardId(cardId);
            card.setYear(year);
            card.setFname(fname);
            card.setLname(lname);
            card.setSport(sport);
            card.setBrand(brand);
            card.setType(type);
            card.setUserId(userId);
            card.setCardNum(cardNum);

            allCards.add(card);

        }
        db.close();

        return allCards;
    }


    public User getUser(String username)
    {
        User newUser = new User();

        String selectStatement = "SELECT * FROM " + users_table_name + " WHERE username = '" + username + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor != null)
        {
            while (cursor.moveToNext())
            {
                String uname = cursor.getString(0);
                int userId = cursor.getInt(1);
                String password = cursor.getString(2);

                newUser.setUsername(uname);
                newUser.setUserId(userId);
                newUser.setPassword(password);
            }
        }

        db.close();
        return newUser;
    }

    public void addUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(" INSERT INTO " + users_table_name + " (username,password) VALUES ('" + u.getUsername() + "', '" + u.getPassword() + "');");

        db.close();
    }

    public void addCard(Card c)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //(year, fname, lname, sport, brand, type ,cardNum, userId)
        db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type, cardNum, userId) VALUES ('" + c.getYear() + "', '" + c.getFname() + "', '" + c.getLname() + "', '" + c.getSport() + "', '" + c.getBrand() + "', '" + c.getType() + "', '" + c.getCardNum() + "', '" + c.getUserId() + "');");

        db.close();
    }

    public void addSport(Sport s)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(" INSERT INTO " + sports_table_name + " (sport) VALUES ('" + s.getSport() + "');");

        db.close();
    }

    public void addBrand(Brand b)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(" INSERT INTO " + brands_table_name + " (brand) VALUES ('" + b.getBrand() + "');");

        db.close();
    }

    public void addType(Type t)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(" INSERT INTO " + types_table_name + " (type) VALUES ('" + t.getType() + "');");
    }

    public ArrayList<String> getAllSports()
    {
        ArrayList<String> listOfSports = new ArrayList<>();

        String selectString = " SELECT sport FROM " + sports_table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);
        while (cursor.moveToNext())
        {
            String sport = cursor.getString(0);
            listOfSports.add(sport);
        }

        return listOfSports;
    }

    public ArrayList<String> getAllBrands()
    {
        ArrayList<String> listOfBrands = new ArrayList<>();

        String selectString = " SELECT brand FROM " + brands_table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);
        while (cursor.moveToNext())
        {
            String sport = cursor.getString(0);
            listOfBrands.add(sport);
        }

        return listOfBrands;
    }

    public ArrayList<String> getAllTypes()
    {
        ArrayList<String> listOfTypes = new ArrayList<>();

        String selectString = " SELECT type FROM " + types_table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);
        while (cursor.moveToNext())
        {
            String sport = cursor.getString(0);
            listOfTypes.add(sport);
        }

        return listOfTypes;
    }


    public void deleteCardFromDb(int cardId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String delete = " DELETE FROM " + cards_table_name + " WHERE cardId = '" + cardId + "';";

        db.execSQL(delete);

        db.close();
    }


    public ArrayList<String> getAllUsernames()
    {
        ArrayList<String> allUsernames = new ArrayList<>();

        String selectString = " SELECT username FROM " + users_table_name;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectString, null);

        while (cursor.moveToNext())
        {
            String uname = cursor.getString(0);
            allUsernames.add(uname);
        }

        return allUsernames;
    }


    public ArrayList<Card> filterCards(String username, String fname, String lname, String brand, String sport, String type)
    {
        ArrayList<Card> listOfCards = new ArrayList<>();

        String selectStatement = " SELECT * FROM " + cards_table_name + " WHERE 1=1";

        if(!username.equals("No User Selected"))
        {
            User newUser = getUser(username);

            int userId = newUser.getUserId();
            selectStatement += " AND userId = " + userId;
        }

        if(!fname.isEmpty())
        {
            selectStatement += " AND fname LIKE '%" + fname + "%'";
        }

        if(!lname.isEmpty())
        {
            selectStatement += " AND lname LIKE '%" + lname + "%'";
        }

        if(!brand.equals("No Brands Selected"))
        {
            selectStatement += " AND brand LIKE '&" + brand + "%'";
        }

        if(!sport.equals("No Sports Selected"))
        {
            selectStatement += " AND sport LIKE '&" + sport + "%'";
        }

        if(!type.equals("No Types Selected"))
        {
            selectStatement += " AND type LIKE '&" + type + "%'";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Card newCard = new Card();
                int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));
                String f = cursor.getString(cursor.getColumnIndexOrThrow("fname"));
                String l = cursor.getString(cursor.getColumnIndexOrThrow("lname"));
                String s = cursor.getString(cursor.getColumnIndexOrThrow("sport"));
                String b = cursor.getString(cursor.getColumnIndexOrThrow("brand"));
                String t = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                int uId = cursor.getInt(cursor.getColumnIndexOrThrow("userId"));
                int cardNum = cursor.getInt(cursor.getColumnIndexOrThrow("cardNum"));


                newCard.setYear(year);
                newCard.setFname(f);
                newCard.setLname(l);
                newCard.setSport(s);
                newCard.setBrand(b);
                newCard.setType(t);
                newCard.setUserId(uId);
                newCard.setCardNum(cardNum);

                listOfCards.add(newCard);

            }
            while (cursor.moveToNext());
        }



        return listOfCards;
    }

    public void unameUpdate(User update, String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues updateUser = new ContentValues();
        updateUser.put("username", update.getUsername());
        updateUser.put("password", update.getPassword());
        updateUser.put("userId", update.getUserId());

        String where = "username=?";
        String[] whereArg = new String[] {username};

        db.update(users_table_name, updateUser, where, whereArg);

    }



    public void deleteUser(String u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String userDelete = " DELETE FROM " + users_table_name + " WHERE username = '" + u + "';";

        db.execSQL(userDelete);

        db.close();
    }






}
