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
public class Evenement {
    private int id;
    private Match M;
    private Joueur_P JP;
    public enum TypeCarton{PasC,Rouge,Jaune};
    private TypeCarton carton;
    private int but;
    private int temps;
    
    public Evenement(){
        
    }

    public Evenement(Match M, Joueur_P JP, TypeCarton carton, int but, int temps) {
        this.M = M;
        this.JP = JP;
        this.carton = carton;
        this.but = but;
        this.temps = temps;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Match getM() {
        return M;
    }

    public void setM(Match M) {
        this.M = M;
    }

    public Joueur_P getJoueur() {
        return JP;
    }

    public void setJoueur(Joueur_P Joueur) {
        this.JP= Joueur;
    }

    public TypeCarton getCarton() {
        return carton;
    }

    public void setCarton(TypeCarton carton) {
        this.carton = carton;
    }

    public int getBut() {
        return but;
    }

    public void setBut(int but) {
        this.but = but;
    }



   

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    
    
    
}
