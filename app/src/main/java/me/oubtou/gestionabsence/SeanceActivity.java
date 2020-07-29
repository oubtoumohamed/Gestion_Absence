package me.oubtou.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SeanceActivity extends AppCompatActivity {
    private ArrayList<Seance> List_seances = new ArrayList<>();
    public static Classe classe = new Classe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance);

        Intent intent = getIntent();
        String class_json = intent.getStringExtra("classe");
        JSONObject jo = null;
        try {
            jo = new JSONObject( class_json );
            classe = new Classe(jo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ListView listView = findViewById(R.id.lst_seances);
        new AccesHTTP() {
            @Override
            protected void onResult(JSONArray data) {

                for ( int i=0; i<data.length(); i++ ) {
                    JSONObject e = null;
                    try {
                        List_seances.add( new Seance( data.getJSONObject(i) ) );
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

                SeanceAdapter adapter = new SeanceAdapter(getApplicationContext(), List_seances);
                //adapter.getView().
                listView.setAdapter(adapter);
            }
        }.execute("https://apps.oubtou.me/android/absence/get_seances.php?classe="+classe.getId());
    }
}