/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author skanderbejaoui
 */
public class Pari {

    public enum EtatPari {
        Encours, Gagne, Perdu
    }

    public enum ResultatPari {
        un, x, deux
    }
    private int id;
    private Match m;
    private float cote;
    private float mise;
    private FichePari fp;
    private Float gain;
    private int type;
    private EtatPari etat;
    private ResultatPari resultat;

    public ResultatPari getResultat() {
        return resultat;
    }
    
    public void setResultat(ResultatPari resultat){
        this.resultat=resultat;
    }
    
    public EtatPari getEtat() {
        return etat;
    }

    public void setEtat(EtatPari etat) {
        this.etat = etat;
    }

  

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
   

   

    public Float getGain() {
        return gain;
    }

    public void setGain(Float gain) {
        this.gain = gain;
    }

    public float getMise() {
        return mise;
    }

    public void setMise(float mise) {
        this.mise = mise;
    }

    public FichePari getFp() {
        return fp;
    }

    public void setFp(FichePari fp) {
        this.fp = fp;
    }

    public Match getM() {
        return m;
    }

    public void setM(Match m) {
        this.m = m;
    }

    public Pari() {
        this.fp = new FichePari();
        this.m = new Match();
       

    }

    public Pari(String a, String b, Float cote, String tf,Float gain, int id,Pari.ResultatPari resultat) {
        this.fp = new FichePari();
        this.m = new Match();
        this.cote = cote;
        this.gain = gain;
        this.id = id;
        this.resultat = resultat;
        //System.out.println(this.getTf().getText());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCote() {
        return cote;
    }

    public void setCote(float cote) {
        this.cote = cote;
    }

    @Override
    public String toString() {
        return "Pari{" + "id=" + id + ", m=" + m + ", cote=" + cote + ", mise=" + mise + ", fp=" + fp + ", gain=" + gain + ", type=" + type + ", etat=" + etat + ", resultat=" + resultat + '}';
    }

 
    
    @Override
    public int hashCode() {
     
        return 9;
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
        final Pari other = (Pari) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
