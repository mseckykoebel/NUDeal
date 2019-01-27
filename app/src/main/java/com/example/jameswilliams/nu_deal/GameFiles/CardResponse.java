package com.example.jameswilliams.nu_deal.GameFiles;

public class CardResponse {
    public boolean success;
    public String message;

    // Implemented in the gameState
    public CardResponse(boolean s, String m)
    {
        success = s;
        message = m;
    }
}
