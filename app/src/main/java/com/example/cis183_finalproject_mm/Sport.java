package com.example.cis183_finalproject_mm;

public class Sport
{
    private int sportId;
    private String sport;

    public Sport()
    {

    }

    public Sport(int id, String s)
    {
        sportId = id;
        sport = s;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
