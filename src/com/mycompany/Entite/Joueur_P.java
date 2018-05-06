/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;



/**
 *
 * @author hseli
 */
public class Joueur_P {
    private int id;
    private Joueur J;
    private int temps_joue;

    public Joueur_P(){
        
    }
    public Joueur_P(int id, Joueur J, int temps_joue) {
        this.id = id;
        this.J = J;
        this.temps_joue = temps_joue;
    }
    public Joueur_P(Joueur J, int temps_joue) {
        this.id = id;
        this.J = J;
        this.temps_joue = temps_joue;
    }

    @Override
    public String toString() {
        return "Joueur_P{" + "id=" + id + ", J=" + J + ", temps_joue=" + temps_joue + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Joueur getJ() {
        return J;
    }

    public void setJ(Joueur J) {
        this.J = J;
    }

    public int getTemps_joue() {
        return temps_joue;
    }

    public void setTemps_joue(int temps_joue) {
        this.temps_joue = temps_joue;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
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
        final Joueur_P other = (Joueur_P) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
