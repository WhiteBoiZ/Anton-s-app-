package com.example.antons;

public class Food {
    private int id;
    private String titel;
    private String beskrivning;
    private String type; // Antog att detta fält finns i din data
    private boolean vald; // Antog att detta fält finns i din data
    private int pris; // Antog att detta fält finns i din data

    public Food(int id, String titel, String beskrivning, String type, boolean vald, int pris) {
        this.id = id;
        this.titel = titel;
        this.beskrivning = beskrivning;
        this.type = type;
        this.vald = vald;
        this.pris = pris;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public String getType() {
        return type;
    }

    public boolean isVald() {
        return vald;
    }

    public int getPris() {
        return pris;
    }


}
