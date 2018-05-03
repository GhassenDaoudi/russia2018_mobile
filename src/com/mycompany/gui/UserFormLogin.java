/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceUser;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import java.util.List;

/**
 *
 * @author Ghassen
 */
public class UserFormLogin {

    private Form form;
    private List<User> users;

    public UserFormLogin() {
        this.form = new Form("Welcome", BoxLayout.y());
        Components.showHamburger(this.form);
        TextField username = new TextField();
        username.setHint("username/email");
        TextField password = new TextField();
        password.setConstraint(TextField.PASSWORD);
        password.setHint("Password");
        CheckBox rememberMe = new CheckBox("Remember Me");
        rememberMe.setSelected(true);
        Button login = new Button("Login");
        Label error = new Label("");
        error.setVisible(false);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!username.getText().equals("") && !password.getText().equals("")) {

                    ServiceUser.login(username.getText(), password.getText());
                    if (Session.getUser() != null) {
                        if (rememberMe.isSelected()) {
                            Storage.getInstance().writeObject("idUser", String.valueOf(Session.getUser().getId()));
                        } else {
                            Storage.getInstance().writeObject("idUser", "-1");   
                        }
                        ArticleFormHome afh = new ArticleFormHome();
                        afh.getForm().show();
                    } else {
                        UserFormLogin a = new UserFormLogin(new Label("Error"));
                        a.getForm().show();
                    }
                }
            }
        });
        this.form.add(username);
        this.form.add(password);
        this.form.add(rememberMe);
        this.form.add(login);
        this.form.add(error);

    }

    public UserFormLogin(Label l) {
        this.form = new Form("Welcome", BoxLayout.y());
        TextField username = new TextField();
        username.setHint("username/email");
        TextField password = new TextField();
        password.setConstraint(TextField.PASSWORD);
        password.setHint("Password");
        Button login = new Button("Login");
        Label error = new Label("");
        error.setVisible(false);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceUser.login(username.getText(), password.getText());
                if (Session.getUser() != null) {
                    GalerieFormHome gfh = new GalerieFormHome();
                    gfh.getForm().show();
                } else {
                    UserFormLogin a = new UserFormLogin(new Label("Error"));
                    a.getForm().show();

                }
            }
        });

        this.form.add(username);
        this.form.add(password);
        this.form.add(login);
        this.form.add(l);
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
