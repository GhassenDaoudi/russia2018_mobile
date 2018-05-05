/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.Entite.Match;
import com.mycompany.Service.ServiceMatch;
import com.mycompany.Utilitaire.Components;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hseli
 */
public class PHFForm {

    Form F;
    
    public PHFForm() {
        F = new Form("Phase Finale", new BorderLayout());
        Components.showHamburger(F);
        F.getToolbar().addCommandToRightBar("Tree", null, (ev) -> {
            TreeForm h = new TreeForm();
            h.getF().show();
        });
        
        Tabs tb = new Tabs() {
            @Override
            protected Component createTab(String title, Image icon) {
                SpanButton custom = new SpanButton(title);
                custom.setIcon(icon);
                custom.setUIID("Container");
                custom.setTextUIID("Tab");
                custom.setIconPosition(BorderLayout.NORTH);
                custom.setIconUIID("Tab");
                return custom;
            }
            
            @Override
            protected void setTabSelectedIcon(Component tab, Image icon) {
                
            }
            
            @Override
            protected void selectTab(Component tab) {
                
            }
            
            @Override
            protected void bindTabActionListener(Component tab, ActionListener l) {
                ((SpanButton) tab).addActionListener(l);
            }
        };
        
        tb.setTabUIID(null);
        
        tb.addTab("Huitieme", FontImage.MATERIAL_MOOD, 4, this.PHF("huit"));
        tb.addTab("Quart", FontImage.MATERIAL_ALL_INCLUSIVE, 4, this.PHF("quartt"));
        tb.addTab("Demi", FontImage.MATERIAL_GRADE, 4, this.PHF("demiF"));
        tb.addTab("Finale", FontImage.MATERIAL_ANNOUNCEMENT, 4, this.PHF("finalee"));
        
        tb.getTabsContainer().setScrollableX(false);
        
        F.add(BorderLayout.CENTER, tb);
        
    }
    
    public Form getF() {
        return F;
    }
    
    public void setF(Form F) {
        this.F = F;
    }

    public Container PHF(String url) {
        Container C1 = new Container(new TableLayout(40, 4));
        C1.setUIID("Form_LOGIN");
        C1.setScrollableY(true);
        ServiceMatch SM = new ServiceMatch();
        ArrayList<Match> LMA = SM.getPHFListMatch(url);
        if (LMA.isEmpty()) {
            Container C4 = new Container(new LayeredLayout());
            SpanLabel l = new SpanLabel("En Attente");
            C4.add(BorderLayout.centerAbsolute(l));
            return C4;
        } else {
            for (Match match : LMA) {
                Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container list = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(60, 60, 0xffff2700), true);
                Image i = URLImage.createToStorage(placeholder, match.getE1().getDrapeau(), "http://localhost/PI/Flags/" + match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
                ImageViewer iv = new ImageViewer(i);
                Image i2 = URLImage.createToStorage(placeholder, match.getE2().getDrapeau(), "http://localhost/PI/Flags/" + match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
                ImageViewer iv2 = new ImageViewer(i2);
                Date d = match.getDate();
                Date d1 = match.getHeure();
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm");
                SpanLabel l = new SpanLabel(match.getE1().getNom() + "     " + sfd.format(d) + " " + sfd1.format(d1) + "          " + match.getE2().getNom());
                list.setLeadComponent(l);
                C3.setLeadComponent(l);
                list.add(iv);
                C3.add(iv2);
                l.setUIID("CaseMatch");
                Label la = new Label(match.getScore1().getA() + ":" + match.getScore1().getB());
                la.setUIID("TitleCommand");
                C2.add(l);
                C1.add(list);
                C1.add(la);
                C1.add(C3);
                C1.add(C2);
            }
            return C1;            
        }
        
    }
}
