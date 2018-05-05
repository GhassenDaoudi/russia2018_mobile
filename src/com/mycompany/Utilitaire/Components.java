/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.Storage;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ArticleFormHome;
import com.mycompany.gui.ChatFormHome;
import com.mycompany.gui.CountForm;
import com.mycompany.gui.GalerieFormHome;
import com.mycompany.gui.GroupesForm;
import com.mycompany.gui.MatchsForm;
import com.mycompany.gui.PHFForm;
import com.mycompany.gui.PariFormHome;
import com.mycompany.gui.StadesForm;
import com.mycompany.gui.UserFormLogin;
import com.mycompany.gui.UserFormPofile;

/**
 *
 * @author Ghassen
 */
public class Components {

    public static void showHamburger(Form current) {
        Toolbar tb = current.getToolbar();
        Resources theme = UIManager.initFirstTheme("/theme");
        Image icon= theme.getImage("logoo.png");
        Container topBar = BorderLayout.centerAbsolute(new Label(icon));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            ArticleFormHome afh = new ArticleFormHome();
            dlg.dispose();
            afh.getForm().show();
        });
        tb.addMaterialCommandToSideMenu("Galerie", FontImage.MATERIAL_IMAGE, e -> {
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            GalerieFormHome gfh = new GalerieFormHome();
            dlg.dispose();
            gfh.getForm().show();

        });
        /*tb.addMaterialCommandToSideMenu("Count", FontImage.MATERIAL_HOME, e -> {
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            CountForm C = new CountForm();
            dlg.dispose();
            C.getF().show();
        });*/
        tb.addMaterialCommandToSideMenu("Matchs", FontImage.MATERIAL_GRADE, e -> {
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            MatchsForm Ma = new MatchsForm();
            dlg.dispose();
            Ma.getM().show();
        });
        tb.addMaterialCommandToSideMenu("Groupes", FontImage.MATERIAL_GROUP, e -> {
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            GroupesForm Gr = new GroupesForm();
            dlg.dispose();
            Gr.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Stades", FontImage.MATERIAL_LOCATION_ON, e -> {
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            StadesForm st = new StadesForm();
            dlg.dispose();
            st.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Phase Finale", FontImage.MATERIAL_BLUR_ON, e -> {
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            PHFForm P = new PHFForm();
            dlg.dispose();
            P.getF().show();
        });

        if (Session.getUser() == null) {
            tb.addMaterialCommandToSideMenu("Login", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                UserFormLogin ufl = new UserFormLogin();
                dlg.dispose();
                ufl.getForm().show();
            });
        } else {
            tb.addMaterialCommandToSideMenu("Chat", FontImage.MATERIAL_PEOPLE, e -> {
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                ChatFormHome cfh = new ChatFormHome();
                dlg.dispose();
                cfh.getForm().show();

            });
            tb.addMaterialCommandToSideMenu("Parier !", FontImage.MATERIAL_EURO_SYMBOL, e -> {
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                PariFormHome pfh = new PariFormHome();
                dlg.dispose();
                pfh.getF().show();
            });
            tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_UPDATE, e -> {
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                UserFormPofile ufp = new UserFormPofile();
                dlg.dispose();
                ufp.getForm().show();
            });
            tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_REMOVE, e -> {
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                Storage.getInstance().writeObject("idUser", "-1");
                Session.setUser(null);
                ArticleFormHome afh = new ArticleFormHome();
                dlg.dispose();
                afh.getForm().show();
            });
        }
    }

    public static void showBack(Form current, Form prev) {
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            prev.showBack();
        });
    }

}
