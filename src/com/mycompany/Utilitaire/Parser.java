/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.Entite.Abonnement;
import com.mycompany.Entite.Commentaire;
import com.mycompany.Entite.Entraineur;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Groupe;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.Match;
import com.mycompany.Entite.Publication;
import com.mycompany.Entite.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghassen
 */
public class Parser {
    

    public static Date toDate(Object obj) {
        Map<String, Object> d = (Map<String, Object>) obj;
        double td = (double) d.get("timestamp");
        long xd = (long) (td * 1000L);
        String formatd = new SimpleDateFormat("dd/MM/yyyy").format(new Date(xd));
        Date date=null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(formatd);
        } catch (ParseException ex) {
        }
        return date;
    }

    public static Commentaire toCommentaire(Object obj) {
        Commentaire c = new Commentaire();
        Map<String, Object> jsonComm = (Map<String, Object>) obj;
        c.setDescription(jsonComm.get("description").toString());
        c.setId((int) Float.parseFloat(jsonComm.get("id").toString()));
        c.setUser(toUser(jsonComm.get("idUser")));
        c.setDate_creation(toDate(jsonComm.get("dateCreation")));
        return c;
    }

    public static User toUser(Object obj) {
        User u = new User();
        Map<String, Object> jsonUser = (Map<String, Object>) obj;
        u.setId((int) Float.parseFloat(jsonUser.get("id").toString()));
        List<String> roles = (List<String> )jsonUser.get("roles");
        String roleString = roles.get(0);
        if(roleString.contains("ADMIN"))u.setRole(User.Role.admin);
        else if(roleString.contains("JOURNALISTE"))u.setRole(User.Role.journaliste);
        else if(roleString.contains("MEMBRE"))u.setRole(User.Role.membre);
        else if(roleString.contains("MODERATEUR"))u.setRole(User.Role.moderateur);
        //System.out.println(roles.get(0));
        /*for (Map<String, Object> role : roles) {
            System.out.println(role);
        }*/
        u.setUsername(jsonUser.get("username").toString());
        u.setEmail(jsonUser.get("email").toString());
        u.setMdp(jsonUser.get("password").toString());
        u.setNom(jsonUser.get("nom").toString());
        u.setPrenom(jsonUser.get("prenom").toString());
        u.setImage(jsonUser.get("image").toString());
        u.setJeton(Float.parseFloat(jsonUser.get("jeton").toString()));
        return u;
    }

    public static Publication toPublication(Object obj, Publication.Type type) {
        Publication p = new Publication();
        Map<String, Object> jsonPublication = (Map<String, Object>) obj;
        p.setType(type);
        p.setLien(jsonPublication.get("lien").toString());
        p.setTitre(jsonPublication.get("titre").toString());
        p.setDescription(jsonPublication.get("description").toString());
        p.setId((int) Float.parseFloat(jsonPublication.get("id").toString()));
        p.setUser(toUser(jsonPublication.get("idUser")));
        p.setDate_creation(toDate(jsonPublication.get("dateCreation")));
        return p;
    }
    
    public static Equipe toEquipe(Object obj){
        Equipe E = new Equipe();
        Map<String , Object> JsonEquipe = (Map<String , Object>) obj ;
        E.setId((int) Float.parseFloat(JsonEquipe.get("id").toString()));
        E.setDrapeau(JsonEquipe.get("drapeau").toString());
        E.setNom(JsonEquipe.get("nom").toString());
        E.setProgress(Equipe.Progress.valueOf(JsonEquipe.get("progress").toString()));
        E.setNb_match_joue((int) Float.parseFloat(JsonEquipe.get("nbMatchJoue").toString()));
        E.setPts((int) Float.parseFloat(JsonEquipe.get("pts").toString()));
        E.setEntraineur(toEntraineur(JsonEquipe.get("idEntraineur")));
        E.setGroupe(toGroupe(JsonEquipe.get("idGroupe")));
        E.setQualification(Equipe.Qualification.valueOf(JsonEquipe.get("qualification").toString()));
        ArrayList<Object> joueurs = (ArrayList<Object>) JsonEquipe.get("joueurs");
        ArrayList<Joueur> liste_joueurs = new ArrayList<>();
        for(Object J : joueurs){
            liste_joueurs.add(toJoueurs(J));
        }
        E.setListe_joueur(liste_joueurs);
        return E ;
    }
    private static Equipe toEquipes(Object map) {
        Equipe e = new Equipe();
        Map<String, Object> jsonEquipe = (Map<String, Object>) map;
        float id = Float.parseFloat(jsonEquipe.get("id").toString());
        e.setId((int) id);
        e.setNom(jsonEquipe.get("nom").toString());
        e.setDrapeau(jsonEquipe.get("drapeau").toString());
        return e;

    }
    public static Entraineur toEntraineur(Object obj){
        Entraineur E = new Entraineur();
        Map<String , Object> JsonEntraineur = (Map<String , Object>) obj ;
        E.setId((int) Float.parseFloat(JsonEntraineur.get("id").toString()));
        E.setNom(JsonEntraineur.get("nom").toString());
        E.setPrenom(JsonEntraineur.get("prenom").toString());
        E.setDescription(JsonEntraineur.get("description").toString());
        return E; 
    }
    public static Groupe toGroupe(Object obj){
        Groupe G = new Groupe();
        Map<String , Object> JsonGroupe = (Map<String , Object>) obj ;
        G.setId((int) Float.parseFloat(JsonGroupe.get("id").toString()));
        G.setNom(JsonGroupe.get("nom").toString());
        G.setEtat(Groupe.Etat.valueOf(JsonGroupe.get("etat").toString()));
        return G ;
    }
    public static Joueur toJoueurs(Object obj){
        Joueur J = new Joueur();
        Map<String , Object> JsonJoueurs = (Map<String , Object>) obj ;
        J.setId((int) Float.parseFloat(JsonJoueurs.get("id").toString()));
        J.setNom(JsonJoueurs.get("nom").toString());
        J.setPrenom(JsonJoueurs.get("prenom").toString());
        J.setClub(JsonJoueurs.get("club").toString());
        J.setAge((int) Float.parseFloat(JsonJoueurs.get("age").toString()));
        J.setPoste(JsonJoueurs.get("poste").toString());
        J.setNumero((int) Float.parseFloat(JsonJoueurs.get("numero").toString()));
        J.setPoste(JsonJoueurs.get("poste").toString());
        J.setImage(JsonJoueurs.get("image").toString());
        J.setPosteF(Joueur.posteF.valueOf(JsonJoueurs.get("postef").toString()));
        J.setPrix((int) Float.parseFloat(JsonJoueurs.get("prix").toString()));
        return J ;
    }
    public static Abonnement toAbonnement(Object obj){
        Abonnement A = new Abonnement();
        Map<String , Object> JsonAbonnement = (Map<String , Object>) obj ;
        A.setJoueur(toJoueurs(JsonAbonnement.get("idJoueur")));
        A.setUser(toUser(JsonAbonnement.get("idUser")));
        A.setId((int) Float.parseFloat(JsonAbonnement.get("id").toString()));
        return A ;
    }
    public static Match toMatch(Object map,Date d) {
        Match m = new Match();
        Map<String, Object> jsonMatch = (Map<String, Object>) map;
        float id = Float.parseFloat(jsonMatch.get("id").toString());
        m.setId((int) id);
        m.setCote_eq1(Float.parseFloat(jsonMatch.get("coteEq1").toString()));
        m.setCote_eq2(Float.parseFloat(jsonMatch.get("coteEq2").toString()));
        m.setCote_nul(Float.parseFloat(jsonMatch.get("coteNul").toString()));
        m.setE1(toEquipes(jsonMatch.get("idEquipe1")));
        m.setE2(toEquipes(jsonMatch.get("idEquipe2")));
        m.setDate(d);
        return m;
    }
    public static Match toMatch(Object map) {
        Match m = new Match();
        Map<String, Object> jsonMatch = (Map<String, Object>) map;
        float id = Float.parseFloat(jsonMatch.get("id").toString());
        m.setId((int) id);
        m.setCote_eq1(Float.parseFloat(jsonMatch.get("coteEq1").toString()));
        m.setCote_eq2(Float.parseFloat(jsonMatch.get("coteEq2").toString()));
        m.setCote_nul(Float.parseFloat(jsonMatch.get("coteNul").toString()));
        m.setE1(toEquipes(jsonMatch.get("idEquipe1")));
        m.setE2(toEquipes(jsonMatch.get("idEquipe2")));
        return m;
    }

}
