/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.Entite.Match;
import com.mycompany.Service.ServiceMatch;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hseli
 */
public class CountForm {
    Form F;
    
    
    public CountForm() {
      
        F=new Form("Count",new TableLayout(1,4));
        F.setUIID("CountForm");
       /* EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.F.getWidth(),this.F.getHeight(), 0xffff2700), true);
        Image i = URLImage.createToStorage(placeholder,"count", "http://localhost:8080/PI/count.png", URLImage.RESIZE_SCALE_TO_FILL); 
        
        F.getUnselectedStyle().setBgImage(i, true);
        F.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);*/
       
        Container C1=new Container(new TableLayout(20,1));
        
        update(C1);
        UITimer t = new UITimer(()->{
            Display.getInstance().callSerially(()->{
                update(C1);
                F.revalidate();
            });
        });
        t.schedule(1000, true, F);
        F.add(C1);
        
        F.show();
    }

    public Form getF() {
        return F;
    }

    public void setF(Form F) {
        this.F = F;
    }

    private void update(Container C1) {
        C1.removeAll();
        ServiceMatch SM=new ServiceMatch();
        ArrayList<Match> LM=SM.getFirstM();
        Container C2=new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        Container C3=new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
        for (Match match : LM) {
            long t=match.getDate().getTime()-System.currentTimeMillis();
            double secondes=t/1000;
            double minutes=secondes/60;
            double heures=minutes/60;
            double day=heures/24;
            
            secondes=Math.floor(secondes%60);
            minutes=Math.floor(minutes%60);
            heures=Math.floor(heures%24);
            day=Math.floor(day);
            
            double s=secondes;
            double m=minutes;
            double h=heures;
            double d=day;
            
            int se=(int)s;
            int mi=(int)m;
            int he=(int)h;
            int da=(int)d;
            Label l=new Label("      "+String.valueOf(da)+"          "+String.valueOf(he)+"         "+String.valueOf(mi)+"        "+String.valueOf(se));
            l.setUIID("Title");
            se--;
            mi--;
            he--;
            da--;
            Label la=new Label("                                                            ");
            C2.add(new Label("    "));
            C2.add(new Label("    "));
            C2.add(new Label("    "));
            C2.add(new Label("    "));
        
            
            C1.add(C2);
           
            C1.add(l);
            
            
        }
        
    }

    
}
