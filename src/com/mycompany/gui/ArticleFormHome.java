/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.NetworkManager;
import com.codename1.io.services.RSSService;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Publication;
import com.mycompany.Service.ServiceArticle;
import com.mycompany.Utilitaire.Components;
import com.mycompany.Utilitaire.Session;
import com.mycompany.Utilitaire.TwitterAPI;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghassen
 */
public class ArticleFormHome {

    private Form form;
    Toolbar tb;
    Container topBar;
    List<Map> records;

    public ArticleFormHome() {
        this.form = new Form("Russia 2018", BoxLayout.y());

        Container actualite = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container twitter = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container RSS = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        updateRSS(RSS);
        updateArticle(actualite);
        updateTwitter(twitter);

        Components.showHamburger(this.form);
        Tabs tb = new Tabs() {
            @Override
            protected Component createTab(String title, Image icon) {
                SpanButton custom = new SpanButton(title);
                custom.setIcon(icon);
                custom.setUIID("Container");
                custom.setTextUIID("Tab2");
                custom.setIconPosition(BorderLayout.NORTH);
                custom.setIconUIID("Tab");
                return custom;
            }

            @Override
            protected void setTabSelectedIcon(Component tab, Image icon) {

            }

            @Override
            protected void selectTab(Component tab) {

            }

            @Override
            protected void bindTabActionListener(Component tab, ActionListener l) {
                ((SpanButton) tab).addActionListener(l);
            }
        };
        tb.setTabUIID(null);
        tb.addTab("Actualite", actualite);

        tb.addTab("Twitter", twitter);
        tb.addTab("RSS", RSS);
        tb.getTabsContainer().setScrollableX(false);
        this.form.add(tb);
        this.form.getContentPane().addPullToRefresh(new Runnable() {
            @Override
            public void run() {
                updateRSS(RSS);
                updateArticle(actualite);
                updateTwitter(twitter);
                form.revalidate();
            }
        });
        this.form.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                for (Component cmp : actualite) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                actualite.animateLayout(150);
            } else {
                text = text.toLowerCase();
                int i = 0;
                if (Session.getUser() != null) {
                    i = 1;
                }
                for (int j = i; j < actualite.getComponentCount(); j++) {
                    Container c = (Container) actualite.getComponentAt(j);
                    Container ctitre = (Container) c.getComponentAt(0);
                    Label titre = (Label) ctitre.getComponentAt(0);
                    Label user = (Label) c.getComponentAt(1);
                    SpanLabel desc = (SpanLabel) c.getComponentAt(2);
                    String line1 = titre.getText();
                    String line2 = user.getText();
                    String line3 = desc.getText();
                    boolean show = line1.toLowerCase().contains(text) || line2.toLowerCase().contains(text) || line3.toLowerCase().contains(text);
                    c.setHidden(!show);
                    c.setVisible(show);
                }
                actualite.animateLayout(150);
            }
        }, 4);
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    private void updateRSS(Container rsscontainer) {
        rsscontainer.removeAll();
        
        RSSService rss = new RSSService("https://talksport.com/rss/sports-news/football/feed");
        NetworkManager.getInstance().addToQueueAndWait(rss);
        
        records = rss.getResults();
        for (Map m : records) {
            rsscontainer.addComponent(new SpanLabel((String) m.get("title")));
            rsscontainer.addComponent(new SpanLabel((String) m.get("pubDate")));
        }
    }

    private void updateArticle(Container articleContainer) {
        articleContainer.removeAll();
        if (Session.getUser() != null) {
            Container c100 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            TextField titre = new TextField();
            titre.setHint("titre");
            TextField description = new TextField();
            description.setHint("contenue");
            Button ajouter = new Button("Ajouter");
            c100.add(titre);
            c100.add(description);
            c100.add(ajouter);
            articleContainer.add(c100);
            ajouter.addPointerPressedListener((e) -> {
                if (!description.getText().equals("") && !titre.getText().equals("")) {
                    ServiceArticle.ajouter(Session.getUser(), titre.getText(), description.getText());
                    ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setMessage("Article Ajouté");
                    status.setExpires(3000);
                    status.show();
                    updateArticle(articleContainer);
                }
            });
        }
        for (Publication p : ServiceArticle.afficher()) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container ctitre = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            Label titre = new Label(p.getTitre());
            titre.addPointerPressedListener((e) -> {
                CommentaireFormHome cfh = new CommentaireFormHome(p, this.form);
                cfh.getForm().show();
            });
            ctitre.add(BorderLayout.CENTER, titre);
            c1.add(ctitre);
            Label user = new Label("@" + p.getUser().getUsername() + ":");
            user.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            c1.add(user);
            SpanLabel description = new SpanLabel(p.getDescription());
            description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
            c1.add(description);
            articleContainer.add(c1);
        }
    }

    private void updateTwitter(Container twitterContainer) {
        twitterContainer.removeAll();
        for (Publication publication : TwitterAPI.searchtweets("Russia2018", 5)) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container ctitre = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            SpanLabel titre = new SpanLabel("#russia2018");
            ctitre.add(BorderLayout.CENTER, titre);
            c1.add(ctitre);
            Label user = new Label("@" + publication.getUser().getUsername() + ":");
            user.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            c1.add(user);
            SpanLabel description = new SpanLabel(publication.getDescription());
            description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
            c1.add(description);
            twitterContainer.add(c1);
        }
    }

}
