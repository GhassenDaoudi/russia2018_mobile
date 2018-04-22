/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;
import java.util.TimeZone;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author hseli
 */
public class Match {

    private int id;

    public enum EtatMatch {
        Debut, Encours, Termine
    };

    public enum progress {
        pool, last_16, quart_final, demi_final, final_
    }
    private Groupe G;
    private Equipe E1;
    private Equipe E2;
    private Stade S;
    private Date date;
    private TimeZone heure;
    private Score score1;
    //private String score;
    private EtatMatch etat;
    private int duree;
    private int nombre_spectateur;
    private progress type;
    private float cote_eq1;
    private float cote_eq2;
    private float cote_nul;
    private String nom1;
    private String nom2;
    private ToggleButton button1;
    private ToggleButton button2;
    private ToggleButton button3;
    private TextField tf;
    private CheckBox ck;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Match() {
        this.E1 = new Equipe();
        this.E2 = new Equipe();
        this.button1 = new ToggleButton();
        this.button2 = new ToggleButton();
        this.button3 = new ToggleButton();
        this.ck = new CheckBox();
    }

    public Match(int i, Groupe G, Equipe E1, Equipe E2, Stade S, Date date, TimeZone heure, Score score1, /*String score,*/ EtatMatch etat, int duree, int nombre_spectateur, progress type) {
        this.id = i;
        this.G = G;
        this.E1 = E1;
        this.E2 = E2;
        this.S = S;
        this.date = date;
        this.heure = heure;
        this.score1 = score1;
        // this.score = score;
        this.etat = etat;
        this.duree = duree;
        this.nombre_spectateur = nombre_spectateur;
        this.type = type;
    }

    public Match(int i, Date date, TimeZone heure, Equipe E1, Score score, Equipe E2, Stade S) {
        this.id = i;
        this.date = date;
        this.heure = heure;
        this.E1 = E1;
        this.score1 = score;
        this.E2 = E2;
        this.S = S;

    }

    public progress getType() {
        return type;
    }

    public void setType(progress type) {
        this.type = type;
    }

    public Groupe getG() {
        return G;
    }

    public void setG(Groupe G) {
        this.G = G;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipe getE1() {
        return E1;
    }

    public void setE1(Equipe E1) {
        this.E1 = E1;
    }

    public Equipe getE2() {
        return E2;
    }

    public void setE2(Equipe E2) {
        this.E2 = E2;
    }

    public Stade getS() {
        return S;
    }

    public void setS(Stade S) {
        this.S = S;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TimeZone getHeure() {
        return heure;
    }

    public void setHeure(TimeZone heure) {
        this.heure = heure;
    }

    public Score getScore1() {
        return score1;
    }

    public void setScore1(Score score1) {
        this.score1 = score1;
    }

    /*  public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }*/
    public EtatMatch getEtat() {
        return etat;
    }

    public void setEtat(EtatMatch etat) {
        this.etat = etat;
    }

    public int getNombre_spectateur() {
        return nombre_spectateur;
    }

    public void setNombre_spectateur(int nombre_spectateur) {
        this.nombre_spectateur = nombre_spectateur;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", G=" + G + ", E1=" + E1 + ", E2=" + E2 + ", S=" + S + ", date=" + date + ", heure=" + heure + ", score1=" + score1 + ", etat=" + etat + ", duree=" + duree + ", nombre_spectateur=" + nombre_spectateur + ", type=" + type + '}';
    }

    public String toString1() {
        return this.getE1().getNom() + " - " + this.getE2().getNom() + "   Cote :" + this.getButton1().getText();
    }

    public String toString2() {
        return this.getE1().getNom() + " - " + this.getE2().getNom() + "   Cote :" + this.getButton2().getText();
    }

    public String toString3() {
        return this.getE1().getNom() + " - " + this.getE2().getNom() + "   Cote :" + this.getButton3().getText();
    }

    public String toString4() {
        return this.getNom1() + "     -     " + this.getNom2();
    }

    public Match(String a, String b, float cote_eq1, float cote_nul, float cote_eq2, int id) {
        this.button1 = new ToggleButton(String.valueOf(cote_eq1));
        this.button2 = new ToggleButton(String.valueOf(cote_nul));
        this.button3 = new ToggleButton(String.valueOf(cote_eq2));
        this.E1 = new Equipe();
        this.E2 = new Equipe();
        this.setNom1(a);
        this.setNom2(b);
        this.cote_eq1 = cote_eq1;
        this.cote_eq2 = cote_eq2;
        this.cote_nul = cote_nul;
        this.id = id;
    }

    public Match(String a, String b, CheckBox ck, int id) {
        this.E1 = new Equipe();
        this.E2 = new Equipe();
        this.setNom1(a);
        this.setNom2(b);
        this.ck = new CheckBox();
        this.setId(id);
    }

    public Match(String a, String b) {
        this.E1 = new Equipe();
        this.E2 = new Equipe();
        this.setNom1(a);
        this.setNom2(b);
        this.ck = new CheckBox();

    }

    public float getCote_eq1() {
        return cote_eq1;
    }

    public void setCote_eq1(float cote_eq1) {
        this.cote_eq1 = cote_eq1;
    }

    public float getCote_eq2() {
        return cote_eq2;
    }

    public void setCote_eq2(float cote_eq2) {
        this.cote_eq2 = cote_eq2;
    }

    public float getCote_nul() {
        return cote_nul;
    }

    public void setCote_nul(float cote_nul) {
        this.cote_nul = cote_nul;
    }

    public String getNom1() {
        return nom1;
    }

    public void setNom1(String nom1) {
        this.nom1 = nom1;
    }

    public String getNom2() {
        return nom2;
    }

    public void setNom2(String nom2) {
        this.nom2 = nom2;
    }

    public ToggleButton getButton1() {
        return button1;
    }

    public void setButton1(ToggleButton button1) {
        this.button1 = button1;
    }

    public ToggleButton getButton2() {
        return button2;
    }

    public void setButton2(ToggleButton button2) {
        this.button2 = button2;
    }

    public ToggleButton getButton3() {
        return button3;
    }

    public void setButton3(ToggleButton button3) {
        this.button3 = button3;
    }

    public TextField getTf() {
        return tf;
    }

    public void setTf(TextField tf) {
        this.tf = tf;
    }

    public CheckBox getCk() {
        return ck;
    }

    public void setCk(CheckBox ck) {
        this.ck = ck;
    }

}
