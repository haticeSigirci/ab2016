package com.haticesigirci.akademikbilisim1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by haticesigirci on 02/02/16.
 */
public class RemoteJSON  extends AppCompatActivity{

    private String data;
    private TextView dataPlace;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_remote_json);

        dataPlace = (TextView) findViewById(R.id.data_place);
        progressDialog = new ProgressDialog(RemoteJSON.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait");

        //data = getDataFromRemote();

        new AsyncTask<Void,Void,Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                Log.d("remoteData","Starting to taken data");
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {

                Log.d("remoteData", "Loading data");

                data = getDataFromRemote();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Log.d("remoteData", "Finished to taken data process");
                dataPlace.setText(data);
                progressDialog.dismiss();
             //   Log.d("remoteDAta", data.toString());
            }
        }.execute();



       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }


    private String getDataFromRemote() {

        String json = "";
        URL url;
        HttpURLConnection connection = null;


        try {
            url =  new URL("https://api.seatgeek.com/2/events");
            connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream );

            int data = reader.read();

            while (data != -1) {

                char currentChar = (char) data;
                data = reader.read();
                json += currentChar;


            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return json;
    }
}
