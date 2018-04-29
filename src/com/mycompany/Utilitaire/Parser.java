/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.Entite.Commentaire;
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.Publication;
import com.mycompany.Entite.User;
import java.util.Date;
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

    

}
