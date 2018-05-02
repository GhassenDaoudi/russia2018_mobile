/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Publication;
import com.mycompany.Service.ServiceCommentaire;
import com.mycompany.Service.ServiceGalerie;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.List;

/**
 *
 * @author Ghassen
 */
public class GalerieFormHome {

    private Form form;
    private List<Publication> galerie;
    private String filePath;
    private Resources theme;

    public GalerieFormHome() {
        theme = UIManager.initFirstTheme("/theme");
        this.filePath = "";
        this.form = new Form("Galerie", BoxLayout.y());
        this.galerie = ServiceGalerie.afficher();
        Components.showHamburger(this.form);
        if (Session.getUser() != null) {
            TextField titre = new TextField();
            titre.setHint("titre");
            TextField description = new TextField();
            description.setHint("description");
            Button image = new Button("image");
            Button ajouter = new Button("ajouter");
            this.form.add(titre);
            this.form.add(description);

            this.form.add(image);
            this.form.add(ajouter);

            image.addPointerPressedListener((e) -> {
                filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            });
            ajouter.addPointerPressedListener((t) -> {
                if (!filePath.equals("") && !titre.getText().equals("") && !description.getText().equals("")) {
                    ServiceGalerie.ajouter(Session.getUser(), filePath, titre.getText(), description.getText());
                }
            });
        }
        this.galerie = ServiceCommentaire.getLikeDislike(this.galerie);
        for (Publication g : this.galerie) {
            this.form.add(createLikeDislikeWidget(g));
        }

    }

    public SwipeableContainer createLikeDislikeWidget(Publication g) {
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
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(200, 200, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, g.getLien(), "http://localhost/PI/image/" + g.getLien(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv = new ImageViewer(i);

            c1.add(iv);
        }
        Container cdescription = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

        SpanLabel description = new SpanLabel(g.getDescription());
        cdescription.add(BorderLayout.CENTER, description);
        c1.add(cdescription);
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createLikeDislikeContainer(g)),
                c1);
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    private Container createLikeDislikeContainer(Publication g) {
        Container test = new Container();
        Label labelLike = new Label(String.valueOf(g.getLiked()));
        Label labeDislike = new Label(String.valueOf(g.getDisliked()));
        Image likeblue = theme.getImage("SYSTEM_LikeBlue.png");
        Image likeback = theme.getImage("SYSTEM_LikeBlack.png");
        Image dislikered = theme.getImage("SYSTEM_DislikeRed.png");
        Image dislikeback = theme.getImage("SYSTEM_DislikeBlack.png");
        Button like = new Button();
        like.setUIID("Container");
        like.setIcon(likeblue);

        Button dislike = new Button();
        dislike.setUIID("Container");
        dislike.setIcon(dislikered);

        if (Session.getUser() != null) {
            like.addActionListener((e) -> {
                List<Integer> p=ServiceCommentaire.ajouterCommentaire("like",Session.getUser(),g);
                labelLike.setText(p.get(0).toString());
                labeDislike.setText(p.get(1).toString());
                form.revalidate();
                //form.show();
            });
            dislike.addActionListener((e) -> {
                List<Integer> p=ServiceCommentaire.ajouterCommentaire("dislike",Session.getUser(),g);
                labelLike.setText(p.get(0).toString());
                labeDislike.setText(p.get(1).toString());
                form.revalidate();
            });
        }

        test.add(labelLike).add(like).add(dislike).add(labeDislike);
        //s.setOpacity(100);
        //s.setFgColor(0);
        //initStarRankStyle(starRank.getSliderEmptySelectedStyle(), likeblue);
        //initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), likeback);
        //initStarRankStyle(starRank.getSliderFullSelectedStyle(), dislikeback);
        //initStarRankStyle(starRank.getSliderFullUnselectedStyle(), dislikered);
        //starRank.setPreferredSize(new Dimension(likeback.getWidth() * 2, likeback.getHeight()));

        return test;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

}
