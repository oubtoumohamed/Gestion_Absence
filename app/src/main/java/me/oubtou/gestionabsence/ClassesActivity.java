package me.oubtou.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {
    private GridView grid_classes;
    private ArrayList<Classe> List_classes = new ArrayList<Classe>();
    public static Boolean archive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        Intent intent = getIntent();
        archive = (boolean) intent.getBooleanExtra("archive", false);

        grid_classes = findViewById(R.id.grid_classes);

        new AccesHTTP() {
            @Override
            protected void onResult(JSONArray data) {

                for ( int i=0; i<data.length(); i++ ) {
                    JSONObject e = null;
                    try {
                        List_classes.add( new Classe( data.getJSONObject(i) ) );
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
                ClasseAdapter adapter = new ClasseAdapter( getApplicationContext(), List_classes);
                grid_classes.setAdapter(adapter);
            }
        }.execute("https://apps.oubtou.me/android/absence/get_classes.php");
    }

}