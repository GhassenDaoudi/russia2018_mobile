/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Match;
import com.mycompany.Service.ServiceMatch;
import java.util.ArrayList;

/**
 *
 * @author hseli
 */
public class TreeForm {
    Form F;
    
    public TreeForm() {
        
        F=new Form("Tree",new TableLayout(6,1));
        F.setUIID("Tree_C");
        Style s = new Style();
        s.setFgColor(0xff0000);
        F.getToolbar().addCommandToRightBar("Back",null, (ev)->{PHFForm h=new PHFForm();
        h.getF().show();
        });
        Container C1=new Container(new TableLayout(20,4));
        F.setScrollable(false);
       // C1.setUIID("Tree_C");
        C1.setScrollableX(false);
        ServiceMatch SM = new ServiceMatch();
        ArrayList<Match> LMA = SM.getPHFListMatch("quartt");
        for (Match match : LMA) {
            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(35, 35, 0xffff2700), true);
            Image i = URLImage.createToStorage(placeholder, match.getE1().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv = new ImageViewer(i);
            Image i2 = URLImage.createToStorage(placeholder, match.getE2().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv2 = new ImageViewer(i2);
            
            C2.add(iv);
            C2.add("");
            C2.add(iv2);
            C2.add("");
            
            C1.add(C2);
            
        
            
        }
        Container C3=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        C3.add(new Label(" "));
        C3.add(new Label(" "));
        //C3.add(new Label(" "));
        C3.add(new Label(" "));
        
        C1.add(C3);
        
        ArrayList<Match> LMD = SM.getPHFListMatch("demiF");
        for (Match match : LMD) {
            Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(35, 35, 0xffff2700), true);
            Image i3 = URLImage.createToStorage(placeholder1, match.getE1().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv5 = new ImageViewer(i3);
            Image i4 = URLImage.createToStorage(placeholder1, match.getE2().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv4 = new ImageViewer(i4);
            
            C4.add(iv5);
            C4.add("");
            C4.add(iv4);
            C4.add("");
            C1.add(C4);
        }
        Container C5=new Container(new BoxLayout(BoxLayout.Y_AXIS));
       // C5.add(new Label(" "));
        //C5.add(new Label(" "));
        //C3.add(new Label(" "));
       // C5.add(new Label(" "));
        
        C1.add(C5);
        Container C8=new Container(new TableLayout(20,4));
        ArrayList<Match> LMF = SM.getPHFListMatch("finalee");
        for (Match match : LMF) {
            Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(35, 35, 0xffff2700), true);
            Image i3 = URLImage.createToStorage(placeholder1, match.getE1().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv5 = new ImageViewer(i3);
            Image i4 = URLImage.createToStorage(placeholder1, match.getE2().getDrapeau(), "http://localhost:8080/PI/Flags/" + match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv4 = new ImageViewer(i4);
            C7.add("       ");
            C7.add(iv5);
            Label l=new Label("        "+match.getScore1().getA()+" : "+match.getScore1().getB()+"      ");
            l.setUIID("TitleCommand");
            C7.add(l);
            C7.add(iv4);
            C7.add("");
            C8.add(C7);
        }
        Container C9=new Container(new TableLayout(20,4));
        Container C11=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ArrayList<Equipe> LE = SM.getGagnant();
        for (Equipe equipe : LE) {
            Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(45, 45, 0xffff2700), true);
            Image i3 = URLImage.createToStorage(placeholder1, equipe.getDrapeau(), "http://localhost:8080/PI/Flags/" + equipe.getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            ImageViewer iv5 = new ImageViewer(i3);
            C7.add("                       ");
            C7.add(iv5);
            C9.add(C7);
            
            Label la=new Label(equipe.getNom());
            la.setUIID("TitleCommand");
            C11.add(la);
        }
        Container C10=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        C10.add(new Label(" "));
        C10.add(new Label(" "));
        //C3.add(new Label(" "));
        Label la=new Label("Gagnant");
        la.setUIID("TitleCommand");
        C10.add(la);
        
        
        F.add(C1);
        F.add(C8);
        F.add(C10);
        F.add(C9);
        F.add(C11);
        F.show();
    }
    
    
    
    
    public Form getF() {
        return F;
    }

    public void setF(Form F) {
        this.F = F;
    }
    
    
}
