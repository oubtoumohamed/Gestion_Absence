package me.oubtou.gestionabsence;

import org.json.JSONException;
import org.json.JSONObject;

public class Seance {
    private int id;
    private String date;
    private Classe classe;

    public Seance(){
        this.id = 0;
        this.date = "";
    }

    public Seance(int i, String d, Classe c){
        this.id = i;
        this.date = d;
        this.classe = c;
    }

    public Seance(JSONObject object){
        try {
            this.id = object.getInt("id");
            this.date = object.getString("date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String toString(){
        return this.date;
    }
    public String toJson(){
        return "{ 'id': '"+this.id+"', 'date': '"+this.date+"', 'classe_': '"+this.classe.toJson()+"'}";
    }
}

