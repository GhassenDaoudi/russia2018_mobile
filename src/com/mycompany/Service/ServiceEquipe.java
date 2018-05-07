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
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Publication;
import com.mycompany.Utilitaire.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elossofa
 */
public class ServiceEquipe {

    public ArrayList<Equipe> afficherEquipes() {

        ArrayList<Equipe> listEquipes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/RS2018/web/mobile/allteams");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listEquipes = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> equipes = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(equipes);
                    //System.out.println(equipes);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) equipes.get("root");
                    for (Map<String, Object> obj : list) {
                        Equipe equipe = new Equipe();

                        equipe.setDrapeau(obj.get("drapeau").toString());
                        equipe.setNom(obj.get("nom").toString());
                        listEquipes.add(equipe);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEquipes;
    }

    public static ArrayList<Equipe> afficher() {
        ArrayList<Equipe> listEquipe = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("adadasdazdadsazdads");
        con.setUrl("http://localhost/RS2018/web/equipe/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> article = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) article.get("root");
                    for (Map<String, Object> obj : list) {
                        Equipe E = Parser.toEquipe(obj);
                        listEquipe.add(E);
                    }
                } catch (IOException | NumberFormatException e) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEquipe;
    }
}
