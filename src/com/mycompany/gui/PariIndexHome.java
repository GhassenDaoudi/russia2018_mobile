/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;

import com.codename1.ui.table.TableModel;
import com.mycompany.Entite.Match;
import com.mycompany.Service.ServicePari;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.PanierPari;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.Background;

/**
 *
 * @author skanderbejaoui
 */
public class PariIndexHome {
    
    private Form form;
    
    public Form getForm() {
        return form;
    }
    
    public void setForm(Form form) {
        this.form = form;
    }
    
    public PariIndexHome() {
        
        this.form = new Form("Parier !", BoxLayout.y());
        this.form.setUIID("asfer");
        Components.showHamburger(this.form);
        //ArrayList<Match> matchs = ServicePari.getList();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(40, 40, 0xffff0000), true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EURO_SYMBOL, s);
        this.form.getToolbar().addCommandToRightBar("Ma fiche (" + PanierPari.getLp().size() + ")", icon, (e) -> {
            PariValidationPanierFormHome pvpfh = new PariValidationPanierFormHome(this.form);
            pvpfh.getForm().show();
        });
        for (Map.Entry<Date, List<Match>> entry : ServicePari.getAll().entrySet()) {
            
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            Label datematch = new Label(String.valueOf(newFormat.format(entry.getKey())));
            Container container_datematch = new Container();
            container_datematch.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            container_datematch.add(BorderLayout.CENTER, datematch);
            this.form.add(container_datematch);
            for (Match match : entry.getValue()) {
                Container container_table = new Container();
                container_table.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                TableModel model = new DefaultTableModel(new String[]{"", "", ""}, new Object[][]{
                    {match.getE1().getDrapeau(), match.getE1().getNom() + "  vs  " + match.getE2().getNom(), match.getE2().getDrapeau()}}) {
                    public boolean isCellEditable(int row, int col) {
                        return col != 0;
                    }
                    
                };
                Table table = new Table(model) {
                    @Override
                    protected Component createCell(Object value, int row, int column, boolean editable) {
                        
                        if (row == -1) {
                            return super.createCell(value, row, column, editable);
                        }
                        if (column == 0) {
                            Image drapeau_gauche = URLImage.createToStorage(placeholder, match.getE1().getDrapeau(), "http://localhost/PI/Flags/" + match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
                            
                            ImageViewer i_drapeau_gauche = new ImageViewer(drapeau_gauche);
                            //Container container_drapeau_gauche = new Container();
                            // container_drapeau_gauche.add( i_drapeau_gauche);
                            return i_drapeau_gauche;
                        } else if (column == 2) {
                            Image drapeau_droit = URLImage.createToStorage(placeholder, match.getE2().getDrapeau(), "http://localhost/PI/Flags/" + match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
                            ImageViewer i_drapeau_droit = new ImageViewer(drapeau_droit);
                            // Container container_drapeau_droit = new Container();
                            // container_drapeau_droit.add( i_drapeau_droit);

                            return i_drapeau_droit;
                        } else if (column == 1) {
                            Label titre = new Label(match.getE1().getNom() + "  vs  " + match.getE2().getNom());
                            titre.addPointerPressedListener((evt) -> {
                                PariChoixFormHome pcfh = new PariChoixFormHome(form, match);
                                pcfh.getF().show();
                            });
                            return titre;
                        } else {
                            return new Label("");
                        }
                    }
                    
                };
                table.setDrawBorder(
                        false);
                table.setCollapseBorder(true);
                container_table.add(BorderLayout.CENTER, table);
                container_table.setWidth(-1);
                container_table.setUIID("Containert");
                this.form.add(container_table);
                
            }
            
        }
        
    }
}
