/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.Layout;
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
    Button Payer = new Button("Payer");
    public PaiementForm(){
        f.add(Payer);
        Payer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                BrowserComponent web = new BrowserComponent();
                web.setURL(Paiement.payer());
                f.add(web);
                f.show();
               //To change body of generated methods, choose Tools | Templates.
            }
        });
        f.show();
    }
    
}
