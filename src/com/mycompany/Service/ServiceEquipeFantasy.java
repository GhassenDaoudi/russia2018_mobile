/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.mycompany.Entite.EquipeFantasy;
import com.mycompany.Utilitaire.Parser;
import com.mycompany.Utilitaire.Session;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author quickstrikes96
 */
public class ServiceEquipeFantasy {
    public static void getEquipe() {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RS2018/web/mobile/get/"+Session.getUser().getId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            JSONParser jsonp = new JSONParser();                
                try {
                    Map<String, Object> jsonEF = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    if(jsonEF.get("ef")!=null){
                        Session.setEf(Parser.toEquipeFantasy(jsonEF.get("ef")));
                    }else{
                        Session.setEf(null);
                    }
                } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void ajoutEquipe(EquipeFantasy eq) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/RS2018/web/mobile/new/"+eq.getNom()+"/"+Session.getUser().getId();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
