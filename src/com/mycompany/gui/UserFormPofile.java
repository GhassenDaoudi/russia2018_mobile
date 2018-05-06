/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;

/**
 *
 * @author Ghassen
 */
public class UserFormPofile {
    private Form form;
    
    public UserFormPofile(){
        this.form = new Form("Votre Profile",BoxLayout.y());
        Components.showHamburger(this.form);
        Label nom = new Label(Session.getUser().getNom());
        Label prenom = new Label(Session.getUser().getPrenom());
        Label username = new Label(Session.getUser().getUsername());
        Label email = new Label(Session.getUser().getEmail());
        Label jeton = new Label(String.valueOf(Session.getUser().getJeton()));
        this.form.add(nom);
        this.form.add(prenom);
        this.form.add(username);
        this.form.add(email);
        this.form.add(jeton);
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
    
    
}
