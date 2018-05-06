/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Service.ServiceUser;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Paiement;
import com.mycompany.Utilitaire.Session;
import com.paydunya.neptune.PaydunyaCheckoutInvoice;

/**
 *
 * @author Ghassen
 */
public class UserFormPofile {

    private Form form;

    public UserFormPofile() {
        this.form = new Form("Votre Profile", BoxLayout.y());
        Components.showHamburger(this.form);
        Label nom = new Label("Nom: " + Session.getUser().getNom());
        Label prenom = new Label("Prenom: " + Session.getUser().getPrenom());
        Label username = new Label("Prenom: " + Session.getUser().getUsername());
        Label email = new Label("Email: " + Session.getUser().getEmail());
        Label jeton = new Label("Jeton :" + String.valueOf(Session.getUser().getJeton()));
        Button p = new Button("Charger des Jetons");

        ///
        Button Payer = new Button("Payer");
        TextField quantite = new TextField();
        Container container_paiement = new Container(BoxLayout.y());
        quantite.setHint("Jeton: min 200 j");
        container_paiement.add(quantite);
        container_paiement.add(Payer);
        container_paiement.setVisible(false);
        //f.add(container_paiement);
        //f.add(Payer);
        Payer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (Float.parseFloat(quantite.getText()) < 200) {
                    Dialog.show("", "Le minimum est de 200 jetons", "Ok", "Cancel");
                }
                BrowserComponent web = new BrowserComponent();
                PaydunyaCheckoutInvoice invoice = Paiement.payer(Float.parseFloat(quantite.getText()));
                float f = Float.parseFloat(quantite.getText());
                web.setURL(invoice.getInvoiceUrl());
                container_paiement.add(web);
                form.revalidate();
                Thread t = new Thread(() -> {
                    boolean bool = true;
                    while (bool) {
                        if (invoice.confirm(invoice.token)) {
                            ServiceUser.UpdateJetonUser(Session.getUser().getId(), Session.getUser().getJeton() + f);
                            bool = false;
                            Display.getInstance().callSerially(() -> {
                                container_paiement.removeAll();
                                jeton.setText(String.valueOf(Session.getUser().getJeton()));
                                form.revalidate();
                            });

                        }
                    }
                });
                t.start();

            }
        });

        p.addActionListener((l) -> {
            container_paiement.setVisible(true);
            form.revalidate();
            //PaiementForm pf = new PaiementForm();
            //pf.getF().show();
        });

        this.form.add(nom);
        this.form.add(prenom);
        this.form.add(username);
        this.form.add(email);
        this.form.add(jeton);
        this.form.add(p);
        this.form.add(container_paiement);
    }

    private void check(PaydunyaCheckoutInvoice invoice, float f) {

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

}
