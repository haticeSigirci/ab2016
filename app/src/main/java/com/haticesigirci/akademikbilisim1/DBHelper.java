package com.haticesigirci.akademikbilisim1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by haticesigirci on 01/02/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "AB2016";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(

                "CREATE TABLE people (" +
                        "id integer," +
                        "ad String," +
                        "soyad String," +
                        "sehir String" + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS people");

    }

    public boolean insertData(int id, String ad, String soyad, String sehir) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("id", id);
        row.put("ad", ad);
        row.put("soyad", soyad);
        row.put("sehir", sehir);
        db.insert("people", null, row);

        return true;
    }

    public DataModel getData(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM people WHERE id=" + String.valueOf(id), null);
        cursor.moveToFirst();
        return new DataModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));  //id ad soyad sehir

    }


    public ArrayList<DataModel> getAllData() {

        ArrayList<DataModel> dataModelArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM people ", null);


        try {
            if (cursor.moveToFirst()) {
                do {

                    DataModel dataModel = new DataModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                    // adding to todo list
                    dataModelArrayList.add(dataModel);
                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }

       /* for (int i = 0; i < cursor.getCount(); ++i) {

            DataModel dataModel = new DataModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.moveToNext();
            dataModelArrayList.add(dataModel);

        }*/

        Log.d("getAll", dataModelArrayList.toString());

        return dataModelArrayList;  //id ad soyad sehir

    }


    public boolean updateData(int id, String ad, String soyad, String sehir) {
        SQLiteDatabase db = this.getWritableDatabase();
       /*  db.execSQL("UPDATE people SET ad=" + ad + ", "+
                       "soyad=" + soyad+ ", "+ sehir + " " +
                       "WHERE id=" + String.valueOf(id)
                              );
      */


        ContentValues row = new ContentValues();
        row.put("ad", ad);
        row.put("soyad", soyad);
        row.put("sehir", sehir);
        db.update("people",
                row,
                "id=?",
                new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteData(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete("people", "id=?" , new String[] {Integer.toString(id)});

        db.execSQL("DELETE FROM people WHERE id=" + String.valueOf(id));
        return true;
    }


}
