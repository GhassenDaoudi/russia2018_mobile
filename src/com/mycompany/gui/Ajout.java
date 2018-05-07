/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.Entite.EquipeFantasy;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceEquipeFantasy;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import com.mycompany.utils.Stats;

/**
 *
 * @author quickstrikes96
 */
public class Ajout {

    Form f;
    TextField tnom;
    Button btnajout;

    public Ajout() {
        Stats s = new Stats();
        f = new Form("Nom de L'Equipe");
        Components.showHamburger(f);
        tnom = new TextField();
        btnajout = new Button("Valider");
        f.add(tnom);

        f.add(btnajout);
        btnajout.addActionListener((e) -> {
            if (tnom.getText().equals("")) {
                Dialog.show("ERREUR", "LE TITRE NE PEUT PAS ETRE VIDE", "OK", "ANNULER");
            } else {
                ServiceEquipeFantasy ser = new ServiceEquipeFantasy();
                EquipeFantasy eq = new EquipeFantasy(tnom.getText(), 0, 0, Session.getUser(), 0, 0, 100);
                ser.ajoutEquipe(eq);
                Session.setEf(eq);
                // Message m = new Message("Création d'équipe");
                //Display.getInstance().sendMessage(new String[] {"yasseraziz.hajji@esprit.tn"}, "Création d'équipe", m);
                Choix c = new Choix();
                c.getF().show();
            }

        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
