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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Commentaire;
import com.mycompany.Entite.Publication;
import com.mycompany.Entite.User;
import com.mycompany.Utilitaire.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghassen
 */
public class ServiceCommentaire {

    public static ArrayList<Commentaire> afficher(int id) {
        ArrayList<Commentaire> listComm = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/commentaire/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> comms = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) comms.get("root");
                    for (Map<String, Object> obj : list) {
                        Commentaire c = Parser.toCommentaire(obj);
                        listComm.add(c);
                    }
                } catch (IOException | NumberFormatException e) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComm;
    }

    public static void ajouter(User user,Publication p, TextField input) {
        ConnectionRequest con = new ConnectionRequest();
        String test = "http://localhost/RS2018/web/commentaire/majouter/" + user.getId() + "/" + p.getId() + "/" + input.getText();
        System.out.println(test);
        con.setUrl(test);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
