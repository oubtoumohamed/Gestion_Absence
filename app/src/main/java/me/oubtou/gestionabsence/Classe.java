package me.oubtou.gestionabsence;

import org.json.JSONException;
import org.json.JSONObject;

public class Classe {
    private int id;
    private String name;
    private int nbr_etudiant;

    public Classe(){
        this.id = 0;
        this.name = "";
        this.nbr_etudiant = 0;
    }

    public Classe(int i, String n, int nbr){
        this.id = i;
        this.name = n;
        this.nbr_etudiant = nbr;
    }

    public Classe(JSONObject object){
        try {
            this.id = object.getInt("id");
            this.name = object.getString("name");
            this.nbr_etudiant = object.getInt("nbr_etudiant");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbr_etudiant() {
        return nbr_etudiant;
    }

    public void setNbr_etudiant(int nbr_etudiant) {
        this.nbr_etudiant = nbr_etudiant;
    }

    public String toString(){
        return this.name;
    }
    public String toJson(){
        return "{ 'id': '"+this.id+"', 'name': '"+this.name+"', 'nbr_etudiant': '"+this.nbr_etudiant+"'}";
    }
}
