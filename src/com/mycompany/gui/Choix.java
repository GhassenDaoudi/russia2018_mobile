/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.JoueurFantasy;
import com.mycompany.Service.ServiceEquipe;
import com.mycompany.Service.ServiceJoueur;
import com.mycompany.Service.ServiceJoueurFantasy;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.ArrayList;

/**
 *
 * @author quickstrikes96
 */
public class Choix {

    Form f2;
    Button btnadd;

    public Choix() {
        f2 = new Form("Equipes Nationales", BoxLayout.y());
        Components.showHamburger(f2);
        ServiceEquipe SE = new ServiceEquipe();
        ServiceJoueur SJ = new ServiceJoueur();
        //f2 = new Form("Joueurs",new TableLayout(74,4));

        ArrayList<Equipe> LE = SE.afficherEquipes();
        for (Equipe equipe : LE) {
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(60, 60, 0xffff2700), true);
            Image i3 = URLImage.createToStorage(placeholder1, equipe.getDrapeau(), "http://localhost/PI/Flags/" + equipe.getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv3 = new ImageViewer(i3);
            Label sp = new Label(equipe.getNom());
            C1.setLeadComponent(sp);
            ArrayList<Joueur> LJ = SJ.afficherJoueurs();
            sp.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form f1 = new Form(equipe.getNom(), new TableLayout(25, 4));
                    Toolbar tb = f1.getToolbar();
                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                        f2.showBack();
                    });

