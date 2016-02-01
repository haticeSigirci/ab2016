package com.haticesigirci.akademikbilisim1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button buttonLogin;
    private Button buttonDataParse;         //ViewById annotation

    String mUsername;
    String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = username.getText().toString();
                mPassword = password.getText().toString();

                if (mUsername.equals("hatice") && mPassword.equals("1234")) {
                    openDashboard();
                }
            }
        });

        buttonDataParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dataParseIntent=new Intent(
                        MainActivity.this,DataParse.class
                );

                startActivity(dataParseIntent);

            }
        });

    }

    private void openDashboard() {

        Intent dashboardIntent = new Intent(MainActivity.this, Dashboard.class);

      /*  Bundle bundle = new Bundle();
        bundle.putString("username", mUsername);
        bundle.putString("password", mPassword);*/

        dashboardIntent.putExtra("username", mUsername);

        startActivity(dashboardIntent);

    }

    private void init() {

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonDataParse=(Button) findViewById(R.id.button_data_parse);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {

            case R.id.action_settings:
                Toast.makeText(MainActivity.this,"Settings not found", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_exit:
                finish();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
