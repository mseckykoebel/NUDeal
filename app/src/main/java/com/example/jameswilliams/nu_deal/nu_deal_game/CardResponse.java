package com.example.jameswilliams.nu_deal.nu_deal_game;

public class CardResponse {
    public boolean success;
    public String message;
    public CardResponse(boolean s, String m)
    {
        success = s;
        message = m;
    }
}
