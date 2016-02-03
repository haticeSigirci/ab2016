package com.haticesigirci.akademikbilisim1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private TextView username;
    private String mUsername;
    private ArrayList<String> cityList = new ArrayList<>();
    private ListView cities;

    private ArrayAdapter<String> adapter;
    private Button logoutButton;

    private ABSharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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

        Log.d("onCreate", "Dashboard is started");

        username = (TextView) findViewById(R.id.username);
        mUsername = getIntent().getStringExtra("username");
        username.setText("Hi " + mUsername);
        logoutButton = (Button) findViewById(R.id.logout_button);

        cities = (ListView) findViewById(R.id.cities);
        fillInCities();

        sp = new ABSharedPreferences(Dashboard.this);

        adapter = new ArrayAdapter<String>(Dashboard.this, android.R.layout.simple_list_item_1, cityList);
        cities.setAdapter(adapter);

        cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Dashboard.this, "You chose " + cityList.get(position), Toast.LENGTH_SHORT).show();


            }
        });

        cities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // Toast.makeText(Dashboard.this, "You chose " + cityList.get(position), Toast.LENGTH_SHORT).show();
                showAlertDialog(position);

                return true;
            }

        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void logout() {

        sp.editor.remove("isLoggedIn");
        sp.editor.commit();

        Intent mainIntent = new Intent(Dashboard.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }

    private void showAlertDialog(final int pos) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(Dashboard.this);

        dialog.setTitle("Sure?");

        dialog.setMessage(cityList.get(pos) + "Do you want to delete this item?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteCity(pos);
                Toast.makeText(Dashboard.this, "Item was deleted", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //TODO
                dialog.dismiss();

            }
        });

        dialog.create().show();
    }

    private void deleteCity(int position) {

        cityList.remove(position);
        adapter.notifyDataSetChanged();
    }


    private void fillInCities() {
        cityList.add("Istanbul");
        cityList.add("Ankara");
        cityList.add("Izmir");
        cityList.add("Adana");
        cityList.add("Aydin");

    }

    @Override
    protected void onPause() {
        Log.d("onPause", "Dashboard is paused");
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("onResume", "Dashboard is resumed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("onDestroy", "Dashboard is closed");

    }


}
