package me.oubtou.gestionabsence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccesHTTP extends AsyncTask<String, Void, String> {

    public AccesHTTP(){ }
    //
    @Override
    protected void onPreExecute() { }
    protected void onPostExecute(String result) {
        JSONArray data = null;
        try {
            data = new JSONArray( result );
        } catch (JSONException ex) {
            ex.printStackTrace();
            Log.d("JSONException", ex.getMessage());
        }

        this.onResult(data);
    }

    protected void onResult(JSONArray result){  }


    @Override
    protected String doInBackground(String... strings) {
        String result= "";
        String serverADDR = strings[0].replaceAll(" ", "%20");
        try {
            URL url=new URL(serverADDR);
            HttpURLConnection http=(HttpURLConnection)url.openConnection();
            http.connect();
            try {
                InputStream in = new BufferedInputStream(http.getInputStream());

                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                result = responseStrBuilder.toString();
            }finally {
                http.disconnect();
            }
        }catch (IOException e) {
            Log.d("IOException", e.getMessage());
        }

        return result;
    }
}