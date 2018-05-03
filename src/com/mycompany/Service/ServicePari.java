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
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.Match;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author skanderbejaoui
 */
public class ServicePari {

    public ArrayList<Match> getList() {
        ArrayList<Match> listMatchs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Match2018/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> matchs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchs.get("root");
                    for (Map<String, Object> obj : list) {
                        Map<String, Object> Equipe1 = (Map<String, Object>) obj.get("idEquipe1");
                        Map<String, Object> Equipe2 = (Map<String, Object>) obj.get("idEquipe2");
                        Map<String, Object> Date = (Map<String, Object>) obj.get("date");
                        /* DateFormat date = new SimpleDateFormat();
                        date = (DateFormat) Date.get("timestamp");*/

                        Equipe E1 = new Equipe();
                        Equipe E2 = new Equipe();
                        Match match = new Match();
                        float id = Float.parseFloat(obj.get("id").toString());
                        match.setId((int) id);
                        match.setCote_eq1(Float.parseFloat(obj.get("coteEq1").toString()));
                        match.setCote_eq2(Float.parseFloat(obj.get("coteEq2").toString()));
                        match.setCote_nul(Float.parseFloat(obj.get("coteNul").toString()));
                        E1.setNom(Equipe1.get("nom").toString());
                        E1.setDrapeau(Equipe1.get("drapeau").toString());
                        E2.setDrapeau(Equipe2.get("drapeau").toString());
                        E2.setNom(Equipe2.get("nom").toString());
                        match.setE1(E1);
                        match.setE2(E2);
                        // task.setNom(obj.get("name").toString());
                        listMatchs.add(match);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMatchs;
    }

    public void AjoutPari(Match match, FichePari fp, float mise, float gain, float cote, String resultat) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Match2018/pari/" + match.getId() + "/" + fp.getId() + "/" + mise + "/" + gain + "/" + cote + "/" + resultat);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   

}
