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
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.User;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author skanderbejaoui
 */
public class ServiceFichePari {

    public FichePari AjoutFichePari(float cotetotale, User user, float misetotale, float gain, int type) {
        FichePari fp = new FichePari();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Match2018/fichepari/" + cotetotale + "/" + user.getId() + "/" + misetotale + "/" + gain + "/" + type);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                System.out.println(new String(con.getResponseData()).toCharArray());
                try {
                    Map<String, Object> ficheparis = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(ficheparis);
                    float idf = Float.parseFloat(ficheparis.get("id").toString());
                    fp.setId((int) idf);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fp;
    }
}
