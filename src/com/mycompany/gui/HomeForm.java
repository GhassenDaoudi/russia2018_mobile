/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;




/**
 *
 * @author hseli
 */
public class HomeForm {
     Form hi;
     Resources theme;
     Toolbar tb;
     Image icon;
     Container topBar;
    
    public HomeForm(){
        hi=new Form("Bienvenue");
        tb=hi.getToolbar();
        theme = UIManager.initFirstTheme("/theme");
        icon= theme.getImage("logoo.png");
        topBar=BorderLayout.centerAbsolute(new Label(icon));
        //topBar.add(BorderLayout.SOUTH,new Label("Bienvenue","SideMenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,e->{
        CountForm C=new CountForm();
        C.getF().show();
        } );
        tb.addMaterialCommandToSideMenu("Matchs",FontImage.MATERIAL_GRADE,e->{
        MatchsForm Ma=new MatchsForm();
        Ma.getM().show();
        } );
        tb.addMaterialCommandToSideMenu("Groupes",FontImage.MATERIAL_GROUP,e->{
        GroupesForm Gr=new GroupesForm();
        Gr.getF().show();
        } );
        tb.addMaterialCommandToSideMenu("Stades",FontImage.MATERIAL_LOCATION_ON,e->{
        StadesForm st=new StadesForm();
        st.getF().show();
        } );
        tb.addMaterialCommandToSideMenu("Phase Finale",FontImage.MATERIAL_BLUR_ON,e->{
        PHFForm P=new PHFForm();
        P.getF().show();
        } );
        
        Button a=new Button("Telecharger le calendrier");
        a.addActionListener(e->{
            MultipartRequest request = new MultipartRequest();
            request.setUrl("https://ics.fixtur.es/v2/league/fifa-2018-russia.ics");
            
                request.addData("fifa-2018-russia.ics",FileSystemStorage.getInstance().getAppHomePath().getBytes(), "text/plain");
                System.out.println(request);
                System.out.println(FileSystemStorage.getInstance().getAppHomePath());
            try {
                Util.copy(FileSystemStorage.getInstance().openInputStream(FileSystemStorage.getInstance().getAppHomePath()), Storage.getInstance().createOutputStream("aaa.ics"));
            } catch (IOException ex) {
                System.out.println("non");
            }
                System.out.println("nn");
            
            NetworkManager.getInstance().addToQueue(request);
        });
        hi.addComponent(new Label("Russia 2018"));
        hi.add(a);
    }

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }
    
    
}
