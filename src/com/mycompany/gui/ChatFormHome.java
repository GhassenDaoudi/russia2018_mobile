/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.Service.ServiceChat;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.List;

/**
 *
 * @author Ghassen
 */
public class ChatFormHome {

    private Form form;

    public ChatFormHome() {
        this.form = new Form("Tcha",new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        Components.showHamburger(this.form);

        Container chatContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        updateChat(chatContainer);
        UITimer u = new UITimer(() -> {
            Display.getInstance().callSerially(() -> {
                updateChat(chatContainer);
                this.form.revalidate();
            });

        });
        u.schedule(1000, true, form);
        Container main = new Container(BoxLayout.y());
        TextField input = new TextField();
        input.setHint("chat");
        Button send = new Button("Send");
        main.add(input).add(send);
        Container inputContainer = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));

        inputContainer.add(BorderLayout.SOUTH,main);
        send.addActionListener((a)->{
            if(!input.getText().equals("")){
                ServiceChat.send(Session.getUser(),input.getText());
                input.setText("");
                form.revalidate();
            }
        });
        this.form.add(BorderLayout.NORTH,chatContainer);
        this.form.add(BorderLayout.SOUTH,inputContainer);

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    private void updateChat(Container chatContainer) {
        chatContainer.removeAll();
        List<String> chat = ServiceChat.removeHtml();
        if(chat.size()>8){
            chat=chat.subList(chat.size()-8, chat.size());
        }
        for (String string : chat) {
            Label message = new Label(string);
            //System.out.println(message.getPreferredH());
            chatContainer.add(message);
        }
    }

}
