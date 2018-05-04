/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.ComponentAnimation;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entite.Match;
import com.mycompany.Service.ServiceMatch;
import java.util.ArrayList;

/**
 *
 * @author hseli
 */
public class MatchsForm {
    Form M;
    Container list;
    Container C2;
    Container C3;
    Resources res;
    String search;
    public MatchsForm(){
        M=new Form("Tout les matchs",new TableLayout(73,3));
   
        M.setUIID("Form_LOGIN");
        ServiceMatch SM=new ServiceMatch();
        res=UIManager.initFirstTheme("/theme");
        
            
        
        ArrayList<Match> LM=SM.getListMatchs();
        
        for (Match match : LM) {
            
          
            list=new Container(new BoxLayout(BoxLayout.X_AXIS));
            C3=new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            //list.setScrollableY(true);
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff2700), true);
            Image i = URLImage.createToStorage(placeholder, match.getE1().getDrapeau(), "http://localhost:8080/PI/Flags/"+match.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL); 
            ImageViewer iv = new ImageViewer(i);
            //EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(80, 80, 0xffff2700), true);
            Image i2 = URLImage.createToStorage(placeholder, match.getE2().getDrapeau(), "http://localhost:8080/PI/Flags/"+match.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL); 
            ImageViewer iv2 = new ImageViewer(i2);
            
            C2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Button l = new Button(/*match.getE1().getNom()+" "+match.getScore1().getA()+" : "+match.getScore1().getB()+" "+match.getE2().getNom()*/);
            l.setText(match.getE1().getNom()+" "+match.getScore1().getA()+" : "+match.getScore1().getB()+" "+match.getE2().getNom());
            l.setUIID("CaseMatch");
            
            l.addActionListener(ee->{
                    Form f2 = new Form("Evenements",new TableLayout(40,5));
                    f2.setUIID("Form_LOGIN");
                    Toolbar tb = f2.getToolbar();
                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
        
                         this.getM().show();
                    });
                    
                    ArrayList<Evenement> LE=SM.getListEvents(match.getId());
                    for (Evenement evenement : LE) {
                    EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(60, 60, 0xffff2700), true);
                    Image i3 = URLImage.createToStorage(placeholder1, evenement.getJoueur().getJ().getImage(), "http://localhost:8080/PI/image/"+evenement.getJoueur().getJ().getImage(), URLImage.RESIZE_SCALE_TO_FILL); 
                    ImageViewer iv3 = new ImageViewer(i3);
                    Label sp = new Label(evenement.getJoueur().getJ().getNom()+" "+evenement.getJoueur().getJ().getPrenom());
                    sp.setUIID("CaseMatch");
                    Label sp4 = new Label(String.valueOf(evenement.getTemps()));
                    sp4.setUIID("TitleCommand");
                    
                   // Label sp2 = new Label(evenement.getCarton().name());
               
                    f2.add(iv3);
                    f2.add(sp);
                    if(String.valueOf(evenement.getBut()).equals("1")){
                        Label sp1 = new Label(res.getImage("but.png").fill(20,20));
                        f2.add(sp1);
                    }else{
                       Label sp1 = new Label(" "); 
                       f2.add(sp1);
                    }
                    if(String.valueOf(evenement.getCarton()).equals("Jaune")){
                        Label sp2 = new Label(res.getImage("Carton_jaune.png").fill(20,20));
                        f2.add(sp2);
                    }else if(String.valueOf(evenement.getCarton()).equals("Rouge")){
                       Label sp2 = new Label(res.getImage("Carton_rouge.png").fill(20,20)); 
                       f2.add(sp2);
                    }else{
                       Label sp2 = new Label(" "); 
                       f2.add(sp2);
                    }
                    f2.add(sp4);
                    f2.show();
                    }
                        });
            list.setLeadComponent(l);
            C3.setLeadComponent(l);
           
            C2.add(l);
            
            list.add(iv);
            C3.add(iv2);
            
            
            M.add(list);
            /*if(String.valueOf(match.getE1().getNom()).toLowerCase().contains(search)){
                
            }*/
            M.add(C2);
            M.add(C3);
            
            
            
        }
        
       
        M.getToolbar().addCommandToRightBar("Back", null, (ev)->{HomeForm h=new HomeForm();
        h.getHi().show();
        });
        M.getToolbar().addSearchCommand(e->{
        search=(String)e.getSource();
        if(search == null || search.length() == 0) {
        // clear search
        for(Component cmp : C2) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        C2.animateLayout(150);
        
        }else {
        search = search.toLowerCase();
        
        for(Component cmp : C2) {
            Button b = (Button)cmp;
           
            
            //Button line1 =(Button)mb.getComponentAt(1,1);
            String line2=b.getText();
            System.out.println(line2);
            boolean show = line2 != null && line2.toLowerCase().indexOf(search) > -1 ;
             b.setHidden(!show);
             b.setVisible(show);
            
           // 
            
        }
        C2.animateLayout(150);
        }
                
            },4);
            
       // M.add(CENTER,list);
        Toolbar tb=M.getToolbar();
        ComponentAnimation cna = tb.createStyleAnimation("TitleAreaClean", 200);
        ComponentAnimation title = tb.getTitleComponent().createStyleAnimation("TitleClean", 200);
        M.getAnimationManager().onTitleScrollAnimation(cna, title);
        M.show();
    }

    public Form getM() {
        return M;
    }

    public void setM(Form M) {
        this.M = M;
    }
    
    
    
}
