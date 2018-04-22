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
public class Abonnement {
    private int id ;
    private User user ;
    private Joueur joueur ;
    
    public Abonnement(){
        this.id=0 ;
        this.user= new User() ;
        this.joueur= new Joueur();
    }
    public Abonnement(int id ,User user ,Joueur joueur){
        this.id=id ;
        this.user =user;
        this.joueur =joueur;
    }
    public Abonnement(User user,Joueur joueur){
        this.user =user;
        this.joueur =joueur;
    }
    
    @Override
    public String toString(){
        return  "id :"+this.id+" id_user :"+this.user.getId()+" id_joueur :"+this.joueur.getId() ;
    }
    
    @Override
    public boolean equals(Object o){
       if(o != null && o instanceof Abonnement){
           Abonnement a = (Abonnement) o ;
           if(a.getId() == this.id)
               return true ;
       } 
       return false ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
        return hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    
}
