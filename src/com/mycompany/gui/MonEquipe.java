/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.table.TableLayout;
import com.mycompany.Entite.JoueurFantasy;
import com.mycompany.Service.ServiceJoueurFantasy;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.ArrayList;

/**
 *
 * @author quickstrikes96
 */
public class MonEquipe {

    Form f1;

    public MonEquipe() {
        f1 = new Form(Session.getEf().getNom(), new TableLayout(15, 3));
        Components.showHamburger(f1);
        Toolbar tb = f1.getToolbar();
        tb.addCommandToOverflowMenu("Transfers", null, e -> {
            Vente v = new Vente(f1);
            v.getF().show();

        });
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
            f1.add(iv4);
            f1.add(spn);
            //f1.add(sp4);
            f1.add(sp5);
        }
    }

    public Form getF() {
        return f1;
    }

    public void setF(Form f) {
        this.f1 = f;
    }
}
