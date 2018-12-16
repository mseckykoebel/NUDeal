package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class OKPair
{
    //Simple object key pairs for figuring out which card the player is selecting
    private Object ob;
    private int key;

    public OKPair(Object o, int k)
    {
        ob = o;
        key = k;
    }

    public Object getObject(){return ob;}
    public int getKey(){return key;}

    //Preamble comes first
    //Can only be used with lists of player or card objects
    public static String OKListToString(ArrayList<OKPair> list, String preamble)
    {
        String ret = preamble;
        int items = 0;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getObject().getClass() == Card.class)
            {
                ret += Integer.toString(items) + ". " + ((Card)(list.get(i).getObject())).getName() + "\n";
                items++;
            }else if(list.get(i).getObject().getClass() == Player.class)
            {
                ret += Integer.toString(items) + ". " + ((Player)(list.get(i).getObject())).getName() + "\n";
                items++;
            }
        }
        return ret;
    }
}
