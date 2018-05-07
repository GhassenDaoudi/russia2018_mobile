/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.EquipeFantasy;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.JoueurFantasy;
import com.mycompany.Utilitaire.Parser;
import com.mycompany.Utilitaire.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quickstrikes96
 */
public class ServiceJoueurFantasy {

    public static void getJoueursByequipe() {
        List<JoueurFantasy> l = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/mobile/jf" + "/" + Session.getEf().getId());
        
        System.out.println(con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> joueurs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) joueurs.get("root");
                    System.out.println(list);
                    for (Map<String, Object> obj : list) {
                        JoueurFantasy joueur = new JoueurFantasy();
                        joueur.setId((int) Float.parseFloat(obj.get("id").toString()));
                        joueur.setJoueur(Parser.toJoueur(obj.get("idJoueur")));
                        joueur.setPoints((int) Float.parseFloat(obj.get("points").toString()));
                        l.add(joueur);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        Session.setJoueurFantasy(l);
        System.out.println(Session.getJoueurFantasy());
    }

    public static void ajoutJoueur(Joueur j, EquipeFantasy EF, int etat, int points) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RS2018/web/mobile/addJF/" + j.getId() + "/" + EF.getId() + "/" + etat + "/" + points;
        con.setUrl(Url);

        System.out.println(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<JoueurFantasy> afficherJoueurs() {
        ArrayList<JoueurFantasy> listJoueurs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/mobile/TeamJF");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listJoueurs = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> joueurs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) joueurs.get("root");
                    for (Map<String, Object> obj : list) {
                        JoueurFantasy joueur = new JoueurFantasy();
                        joueur.setId((int) Float.parseFloat(obj.get("id").toString()));
                        joueur.setJoueur(Parser.toJoueur(obj.get("idJoueur")));
                        joueur.setPoints((int) Float.parseFloat(obj.get("points").toString()));
                        listJoueurs.add(joueur);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJoueurs;
    }

    public void vendreJoueur(JoueurFantasy j) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RS2018/web/mobile/SellJF/" + j.getId();
        con.setUrl(Url);

        System.out.println(Url);

        /*con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });*/
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
