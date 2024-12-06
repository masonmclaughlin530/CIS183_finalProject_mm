package com.example.cis183_finalproject_mm;

public class Card
{
    private int userId;
    private int cardId;
    private String fname;
    private String lname;
    private int year;
    private String type;
    private String sport;
    private String brand;
    private int cardNum;

    public Card()
    {

    }

    public Card(int uId, int cId, String f, String l, int y, String t, String s, String b, int cN)
    {
        userId = uId;
        cardId = cId;
        fname = f;
        lname = l;
        year = y;
        type = t;
        sport = s;
        brand = b;
        cardNum = cN;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
