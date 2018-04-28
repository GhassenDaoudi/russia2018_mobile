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
import com.mycompany.gui.ArticleFormHome;
import com.mycompany.gui.GalerieFormHome;
import com.mycompany.gui.UserFormLogin;

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
        tb.addMaterialCommandToSideMenu("Galerie", FontImage.MATERIAL_GRADE, e -> {
            GalerieFormHome gfh = new GalerieFormHome();
            gfh.getForm().show();

        });
        if (Session.getUser() == null) {
            tb.addMaterialCommandToSideMenu("Login", FontImage.MATERIAL_GRADE, e -> {
                UserFormLogin ufl = new UserFormLogin();
                ufl.getForm().show();
            });
        } else {
            tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_GRADE, e -> {
                Session.setUser(null);
                ArticleFormHome afh = new ArticleFormHome();
                afh.getForm().show();
            });
        }
    }

    public static void showBack(Form current,Form prev) {
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            prev.showBack();
        });
    }

}
