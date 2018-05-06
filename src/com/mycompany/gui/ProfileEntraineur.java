/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Entraineur;
import com.mycompany.Utilitaire.Components;

/**
 *
 * @author elossofa
 */
public class ProfileEntraineur {
    private Form Form ;
    
    public ProfileEntraineur(Entraineur entraineur , Form prev){
        
        Container ContainerEntraineur_1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEntraineur_2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEntraineur_3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        Label Nom = new Label("Nom :");
        Label NomOfEntraineur = new Label(entraineur.getNom());
        
        ContainerEntraineur_1.add(Nom);
        ContainerEntraineur_1.add(NomOfEntraineur);
        
        Label Prenom = new Label("Prenom :");
        Label PrenomOfEntraineur = new Label(entraineur.getPrenom());
        
        ContainerEntraineur_2.add(Prenom);
        ContainerEntraineur_2.add(PrenomOfEntraineur);
        
        Label Description = new Label("Description :");
        Label DescriptionOfEntraineur = new Label(entraineur.getDescription());
        
        ContainerEntraineur_3.add(Description);
        ContainerEntraineur_3.add(DescriptionOfEntraineur);
        
        this.Form = new Form(entraineur.getNom(), BoxLayout.y());
        Components.showBack(this.Form, prev);
        
        this.Form.add(ContainerEntraineur_1);
        this.Form.add(ContainerEntraineur_2);
        this.Form.add(ContainerEntraineur_3);
    }

    public Form getForm() {
        return Form;
    }

    public void setForm(Form Form) {
        this.Form = Form;
    }
}
