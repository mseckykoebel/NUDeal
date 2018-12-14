package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.Scanner;

public class UserInterface
{
    public UserInterface()
    {
       //Maybe select between text based and GUI
    }

    public String getInput(String message)
    {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.nextLine();
    }

    public void displayMessage(String message)
    {
        System.out.println(message);
    }



}
