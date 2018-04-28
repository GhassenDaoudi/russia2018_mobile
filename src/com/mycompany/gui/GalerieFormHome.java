/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Publication;
import com.mycompany.Service.ServiceGalerie;
import com.mycompany.Utilitaire.Components;
import java.util.List;

/**
 *
 * @author Ghassen
 */
public class GalerieFormHome {

    private Form form;
    private List<Publication> galerie;
    private ServiceGalerie service;

    public GalerieFormHome() {
        this.service = new ServiceGalerie();
        this.form = new Form("Galerie", BoxLayout.y());
        this.galerie = this.service.afficher();
        Components.showHamburger(this.form);
        for (Publication g : this.galerie) {
            String youtube = "https://www.youtube.com/watch?v=";
            //LIEN:<iframe width="560" height="315" src="https://www.youtube.com/embed/0sYaz3TmIO4" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container ctitre = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            Label titre = new Label(g.getTitre());
            ctitre.add(BorderLayout.CENTER, titre);
            c1.add(ctitre);
            if (g.getLien().startsWith("LIEN:")) {
                BrowserComponent bc = new BrowserComponent();
                bc.setPage(g.getLien().substring(5, g.getLien().length()), g.getLien().substring(5, g.getLien().length()));
                c1.add(bc);
            } else {
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(70, 70, 0xffff2700), true);
                Image i = URLImage.createToStorage(placeholder, g.getLien(), "http://localhost/PI/image/" + g.getLien(), URLImage.RESIZE_SCALE_TO_FILL);
                ImageViewer iv = new ImageViewer(i);
                c1.add(iv);
            }
            Container cdescription = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

            SpanLabel description = new SpanLabel(g.getDescription());
            cdescription.add(BorderLayout.CENTER, description);
            c1.add(cdescription);
            this.form.add(c1);
        }

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

}
