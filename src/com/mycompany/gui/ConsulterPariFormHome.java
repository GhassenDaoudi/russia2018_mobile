/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.Pari;
import com.mycompany.Service.ServicePari;
import com.mycompany.Utilitaire.Components;

/**
 *
 * @author skanderbejaoui
 */
public class ConsulterPariFormHome {

    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public ConsulterPariFormHome(Form previous, FichePari fp) {
        this.form = new Form(BoxLayout.y());
        Container container_main = new Container(BoxLayout.y());
         Components.showBack(this.form, previous);
        for (Pari p : ServicePari.getPariparFiche(fp.getId())) {

            Label titre = new Label("Match: " + p.getM().getE1().getNom() + "   vs  " + p.getM().getE2().getNom());
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            Label datematch = new Label("Date :" + String.valueOf(newFormat.format(p.getM().getDate())));
            container_main.add(titre);
            container_main.add(datematch);
            if (p.getResultat().equals(Pari.ResultatPari.un)) {
                Label resultat = new Label("Resultat   : " + p.getM().getE1().getNom());
                container_main.add(resultat);
            }
            else if (p.getResultat().equals(Pari.ResultatPari.x)) {
                Label resultat = new Label("Resultat   : Nul");
                container_main.add(resultat);
            }
            else if (p.getResultat().equals(Pari.ResultatPari.deux)) {
                Label resultat = new Label("Resultat   : " + p.getM().getE2().getNom());
                container_main.add(resultat);
            }
            
           
        }
         this.form.add(container_main);
    }
}
