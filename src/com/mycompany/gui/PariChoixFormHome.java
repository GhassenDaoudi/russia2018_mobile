/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;

import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.Entite.Match;
import com.mycompany.Entite.Pari;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.PanierPari;


/**
 *
 * @author skanderbejaoui
 */
public class PariChoixFormHome {

    private Form form;

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }

    public PariChoixFormHome(Form previous, Match m) {
        this.form = new Form("Parier", BoxLayout.y());
         Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EURO_SYMBOL, s);
         this.form.getToolbar().addCommandToRightBar("Ma fiche (" + PanierPari.getLp().size() + ")", icon, (e) -> {
             PariValidationPanierFormHome pvpfh = new PariValidationPanierFormHome(this.form);
             pvpfh.getForm().show();
         });
        Components.showBack(this.form, previous);
        Container container_main = new Container(BoxLayout.y());

        Label titre = new Label(m.getE1().getNom() + "  vs  " + m.getE2().getNom());
        Container container_titre = new Container(BoxLayout.x());

        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        Label datematch = new Label(String.valueOf(newFormat.format(m.getDate())));
        Container container_date = new Container(BoxLayout.x());

        RadioButton button_cote1 = new RadioButton(String.valueOf(m.getCote_eq1()));
        RadioButton button_cote2 = new RadioButton(String.valueOf(m.getCote_eq2()));
        RadioButton button_cotenul = new RadioButton(String.valueOf(m.getCote_nul()));
        Container container_button = new Container(BoxLayout.x());

        Label mise = new Label("Mise");
        TextField misevaleur = new TextField();
        misevaleur.setHint("Mise");
        Container container_mise = new Container(BoxLayout.x());

        Label gain = new Label("Gain");
        Label gainvaleur = new Label("0");
        Container container_gain = new Container(BoxLayout.x());

        Button ValiderPanier = new Button("Valider");

        Label resultat = new Label("Resultat");
        Label resultatchoisi = new Label();
        Container container_resultat = new Container(BoxLayout.x());

        container_titre.add(titre);

        container_date.add(datematch);
        container_main.add(container_titre);
        container_main.add(container_date);

        container_button.add(button_cote1);
        container_button.add(button_cotenul);
        container_button.add(button_cote2);
        container_main.add(container_button);
        container_mise.add(mise);
        container_mise.add(misevaleur);
        container_main.add(container_mise);

        container_gain.add(gain);
        container_gain.add(gainvaleur);
        container_main.add(container_gain);

        container_resultat.add(resultat);
        container_resultat.add(resultatchoisi);
        container_main.add(container_resultat);

        container_main.add(ValiderPanier);
        this.form.add(container_main);

        misevaleur.addDataChangedListener((i, ii) -> {
            if (isValidInput(misevaleur.getText())) {
                misevaleur.putClientProperty("LastValid", misevaleur.getText());
            } else {
                misevaleur.stopEditing();
                misevaleur.setText((String) misevaleur.getClientProperty("LastValid"));
                misevaleur.startEditingAsync();
            }
        });

        button_cote1.addActionListener((evt) -> {
            if (misevaleur.getText().equals("")) {
                misevaleur.setText("0");
            }
            float cote = Float.parseFloat(button_cote1.getText());
            float calculmise = Float.parseFloat(misevaleur.getText());
            float calculgain = cote * calculmise;
            gainvaleur.setText(String.valueOf(calculgain));
            resultatchoisi.setText(m.getE1().getNom());
            button_cote2.setSelected(false);
            button_cotenul.setSelected(false);
            form.revalidate();
        });

        button_cote2.addActionListener((evt) -> {
            if (misevaleur.getText().equals("")) {
                misevaleur.setText("0");
            }
            float cote = Float.parseFloat(button_cote2.getText());
            float calculmise = Float.parseFloat(misevaleur.getText());
            float calculgain = cote * calculmise;
            gainvaleur.setText(String.valueOf(calculgain));
            resultatchoisi.setText(m.getE2().getNom());
            button_cote1.setSelected(false);
            button_cotenul.setSelected(false);
            form.revalidate();
        });
        button_cotenul.addActionListener((evt) -> {
            if (misevaleur.getText().equals("")) {
                misevaleur.setText("0");
            }
            float cote = Float.parseFloat(button_cotenul.getText());
            float calculmise = Float.parseFloat(misevaleur.getText());
            float calculgain = cote * calculmise;
            gainvaleur.setText(String.valueOf(calculgain));
            resultatchoisi.setText("nul");
            button_cote1.setSelected(false);
            button_cote2.setSelected(false);
            form.revalidate();
        });

        misevaleur.addDataChangedListener(new textListener() {
            public void dataChanged(int type, int index) {
                if (misevaleur.getText().equals("")) {
                    gainvaleur.setText("0");
                }
                 if ((button_cote1.isSelected() == true)&&(!misevaleur.getText().equals(""))) {
                    float cote_event = Float.parseFloat(button_cote1.getText());
                    float mise_event = Float.parseFloat(misevaleur.getText());
                    float gain_event = cote_event * mise_event;
                    gainvaleur.setText(String.valueOf(gain_event));
                }
                if ((button_cote2.isSelected() == true)&&(!misevaleur.getText().equals(""))) {
                    float cote_event = Float.parseFloat(button_cote2.getText());
                    float mise_event = Float.parseFloat(misevaleur.getText());
                    float gain_event = cote_event * mise_event;
                    gainvaleur.setText(String.valueOf(gain_event));
                }
                if ((button_cotenul.isSelected() == true)&&(!misevaleur.getText().equals(""))) {
                    float cote_event = Float.parseFloat(button_cotenul.getText());
                    float mise_event = Float.parseFloat(misevaleur.getText());
                    float gain_event = cote_event * mise_event;
                    gainvaleur.setText(String.valueOf(gain_event));
                }
                form.revalidate();
            }

        });
        ValiderPanier.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            if((misevaleur.getText().equals(""))){
                                 Dialog.show("Vous n'avez pas misé!", "Entrez une mise et réessayez", "OK", "Cancel");
                            }
                            else if ((Float.parseFloat(misevaleur.getText()) <= 0.0f) && (button_cote1.isSelected() == false) && (button_cote2.isSelected() == false) && (button_cotenul.isSelected() == false)) {
                                Dialog.show("", "Choisissez une côte et une mise pour parier!", "OK", "Cancel");
                            } else if (Float.parseFloat(misevaleur.getText()) <= 0.0f) {
                                Dialog.show("Vous n'avez pas misé!", "Entrez une mise et réessayez", "OK", "Cancel");
                            } else if ((button_cote1.isSelected() == false) && (button_cote2.isSelected() == false) && (button_cotenul.isSelected() == false)) {
                                Dialog.show("", "Choisissez une cote et réessayez", "OK", "Cancel");
                            } else {

                               Pari P = new Pari();

                                P.setEtat(Pari.EtatPari.Encours);
                                P.setGain(Float.parseFloat(gainvaleur.getText()));
                                P.setM(m);
                                P.setMise(Float.parseFloat(misevaleur.getText()));
                                P.setType(1);
                                if (button_cote1.isSelected()) {
                                    P.setCote(Float.parseFloat(button_cote1.getText()));
                                    P.setResultat(Pari.ResultatPari.un);
                                } else if (button_cotenul.isSelected()) {
                                    P.setCote(Float.parseFloat(button_cotenul.getText()));
                                    P.setResultat(Pari.ResultatPari.x);
                                } else if (button_cote2.isSelected()) {
                                    P.setCote(Float.parseFloat(button_cote2.getText()));
                                    P.setResultat(Pari.ResultatPari.deux);
                                }
                                PanierPari.getLp().add(P);
                                PariIndexHome pih = new PariIndexHome();
                                pih.getForm().show();
                               
                            
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
