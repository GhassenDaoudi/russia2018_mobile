/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.Match;
import com.mycompany.Entite.Pari;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceFichePari;
import com.mycompany.Service.ServicePari;
import com.mycompany.Utilitaire.Paiement;
import com.mycompany.Utilitaire.PanierPari;
import com.mycompany.Utilitaire.SMS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skanderbejaoui
 */
public class PariFormHome {

    List<Integer> intlist = new ArrayList<>();
    float cm = 1;
    float mm = 0;
    int cx = 0;
    Form f;
    Form f3 = new Form();
    SpanLabel lb;
    float cote = 1;
    float mise = 0;
    List<Pari> listparis = new ArrayList<>();
    Container CP = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container CPM = new Container(new BoxLayout(BoxLayout.X_AXIS));
    Container CPV = new Container(new BoxLayout(BoxLayout.X_AXIS));
    Container CPY = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container CPC = new Container(new BoxLayout(BoxLayout.X_AXIS));
    Container CPG = new Container(new BoxLayout(BoxLayout.X_AXIS));
    Container CPFP = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    RadioButton SimpleButton = new RadioButton("Simple");
    RadioButton MultipleButton = new RadioButton("Multiple");
    Button ValiderFiche = new Button("Valider");
    Label cotefp = new Label("Cote totale");
    Label gainfp = new Label("Gain totaux");
    Label cotefpm = new Label();
    Label gainfpm = new Label();
    float misefpmt = 0;
    float cotefpmt = 1;
    float gainfpmt = 0;
    ServicePari sp = new ServicePari();
    ServiceFichePari sfp = new ServiceFichePari();
    User user = new User();
    Boolean test = false;
    public PariFormHome() {
        // SMS.creer();
        user.setId(10);
        gainfp.setVisible(false);
        cotefpm.setVisible(false);
        cotefp.setVisible(false);
        gainfpm.setVisible(false);
        SimpleButton.setVisible(false);
        MultipleButton.setVisible(false);
        ValiderFiche.setVisible(false);

        f = new Form("Parier !", new TableLayout(48, 3));
        f3 = new Form("Ma fiche", BoxLayout.y());

        CPC.add(cotefp);
        CPC.add(cotefpm);
        CPG.add(gainfp);
        CPG.add(gainfpm);
        CPFP.add(CPC);
        CPFP.add(CPG);
        CPM.add(SimpleButton);
        CPM.add(MultipleButton);
        CPV.add(ValiderFiche);
        CPY.add(CP);
        CPY.add(CPM);

        CPY.add(CPFP);
        CPY.add(CPV);
        f3.add(CPY);
        Toolbar tb2 = f3.getToolbar();
        tb2.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, x -> {
            f.showBack();

        });
        ServicePari sp = new ServicePari();
        ArrayList<Match> lis = sp.getList();
        EncodedImage placeholderr = EncodedImage.createFromImage(Image.createImage(40, 40, 0xffff0000), true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EURO_SYMBOL, s);

