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
import com.mycompany.Entite.Joueur;
import com.mycompany.Utilitaire.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quickstrikes96
 */
public class ServiceJoueur {
    
    public ArrayList<Joueur> afficherJoueurs() {
        ArrayList<Joueur> listJoueurs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/mobile/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listJoueurs = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> joueurs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) joueurs.get("root");
                    for (Map<String, Object> obj : list) {  
                        Joueur joueur = new Joueur();
                        
                        joueur.setId((int)Float.parseFloat(obj.get("id").toString()));
                        joueur.setImage(obj.get("image").toString());
                        joueur.setNom(obj.get("nom").toString());
                        joueur.setPrenom(obj.get("prenom").toString());
                        joueur.setEquipe(Parser.toEquipe2(obj.get("idEquipe")));
                        joueur.setPosteF(Joueur.posteF.valueOf(obj.get("postef").toString()));
                        joueur.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        listJoueurs.add(joueur);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJoueurs;
    }
    
}
