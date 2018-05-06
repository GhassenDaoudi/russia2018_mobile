/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.media.Media;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.User;


/**
 *
 * @author Ghassen
 */
public class Session {

    private static User user = null;
    private static Equipe equipe = null ;
    private static Joueur joueur=null ;
    private static Media media = null ;

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
}
