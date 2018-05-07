/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Commentaire;
import com.mycompany.Entite.Publication;
import com.mycompany.Service.ServiceCommentaire;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.Calendar;

/**
 *
 * @author Ghassen
 */
public class CommentaireFormHome {

    private Form form;

    public CommentaireFormHome(Publication p, Form prev) {
        this.form = new Form(p.getTitre(), BoxLayout.y());
        Components.showBack(this.form, prev);
        //Container ajoutCommentaire = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Container commentaires = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        updateCommentaire(commentaires, p);
        //this.form.add(ajoutCommentaire);
        this.form.add(commentaires);

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    private void updateCommentaire(Container commentaires, Publication p) {
        commentaires.removeAll();
        if (Session.getUser() != null) {
            TextField input = new TextField();
            input.setHint("Commentaire");
            commentaires.add(input);
            Button ajouter = new Button("Ajouter un Commentaire");
            commentaires.add(ajouter);
            ajouter.addPointerPressedListener((e) -> {
                if (!input.getText().equals("")) {
                    ServiceCommentaire.ajouter(Session.getUser(), p, input);
                    ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setMessage("Commentaire Ajouté");
                    status.setExpires(3000);
                    status.show();
                    updateCommentaire(commentaires, p);
                } else {
                    Dialog.show("Erreur", "donnée invalide", "Ok", "Cancel");
                }
            });
        }

        for (Commentaire c : ServiceCommentaire.afficher(p.getId())) {
            Label user = new Label("@" + c.getUser().getUsername() + ":");
            SpanLabel sl = new SpanLabel(c.getDescription());
            sl.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
            commentaires.add(user);
            System.out.println(c.getDate_creation());
            Calendar cal = Calendar.getInstance();
            cal.setTime(c.getDate_creation());
            Label l = new Label(((int) cal.get(Calendar.DAY_OF_MONTH) + 1) + "/" + ((int) cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));
            commentaires.add(l);
            //SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            //sfd.format(c.getDate_creation());
            //Label date = new Label(sfd.toString());
            ///commentaires.add(sfd.format("dd-MM-yyyy"));
            commentaires.add(sl);
        }
    }

}
