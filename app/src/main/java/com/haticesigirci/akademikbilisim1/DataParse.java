package com.haticesigirci.akademikbilisim1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataParse extends AppCompatActivity {

    private TextView result;
    private String data = " ";
    private ArrayList<DataModel> models=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_parse);


        result = (TextView) findViewById(R.id.result);


        Log.d("jsondata", getData());

        parseData();
        result.setText(models.get(2).ad);

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
    }

    private void parseData() {


        try {
            JSONObject object = new JSONObject(getData());
            JSONArray kayitlar = object.getJSONArray("kayitlar");


            for (int i = 0; i < kayitlar.length(); i++) {
                JSONObject kayitObject = kayitlar.getJSONObject(i);
                DataModel kayit = new DataModel(
                        kayitObject.getInt("id"),
                        kayitObject.getString("ad"),
                        kayitObject.getString("soyad"),
                        kayitObject.getString("sehir")


                );

              models.add(kayit);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private String getData() {
        String json = "";
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