        f.getToolbar().addCommandToRightBar("Ma fiche (" + cx + ")", icon, (e) -> {
            intlist.clear();
            mise = 0.0f;
            if (listparis.isEmpty() == false) {
                SimpleButton.setVisible(true);
                MultipleButton.setVisible(true);
                ValiderFiche.setVisible(true);
            }
            MultipleButton.addActionListener((y) -> {

                System.out.println("multiple");
                for (Pari listpari : listparis) {
                    misefpmt += listpari.getMise();
                    cotefpmt *= listpari.getCote();
                }
                cotefpm.setText(String.valueOf(cotefpmt));
                gainfpm.setText(String.valueOf(misefpmt * cotefpmt));
                cotefpmt = 1;
                misefpmt = 0;
                SimpleButton.setSelected(false);

                gainfp.setVisible(true);
                cotefpm.setVisible(true);
                cotefp.setVisible(true);
                gainfpm.setVisible(true);
                intlist.clear();

                f3.show();

            });
            SimpleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("simple");
                    cotefpmt = 1;
                    misefpmt = 0;
                    MultipleButton.setSelected(false);
                    gainfp.setVisible(false);
                    cotefpm.setVisible(false);
                    cotefp.setVisible(false);
                    gainfpm.setVisible(false);
                    intlist.clear();

                    f3.show();
                }
            });
            intlist.clear();
            f3.show();
            ValiderFiche.addActionListener((x -> {
                if (MultipleButton.isSelected()) {

                    
                   ArrayList<Pari> listmultiple = new ArrayList<>();
                    for (Pari listpari : listparis) {

                        if (intlist.contains(listpari.getM().getId())) {
                            System.out.println("ta7ché");
                            test = true;
                            break;
                        } else {
                            cm *= listpari.getCote();
                            mm += listpari.getMise();
                            listmultiple.add(listpari);
                        }

                        intlist.add(listpari.getM().getId());

                    }
                    if(test==false){
                         FichePari fp = new FichePari();
                    FichePari fpp = new FichePari();
                    fp.setMisetotal(mm);
                    fp.setCote_total(cm);
                    fp.setGain(mm*cm);
                    fp.setType(2);
                    fp.setU(user);
                     
                   fpp = sfp.AjoutFichePari(fp.getCote_total(), fp.getU(), fp.getMisetotal(), fp.getGain(), fp.getType());
                   fpp.setParis(listmultiple);
                        for (Pari pari : listmultiple) {
                                Pari p = new Pari();
                                p.setFp(fpp);
                        p.setM(pari.getM());

                        p.setMise(pari.getMise());
                        p.setGain(pari.getGain());
                        p.setCote(pari.getCote());
                        p.setResultat(pari.getResultat());
                        sp.AjoutPari(p.getM(), p.getFp(), p.getMise(), p.getGain(), p.getCote(), p.getResultat().toString());
                        }
                    }
                   
                    intlist.clear();
                } else if (SimpleButton.isSelected()) {
                    System.out.println("simple metier");
                    for (Pari listpari : listparis) {
                        FichePari fp = new FichePari();
                        FichePari fpp = new FichePari();
                        fp.setMisetotal(listpari.getMise());
                        fp.setCote_total(listpari.getCote());
                        fp.setGain(listpari.getGain());
                        fp.setType(1);
                        fp.setU(user);
                        fpp = sfp.AjoutFichePari(fp.getCote_total(), fp.getU(), fp.getMisetotal(), fp.getGain(), fp.getType());

                        Pari p = new Pari();
                        p.setM(listpari.getM());

                        p.setMise(listpari.getMise());
                        p.setGain(listpari.getGain());
                        p.setCote(listpari.getCote());
                        p.setResultat(listpari.getResultat());
                        p.setFp(fpp);
                        sp.AjoutPari(p.getM(), p.getFp(), p.getMise(), p.getGain(), p.getCote(), p.getResultat().toString());
                    }
                }
            }));
        });

        for (Match m : lis) {

            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(40, 40, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, m.getE1().getDrapeau(), "http://localhost/PI/Flags/" + m.getE1().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);
            Image ii = URLImage.createToStorage(placeholder, m.getE2().getDrapeau(), "http://localhost/PI/Flags/" + m.getE2().getDrapeau(), URLImage.RESIZE_SCALE_TO_FILL);

            ImageViewer iv = new ImageViewer(i);
            ImageViewer ivv = new ImageViewer(ii);

            Label l = new Label(m.getE1().getNom() + " vs " + m.getE2().getNom());
            C3.add(iv);

            C4.add(ivv);
            C1.setLeadComponent(l);
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form f2 = new Form(BoxLayout.x());
                    Toolbar tb = f2.getToolbar();
                    f2.getToolbar().addCommandToRightBar("Ma fiche (" + cx + ")", icon, (e) -> {

                        for (Pari pari : PanierPari.getLp()) {

                            Label lpm = new Label("Match :" + pari.getM().getE1().getNom() + "  vs  " + pari.getM().getE2().getNom());
                            Label lpr = new Label();
                            if (pari.getResultat().equals(Pari.ResultatPari.un)) {
                                lpr.setText("Résultat:  " + pari.getM().getE1().getNom());
                            } else if (pari.getResultat().equals(Pari.ResultatPari.x)) {
                                lpr.setText("Résultat:  X");
                            } else if (pari.getResultat().equals(Pari.ResultatPari.deux)) {
                                lpr.setText("Résultat:  " + pari.getM().getE1().getNom());
                            }
                            Label lpg = new Label("Gain :" + String.valueOf(pari.getGain()));
                            Label lpmise = new Label("Mise :" + String.valueOf(pari.getMise()));
                            lpm.addPointerPressedListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    Form f4 = new Form();
                                    Container CF = new Container(BoxLayout.y());
                                    Container CFF = new Container(BoxLayout.x());
                                    Container CFG = new Container(BoxLayout.x());
                                    Label lpf = new Label("Match :" + pari.getM().getE1().getNom() + "  vs  " + pari.getM().getE2().getNom());
                                    Label lpff = new Label();
                                    if (pari.getResultat().equals(Pari.ResultatPari.un)) {
                                        lpff.setText("Résultat:  " + pari.getM().getE1().getNom());
                                    } else if (pari.getResultat().equals(Pari.ResultatPari.x)) {
                                        lpff.setText("Résultat:  X");
                                    } else if (pari.getResultat().equals(Pari.ResultatPari.deux)) {
                                        lpff.setText("Résultat:  " + pari.getM().getE1().getNom());
                                    }
                                    Label lpfg = new Label("Gain ");
                                    Label lpfgg = new Label(String.valueOf(pari.getGain()));
                                    Label lpfff = new Label("Mise :");
                                    TextField lpfmise = new TextField("" + String.valueOf(pari.getMise()));

                                    CF.add(lpf);
                                    CF.add(lpff);
                                    CFF.add(lpfff);
                                    CFF.add(lpfmise);
                                    CFG.add(lpfg);
                                    CFG.add(lpfgg);
                                    CF.add(CFF);
                                    CF.add(CFG);

                                    lpfmise.setEditable(false);
                                    f4.add(CF);
                                    f4.getToolbar().addCommandToOverflowMenu("Modifier", null, (x) -> {
                                        Button ValiderModif = new Button("Valider");
                                        f4.add(ValiderModif);
                                        lpfmise.setEditable(true);
                                        lpfmise.addDataChangedListener(new DataChangedListener() {
                                            @Override
                                            public void dataChanged(int type, int index) {
                                                lpfgg.setText(String.valueOf(Float.parseFloat(lpfmise.getText()) * pari.getCote()));

                                            }
                                        });
                                        ValiderModif.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {

                                                pari.setMise(Float.parseFloat(lpfmise.getText()));
                                                pari.setGain(Float.parseFloat(lpfgg.getText()));
                                                lpmise.setText("Mise :" + lpfmise.getText());
                                                lpg.setText("Gain :" + lpfgg.getText());
                                                f3.show();
                                                System.out.println(pari);
                                            }
                                        });

                                    });
                                    f4.getToolbar().addCommandToOverflowMenu("Annuler", null, (x) -> {
                                        PanierPari.getLp().remove(pari);
                                        listparis.remove(pari);
                                        lpmise.remove();
                                        lpg.remove();
                                        lpm.remove();
                                        lpr.remove();
                                        if (listparis.isEmpty() == true) {
                                            SimpleButton.setVisible(false);
                                            MultipleButton.setVisible(false);
                                            ValiderFiche.setVisible(false);
                                        }
                                        f3.show();
                                    });
                                    f4.show();//To change body of generated methods, choose Tools | Templates.
                                }
                            });
                            CP.add(lpm);
                            CP.add(lpr);

                            CP.add(lpmise);
                            CP.add(lpg);

                        }
                        listparis.addAll(PanierPari.getLp());

                        PanierPari.getLp().clear();
                        if (listparis.isEmpty() == false) {
                            SimpleButton.setVisible(true);
                            MultipleButton.setVisible(true);
                            ValiderFiche.setVisible(true);
                        }
                        intlist.clear();

                        f3.show();
                        MultipleButton.addActionListener((y) -> {

                            System.out.println("multiple");
                            for (Pari listpari : listparis) {
                                misefpmt += listpari.getMise();
                                cotefpmt *= listpari.getCote();
                            }
                            cotefpm.setText(String.valueOf(cotefpmt));
                            gainfpm.setText(String.valueOf(misefpmt * cotefpmt));
                            cotefpmt = 1;
                            misefpmt = 0;
                            SimpleButton.setSelected(false);

                            gainfp.setVisible(true);
                            cotefpm.setVisible(true);
                            cotefp.setVisible(true);
                            gainfpm.setVisible(true);
                            intlist.clear();

                            f3.show();

                        });
                        SimpleButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                System.out.println("simple");
                                cotefpmt = 1;
                                misefpmt = 0;
                                MultipleButton.setSelected(false);
                                gainfp.setVisible(false);
                                cotefpm.setVisible(false);
                                cotefp.setVisible(false);
                                gainfpm.setVisible(false);
                                intlist.clear();

                                f3.show();
                            }
                        });

                    });
                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                        f.showBack();
                        cote = 1;
                        mise = 0;
                    });
                    // ImageViewer img = new ImageViewer(theme.getImage(t.getImg()));

                    Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C9 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label Equipe12 = new Label("Match : " + m.getE1().getNom() + "  vs  " + (m.getE2().getNom()));
                    C9.add(Equipe12);
                    RadioButton a = new RadioButton();
                    RadioButton b = new RadioButton();
                    RadioButton c = new RadioButton();
                    Button v = new Button("Valider");
                    Label label = new Label("Votre mise");
                    TextField tf = new TextField();
                    Label labell = new Label("Gain");
                    TextField tff = new TextField();
                    String s = String.valueOf(m.getCote_eq1());
                    String y = String.valueOf(m.getCote_eq2());
                    String z = String.valueOf(m.getCote_nul());
                    a.addActionListener((e) -> {

                        cote = 1;
                        cote *= Float.parseFloat(a.getText());
                        if (mise != 0.0) {
                            mise = Float.parseFloat(tf.getText());
                            tff.setText(String.valueOf(cote * mise));
                        }
                        b.setSelected(false);
                        c.setSelected(false);

                    });
                    b.addActionListener((e) -> {
                        cote = 1;
                        cote *= Float.parseFloat(b.getText());
                        if (mise != 0.0) {
                            mise = Float.parseFloat(tf.getText());
                            tff.setText(String.valueOf(cote * mise));
                        }
                        a.setSelected(false);
                        c.setSelected(false);
                    });
                    c.addActionListener((e) -> {
                        cote = 1;
                        cote *= Float.parseFloat(c.getText());
                        if (mise != 0.0) {
                            mise = Float.parseFloat(tf.getText());
                            tff.setText(String.valueOf(cote * mise));
                        }
                        a.setSelected(false);
                        b.setSelected(false);
                    });

                    tf.addDataChangedListener(new textListener() {
                        public void dataChanged(int type, int index) {
                            //Run the following method  
                            if (tf.getText().equals("")) {
                                tff.setText("0");
                            } else {
                                mise = Float.parseFloat(tf.getText());
                                if (mise == 0.0) {
                                    tff.setText("0");
                                } else if (mise != 0.0) {
                                    tff.setText(String.valueOf(cote * mise));
                                }
                            }
                        }
                    });

                    a.setText(s);
                    b.setText(z);
                    c.setText(y);

                    C7.add(a);
                    C7.add(b);
                    C7.add(c);

                    C8.add(label);
                    C8.add(tf);
                    C8.add(labell);
                    C8.add(tff);
                    C8.add(v);
                    C6.add(C9);
                    C6.add(C7);
                    C6.add(C8);
                    ;

                    f2.add(C6);

                    f2.show();
                    tf.setText("0");
                    v.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {

                            if ((Float.parseFloat(tf.getText()) <= 0.0f) && (a.isSelected() == false) && (b.isSelected() == false) && (c.isSelected() == false)) {
                                Dialog.show("", "Choisissez une côte et une mise pour parier!", "OK", "Cancel");
                            } else if (Float.parseFloat(tf.getText()) <= 0.0f) {
                                Dialog.show("Vous n'avez pas misé!", "Entrez une mise et réessayez", "OK", "Cancel");
                            } else if ((a.isSelected() == false) && (b.isSelected() == false) && (c.isSelected() == false)) {
                                Dialog.show("", "Choisissez une cote et réessayez", "OK", "Cancel");
                            } else {

                                Pari P = new Pari();

                                P.setEtat(Pari.EtatPari.Encours);
                                P.setGain(Float.parseFloat(tff.getText()));
                                P.setM(m);
                                P.setMise(Float.parseFloat(tf.getText()));
                                P.setType(1);
                                if (a.isSelected()) {
                                    P.setCote(Float.parseFloat(a.getText()));
                                    P.setResultat(Pari.ResultatPari.un);
                                } else if (b.isSelected()) {
                                    P.setCote(Float.parseFloat(b.getText()));
                                    P.setResultat(Pari.ResultatPari.x);
                                } else if (c.isSelected()) {
                                    P.setCote(Float.parseFloat(c.getText()));
                                    P.setResultat(Pari.ResultatPari.deux);
                                }
                                PanierPari.getLp().add(P);
                                mise = 0.0f;

                            }
                        }
                    });
                }

            });
            C2.add(l);
            C1.add(C2);
            f.add(C3);

            f.add(C1);
            f.add(C4);

        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private abstract class textListener implements DataChangedListener {

    }

}
