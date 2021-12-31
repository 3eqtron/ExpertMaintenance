package com.example.projettp;

public class Task {
int checkbox;
int id;
    String titre;
    String heuredebutplan;
    String heurefinplan;
     String adresse;

    public Task(String titre, int id,String heuredebutplan, String heurefinplan, int checkbox,String adresse) {
        this.checkbox = checkbox;
        this.titre = titre;
        this.heuredebutplan = heuredebutplan;
        this.heurefinplan = heurefinplan;
        this.adresse=adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(int checkbox) {
        this.checkbox = checkbox;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getHeuredebutplan() {
        return heuredebutplan;
    }

    public void setHeuredebutplan(String heuredebutplan) {
        this.heuredebutplan = heuredebutplan;
    }

    public String getHeurefinplan() {
        return heurefinplan;
    }

    public void setHeurefinplan(String heurefinplan) {
        this.heurefinplan = heurefinplan;
    }
}
