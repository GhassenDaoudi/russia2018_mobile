/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.mycompany.Entite.Publication;
import com.mycompany.Entite.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Ghassen
 */
public class TwitterAPI {
    public static Twitter getTwitterinstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("tJ36vad6eICClaqxiha6rCdi0")
                .setOAuthConsumerSecret("3yZopikINkAEP3WcVBXPcMWG8mh3K4r7n6qtWzuw1jeJuSBrB2")
                .setOAuthAccessToken("942261509673111552-ehdzseK78MLzl2HGL3CIi6laXXjI6iu")
                .setOAuthAccessTokenSecret("Mwxc4Nbb7zgR0Zj7AR2InGBBXnJs9uXtyb982IaEV2PHw");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
    }
    public static List<Publication> searchtweets(String hashtag,int nbr)  {
        List<Publication> tmp = new ArrayList<>();
        Twitter twitter = getTwitterinstance();
        Query query = new Query(hashtag);
        query.setLang("fr");
        query.setCount(nbr);
        QueryResult result=null;
        try {
            result = twitter.search(query);
        } catch (TwitterException ex) {
            return tmp;
        }
        List<Status> statuses = result.getTweets();
        for (Status statuse : statuses) {
            Publication p = new Publication();
            p.setTitre(hashtag);
            p.setType(Publication.Type.article);
            User u = new User();
            u.setUsername(statuse.getUser().getName());
            p.setUser(u);
            p.setDescription("Twitter : "+statuse.getText());
            p.setDate_creation(new Date( statuse.getCreatedAt().getTime()));
            tmp.add(p);
        }
        return tmp;
        
    }
}
