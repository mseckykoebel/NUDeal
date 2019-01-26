package com.example.jameswilliams.nu_deal.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jameswilliams.nu_deal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Lines to be written to the terminal
    private static ArrayList<String> lines;
    private static final int MAX_LINES = 25;
    private static TextView tv;
    private static String response;

    // adding some logic to the account information button
    private Button btnMyAccount;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.term_window);
        lines = new ArrayList<String>();
        response = "";

        //Get Fire base auth instance
        auth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();

        btnMyAccount = (Button) findViewById(R.id.myAccount);

        // Listener for the "log me in" button
        btnMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, UserAccount.class));

                // close the activity
                finish();
            }
        });

    }


    public void sendCommand(View view)
    {
        // R means resources, and we reference the command window
        // Convert to an edit text with (EditText), as the type, EditText
        EditText editText = (EditText) findViewById(R.id.command_window);

        // get the text within editText, and converts it to a string
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

    // Function that prints the terminal contents onto the screen
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
        String[] ls = l.split("\n");

        lines.addAll(Arrays.asList(ls));
        while(lines.size() > MAX_LINES)
        {
            lines.remove(0);
        }
        redrawTerminal();
    }
}
