/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entite.Groupe;
import com.mycompany.Entite.Joueur;
import com.mycompany.Entite.Joueur_P;
import com.mycompany.Entite.Match;
import com.mycompany.Entite.Score;
import com.mycompany.Entite.Stade;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;



/**
 *
 * @author hseli
 */
public class ServiceMatch {
    
    public ArrayList<Match> getListMatchs() {
        ArrayList<Match> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/matchs/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Match match = new Match();
                        Map<String,Object> m = (Map<String,Object>)obj.get("match");
                        float id = Float.parseFloat(m.get("id").toString());
                        float a = Float.parseFloat(obj.get("a").toString());
                        float b = Float.parseFloat(obj.get("b").toString());
                        match.setId((int) id);
                        
                       // float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                        Map<String,Object> eq1 = (Map<String,Object>)m.get("idEquipe1");
                        Equipe e = new Equipe();
                        e.setNom(eq1.get("nom").toString());
                        e.setDrapeau(eq1.get("drapeau").toString());
                        Map<String,Object> eq2 = (Map<String,Object>)m.get("idEquipe2");
                        Equipe e2 = new Equipe();
                        e2.setNom(eq2.get("nom").toString());
                        e2.setDrapeau(eq2.get("drapeau").toString());
                        
                        Score S=new Score();
                        S.setA((int)a);
                        S.setB((int)b);
                       // match.setId((int) id);
                        match.setEtat(Match.EtatMatch.valueOf(m.get("etat").toString()));
                        match.setE1(e);
                        match.setE2(e2);
                        match.setScore1(S);
                       // task.setNom(obj.get("name").toString());
                       // match.setE1(obj.get("id_equipe_1").toString());
                        listTasks.add(match);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Evenement> getListEvents(int id_M){
        ArrayList<Evenement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/matchs/allEvent/"+id_M);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
               
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Evenement event = new Evenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float but = Float.parseFloat(obj.get("but").toString());
                        float temps = Float.parseFloat(obj.get("temps").toString());
                        Map<String,Object> JP = (Map<String,Object>)obj.get("idJoueurParticipant");
                        Joueur_P jp=new Joueur_P();
                        Map<String,Object> J = (Map<String,Object>)JP.get("idJoueur");
                        Joueur Jo=new Joueur();
                        Jo.setNom(J.get("nom").toString());
                        Jo.setImage(J.get("image").toString());
                        Jo.setPrenom(J.get("prenom").toString());
                        
                        
                        jp.setJ(Jo);
                        event.setId((int)id);
                        event.setJoueur(jp);
                        event.setBut((int)but);
                        event.setCarton(Evenement.TypeCarton.valueOf(obj.get("carton").toString()));
                        event.setTemps((int)temps);
                     
                        listTasks.add(event);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Match> getListMatchParGroupe(int id_G){
        ArrayList<Match> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/matchs/allGroupe/"+id_G);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
               
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Match match = new Match();
                        Map<String,Object> m = (Map<String,Object>)obj.get("match");
                        float id = Float.parseFloat(m.get("id").toString());
                        float a = Float.parseFloat(obj.get("a").toString());
                        float b = Float.parseFloat(obj.get("b").toString());
                        match.setId((int) id);
                        
                       // float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                        Map<String,Object> eq1 = (Map<String,Object>)m.get("idEquipe1");
                        Equipe e = new Equipe();
                        e.setNom(eq1.get("nom").toString());
                        e.setDrapeau(eq1.get("drapeau").toString());
                        Map<String,Object> eq2 = (Map<String,Object>)m.get("idEquipe2");
                        Equipe e2 = new Equipe();
                        e2.setNom(eq2.get("nom").toString());
                        e2.setDrapeau(eq2.get("drapeau").toString());
                        
                        Score S=new Score();
                        S.setA((int)a);
                        S.setB((int)b);
                       // match.setId((int) id);
                        match.setEtat(Match.EtatMatch.valueOf(m.get("etat").toString()));
                        match.setE1(e);
                        match.setE2(e2);
                        match.setScore1(S);
                        Map<String,Object> d = (Map<String,Object>)m.get("date");
                       
                        float tim = Float.parseFloat(d.get("timestamp").toString());
                        long batch_date = (long) tim;
                        Date dt = new Date (batch_date * 1000); 
                        match.setDate(dt);
                        Map<String,Object> time = (Map<String,Object>)m.get("time");
                        float tim1 = Float.parseFloat(time.get("timestamp").toString());
                        long batch_date1 = (long) tim1;
                        Date dt1 = new Date (batch_date1 * 1000);
                        match.setHeure(dt1);
                        listTasks.add(match);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Stade> getListStades(){
        ArrayList<Stade> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/matchs/allStades");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Stade stade=new Stade();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        float capacite= Float.parseFloat(obj.get("capacite").toString());
                        stade.setId((int) id);
                        stade.setCapacite((int) capacite);
                        stade.setAdresse(obj.get("adresse").toString());
                        stade.setImage(obj.get("image").toString());
                        stade.setNom(obj.get("nom").toString());
                        
                        listTasks.add(stade);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Match> getListMatchStades(int id_S){
        ArrayList<Match> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/matchs/allStadesM/"+id_S);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Match match = new Match();
   
                        float id = Float.parseFloat(obj.get("id").toString());
                        match.setId((int) id);
        
                                      
                        Map<String,Object> eq1 = (Map<String,Object>)obj.get("idEquipe1");
                        Equipe e = new Equipe();
                        e.setNom(eq1.get("nom").toString());
                        e.setDrapeau(eq1.get("drapeau").toString());
                        Map<String,Object> eq2 = (Map<String,Object>)obj.get("idEquipe2");
                        Equipe e2 = new Equipe();
                        e2.setNom(eq2.get("nom").toString());
                        e2.setDrapeau(eq2.get("drapeau").toString());
 
                        match.setEtat(Match.EtatMatch.valueOf(obj.get("etat").toString()));
                        match.setE1(e);
                        match.setE2(e2);
                        Map<String,Object> d = (Map<String,Object>)obj.get("date");
                       
                        float tim = Float.parseFloat(d.get("timestamp").toString());
                        long batch_date = (long) tim;
                        Date dt = new Date (batch_date * 1000); 
                        match.setDate(dt);
                        Map<String,Object> time = (Map<String,Object>)obj.get("time");
                        float tim1 = Float.parseFloat(time.get("timestamp").toString());
                        long batch_date1 = (long) tim1;
                        Date dt1 = new Date (batch_date1 * 1000);
                        match.setHeure(dt1);
  
                        listTasks.add(match);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
        
    }
    public ArrayList<Match> getPHFListMatch(String url){
        ArrayList<Match> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/"+url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    if(!tasks.isEmpty()){
                        
                    
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    
                         for (Map<String, Object> obj : list) {
                        Match match = new Match();
                        Map<String,Object> m = (Map<String,Object>)obj.get("match");
                        float id = Float.parseFloat(m.get("id").toString());
                        float a = Float.parseFloat(obj.get("a").toString());
                        float b = Float.parseFloat(obj.get("b").toString());
                        match.setId((int) id);
                        
                       // float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                        Map<String,Object> eq1 = (Map<String,Object>)m.get("idEquipe1");
                        Equipe e = new Equipe();
                        e.setNom(eq1.get("nom").toString());
                        e.setDrapeau(eq1.get("drapeau").toString());
                        Map<String,Object> eq2 = (Map<String,Object>)m.get("idEquipe2");
                        Equipe e2 = new Equipe();
                        e2.setNom(eq2.get("nom").toString());
                        e2.setDrapeau(eq2.get("drapeau").toString());
                        
                        Score S=new Score();
                        S.setA((int)a);
                        S.setB((int)b);
                       // match.setId((int) id);
                        match.setEtat(Match.EtatMatch.valueOf(m.get("etat").toString()));
                        match.setE1(e);
                        match.setE2(e2);
                        match.setScore1(S);
                        Map<String,Object> d = (Map<String,Object>)m.get("date");
                       
                        float tim = Float.parseFloat(d.get("timestamp").toString());
                        long batch_date = (long) tim;
                        Date dt = new Date (batch_date * 1000); 
                        match.setDate(dt);
                        Map<String,Object> time = (Map<String,Object>)m.get("time");
                        float tim1 = Float.parseFloat(time.get("timestamp").toString());
                        long batch_date1 = (long) tim1;
                        Date dt1 = new Date (batch_date1 * 1000);
                        match.setHeure(dt1);
                        listTasks.add(match);
                        
                    }
                    
                    }else{
                        
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Equipe> getGagnant(){
        ArrayList<Equipe> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/equipeG");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    if(!tasks.isEmpty()){
                        
           
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                       
                         for (Map<String, Object> obj : list) {
                        Equipe equipe = new Equipe();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        equipe.setId((int) id);
                        equipe.setDrapeau(obj.get("drapeau").toString());
                        equipe.setNom(obj.get("nom").toString());
                        
                        listTasks.add(equipe);
                        
                    }
                    
                    }else{
                        
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public ArrayList<Match> getFirstM(){
         ArrayList<Match> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8080/Selim/RS2018/web/app_dev.php/first");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    if(!tasks.isEmpty()){
                        
           
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                       
                         for (Map<String, Object> obj : list) {
                        Match match = new Match();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        match.setId((int) id);
                        Map<String,Object> d = (Map<String,Object>)obj.get("date");
                       
                        float tim = Float.parseFloat(d.get("timestamp").toString());
                        long batch_date = (long) tim;
                        Date dt = new Date (batch_date * 1000); 
                        match.setDate(dt);
                        Map<String,Object> time = (Map<String,Object>)obj.get("time");
                        float tim1 = Float.parseFloat(time.get("timestamp").toString());
                        long batch_date1 = (long) tim1;
                        Date dt1 = new Date (batch_date1 * 1000);
                        match.setHeure(dt1);
                        
                        listTasks.add(match);
                        
                    }
                    
                    }else{
                        
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
}
