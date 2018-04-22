/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author eloss
 */
public class Entraineur {
    private int id ;
    String nom ;
    String prenom ;
    String description ;
    
    public Entraineur(){
        this.id = 0 ;
        this.nom ="";
        this.prenom="";
        this.description = "";
    }
    public Entraineur(int id ,String nom ,String prenom ,String description){
        this.id = id ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.description=description ;
    }
    public Entraineur(String nom ,String prenom ,String description){
        this.nom = nom ;
        this.prenom = prenom ;
        this.description=description ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return "id :"+this.id+" nom :"+this.nom+" prenom :"+this.prenom+" description :"+this.description;
    }
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof Entraineur){
            Entraineur e = (Entraineur) o ;
            if(e.getId() == this.id){
                return true ;
            }
        }
        return false ;
    }
}
