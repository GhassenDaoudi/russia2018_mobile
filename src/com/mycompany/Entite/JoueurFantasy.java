/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author quickstrikes96
 */
public class JoueurFantasy {

    private int id;
    private Joueur Joueur;
    private EquipeFantasy FEquipes;
    private int etat;
    private int points;
    

    public JoueurFantasy() {
        

    }

    public JoueurFantasy(int id, Joueur Joueur, EquipeFantasy FEquipes, int etat,int points) {
        this.id = id;
        this.Joueur = Joueur;
        this.FEquipes = FEquipes;
        this.etat = etat;
        this.points = points;
    }

    public JoueurFantasy(Joueur Joueur, int etat, int points) {

        this.Joueur = Joueur;
        this.etat = etat;
        this.points = points;
    }

    public JoueurFantasy(int id, Joueur Joueur, int etat, int points) {
        this.id = id;
        this.Joueur = Joueur;
        this.etat = etat;
        this.points = points;
    }   

    public JoueurFantasy(Joueur Joueur) {
        this.Joueur = Joueur;
    }

    public JoueurFantasy(Joueur Joueur, int etat) {
        this.Joueur = Joueur;
        this.etat = etat;
    }
    
    public JoueurFantasy(int id, Joueur Joueur, EquipeFantasy FEquipe) 
    {
        this.id = id;
        this.Joueur = Joueur;
        this.FEquipes = FEquipe;
    }
    
    public JoueurFantasy(int id, int points) 
    {
        this.points = points;
    }

    public JoueurFantasy(int id, String prenom, String nom, String club) {
        
        this.Joueur = new Joueur();
    }

    
    public Joueur getJoueur() {
        return Joueur;
    }

    public void setJoueur(Joueur Joueur) {
        this.Joueur = Joueur;
    }
    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EquipeFantasy getFEquipes() {
        return FEquipes;
    }

    public void setFEquipes(EquipeFantasy FEquipes) {
        this.FEquipes = FEquipes;
    }

    @Override
    public String toString() {
        return "JoueurFantasy{" + "id=" + id + ", Joueur=" + Joueur + ", etat=" + etat + ", points=" + points + ", FEquipes=" + FEquipes + '}';
    }

    @Override
    public int hashCode() {
        return 5 ;
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
        final JoueurFantasy other = (JoueurFantasy) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

}
