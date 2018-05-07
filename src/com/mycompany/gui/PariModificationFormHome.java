/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Pari;
import com.mycompany.Utilitaire.Components;

import com.mycompany.Utilitaire.PanierPari;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author skanderbejaoui
 */
public class PariModificationFormHome {

    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    PariModificationFormHome(Form previous, Pari p) {
        List<Pari> pari_remove = new ArrayList<>();
        this.form = new Form(BoxLayout.y());
        Components.showBack(form, previous);

        Label titre = new Label("Match:    " + p.getM().getE1().getNom() + " vs  " + p.getM().getE2().getNom());
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        Label datematch = new Label("Date du match: " + String.valueOf(newFormat.format(p.getM().getDate())));
        Button ValiderModif = new Button("Valider");
        ValiderModif.setVisible(false);
        Container container_mise = new Container(BoxLayout.x());
        Container container_cote = new Container(BoxLayout.x());
        Container container_gain = new Container(BoxLayout.x());
        Label mise_text = new Label("Mise   :");
        TextField mise_valeur = new TextField();
        mise_valeur.setEditable(false);
        container_mise.add(mise_text);
        container_mise.add(mise_valeur);
        Label cote_text = new Label("Cote:  ");
        Label cote_valeur = new Label(String.valueOf(p.getCote()));
        container_cote.add(cote_text);
        container_cote.add(cote_valeur);
        Label gain_text = new Label("Gain:  ");
        Label gain_valeur = new Label(String.valueOf(p.getGain()));
        container_cote.add(gain_text);
        container_cote.add(gain_valeur);
        this.form.add(titre);
        this.form.add(datematch);
        this.form.add(container_mise);
        this.form.add(container_cote);
        this.form.add(container_gain);
        this.form.add(ValiderModif);
        mise_valeur.setText(String.valueOf(p.getMise()));
        this.form.getToolbar().addCommandToOverflowMenu("Modifier la mise", null, (x) -> {
            mise_valeur.setEditable(true);
            ValiderModif.setVisible(true);
        });
        this.form.getToolbar().addCommandToOverflowMenu("Annuler le pari", null, (x) -> {
            for (int i = PanierPari.getLp().size() - 1; i >= 0; i--) {
                if (PanierPari.getLp().get(i).getM().getId() == p.getM().getId() && (PanierPari.getLp().get(i).getCote() == p.getCote()) && (PanierPari.getLp().get(i).getMise() == p.getMise())) {
                    PanierPari.getLp().remove(i);
                }
            }
            PariIndexHome pih = new PariIndexHome();
            pih.getForm().show();
        });

        ValiderModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                for (Pari pari : PanierPari.getLp()) {
                    if (pari.getM().getId() == p.getM().getId() && (pari.getCote() == p.getCote()) && (pari.getMise() == p.getMise())) {
                        pari.setMise(Float.parseFloat(mise_valeur.getText()));
                        pari.setGain(pari.getCote() * pari.getMise());
                    }
                }
                PariValidationPanierFormHome pvpfh = new PariValidationPanierFormHome(form);
                pvpfh.getForm().revalidate();
                pvpfh.getForm().show();
            }

        });
         mise_valeur.addDataChangedListener((i, ii) -> {
            if (isValidInput(mise_valeur.getText())) {
                mise_valeur.putClientProperty("LastValid", mise_valeur.getText());
            } else {
                mise_valeur.stopEditing();
                mise_valeur.setText((String) mise_valeur.getClientProperty("LastValid"));
                mise_valeur.startEditingAsync();
            }
        });
        mise_valeur.addDataChangedListener(new textListener() {
            public void dataChanged(int type, int index) {
                if (mise_valeur.getText().equals("")) {
                    gain_valeur.setText("0");
                }
                if(!mise_valeur.getText().equals("")){
                float cote_event = p.getCote();
                    float mise_event = Float.parseFloat(mise_valeur.getText());
                    float gain_event = cote_event * mise_event;
                    gain_valeur.setText(String.valueOf(gain_event));
                form.revalidate();
                }
            }

        });
        
         

    }
    
     public boolean isValidInput(String input) {

        if (input.contains("a") || input.contains("b") || input.contains("c")
                || input.contains("d") || input.contains("e") || input.contains("f")
                || input.contains("g") || input.contains("h") || input.contains("i")
                || input.contains("j") || input.contains("k") || input.contains("l")
                || input.contains("m") || input.contains("n") || input.contains("o")
                || input.contains("p") || input.contains("q") || input.contains("r")
                || input.contains("s") || input.contains("t") || input.contains("u")
                || input.contains("v") || input.contains("w") || input.contains("x")
                || input.contains("y") || input.contains("z")) {
            return false;
        } else {
            return true;
        }
    }

    private abstract class textListener implements DataChangedListener {

    }
}
