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
public class Joueur {

    public Joueur(String prenom, String nom, Equipe Equipe,float prix, posteF PosteF) {
        this.prenom=prenom;
        this.nom=nom;
        this.equipe=Equipe;
        this.prix=prix;
        this.PosteF=PosteF;
    }
    
    public Joueur(int id,String prenom, String nom, Equipe Equipe,float prix, posteF PosteF) {
        this.id=id;
        this.prenom=prenom;
        this.nom=nom;
        this.equipe=Equipe;
        this.prix=prix;
        this.PosteF=PosteF;
    }
    
    public enum posteF{
        Gardien ,
        Defenseur,
        Milieu ,
        Attaquant ,
    }
    private int id ;
    private Equipe equipe ;
    private String nom ;
    private String prenom ;
    private int age ;
    private String poste ;
    private int numero ;
    private String club ;
    private String image ;
    private posteF PosteF ;
    private float prix ;
    private int nb_but ;

    public int getNb_but() {
        return nb_but;
    }

    public void setNb_but(int nb_but) {
        this.nb_but = nb_but;
    }
    
    public Joueur(){
        this.id =0 ;
        this.equipe = new Equipe();
        this.nom="";
        this.prenom="";
        this.age=0;
        this.poste="";
        this.numero=0;
        this.club="";
        this.image="";
        this.PosteF = Joueur.posteF.Gardien ;
        this.prix = (float)0 ;
    }

    public posteF getPosteF() {
        return PosteF;
    }

    public void setPosteF(posteF PosteF) {
        this.PosteF = PosteF;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    public Joueur(int id ,Equipe equipe,String nom ,String prenom ,int age ,String poste,int numero ,String club,String image,posteF PosteF,float prix){
        this.id = id ;
        this.equipe =equipe;
        this.nom = nom ;
        this.prenom = prenom ;
        this.age = age ;
        this.poste = poste ;
        this.numero=numero;
        this.club = club ;
        this.image= image ;
        this.PosteF = PosteF ;
        this.prix = prix ;
    }

    
    public Joueur(Equipe equipe,String nom ,String prenom ,int age ,String poste ,int numero,String club,String image,posteF PosteF,float prix){
        this.equipe = equipe;
        this.nom = nom ;
        this.prenom = prenom ;
        this.age = age ;
        this.poste = poste ;
        this.numero= numero;
        this.club = club ;
        this.image = image ;
        this.PosteF = PosteF;
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString(){
       return "id :"+id+" id_equipe :"+this.equipe.getId()+" nom :"+this.nom+" prenom :"+this.prenom+" age :"+this.age+" poste :"+this.poste+" numero :"+this.numero+" club :"+this.club ; 
    }
    
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof Joueur){
            Joueur j= (Joueur) o ;
            if(j.getId() == this.id)
                return true ;
        }
        return false ;
    }
    @Override
    public int hashCode(){
        return 5 ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}