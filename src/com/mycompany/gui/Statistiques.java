/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.mycompany.utils.Stats;

/**
 *
 * @author quickstrikes96
 */
public class Statistiques {
    
        Form f;

    public Statistiques() {
        Stats s = new Stats();
        f = s.createPieChartForm();
        Toolbar tb = f.getToolbar();
                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                        f.showBack();
                    });
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
