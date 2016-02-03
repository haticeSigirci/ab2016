package com.haticesigirci.akademikbilisim1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataParse extends AppCompatActivity {

    private ListView liste;
    private ArrayList<DataModel> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_parse);

        liste = (ListView) findViewById(R.id.liste);

        insertDummyData();

        getDummyData(10);
        deleteDummyData();
        updateDummyData();
        getPrintData();

        Log.d("jsondata", getData());

        parseData();
        getList();


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

    private void getDummyData(int id) {

        DBHelper db = new DBHelper(DataParse.this);
        DataModel data = db.getData(id);
        db.getAllData();

        Toast.makeText(DataParse.this, "Data taken from database:\n" +
                "ID:" + String.valueOf(data.id) + "\n" +
                        "Ad: " + String.valueOf(data.ad) + "\n" +
                        "Soyad: " + String.valueOf(data.soyad) + "\n" +
                        "Sehir: " + String.valueOf(data.sehir), Toast.LENGTH_LONG
        ).show();
    }

    private void insertDummyData() {

        DBHelper dbHelper = new DBHelper(DataParse.this);
        boolean result = dbHelper.insertData(10, "Ahmet", "Mehmet", "Rize");

        if(result) {
            Toast.makeText(DataParse.this, "Item was added succesfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(DataParse.this, "Item was not add", Toast.LENGTH_LONG).show();

        }

    }

    private void getPrintData() {

        DBHelper db = new DBHelper(DataParse.this);
        ArrayList<DataModel> dataModels = db.getAllData();

        for (DataModel item : dataModels) {
            Log.d("dbResult", String.valueOf(item.id) + ", " + item.ad + " " + item.soyad + item.sehir );
        }
    }


    private void getList() {
        CustomAdapter adapter = new CustomAdapter(
                DataParse.this, R.layout.item_row, models);

        liste.setAdapter(adapter);
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



    private void updateDummyData()
    {
        DBHelper db=new DBHelper(DataParse.this);
        boolean result=db.updateData(12,"Hayriye","Deneme","Van");
        if(result)
        {
            Toast.makeText(DataParse.this,"Kayit güncellendi!",Toast.LENGTH_SHORT).show();


        }
    }
    private void deleteDummyData()
    {
        DBHelper db=new DBHelper(DataParse.this);
        if(db.deleteData(11))
        {
            Toast.makeText(DataParse.this,"Kayit silindi!",Toast.LENGTH_SHORT).show();
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
