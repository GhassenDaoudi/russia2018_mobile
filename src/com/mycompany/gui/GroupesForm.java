/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.*;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.mycompany.Entite.Match;
import com.mycompany.Service.ServiceMatch;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hseli
 */
public class GroupesForm {

    Form F;
   
    public GroupesForm() {
        F = new Form("Les Groupes", new BorderLayout());
       
        
        F.getToolbar().addCommandToRightBar("Back",null, (ev)->{HomeForm h=new HomeForm();
        h.getHi().show();
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
        
        tb.addTab("A", FontImage.MATERIAL_GRADE, 4, this.Groupe(11));
        tb.addTab("B", FontImage.MATERIAL_GRADE, 4, this.Groupe(12));
        tb.addTab("C", FontImage.MATERIAL_GRADE, 4, this.Groupe(13));
        tb.addTab("D", FontImage.MATERIAL_GRADE, 4, this.Groupe(14));
        tb.addTab("E", FontImage.MATERIAL_GRADE, 4, this.Groupe(15));
        tb.addTab("F", FontImage.MATERIAL_GRADE, 4, this.Groupe(16));
        tb.addTab("G", FontImage.MATERIAL_GRADE, 4, this.Groupe(17));
        tb.addTab("H", FontImage.MATERIAL_GRADE, 4, this.Groupe(18));

        tb.getTabsContainer().setScrollableX(false);

        F.add(BorderLayout.CENTER, tb);
            
        F.show();
    }

    public Form getF() {
        return F;
    }

    public void setF(Form F) {
        this.F = F;
    }
    public Container Groupe(int id_G){
        Container C1 = new Container(new TableLayout(40,4));
        C1.setUIID("Form_LOGIN");
        C1.setScrollableY(true);
        ServiceMatch SM = new ServiceMatch();
        ArrayList<Match> LMA = SM.getListMatchParGroupe(id_G);
        for (Match match : LMA) {
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container list = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(60, 60, 0xffff2700), true);
            Image i = URLImage.createToStorage(placeholder, match.getE1().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv = new ImageViewer(i);
            Image i2 = URLImage.createToStorage(placeholder, match.getE2().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
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
            Label la=new Label(match.getScore1().getA()+":"+match.getScore1().getB());
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
