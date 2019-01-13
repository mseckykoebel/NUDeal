package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Utils {

    public static boolean containsString(ArrayList<String> list, String s){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == s){
                return true;
            }
        }
        return false;
    }
}
