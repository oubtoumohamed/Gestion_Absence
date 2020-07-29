package me.oubtou.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AbsenceActivity extends AppCompatActivity {
    private ArrayList<Etudiant> List_etudiants = new ArrayList<Etudiant>();
    private Classe classe;
    private int seance_id = 0;
    private String date_ = "";
    private Button btn_setDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        Intent intent = getIntent();
        seance_id = intent.getIntExtra("seance_id", 0);

        btn_setDateTime = findViewById(R.id.btn_setDateTime);
        date_ = intent.getStringExtra("seance_date");
        if( date_ != null )
            btn_setDateTime.setText( date_ );

        String class_json = intent.getStringExtra("classe");
        JSONObject jo = null;
        try {
            jo = new JSONObject( class_json );
            classe = new Classe(jo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView classe_name = findViewById(R.id.classe_name);
        classe_name.setText(classe.getName());

        loadEtudiants(classe.getId());
    }

    public void loadEtudiants(int classe_id){
        final ListView listView = findViewById(R.id.lst_etudians);
        new AccesHTTP() {
            @Override
            protected void onResult(JSONArray data) {

                for ( int i=0; i<data.length(); i++ ) {
                    JSONObject e = null;
                    try {
                        List_etudiants.add( new Etudiant( data.getJSONObject(i) ) );
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

                AbsenceAdapter adapter = new AbsenceAdapter( getApplicationContext(), List_etudiants);
                listView.setAdapter(adapter);
            }
        }.execute("https://apps.oubtou.me/android/absence/get_seance.php?id="+seance_id+"&classe="+classe_id);

    }

    public void setDateTime(View v){
        final View dialogView = getLayoutInflater().inflate(R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(AbsenceActivity.this).create();

        dialogView.findViewById(R.id.valider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);
                timePicker.setIs24HourView(true);

                if( datePicker.getVisibility() == View.VISIBLE ){
                    datePicker.setVisibility(View.GONE);
                    timePicker.setVisibility(View.VISIBLE);
                }else{
                    alertDialog.dismiss();
                    date_ = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+
                            " "+ timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute();
                    btn_setDateTime.setText(date_);
                }
            }
        });

        alertDialog.setView(dialogView);
        alertDialog.show();
    }

    public void valider(View view) {
        String url = "https://apps.oubtou.me/android/absence/set_absence.php?seance_id="+seance_id+"&date="+date_+"&classe="+classe.getId();
        for (int i=0; i<List_etudiants.size(); i++){
            Etudiant e = (Etudiant) List_etudiants.get(i);
            if ( e.isIs_abs() )
                url += "&etudiants["+e.getCode()+"]="+e.getCode();
        }

        new AccesHTTP() {
            @Override
            protected void onResult(JSONArray data) {
                ArrayList<Etudiant> etds = new ArrayList<Etudiant>();
                for ( int i=0; i<data.length(); i++ ) {
                    JSONObject e = null;
                    try {
                        e = data.getJSONObject(i);
                        if( e.getInt("success") == 1 ){
                            seance_id = e.getInt("latest_id");
                        }
                        Toast.makeText(getApplicationContext(), e.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }.execute(url);
    }
}