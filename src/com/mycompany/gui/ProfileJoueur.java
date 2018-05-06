/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.Entite.Abonnement;
import com.mycompany.Entite.Joueur;
import com.mycompany.Service.ServiceAbonnement;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elossofa
 */
public class ProfileJoueur {
    private Form Form ;
    
    public ProfileJoueur(Joueur joueur ,Form prev,Map<Joueur,ArrayList<Abonnement>> mymap){
        Container ContainerJoueur_1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerJoueur_2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerJoueur_3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerJoueur_4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerJoueur_5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerJoueur_6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container StarContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        Label NomJoueur = new Label(joueur.getNom() + " " + joueur.getPrenom());
        EncodedImage placeholderJoueur = EncodedImage.createFromImage(Image.createImage(100, 100, 0xffff0000), true);
        Image ij = URLImage.createToStorage(placeholderJoueur, joueur.getImage(), "http://localhost/PI/image/" + joueur.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer ivj = new ImageViewer(ij);
        
        ContainerJoueur_1.add(ivj);
        ContainerJoueur_1.add(NomJoueur);
        
        Label Age = new Label("Age :");
        Label AgeOfJoueur = new Label(Integer.toString(joueur.getAge()));
        
        ContainerJoueur_2.add(Age);
        ContainerJoueur_2.add(AgeOfJoueur);
        
        Label Poste = new Label("Poste :");
        Label PosteOfJoueur = new Label(joueur.getPoste());
        
        ContainerJoueur_3.add(Poste);
        ContainerJoueur_3.add(PosteOfJoueur);
        
        Label Numero = new Label("Numero :");
        Label NumeroOfJoueur = new Label(Integer.toString(joueur.getNumero()));
        
        ContainerJoueur_4.add(Numero);
        ContainerJoueur_4.add(NumeroOfJoueur);
        
        Label Club = new Label("Club :");
        Label ClubOfJoueur = new Label(joueur.getClub());
        
        ContainerJoueur_5.add(Club);
        ContainerJoueur_5.add(ClubOfJoueur);
        Button b = null ;
        
        Label Rating = new Label("Rating :");
        ContainerJoueur_6.add(Rating);
        //Map<Joueur,ArrayList<Abonnement>> mymap = new HashMap<Joueur,ArrayList<Abonnement>>(ServiceAbonnement.LesAbonnements());
        if(mymap.containsKey(joueur)){
                if(mymap.containsKey(joueur)){
                for(Abonnement Abo : mymap.get(joueur)){
                    if(Session.getUser().getId() == Abo.getUser().getId()){
                        b = new Button("Se désabonner");
                    }
                }
            }
                int i=0;
                if(mymap.get(joueur) != null)
                    i=mymap.get(joueur).size();
                if(i>5)
                    i=5;
                for(int j=0;j<i;j++){
                    StarContainer.add(this.getStarFull());
                }
                for(int k=0;k<5-i;k++){
                    StarContainer.add(this.getStarEmpty());
                }
            }
        else{
           for(int k=0;k<5;k++){
                    StarContainer.add(this.getStarEmpty());
                } 
        }
            if(b == null){
                b = new Button("S'abonner");
            }           
            b.addActionListener((e)->{                
                ServiceAbonnement.SAbonner(joueur);        
                Form.show();
            });
        
        this.Form = new Form(joueur.getNom(), BoxLayout.y());
        Components.showBack(this.Form, prev);
        
        this.Form.add(ContainerJoueur_1);
        this.Form.add(ContainerJoueur_2);
        this.Form.add(ContainerJoueur_3);
        this.Form.add(ContainerJoueur_4);
        this.Form.add(ContainerJoueur_5);
        this.Form.add(b);
        this.Form.add(ContainerJoueur_6);
        this.Form.add(StarContainer);
        
    }

    public Form getForm() {
        return Form;
    }

    public void setForm(Form Form) {
        this.Form = Form;
    }
    private Image getStarFull(){
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        return fullStar ;
    }
    private Image getStarEmpty(){
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        return emptyStar ;
    }
    
    public SwipeableContainer createRankWidget(String title, String year) {
    MultiButton button = new MultiButton(title);
    button.setTextLine2(year);            
    return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()), 
            button);
    }


    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        //starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        //starRank.setSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
}
