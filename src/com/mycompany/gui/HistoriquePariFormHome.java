/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.FichePari;
import com.mycompany.Service.ServicePari;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import com.mycompany.Utilitaire.StatsPari;

/**
 *
 * @author skanderbejaoui
 */
public class HistoriquePariFormHome {

    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public HistoriquePariFormHome() {
        this.form = new Form("Mes Paris", BoxLayout.y());
        Components.showHamburger(this.form);
        this.form.getToolbar().addCommandToOverflowMenu("Mes stats", null, (x) -> {

            StatsPari SP = new StatsPari();
            Form f2 = new Form();
            f2 = SP.createPieChartForm(this.form);
            f2.show();
        });
        for (FichePari fichepari : ServicePari.getListHistoriquePari(Session.getUser().getId())) {
            Container container_fiche_pari = new Container(BoxLayout.y());
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            String datematch = String.valueOf(newFormat.format(fichepari.getDate()));
            Label titre = new Label("Votre fiche de pari du:    " + datematch);
            Label mise = new Label("Mise:   :" + fichepari.getMisetotal());
            mise.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

            Label cote = new Label("Cote:   " + fichepari.getCote_total());
            cote.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

            Label gain = new Label("Gain:   " + fichepari.getGain());
            gain.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

            Label etat = new Label("Etat:   " + fichepari.getEtat().toString());
            etat.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

            container_fiche_pari.add(titre);
            container_fiche_pari.add(mise);
            container_fiche_pari.add(cote);
            container_fiche_pari.add(gain);
            container_fiche_pari.add(etat);
            if (fichepari.getType() == 0) {
                Label type = new Label("Type:   Simple");
                type.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

                container_fiche_pari.add(type);
            } else if (fichepari.getType() == 1) {
                Label type = new Label("Type:   Multiple");
                type.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

                container_fiche_pari.add(type);
            } else if (fichepari.getType() == 2) {
                Label type = new Label("Type:   Promosport");
                type.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

                container_fiche_pari.add(type);
            }
            titre.addPointerPressedListener((evt) -> {
                ConsulterPariFormHome cpfh = new ConsulterPariFormHome(this.form, fichepari);
                cpfh.getForm().show();
            });
            this.form.add(container_fiche_pari);
        }
    }
}
