/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import com.mycompany.Entite.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ghassen
 */
public class ServiceChat {

    private static List<String> readChat() {
        List<String> lines = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String test = "http://localhost/RS2018/web/log.html";
        con.setUrl(test);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent e) {
                JSONParser jsonp = new JSONParser();
                try {
                    String message = new String(con.getResponseData(), "UTF-8");
                    while (true) {
                        String str = destroyChat(0, message);
                        message = message.substring(str.length());
                        lines.add(str);
                        if (message.length() == 0) {
                            break;
                        }
                    }
                } catch (IOException ex) {
                }
            }

            private String destroyChat(int debut, String message) {
                String newLine = "";
                int len = message.length();
                for (int i = debut; i < len; i++) {
                    if (message.charAt(i) == '<'
                            && message.charAt(i + 1) == '/'
                            && message.charAt(i + 2) == 'd'
                            && message.charAt(i + 3) == 'i'
                            && message.charAt(i + 4) == 'v'
                            && message.charAt(i + 5) == '>') {
                        newLine = message.substring(debut, i + 6);

                        break;
                    } else {
                    }
                }
                return newLine;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lines;
    }
    public static List<String> removeHtml(){
        List<String> tmp = new ArrayList<>();
        for (String line : readChat()) {
            line = StringUtil.replaceAll(line, "<div class='msgln'>", "");
            line = StringUtil.replaceAll(line, "<br></div>", "");
            line = StringUtil.replaceAll(line, "<b>", "");
            line = StringUtil.replaceAll(line, "</b>", "");
            tmp.add(line);
        }
        return tmp;
    }

    public static void send(User user, String text) {
        ConnectionRequest con = new ConnectionRequest();
        String test = "http://localhost/RS2018/web/mchat/"+user.getUsername()+"/"+text;
        con.setUrl(test);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
