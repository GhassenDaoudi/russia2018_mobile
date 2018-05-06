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
import com.mycompany.Entite.Abonnement;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.User;
import com.mycompany.Utilitaire.Parser;
import com.mycompany.Utilitaire.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elossofa
 */
public class ServiceAbonnement {
    public static Map<Joueur, ArrayList<Abonnement> > LesAbonnements() {
        ArrayList<Abonnement> listAbonnement = new ArrayList<>();
        Map<Joueur, ArrayList<Abonnement> > mymap = new HashMap<Joueur,ArrayList<Abonnement> >();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/abonnements/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> article = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) article.get("root");
                    for (Map<String, Object> obj : list) {
                        Abonnement A = Parser.toAbonnement(obj);
                        listAbonnement.add(A);
                        if(mymap.containsKey(A.getJoueur())){
                            mymap.get(A.getJoueur()).add(A);
                        }
                        else{
                            mymap.put(A.getJoueur(), new ArrayList<Abonnement>());
                            mymap.get(A.getJoueur()).add(A);
                        }
                    }
                } catch (IOException | NumberFormatException e) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return mymap;
    }
    public static void SAbonner(Joueur joueur) {
        ConnectionRequest con = new ConnectionRequest();
        String test = "http://localhost/RS2018/web/abonnement/sabonnermobile/" + Session.getUser().getId() + "/" + joueur.getId() ;
        System.out.println(test);
        con.setUrl(test);
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
}
