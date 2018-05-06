/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.background.BackgroundFetch;
import com.codename1.io.NetworkManager;
import com.codename1.io.services.RSSService;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghassen
 */
public class RssFormHome implements BackgroundFetch{
    private Form current;
    private Resources theme;
    List<Map> records;

    @Override
    public void performBackgroundFetch(long deadline, Callback<Boolean> onComplete) {
        RSSService rss = new RSSService("http://www.fifa.com/worldcup/news/rss.xml");
        NetworkManager.getInstance().addToQueueAndWait(rss);
        records = rss.getResults();
        System.out.println(records);
        onComplete.onSucess(Boolean.TRUE);
        
    }
    
}
