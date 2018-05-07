/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.media.Media;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.EquipeFantasy;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.JoueurFantasy;
import com.mycompany.Entite.User;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ghassen
 */
public class Session {

    private static User user = null;
    private static Equipe equipe = null ;
    private static Joueur joueur=null ;
    private static Media media = null ;
    private static EquipeFantasy ef = null;
    private static List<JoueurFantasy> JoueurFantasy = new ArrayList<>();
    private static List<JoueurFantasy> gard = new ArrayList<>();
    private static List<JoueurFantasy> def = new ArrayList<>();
    private static List<JoueurFantasy> mil = new ArrayList<>();
    private static List<JoueurFantasy> atk = new ArrayList<>();
    private static float banque=100;

    public static List<JoueurFantasy> getGard() {
        return gard;
    }

    public static void setGard(List<JoueurFantasy> gard) {
        Session.gard = gard;
    }

    public static List<JoueurFantasy> getDef() {
        return def;
    }

    public static void setDef(List<JoueurFantasy> def) {
        Session.def = def;
    }

    public static List<JoueurFantasy> getMil() {
        return mil;
    }

    public static void setMil(List<JoueurFantasy> mil) {
        Session.mil = mil;
    }

    public static List<JoueurFantasy> getAtk() {
        return atk;
    }

    public static void setAtk(List<JoueurFantasy> atk) {
        Session.atk = atk;
    }

    public static float getBanque() {
        return banque;
    }

    public static void setBanque(float banque) {
        Session.banque = banque;
    }
    

    public static List<JoueurFantasy> getJoueurFantasy() {
        return JoueurFantasy;
    }

    public static void setJoueurFantasy(List<JoueurFantasy> JoueurFantasy) {
        Session.JoueurFantasy = JoueurFantasy;
    }

    public static EquipeFantasy getEf() {
        return ef;
    }

    public static void setEf(EquipeFantasy ef) {
        Session.ef = ef;
    }
    

    public static Equipe getEquipe() {
        return equipe;
    }

    public static void setEquipe(Equipe equipe) {
        Session.equipe = equipe;
    }

    public static Joueur getJoueur() {
        return joueur;
    }

    public static void setJoueur(Joueur joueur) {
        Session.joueur = joueur;
    }

    public static Media getMedia() {
        return media;
    }

    public static void setMedia(Media media) {
        Session.media = media;
    }

    public static void setUser(User u) {
        Session.user = u;
    }

    public static User getUser() {
        return Session.user;
    }

    public static boolean getverif() {
        if(gard.size()!=2)return false;
        if(def.size()!=5)return false;
        if(mil.size()!=5)return false;
        if(atk.size()!=3)return false;
        if(banque<0)return false;
        JoueurFantasy.addAll(gard);
        JoueurFantasy.addAll(def);
        JoueurFantasy.addAll(mil);
        JoueurFantasy.addAll(atk);
        return true;
    }
}
