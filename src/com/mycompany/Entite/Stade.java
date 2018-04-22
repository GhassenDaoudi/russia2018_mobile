/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import javafx.scene.control.Button;

/**
 *
 * @author hseli
 */
public class Stade {
    private int id;
    private String nom;
    private String adresse;
    private int capacite;
    private String image;
    //private Button button;
    
    public Stade(){
        
    }
    public Stade(int id, String nom, String adresse, int capacite, String image) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
        this.image = image;
    }
    public Stade(int id,String nom,String adresse,int capacite){
        this.id = id;
         this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
       // this.button=new Button("Geolocaliser");
    }
    public Stade(int i){
        this.id = i;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   /* public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    
    */
    
}
