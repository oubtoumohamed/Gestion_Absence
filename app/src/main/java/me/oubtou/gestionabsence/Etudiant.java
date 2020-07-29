package me.oubtou.gestionabsence;

import org.json.JSONException;
import org.json.JSONObject;

public class Etudiant {

    private int code;
    private String nom;
    private String prenom;
    private String classe;
    private boolean is_abs;

    public Etudiant(){
        this.code = 0;
        this.nom = "";
        this.prenom = "";
        this.classe = "";
        this.is_abs = false;
    }

    public Etudiant(int code, String nom, String prenom, String classe, Boolean is_abs){
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.is_abs = is_abs;
    }

    public Etudiant(JSONObject object){
        try {
            this.code = object.getInt("code");
            this.nom = object.getString("nom");
            this.prenom = object.getString("prenom");
            this.classe = object.getString("classe");
            this.is_abs = object.getBoolean("is_abs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public boolean isIs_abs() {
        return is_abs;
    }

    public void setIs_abs(boolean is_abs) {
        this.is_abs = is_abs;
    }

    public String toString(){
        return this.code + " - " + this.nom + " " + this.prenom;
    }
}
