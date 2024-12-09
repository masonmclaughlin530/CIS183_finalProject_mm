package com.example.cis183_finalproject_mm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CardCustomAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Card> listOfCards;

    public CardCustomAdapter(Context c, ArrayList<Card> lc)
    {
        context = c;
        listOfCards = lc;
    }


    @Override
    public int getCount()
    {
        return listOfCards.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfCards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater)  context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.cardcustomadapter,null);
        }

        TextView fname = view.findViewById(R.id.tv_v_cc_fname);
        TextView lname = view.findViewById(R.id.tv_v_cc_lname);
        TextView sport = view.findViewById(R.id.tv_v_cc_sport);
        TextView year = view.findViewById(R.id.tv_v_cc_year);
        TextView cardNum = view.findViewById(R.id.tv_v_cc_cardNum);
        TextView brand = view.findViewById(R.id.tv_v_cc_brand);
        TextView type = view.findViewById(R.id.tv_v_cc_type);

        Card card = listOfCards.get(i);

        fname.setText(card.getFname());
        lname.setText(card.getLname());
        sport.setText(card.getSport());
        year.setText(String.valueOf(card.getYear()));
        cardNum.setText(String.valueOf(card.getCardNum()));
        brand.setText(card.getBrand());
        type.setText(card.getType());


        return view;
    }
}
