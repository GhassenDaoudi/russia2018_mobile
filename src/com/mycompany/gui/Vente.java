/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.table.TableLayout;
import com.mycompany.Entite.JoueurFantasy;
import com.mycompany.Service.ServiceJoueurFantasy;
import com.mycompany.Utilitaire.Components;
import java.util.ArrayList;

/**
 *
 * @author quickstrikes96
 */
public class Vente {

    Form f1;
    Button btnsell;

    public Vente(Form prev) {

        f1 = new Form("My Team", new TableLayout(25, 4));
        Toolbar tb = f1.getToolbar();
        Components.showBack(f1, prev);
        ServiceJoueurFantasy SJ = new ServiceJoueurFantasy();

        ArrayList<JoueurFantasy> LJF = SJ.afficherJoueurs();
        for (JoueurFantasy joueur : LJF) {
            // Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(60, 60, 0xffff2700), true);
            Image i4 = URLImage.createToStorage(placeholder1, joueur.getJoueur().getImage(), "http://localhost/PI/image/" + joueur.getJoueur().getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv4 = new ImageViewer(i4);
            Label spn = new Label(joueur.getJoueur().getNom() + " " + joueur.getJoueur().getPrenom());
            spn.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            //Label sp4 = new Label(String.valueOf(joueur.getJoueur().getPosteF()));
            Label sp5 = new Label(String.valueOf(joueur.getPoints()));
            sp5.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            btnsell = new Button("Vendre");
            f1.add(iv4);
            f1.add(spn);
            //f1.add(sp4);
            f1.add(sp5);
            f1.add(btnsell);
            btnsell.addActionListener((e) -> {
                ServiceJoueurFantasy ser = new ServiceJoueurFantasy();
                ser.vendreJoueur(joueur);
                Choix c = new Choix();
                c.getF().show();

            });
        }

    }

    public Form getF() {
        return f1;
    }

    public void setF(Form f) {
        this.f1 = f;
    }

}
