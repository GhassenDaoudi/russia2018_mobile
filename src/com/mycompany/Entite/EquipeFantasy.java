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
public class EquipeFantasy {
    private int id;
    private String nom;
    private long totalpoints;
    private long classement;
    private User User;
    private int transfers;
    private float valeur;
    private float banque;
    

    public EquipeFantasy() {
        this.User = new User();
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

    public long getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(long totalpoints) {
        this.totalpoints = totalpoints;
    }

    public long getClassement() {
        return classement;
    }

    public void setClassement(long classement) {
        this.classement = classement;
    }
    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public int getTransfers() {
        return transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public float getBanque() {
        return banque;
    }

    public void setBanque(float banque) {
        this.banque = banque;
    }

    public EquipeFantasy(int id, String nom, long totalpoints, long classement, User User, int transfers, float valeur, float banque) {
        this.id = id;
        this.nom = nom;
        this.totalpoints = totalpoints;
        this.classement = classement;
        this.User = User;
        this.transfers = transfers;
        this.valeur = valeur;
        this.banque = banque;
    }
    
    public EquipeFantasy(int id, User User, String nom) {
        this.id = id;
        this.User = new User();
        this.nom = nom;
    }
    
    public EquipeFantasy(String nom) {
        this.nom = nom;
    }
    
        public EquipeFantasy(User User, String nom) {
        this.User = new User();
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "EquipeFantasy{" + "id=" + id + ", nom=" + nom + ", totalpoints=" + totalpoints + ", classement=" + classement + ", User=" + User + ", transfers=" + transfers + ", valeur=" + valeur + ", banque=" + banque + '}';
    }
    
        
}
