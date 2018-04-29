/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
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
public class ServiceGalerie {
        public static ArrayList<Publication> afficher() {
        ArrayList<Publication> listGalerie = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/galerie/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();                
                try {
                    Map<String, Object> galerie = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) galerie.get("root");
                    for (Map<String, Object> obj : list) {
                        Publication g = Parser.toPublication(obj, Publication.Type.galerie);
                        listGalerie.add(g);
                    }
                } catch (IOException | NumberFormatException e) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listGalerie;
    }

    public static void ajouter(User user, String filePath, String titre, String description) {
        MultipartRequest cr = new MultipartRequest();
        cr.setUrl("http://localhost/RS2018/web/galerie/majouter/"+user.getId()+"/"+titre+"/"+description);
        cr.addResponseListener((e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> json = jsonp.parseJSON(new CharArrayReader(new String(cr.getResponseData()).toCharArray()));
                System.out.println(json);
            } catch (IOException ex) {
            }
        });
        String mime = "image/jpeg";
        try {
            cr.addData("file", filePath, mime);
        } catch (IOException ex) {
        }
        cr.setFilename("file", String.valueOf(System.currentTimeMillis()));
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        cr.setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(cr);

    }

}
