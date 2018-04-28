/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.mycompany.Entite.User;


/**
 *
 * @author Ghassen
 */
public class Session {

    private static User user = null;

    public static void setUser(User u) {
        Session.user = u;
    }

    public static User getUser() {
        return Session.user;
    }
}
