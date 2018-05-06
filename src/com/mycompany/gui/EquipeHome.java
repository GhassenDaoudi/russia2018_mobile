/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Equipe;
import com.mycompany.Service.ServiceEquipe;
import com.mycompany.Utilitaire.Components;

/**
 *
 * @author elossofa
 */
public class EquipeHome {
    private Form Form ;

    public EquipeHome() {
        this.Form = new Form("Les Equipes",BoxLayout.y());
        Components.showHamburger(Form);
        for(Equipe E : ServiceEquipe.afficher()){
            Container C = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label Nom = new Label(E.getNom());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100, 100, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, E.getDrapeau(), "http://localhost/PI/image/" + E.getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv = new ImageViewer(i);
            Nom.addPointerPressedListener((e)->{
                ProfileEquipe PE = new ProfileEquipe(E ,this.Form);
                PE.getForm().show();
            });    
            C.add(iv);
            C.add(Nom);
            this.Form.add(C);
        }
        
    }

    public Form getForm() {
        return Form;
    }

    public void setForm(Form Form) {
        this.Form = Form;
    }
    
}
