/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Abonnement;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceAbonnement;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elossofa
 */
public class ProfileEquipe {
    private Form Form ;
    
    public ProfileEquipe(Equipe equipe , Form prev){
        this.Form = new Form("Les Equipes",BoxLayout.y());
        Components.showHamburger(Form);
        Container ContainerEquipe_1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEquipe_2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEquipe_3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEquipe_4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEquipe_5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEquipe_6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEquipe_7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ContainerEquipe_8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ContainerEquipe_9 = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        
        
        Label Nom = new Label(equipe.getNom());
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100, 100, 0xffff0000), true);
        Image i = URLImage.createToStorage(placeholder, equipe.getDrapeau(), "http://localhost/PI/image/" + equipe.getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer iv = new ImageViewer(i);
        ContainerEquipe_1.add(iv);
        ContainerEquipe_1.add(Nom);
        
        Label Progress = new Label("Progress :");
        Progress.getAllStyles().setFgColor(0xff000);
        Label ProgressOfEquipe= new Label(equipe.getProgress().toString());
        ContainerEquipe_2.add(Progress);
        ContainerEquipe_2.add(ProgressOfEquipe);
        
        Label Points = new Label("Points :");
        Points.getAllStyles().setFgColor(0xff000);
        Label PointOfEquipe = new Label(Integer.toString(equipe.getPts()));
        ContainerEquipe_3.add(Points);
        ContainerEquipe_3.add(PointOfEquipe);
        
        Label Qualification = new Label("Qualification : ");
        Qualification.getAllStyles().setFgColor(0xff000);
        Label QualificationOfEquipe = new Label(equipe.getQualification().toString());
        ContainerEquipe_4.add(Qualification);
        ContainerEquipe_4.add(QualificationOfEquipe);
        
        Label NbMatchJoue = new Label("NbMatchJoue :");
        NbMatchJoue.getAllStyles().setFgColor(0xff000);
        Label NbMatchJoueOfEquipe = new Label(Integer.toString(equipe.getNb_match_joue()));
        ContainerEquipe_5.add(NbMatchJoue);
        ContainerEquipe_5.add(NbMatchJoueOfEquipe);
        
        Label Groupe = new Label("Groupe :");
        Groupe.getAllStyles().setFgColor(0xff000);
        Label GroupeOfEquipe = new Label(equipe.getGroupe().getNom());
        ContainerEquipe_6.add(Groupe);
        ContainerEquipe_6.add(GroupeOfEquipe);
        
        Label Entraineur = new Label("Entraineur :") ;
        Entraineur.getAllStyles().setFgColor(0xff000);
        Label EntraineurOfEquipe= new Label(equipe.getEntraineur().getNom() + " " + equipe.getEntraineur().getPrenom());
        EntraineurOfEquipe.addPointerPressedListener((e)->{
                ProfileEntraineur PE = new ProfileEntraineur(equipe.getEntraineur() ,this.Form);
                PE.getForm().show();
            });
        ContainerEquipe_7.add(Entraineur);
        ContainerEquipe_7.add(EntraineurOfEquipe);
        
        Label ToutLesJoueurs = new Label("Les joueurs de "+equipe.getNom());
        ContainerEquipe_9.add(BorderLayout.CENTER,ToutLesJoueurs);
        
        Map<Joueur,ArrayList<Abonnement>> mymap = new HashMap<Joueur,ArrayList<Abonnement>>(ServiceAbonnement.LesAbonnements());
        for(Joueur J : equipe.getListe_joueur()){
            System.out.println("test 1");
            Label NomJoueur = new Label(J.getNom() + " " + J.getPrenom());
            EncodedImage placeholderJoueur = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image ij = URLImage.createToStorage(placeholderJoueur, J.getImage(), "http://localhost/PI/image/" + J.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer ivj = new ImageViewer(ij);
            NomJoueur.addPointerPressedListener((e)->{
                ProfileJoueur PJ = new ProfileJoueur(J ,this.Form,mymap);
                PJ.getForm().show();
            });
            Container CaseJoueur = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Button b = null ;
           // User U = new User() ;
            //U.setId(10);
            //Session.setUser(U);
            if(mymap.containsKey(J)){
                for(Abonnement Abo : mymap.get(J)){
                    if(Session.getUser().getId() == Abo.getUser().getId()){
                        b = new Button("Se désabonner");
                    }
                }
            }
            if(b == null){
                b = new Button("S'abonner");
            }
            b.addActionListener((e)->{  
                ServiceAbonnement.SAbonner(J);
                Form.show();
            });
            CaseJoueur.add(ivj);
            CaseJoueur.add(NomJoueur);
            CaseJoueur.add(b);
            ContainerEquipe_8.add(CaseJoueur);
        }
        
        this.Form = new Form(equipe.getNom(), BoxLayout.y());
        Components.showBack(this.Form, prev);
        this.Form.add(ContainerEquipe_1);
        this.Form.add(ContainerEquipe_2);
        this.Form.add(ContainerEquipe_3);
        this.Form.add(ContainerEquipe_4);
        this.Form.add(ContainerEquipe_5);
        this.Form.add(ContainerEquipe_6);
        this.Form.add(ContainerEquipe_7);
        this.Form.add(ContainerEquipe_9);
        this.Form.add(ContainerEquipe_8);
        FileSystemStorage fs = FileSystemStorage.getInstance();
        //String recordingsDir =fs.getAppHomePath()+"C:\\xampp\\htdocs\\PI\\Musique\\";
        //fs.mkdir(recordingsDir);
        try {
            //Session.setEquipe(equipe);
            //Media m = MediaManager.createMedia("C:\\xampp\\htdocs\\PI\\Musique\\" + equipe.getNom() +".mp3", false);
            //Session.setMedia(MediaManager.createBackgroundMedia("D:\\xampp\\htdocs\\PI\\Musique\\" + Session.getEquipe().getNom() +".mp3"));
            if(Session.getEquipe() == null){
               
                Session.setEquipe(equipe);
                Session.setMedia(MediaManager.createBackgroundMedia("D:\\xampp\\htdocs\\PI\\Musique\\" + Session.getEquipe().getNom() +".mp3"));
                Session.getMedia().play();
            }
            else{              
                Session.getMedia().cleanup();
                Session.setEquipe(equipe);
                Session.setMedia(MediaManager.createBackgroundMedia("D:\\xampp\\htdocs\\PI\\Musique\\" + Session.getEquipe().getNom() +".mp3"));
                Session.getMedia().play();
            }
            } catch(IOException err) {
                Log.e(err);
            }
    }

    public Form getForm() {
        return Form;
    }

    public void setForm(Form Form) {
        this.Form = Form;
    }
}
