package me.oubtou.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Ajouter(View v){
        Intent classes_intent = new Intent(MainActivity.this, ClassesActivity.class);
        startActivity(classes_intent);
    }

    public void Archive(View v){
        Intent classes_intent = new Intent(MainActivity.this, ClassesActivity.class);
        classes_intent.putExtra("archive", true);
        startActivity(classes_intent);
    }
}