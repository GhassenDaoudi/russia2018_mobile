/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;

import com.codename1.ui.*;
import com.codename1.ui.animations.FlipTransition;

import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.mycompany.Entite.Match;
import com.mycompany.Entite.Stade;
import com.mycompany.Service.ServiceMatch;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hseli
 */
public class StadesForm {

    Form F;
    Container B;

    public StadesForm() {
        F = new Form("Stades");
        B = new Container(new TableLayout(12, 2));
        ServiceMatch SM = new ServiceMatch();
        ArrayList<Stade> LS = SM.getListStades();
        for (Stade stade : LS) {
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100, 100, 0xffff2700), true);
            Image i = URLImage.createToStorage(placeholder, stade.getImage(), "http://localhost:8080/PI/image/" + stade.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv = new ImageViewer(i);
            Button S1 = new Button("   " + stade.getNom());
            //Action Button
            S1.addActionListener(ee -> {
                Form f2 = new Form("Details", new TableLayout(3, 1));
                Toolbar tb = f2.getToolbar();
                tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                    this.getF().show();
                });
                SpanLabel l = new SpanLabel("Ville : " + stade.getAdresse() + "                          Capacite :   " + stade.getCapacite());
                l.setUIID("CaseMatch");
                Button lb = new Button("Voir stade sur la carte");
                lb.addActionListener(e -> {
                    String HTML_API_KEY = "AIzaSyCfqMhMcoqniv4GMrB-b4yh6Xu6_xvn7Bg";
                    Form hi = new Form("Google Maps", new BorderLayout());
                    hi.getToolbar().addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, e1 -> {
                        f2.show();
                    });
                    MapContainer cnt = new MapContainer(HTML_API_KEY);
                    Style s = new Style();
                    s.setFgColor(0xff0000);
                    s.setBgTransparency(0);
                    FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));

                    final boolean[] init = {false};

                    cnt.addMapListener((e1, e2, e3) -> {
                        if (!init[0]) {
                            if (stade.getId() == 1) {
                                Coord coord = new Coord(54.6982, 20.5339);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, String.valueOf("Capacite : " + stade.getCapacite()), "Text", evt -> {
                                    ToastBar.showMessage(stade.getNom() + "                       Ville :" + stade.getAdresse(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }
                            if (stade.getId() == 2) {
                                Coord coord = new Coord(48.7345, 44.5483);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, String.valueOf("Capacite : " + stade.getCapacite()), "Text", evt -> {
                                    ToastBar.showMessage(stade.getNom() + "        Ville :" + stade.getAdresse(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }
                            if (stade.getId() == 3) {
                                Coord coord = new Coord(53.2781, 50.2389);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, String.valueOf("Capacite : " + stade.getCapacite()), "Text", evt -> {
                                    ToastBar.showMessage(stade.getNom() + "                          Ville :" + stade.getAdresse(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }
                            if (stade.getId() == 4) {
                                Coord coord = new Coord(55.8210, 49.1610);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, String.valueOf("Capacite : " + stade.getCapacite()), "Text", evt -> {
                                    ToastBar.showMessage(stade.getNom() + "                          Ville :" + stade.getAdresse(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }

                        }
                    });

                    Container root = LayeredLayout.encloseIn(
                            BorderLayout.center(cnt)
                    );

                    hi.add(BorderLayout.CENTER, root);

                    hi.show();
                });
                Container C1 = new Container(new TableLayout(10, 4));
                ArrayList<Match> LMS = SM.getListMatchStades(stade.getId());
                for (Match match : LMS) {
                    EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(60, 60, 0xffff2700), true);
                    Image i1 = URLImage.createToStorage(placeholder1, match.getE1().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
                    ImageViewer iv1 = new ImageViewer(i1);
                    Image i2 = URLImage.createToStorage(placeholder1, match.getE2().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
                    ImageViewer iv2 = new ImageViewer(i2);
                    Date d = match.getDate();
                    Date d1 = match.getHeure();
                    SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm");
                    SpanLabel l1 = new SpanLabel(match.getE1().getNom() + " " + sfd.format(d) + " " + sfd1.format(d1) + "         " + match.getE2().getNom());
                    l1.setUIID("CaseMatch");
                    Label la = new Label("vs");
                    la.setUIID("TitleCommand");
                    C1.setScrollableY(true);
                    C1.add(iv1);
                    C1.add(la);
                    C1.add(iv2);
                    C1.add(l1);
                }
                /* C1.add(new Label("aa"));
                C1.add(new Label("bb"));
                C1.add(new Label("cc"));
                C1.add(new Label("dd"));*/
                f2.add(l);
                f2.add(C1);
                f2.add(lb);
                f2.setUIID("Form_LOGIN");
                f2.show();
            });

            S1.setUIID("CaseMatch");
            B.setUIID("Form_LOGIN");
            B.add(iv);
            B.add(S1);
        }

        F.add(B);
        F.setTransitionOutAnimator(new FlipTransition());
        F.show();
    }

    public Form getF() {
        return F;
    }

    public void setF(Form F) {
        this.F = F;
    }

}