                    for (Joueur joueur : LJ) {
                        if (joueur.getEquipe().getNom().equals(equipe.getNom())) {
                            //if(joueur.getPosteF().equals(Joueur.posteF.Gardien)){
                            // Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            // Label poste = new Label("Gardiens");
                            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(60, 60, 0xffff2700), true);
                            Image i4 = URLImage.createToStorage(placeholder1, joueur.getImage(), "http://localhost/PI/image/" + joueur.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
                            ImageViewer iv4 = new ImageViewer(i4);
                            Label spn = new Label(joueur.getNom() + " " + joueur.getPrenom());
                            spn.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                            //Label sp4 = new Label(String.valueOf(joueur.getPosteF()));
                            //sp4.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                            Label sp5 = new Label(String.valueOf(joueur.getPrix()));
                            sp5.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                            btnadd = new Button("Ajouter");
                            //C3.add(poste);
                            //f1.add(C3);
                            f1.add(iv4);
                            f1.add(spn);
                            //f1.add(sp4);
                            f1.add(sp5);
                            f1.add(btnadd);

                            btnadd.addActionListener((e) -> {
                                //ServiceJoueurFantasy ser = new ServiceJoueurFantasy();
                                JoueurFantasy j = new JoueurFantasy();
                                // EquipeFantasy eq = new EquipeFantasy();
                                j.setJoueur(joueur);
                                j.setFEquipes(Session.getEf());
                                j.setEtat(1);
                                j.setPoints(0);
                                //ser.ajoutJoueur(j.getJoueur(), j.getFEquipes(), j.getEtat(), j.getPoints());
                                float pp = Session.getBanque() - joueur.getPrix();
                                if (pp > 0) {

                                    switch (joueur.getPosteF()) {
                                        case Gardien:
                                            boolean test1 = true;
                                            for (JoueurFantasy jf : Session.getGard()) {
                                                if (jf.getJoueur().getId() == joueur.getId()) {
                                                    test1 = false;
                                                    Dialog.show("Erreur", "2 Joueurs differents", "OK", "Cancel");
                                                    break;
                                                }
                                            }
                                            if (Session.getGard().size() < 2 && test1) {
                                                Session.setBanque(pp);
                                                Session.getGard().add(j);
                                            }
                                            break;
                                        case Defenseur:
                                            boolean test2 = true;
                                            for (JoueurFantasy jf : Session.getDef()) {
                                                if (jf.getJoueur().getId() == joueur.getId()) {
                                                    test2 = false;
                                                    Dialog.show("Erreur", "5 Joueurs differents", "OK", "Cancel");
                                                    break;
                                                }
                                            }
                                            if (Session.getDef().size() < 5 && test2) {

                                                Session.setBanque(pp);

                                                Session.getDef().add(j);

                                            }
                                            break;
                                        case Milieu:
                                            boolean test3 = true;
                                            for (JoueurFantasy jf : Session.getMil()) {
                                                if (jf.getJoueur().getId() == joueur.getId()) {
                                                    test3 = false;
                                                    Dialog.show("Erreur", "5 Joueurs differents", "OK", "Cancel");
                                                    break;
                                                }
                                            }
                                            if (Session.getMil().size() < 5 && test3) {
                                                Session.setBanque(pp);

                                                Session.getMil().add(j);

                                            }
                                            break;
                                        case Attaquant:
                                            boolean test = true;
                                            for (JoueurFantasy jf : Session.getAtk()) {
                                                if (jf.getJoueur().getId() == joueur.getId()) {
                                                    test = false;
                                                    Dialog.show("Erreur", "3 Joueurs differents", "OK", "Cancel");
                                                    break;
                                                }
                                            }
                                            if (Session.getAtk().size() < 3 && test) {
                                                Session.setBanque(pp);

                                                Session.getAtk().add(j);

                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                    Choix c = new Choix();
                                    c.getF().show();
                                } else {
                                    Dialog.show("Erreur", "ne depassez pas 100$", "ok", "Cancel");
                                }
                            });
                        }

                    }
                    f1.getToolbar().addCommandToOverflowMenu("G: " + Session.getGard().size(), null, (e) -> {
                    });
                    f1.getToolbar().addCommandToOverflowMenu("D: " + Session.getDef().size(), null, (e) -> {
                    });
                    f1.getToolbar().addCommandToOverflowMenu("M: " + Session.getMil().size(), null, (e) -> {
                    });
                    f1.getToolbar().addCommandToOverflowMenu("A: " + Session.getAtk().size(), null, (e) -> {
                    });
                    f1.getToolbar().addCommandToOverflowMenu("Banque: " + Session.getBanque(), null, (e) -> {
                    });
                    if (Session.getverif()) {
                        f1.getToolbar().addCommandToOverflowMenu("Valider", null, (e) -> {
                            for (JoueurFantasy j : Session.getJoueurFantasy()) {
                                ServiceJoueurFantasy.ajoutJoueur(j.getJoueur(), Session.getEf(), 0, 0);
                            }
                            MonEquipe me = new MonEquipe();
                            me.getF().show();
                        });
                    }
                    f1.show();
                }
            });

            C2.add(sp);
            C1.add(iv3);
            C1.add(C2);
            f2.add(C1);

        }
        f2.getToolbar().addCommandToOverflowMenu("G: " + Session.getGard().size(), null, (e) -> {
        });
        f2.getToolbar().addCommandToOverflowMenu("D: " + Session.getDef().size(), null, (e) -> {
        });
        f2.getToolbar().addCommandToOverflowMenu("M: " + Session.getMil().size(), null, (e) -> {
        });
        f2.getToolbar().addCommandToOverflowMenu("A: " + Session.getAtk().size(), null, (e) -> {
        });
        f2.getToolbar().addCommandToOverflowMenu("Banque: " + Session.getBanque(), null, (e) -> {
        });
        if (Session.getverif()) {
            f2.getToolbar().addCommandToOverflowMenu("Valider", null, (e) -> {
                for (JoueurFantasy j : Session.getJoueurFantasy()) {
                    ServiceJoueurFantasy.ajoutJoueur(j.getJoueur(), Session.getEf(), 0, 0);
                }
            });
        }

        f2.revalidate();
    }

    public Form getF() {
        return f2;
    }

    public void setF(Form f) {
        this.f2 = f;
    }

}
