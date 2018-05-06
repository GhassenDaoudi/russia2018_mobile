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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.Utilitaire.Paiement;

/**
 *
 * @author skanderbejaoui
 */
public class PaiementForm {
    Form f = new Form();

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    public PaiementForm(){
        Button Payer = new Button("Payer");
    TextField quantite = new TextField();
    Container container_paiement = new Container(BoxLayout.x());
    quantite.setHint("Insérez le nombre de jetons souhaité ici");
    Label minimum = new Label("min = 200jetons");
    container_paiement.add(quantite);
    container_paiement.add(minimum);
    f.add(container_paiement);
        f.add(Payer);
        Payer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(Float.parseFloat(quantite.getText())<200){
                    Dialog.show("", "Le minimum est de 200 jetons","Ok","Cancel");
                }
                BrowserComponent web = new BrowserComponent();
                web.setURL(Paiement.payer(Float.parseFloat(quantite.getText())));
                f.add(web);
                f.show();
               //To change body of generated methods, choose Tools | Templates.
            }
        });
        f.show();
    }
    
}
