/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.Pari;
import com.mycompany.Service.ServiceFichePari;
import com.mycompany.Service.ServicePari;
import com.mycompany.Service.ServiceUser;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.PanierPari;
import com.mycompany.Utilitaire.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skanderbejaoui
 */
public class PariValidationPanierFormHome {

    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public PariValidationPanierFormHome(Form previous) {

        RadioButton button_simple = new RadioButton("Simple");
        RadioButton button_multiple = new RadioButton("Multiple");
        Button ValiderFiche = new Button("Valider");
        ValiderFiche.setVisible(false);
        Label mise_totale = new Label("Mise totale: ");
        Label cote_totale = new Label("Cote totale: ");
        Label gain_totaux = new Label("Gain totaux: ");
        Float mise_calcul;
        Float cote_calcul;
        Float gain_calcul;
        cote_totale.setVisible(false);
        mise_totale.setVisible(false);
        gain_totaux.setVisible(false);
        Container container_multiple = new Container(BoxLayout.x());
        container_multiple.add(mise_totale);
        container_multiple.add(cote_totale);
        this.form = new Form("Ma fiche", BoxLayout.y());
        Components.showBack(this.form, previous);
        for (Pari pari : PanierPari.getLp()) {
            Label titre = new Label("Match: " + pari.getM().getE1().getNom() + "  vs  " + pari.getM().getE2().getNom());
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            Label datematch = new Label("Date du match: " + String.valueOf(newFormat.format(pari.getM().getDate())));
            datematch.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));

            Label misepari = new Label("Mise :" + pari.getMise());
            misepari.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            Label cotepari = new Label("Cote :" + pari.getCote());
            cotepari.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            Label gainpari = new Label("Gain :" + pari.getGain());
            gainpari.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            titre.addPointerPressedListener((evt) -> {
                PariModificationFormHome pmfh = new PariModificationFormHome(this.form, pari);
                pmfh.getForm().show();
            });
            this.form.add(titre);
            this.form.add(datematch);
            if (pari.getResultat().equals(Pari.ResultatPari.un)) {
                Label resultat = new Label("Resultat :  " + pari.getM().getE1().getNom());
                resultat.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                this.form.add(resultat);
            } else if (pari.getResultat().equals(Pari.ResultatPari.deux)) {
                Label resultat = new Label("Resultat :    " + pari.getM().getE2().getNom());
                resultat.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                this.form.add(resultat);
            } else if (pari.getResultat().equals(Pari.ResultatPari.x)) {
                Label resultat = new Label("Resultat :   Match Nul");
                resultat.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                this.form.add(resultat);
            }
            this.form.add(misepari);
            this.form.add(cotepari);
            this.form.add(gainpari);

        }

        if (!PanierPari.getLp().isEmpty()) {
            Container container_simple_multiple = new Container(BoxLayout.x());
            container_simple_multiple.add(button_simple);
            container_simple_multiple.add(button_multiple);
            this.form.add(container_simple_multiple);
        }

        button_multiple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                float m = 0;
                float c = 1;
                float g = 0;
                for (Pari pari : PanierPari.getLp()) {

                    m += pari.getMise();
                    c *= pari.getCote();
                    g = c * m;
                    mise_totale.setText("Mise totale:  " + m);
                    cote_totale.setText("Cote totale:  " + c);
                    gain_totaux.setText("Gain totaux:  " + g);
                    mise_totale.setVisible(true);
                    cote_totale.setVisible(true);
                    gain_totaux.setVisible(true);

                }

                button_simple.setSelected(false);
                ValiderFiche.setVisible(true);
                form.revalidate();
            }

        });

        button_simple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                button_multiple.setSelected(false);
                mise_totale.setVisible(false);
                cote_totale.setVisible(false);
                gain_totaux.setVisible(false);
                button_multiple.setSelected(false);
                ValiderFiche.setVisible(true);
                form.revalidate();
            }

        });

        ValiderFiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (button_simple.isSelected() == true) {

                    float mise_test = 0;
                    for (Pari pari : PanierPari.getLp()) {
                        mise_test += pari.getMise();
                    }
                    if (mise_test > Session.getUser().getJeton()) {
                        Dialog.show("", "Vous ne possédez pas assez de jetons!", "Ok", "Cancer");
                    } else {
                        ServiceFichePari SFP = new ServiceFichePari();
                        ServicePari SP = new ServicePari();

                        for (Pari pari : PanierPari.getLp()) {
                            FichePari fichepari = new FichePari();

                            fichepari.setU(Session.getUser());

                            fichepari.setCote_total(pari.getCote());
                            fichepari.setGain(pari.getGain());
                            fichepari.setMisetotal(pari.getMise());
                            fichepari.setType(0);

                            fichepari = SFP.AjoutFichePari(fichepari.getCote_total(), fichepari.getU(), fichepari.getMisetotal(), fichepari.getGain(), fichepari.getType());
                            pari.setFp(fichepari);
                            SP.AjoutPari(pari.getM(), pari.getFp(), pari.getMise(), pari.getGain(), pari.getCote(), pari.getResultat().toString());
                            ServiceUser.UpdateJetonUser(Session.getUser().getId(), Session.getUser().getJeton() - pari.getMise());
                        }
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setMessage("Votre fiche de pari simple a été ajoutée !");
                        status.setExpires(3000);
                        status.show();
                        PanierPari.getLp().clear();
                        PariIndexHome pih = new PariIndexHome();
                        pih.getForm().show();
                    }

                } else if (button_multiple.isSelected() == true) {
                    List<Integer> test_list = new ArrayList<>();
                    boolean test = false;
                    float mise = 0;
                    float cote = 1;
                    float gain = 0;
                    for (Pari pari : PanierPari.getLp()) {

                        mise += pari.getMise();
                        cote *= pari.getCote();
                        if (test_list.contains(pari.getM().getId())) {

                            test = true;

                            break;
                        }
                        test_list.add(pari.getM().getId());
                        test = false;

                    }
                    if (mise > Session.getUser().getJeton()) {
                        Dialog.show("", "Vous ne possédez pas assez de jetons!", "Ok", "Cancer");
                    } else if (test == true) {
                        Dialog.show("", "Vous ne pouvez pas parier plus d'une fois sur le même match!", "OK", "Cancel");

                    } else {
                        gain = mise * cote;
                        FichePari fichepari = new FichePari();
                        ServiceFichePari SFP = new ServiceFichePari();
                        ServicePari SP = new ServicePari();
                        fichepari.setU(Session.getUser());

                        fichepari.setCote_total(cote);
                        fichepari.setGain(gain);
                        fichepari.setMisetotal(mise);
                        fichepari.setType(1);
                        fichepari.setParis(PanierPari.getLp());
                        fichepari = SFP.AjoutFichePari(fichepari.getCote_total(), fichepari.getU(), fichepari.getMisetotal(), fichepari.getGain(), fichepari.getType());
                        for (Pari pari : PanierPari.getLp()) {
                            pari.setFp(fichepari);
                            SP.AjoutPari(pari.getM(), pari.getFp(), pari.getMise(), pari.getGain(), pari.getCote(), pari.getResultat().toString());
                        }
                        ServiceUser.UpdateJetonUser(Session.getUser().getId(), Session.getUser().getJeton() - mise);
                        PanierPari.getLp().clear();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setMessage("Votre fiche de pari multiple a été ajouté !");
                        status.setExpires(3000);
                        status.show();
                        PariIndexHome pih = new PariIndexHome();
                        pih.getForm().show();

                    }
                }
            }
        });

        form.add(container_multiple);
        form.add(gain_totaux);
        form.add(ValiderFiche);
    }

}
