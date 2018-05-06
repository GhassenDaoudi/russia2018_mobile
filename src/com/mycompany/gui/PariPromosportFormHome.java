/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.Form;

import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;

import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.Match;

import com.mycompany.Entite.Pari;
import com.mycompany.Service.ServiceFichePari;
import com.mycompany.Service.ServicePari;
import com.mycompany.Service.ServiceUser;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author skanderbejaoui
 */
public class PariPromosportFormHome {

    private Map<Match, Pari.ResultatPari> mymap = new HashMap<>();
    private Label cote_valeur = new Label("1");
    private Form form;
    private TextField mise_valeur = new TextField();
    private Label gain_valeur = new Label();
    private int test;

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }

    public PariPromosportFormHome() {

        ServiceFichePari SFP = new ServiceFichePari();
        ServicePari SP = new ServicePari();
        this.form = new Form("Promosport", new BoxLayout(BoxLayout.Y_AXIS));
        if (ServicePari.getListPromosport().isEmpty()) {
            Container container_vide = new Container();
            Label vide = new Label("Aucune Fiche Promosport n'existe !");
            container_vide.add(vide);
            Components.showHamburger(this.form);
            this.form.add(container_vide);
            
        }else{
        for (Pari pari : ServicePari.getListPromosport()) {
            mymap.put(pari.getM(), null);
            this.form.add(createPariWidget(pari));
        }
        Components.showHamburger(this.form);
        Container container_mise = new Container(BoxLayout.x());
        Container container_cote = new Container(BoxLayout.x());
        Container container_gain = new Container(BoxLayout.x());
        Label label_mise = new Label("Mise: ");

        Label label_cote = new Label("Cote: ");

        Label label_gain = new Label("Gain");

        Button button_valider = new Button("Valider");
        container_mise.add(label_mise);
        container_mise.add(mise_valeur);
        container_gain.add(label_gain);
        container_gain.add(gain_valeur);
        container_cote.add(label_cote);
        container_cote.add(cote_valeur);
        this.form.add(container_mise);
        this.form.add(container_cote);
        this.form.add(container_gain);
        this.form.add(button_valider);
        this.form.show();
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
                if (!mise_valeur.getText().equals("")) {
                    float cote_event = Float.parseFloat(cote_valeur.getText());
                    float mise_event = Float.parseFloat(mise_valeur.getText());
                    float gain_event = cote_event * mise_event;
                    gain_valeur.setText(String.valueOf(gain_event));
                    form.revalidate();
                }
            }

        });

        button_valider.addActionListener((evt) -> {
            if (test != ServicePari.getListPromosport().size()) {
                Dialog.show("", "Vous devez parier sur tous les matchs !", "Ok", "Cancel");
            } else if (Float.parseFloat(mise_valeur.getText()) <= 0) {
                Dialog.show("", "Insérez une mise et réessayez !", "Ok", "Cancel");
            } else if (Session.getUser().getJeton() < Float.parseFloat(mise_valeur.getText())) {
                Dialog.show("", "Vous ne possédez pas assez de jetons!", "Ok", "Cancel");
            } else {
                FichePari fichepari = new FichePari();
                fichepari.setCote_total(Float.parseFloat(cote_valeur.getText()));
                fichepari.setGain(Float.parseFloat(gain_valeur.getText()));
                fichepari.setMisetotal(Float.parseFloat(mise_valeur.getText()));
                fichepari.setType(2);
                fichepari.setU(Session.getUser());
                fichepari = SFP.AjoutFichePari(fichepari.getCote_total(), fichepari.getU(), fichepari.getMisetotal(), fichepari.getGain(), fichepari.getType());
                ServiceUser.UpdateJetonUser(Session.getUser().getId(), Session.getUser().getJeton() - Float.parseFloat(mise_valeur.getText()));
                for (Map.Entry<Match, Pari.ResultatPari> entry : mymap.entrySet()) {
                    Pari pari = new Pari();
                    pari.setFp(fichepari);
                    pari.setM(entry.getKey());
                    pari.setCote(0);
                    pari.setMise(0);
                    pari.setGain(0f);
                    pari.setResultat(entry.getValue());
                    System.out.println(pari.getM());
                    SP.AjoutPari(pari.getM(), pari.getFp(), pari.getMise(), pari.getGain(), pari.getCote(), pari.getResultat().toString());

                }
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setMessage("Votre fiche promosport a été ajoutée !");
                status.setExpires(3000);
                status.show();
                HistoriquePariFormHome hpfh  = new HistoriquePariFormHome();
                hpfh.getForm().show();
            }
        });
        }
    }

    public SwipeableContainer createPariWidget(Pari p) {
        Container container_titre = new Container(BoxLayout.y());
        Label title = new Label(p.getM().getE1().getNom() + "   vs  " + p.getM().getE2().getNom());
        container_titre.add(title);
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createButtonPari(p)),
                container_titre);
    }

    private Container createButtonPari(Pari p) {

        Container container_button = new Container(BoxLayout.x());

        RadioButton button_cote1 = new RadioButton(String.valueOf(p.getM().getCote_eq1()));
        RadioButton button_cote2 = new RadioButton(String.valueOf(p.getM().getCote_eq2()));
        RadioButton button_cotenul = new RadioButton(String.valueOf(p.getM().getCote_nul()));
        container_button.add(button_cote1);
        container_button.add(button_cotenul);
        container_button.add(button_cote2);
        button_cote1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (button_cote2.isSelected()) {
                    cote_valeur.setText(String.valueOf(diviser_cote(button_cote2, cote_valeur)));

                } else if (button_cotenul.isSelected()) {
                    cote_valeur.setText(String.valueOf(diviser_cote(button_cotenul, cote_valeur)));

                } else {

                    test++;
                }
                mymap.put(p.getM(), Pari.ResultatPari.un);
                cote_valeur.setText(String.valueOf(calcul_cote(button_cote1, cote_valeur)));
                button_cote2.setSelected(false);
                button_cotenul.setSelected(false);
                if (!mise_valeur.getText().equals("")) {
                    float cote_event = Float.parseFloat(cote_valeur.getText());
                    float mise_event = Float.parseFloat(mise_valeur.getText());
                    float gain_event = cote_event * mise_event;
                    gain_valeur.setText(String.valueOf(gain_event));
                    form.revalidate();

                }
                form.revalidate();

            }

        });
        button_cote2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (button_cote1.isSelected()) {
                    cote_valeur.setText(String.valueOf(diviser_cote(button_cote1, cote_valeur)));

                } else if (button_cotenul.isSelected()) {
                    cote_valeur.setText(String.valueOf(diviser_cote(button_cotenul, cote_valeur)));

                } else {

                    test++;
                }
                cote_valeur.setText(String.valueOf(calcul_cote(button_cote2, cote_valeur)));
                if (!mise_valeur.getText().equals("")) {
                    float cote_event = Float.parseFloat(cote_valeur.getText());
                    float mise_event = Float.parseFloat(mise_valeur.getText());
                    float gain_event = cote_event * mise_event;
                    gain_valeur.setText(String.valueOf(gain_event));
                    form.revalidate();
                }
                mymap.put(p.getM(), Pari.ResultatPari.deux);
                button_cote1.setSelected(false);
                button_cotenul.setSelected(false);
                form.revalidate();

            }
        });
        button_cotenul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (button_cote1.isSelected()) {
                    cote_valeur.setText(String.valueOf(diviser_cote(button_cote1, cote_valeur)));

                } else if (button_cote2.isSelected()) {
                    cote_valeur.setText(String.valueOf(diviser_cote(button_cote2, cote_valeur)));

                } else {

                    test++;
                }
                cote_valeur.setText(String.valueOf(calcul_cote(button_cotenul, cote_valeur)));
                if (!mise_valeur.getText().equals("")) {
                    float cote_event = Float.parseFloat(cote_valeur.getText());
                    float mise_event = Float.parseFloat(mise_valeur.getText());
                    float gain_event = cote_event * mise_event;
                    gain_valeur.setText(String.valueOf(gain_event));
                    form.revalidate();
                }
                mymap.put(p.getM(), Pari.ResultatPari.x);
                button_cote2.setSelected(false);
                button_cote1.setSelected(false);
                form.revalidate();

            }
        });
        return container_button;
    }

    public Float calcul_cote(Button button, Label label) {
        float c = Float.parseFloat(label.getText());
        c *= (Float.parseFloat(button.getText()));
        return c;
    }

    public Float diviser_cote(Button button, Label label) {
        float c = Float.parseFloat(label.getText());
        c /= Float.parseFloat((button.getText()));
        return c;
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
