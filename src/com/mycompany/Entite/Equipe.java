/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eloss
 */
public class Equipe {
    public Equipe(int id ,Entraineur entraineur,String nom ,String drapeau ,String maillot,List<Joueur> liste_joueur,Groupe groupe,int pts,Equipe.Progress progress,int nb_match_joue){
        this.id=id; 
        this.entraineur =entraineur;
        this.nom = nom ;
        this.drapeau = drapeau;
        this.maillot = maillot;
        this.liste_joueur = new ArrayList<>(liste_joueur);
        this.pts=pts;
        this.progress = progress ;
        this.groupe = groupe ;
        this.nb_match_joue =nb_match_joue ;
    }
    public Equipe(Entraineur entraineur,String nom ,String drapeau ,String maillot,List<Joueur> liste_joueur,Groupe groupe,int pts,int nb_match_joue){
        this.entraineur = entraineur;
        this.nom = nom ;
        this.drapeau = drapeau;
        this.maillot = maillot;
        this.liste_joueur = new ArrayList<>(liste_joueur);
        this.pts=pts;
        this.progress = Equipe.Progress.pool ;
        this.groupe = groupe ;
        this.nb_match_joue = nb_match_joue ;
    }
    public Equipe(String n,int p,int b){
        this.nom=n;
        this.pts=p;
        this.b=b;
    }
    private int b;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    public enum Qualification{
        En_attente ,
        Qualifie ,
        Disqualifie,
    }
    public enum Progress{
        pool ,
        last_16,
        quart_final ,
        demi_final ,
        final_,
    }
    private int id ;
    private String nom ;
    private String drapeau ;
    private String maillot ;
    private Entraineur entraineur ;
    private Progress progress ;
    private List<Joueur> liste_joueur ;
    private int pts ;
    private Groupe groupe ;
    private Qualification qualification ;
    private int nb_match_joue ;

    public int getNb_match_joue() {
        return nb_match_joue;
    }

    public void setNb_match_joue(int nb_match_joue) {
        this.nb_match_joue = nb_match_joue;
    }
    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
    
    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }


    public List<Joueur> getListe_joueur() {
        return liste_joueur;
    }

    public void setListe_joueur(List<Joueur> liste_joueur) {
        this.liste_joueur = liste_joueur;
    }
    
    public Equipe(){
        this.id = 0 ;
        this.nom = "";
        this.drapeau="";
        this.maillot="";
        this.entraineur = new Entraineur();
        this.liste_joueur = new ArrayList<>();
        this.pts = 0 ;
        this.progress = Equipe.Progress.pool ;
        this.groupe = new Groupe();
        this.nb_match_joue = 0 ;
        this.qualification = Equipe.Qualification.En_attente; 
    }
    public Equipe(int id ,Entraineur entraineur,String nom ,String drapeau ,String maillot,List<Joueur> liste_joueur,Groupe groupe,int pts,Equipe.Progress progress,int nb_match_joue,Qualification qualification){
        this.id=id; 
        this.entraineur =entraineur;
        this.nom = nom ;
        this.drapeau = drapeau;
        this.maillot = maillot;
        this.liste_joueur = new ArrayList<>(liste_joueur);
        this.pts=pts;
        this.progress = progress ;
        this.groupe = groupe ;
        this.nb_match_joue =nb_match_joue ;
        this.qualification= qualification ;
    }
    public Equipe(Entraineur entraineur,String nom ,String drapeau ,String maillot,List<Joueur> liste_joueur,Groupe groupe,int pts,int nb_match_joue,Qualification qualification){
        this.entraineur = entraineur;
        this.nom = nom ;
        this.drapeau = drapeau;
        this.maillot = maillot;
        this.liste_joueur = new ArrayList<>(liste_joueur);
        this.pts=pts;
        this.progress = Equipe.Progress.pool ;
        this.groupe = groupe ;
        this.nb_match_joue = nb_match_joue ;
        this.qualification = qualification ;
    }

    @Override
    public String toString() {
        return "id=" + id + ", id_entraineur=" + entraineur.getId() + ", nom=" + nom + ", drapeau=" + drapeau + ", maillot=" + maillot + " joueur : "+liste_joueur +" progress :"+this.progress +" groupe :"+groupe+" pts :"+this.pts;
    }
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof Equipe){
            Equipe e = (Equipe) o ;
            if(e.getId() == this.id)
                return true ;
        }
        return false ;
    }

    @Override
    public int hashCode() {
       
        return 5;
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

    public String getDrapeau() {
        return drapeau;
    }

    public void setDrapeau(String drapeau) {
        this.drapeau = drapeau;
    }

    public String getMaillot() {
        return maillot;
    }

    public void setMaillot(String maillot) {
        this.maillot = maillot;
    }
    public Entraineur getEntraineur() {
        return entraineur;
    }

    public void setEntraineur(Entraineur entraineur) {
        this.entraineur = entraineur;
    }    
}
