package com.haticesigirci.akademikbilisim1;

/**
 * Created by haticesigirci on 02/02/16.
 */

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.ListView;

        import java.util.ArrayList;

/**
 * Created by Taha on 02.02.2016.
 */
public class DatabaseList extends AppCompatActivity {
    private ListView liste;
    private ArrayList<DataModel> insanlar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_parse);

        insanlar = getAllData();

        liste = (ListView) findViewById(R.id.liste);

        CustomAdapter cAdapter = new CustomAdapter(
                DatabaseList.this,
                R.layout.item_row,
                insanlar
        );
        liste.setAdapter(cAdapter);

    }

    private ArrayList<DataModel> getAllData(){
        DBHelper db = new DBHelper(DatabaseList.this);
        ArrayList<DataModel> data = db.getAllData();
        //Log.d("dbResult",data.toString());
        for (DataModel item:data){
            Log.d(
                    "dbResult",
                    String.valueOf(item.id) + "," +
                            item.ad + "," +
                            item.soyad + "," +
                            item.sehir
            );
        }

        return data;
    }
}
