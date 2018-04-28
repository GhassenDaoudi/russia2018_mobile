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
import com.mycompany.Entite.Publication;
import com.mycompany.Entite.User;
import com.mycompany.Utilitaire.Parser;
import com.mycompany.Utilitaire.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghassen
 */
public class ServiceUser {
    public static void login(String username,String password){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/user/login/"+password+"/"+username);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();                
                try {
                    Map<String, Object> jsonUser = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    if(jsonUser.get("user")!=null){
                        Session.setUser(Parser.toUser(jsonUser.get("user")));
                    }else{
                        Session.setUser(null);
                    }
                } catch (IOException | NumberFormatException e) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public static List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/user/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> jsonUsers = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) jsonUsers.get("root");
                    for (Map<String, Object> obj : list) {
                        User g = Parser.toUser(obj);
                        users.add(g);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return users;
    }
}
