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
        //actualite.setScrollableY(true);
        Container twitter = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //twitter.setScrollableY(true);
        Container RSS = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //RSS.setScrollableY(true);
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
                custom.setTextUIID("Tab");
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
        this.form.add( tb);
        this.form.getContentPane().addPullToRefresh(new Runnable() {
            @Override
            public void run() {
                updateRSS(RSS);
                updateArticle(actualite);
                updateTwitter(twitter);
                form.revalidate();
            }
        });
        /*for (Publication publication : article) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container ctitre = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            Label titre = new Label(publication.getTitre());
            titre.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form f2 = new Form();
                    f2 = new Form(BoxLayout.y());
                    Toolbar tb = f2.getToolbar();
                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                        form.showBack();
                    });
                    Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label sp = new Label(publication.getTitre());
                    c.add(sp);
                    Label user = new Label("@" + publication.getUser().getUsername() + ":");
                    user.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                    c.add(user);
                    SpanLabel description = new SpanLabel(publication.getDescription());
                    description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                    c.add(description);
                    Label com = new Label("Commentaires:");
                    c.add(com);
                    List<Commentaire> comms = ServiceCommentaire.afficher(publication.getId());
                    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    for (Commentaire comm : comms) {
                        SpanLabel sl = new SpanLabel(comm.getDescription());
                        sl.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                        c1.add(sl);
                    }
                    f2.add(c1);
                    f2.add(c);
                    f2.show();
                }
            });
            ctitre.add(BorderLayout.CENTER, titre);
            c1.add(ctitre);
            Label user = new Label("@" + publication.getUser().getUsername() + ":");
            user.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
            c1.add(user);
            SpanLabel description = new SpanLabel(publication.getDescription());
            description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
            c1.add(description);
            this.form.add(c1);

        }*/
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
        if(Session.getUser()!=null){
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
            ajouter.addPointerPressedListener((e)->{
                if(!description.getText().equals("")&&!titre.getText().equals("")){
                    ServiceArticle.ajouter(Session.getUser(),titre.getText(),description.getText());
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
            titre.addPointerPressedListener((e)->{
                CommentaireFormHome cfh = new CommentaireFormHome(p ,this.form);
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
