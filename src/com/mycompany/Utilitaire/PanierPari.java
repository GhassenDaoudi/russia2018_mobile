/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.mycompany.Entite.Pari;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skanderbejaoui
 */
public class PanierPari {
    private static ArrayList<Pari> lp = new ArrayList<>();

    public static ArrayList<Pari> getLp() {
        return lp;
    }

    public static void setLp(ArrayList<Pari> lp) {
        PanierPari.lp = lp;
    }
    
    
}
