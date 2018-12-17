package com.example.jameswilliams.nu_deal;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Lines to be written to the terminal
    private static ArrayList<String> lines;
    private static final int MAX_LINES = 25;
    private static TextView tv;
    private static String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.term_window);
        lines = new ArrayList<String>();
        response = "";
    }



    public void sendCommand(View view)
    {
        EditText editText = (EditText) findViewById(R.id.command_window);
        response = editText.getText().toString();
    }

    public static String getResponse()
    {
        //Wait for the response to come in
        while(response == "");
        //Grab the response and reset the string
        String ret = response;
        response = "";
        return ret;
    }

    public static void redrawTerminal()
    {
        //make all of the lines into one massive string
        String t_m = "";
        for(int i = 0; i < lines.size(); i++)
        {
            t_m += lines.get(i) + "\n";
        }
        //Write them to the screen
        tv.setText(t_m);
    }

    public static void addLine(String l)
    {
        //String[] ls = l.split("\n");

        //lines.addAll(Arrays.asList(ls));
        lines.add(l);
        while(lines.size() > MAX_LINES)
        {
            lines.remove(0);
        }
        redrawTerminal();
    }
}
