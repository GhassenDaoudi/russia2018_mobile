/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.Service.ServicePari;
import com.mycompany.gui.ArticleFormHome;
import com.mycompany.gui.GalerieFormHome;
import com.mycompany.gui.HistoriquePariFormHome;
import com.mycompany.gui.PaiementForm;
import com.mycompany.gui.PariIndexHome;
import com.mycompany.gui.PariPromosportFormHome;

import com.mycompany.gui.UserFormLogin;
import com.mycompany.gui.UserFormPofile;

/**
 *
 * @author Ghassen
 */
public class Components {

    public static void showHamburger(Form current) {
        Toolbar tb = current.getToolbar();
        Container topBar = BorderLayout.centerAbsolute(new Label("Russia 2018"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            ArticleFormHome afh = new ArticleFormHome();
            afh.getForm().show();
        });
        tb.addMaterialCommandToSideMenu("Galerie", FontImage.MATERIAL_IMAGE, e -> {
            GalerieFormHome gfh = new GalerieFormHome();
            gfh.getForm().show();

        });
        if (Session.getUser() == null) {
            tb.addMaterialCommandToSideMenu("Login", FontImage.MATERIAL_SAVE, e -> {
                UserFormLogin ufl = new UserFormLogin();
                ufl.getForm().show();
            });
        } else {
            tb.addMaterialCommandToSideMenu("Parier !", FontImage.MATERIAL_EURO_SYMBOL, e -> {
                PariIndexHome pih = new PariIndexHome();
                pih.getForm().show();
            });
            
              tb.addMaterialCommandToSideMenu("Promosport !", FontImage.MATERIAL_EURO_SYMBOL, e -> {
                  PariPromosportFormHome ppsh= new PariPromosportFormHome();
                  ppsh.getF().show();
            });
              tb.addMaterialCommandToSideMenu("Mes paris", FontImage.MATERIAL_EURO_SYMBOL, e -> {
                  HistoriquePariFormHome hpfh = new HistoriquePariFormHome();
                  hpfh.getForm().show();
                 
            });
            tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_UPDATE, e -> {
                UserFormPofile ufp= new UserFormPofile();
                ufp.getForm().show();
            });
            tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_REMOVE, e -> {
                //DataBase db = new DataBase();
                //db.clearUser();
                Session.setUser(null);
                ArticleFormHome afh = new ArticleFormHome();
                afh.getForm().show();
            });
        }
    }

    public static void showBack(Form current,Form prev) {
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            
            prev.revalidate();
            prev.showBack();
        });
    }

}
