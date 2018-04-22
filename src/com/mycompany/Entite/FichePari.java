/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author skanderbejaoui
 */
public class FichePari {

    @Override
    public int hashCode() {
       
        return 8;
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
        final FichePari other = (FichePari) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    public enum EtatFiche{Encours,Gagne,Perdu};
    private int id;
    private float cote_total;
    private EtatFiche etat;
    private List<Pari> paris;
    private User u;
    private float misetotal;
    private Date date;
    private float gain;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getMisetotal() {
        return misetotal;
    }

    public void setMisetotal(float misetotal) {
        this.misetotal = misetotal;
    }
    public FichePari() {
        this.u = new User();
        this.paris = new ArrayList<>();
      this.date = new Date(System.currentTimeMillis());
    }

    public List<Pari> getParis() {
        return paris;
    }

    public void setParis(List<Pari> paris) {
        this.paris = paris;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCote_total() {
        return cote_total;
    }

    public void setCote_total(float cote_total) {
        this.cote_total = cote_total;
    }


    public EtatFiche getEtat() {
        return etat;
    }

    public void setEtat(EtatFiche etat) {
        this.etat = etat;
    }
    
    public String toString(){
        return "        "+this.getCote_total()+"       "+this.getMisetotal()+"     "+this.getGain()+"           "+this.getDate();
      
    }
      public String toString2(){
        return "        "+this.getCote_total()+"       "+this.getMisetotal()+"     "+this.getGain()+"           "+this.getDate()+"          "+this.getU().getUsername();
      
    }
}
